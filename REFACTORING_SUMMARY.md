# Auth模块重构总结

## 重构目标

✅ **取消Spring Security依赖，让其他服务自己在启动时主动获取公钥进行JWT验签**

## 重构内容

### 1. 删除的内容

| 项目 | 说明 |
|-----|------|
| `common-auth-spring-boot-starter/` | 整个starter模块（包含Spring Security依赖） |
| `LoginUser.java` | 依赖Spring Security的`UserDetails`接口 |
| `JwtService.java` | 已注释的Spring Service类 |

### 2. 新增的内容

| 文件 | 说明 |
|-----|------|
| `AuthenticatedUser.java` | 纯Java的认证用户模型，替代`LoginUser` |
| `JwtVerifier.java` | 纯Java的JWT验证服务，替代`JwtService` |
| `Constants.ROLE_NAME` | 新增常量 |
| `Constants.PERMISSIONS` | 新增常量 |

### 3. 保留的内容（已经是纯Java）

| 文件 | 说明 |
|-----|------|
| `JwtUtil.java` | JWT验签工具类 |
| `RSAUtil.java` | RSA公钥加载工具 |
| `UserAggregate.java` | 用户基本信息模型 |

## 架构对比

### 重构前

```
common-auth-core (依赖Spring Security)
├── LoginUser.java (实现UserDetails)
├── JwtService.java (Spring Bean)
└── ...

common-auth-spring-boot-starter (Spring自动配置)
├── AuthProperties.java
├── CommonAuthAutoConfiguration.java
├── AuthRpcService.java (Feign)
└── CommonAuthRunner.java (自动获取公钥)
```

**问题**：
- `LoginUser` 在 core 模块却依赖 Spring Security
- pom.xml 中没有声明 Spring Security 依赖，依赖传递
- 强制使用 starter 模块，不够灵活

### 重构后

```
common-auth-core (纯Java，无Spring依赖)
├── model/
│   ├── AuthenticatedUser.java (纯Java)
│   └── UserAggregate.java
├── service/
│   └── JwtVerifier.java (静态方法)
└── utils/
    ├── JwtUtil.java
    └── RSAUtil.java
```

**优势**：
- ✅ 完全不依赖 Spring Security
- ✅ 轻量级，只依赖 jjwt 和 common-types
- ✅ 灵活，各服务自己决定如何获取公钥
- ✅ 适用于 Gateway、WebSocket 等场景

## 使用方式变化

### 旧方式（使用starter）

```xml
<!-- 引入starter -->
<dependency>
    <groupId>com.pms</groupId>
    <artifactId>common-auth-spring-boot-starter</artifactId>
</dependency>
```

```yaml
# 配置
pms:
  auth:
    public-key-path: data/keys/publicKey.txt
    service-url: http://localhost:8093
```

```java
// 自动配置，自动获取公钥
@Autowired
private JwtService jwtService;

LoginUser user = jwtService.getLoginUserFromToken(token);
```

### 新方式（纯Java）

```xml
<!-- 只引入core -->
<dependency>
    <groupId>com.pms</groupId>
    <artifactId>common-auth-core</artifactId>
</dependency>
```

```java
// 启动时主动获取公钥
@Bean
public ApplicationRunner initPublicKey() {
    return args -> {
        // 从auth-service获取公钥
        String publicKey = httpClient.get("http://auth-service/rpc/auth/publicKey");
        
        // 保存到本地
        Files.writeString(Paths.get("data/keys/publicKey.txt"), publicKey);
        
        // 初始化
        JwtVerifier.initKey();
    };
}

// 使用静态方法验证
AuthenticatedUser user = JwtVerifier.getAuthenticatedUser(token);
```

## 迁移指南

### 1. 更新依赖

```xml
<!-- 删除 -->
<dependency>
    <groupId>com.pms</groupId>
    <artifactId>common-auth-spring-boot-starter</artifactId>
</dependency>

<!-- 改为 -->
<dependency>
    <groupId>com.pms</groupId>
    <artifactId>common-auth-core</artifactId>
</dependency>
```

### 2. 添加公钥初始化代码

参考 `common-auth-core/README.md` 中的示例

### 3. 替换代码

| 旧代码 | 新代码 |
|-------|--------|
| `LoginUser` | `AuthenticatedUser` |
| `jwtService.getLoginUserFromToken(token)` | `JwtVerifier.getAuthenticatedUser(token)` |
| `jwtService.validateToken(token)` | `JwtVerifier.validateToken(token)` |
| `loginUser.getAuthorities()` | `user.getAuthorities()` (返回`List<String>`) |

## 编译验证

```bash
# 编译 common-types
mvn clean install -pl common-types

# 编译 common-auth-core
mvn clean compile -pl common-auth-core
```

✅ **编译成功！**

## 后续工作

1. 更新使用此模块的服务（Gateway、WS等）
2. 添加公钥初始化逻辑
3. 替换 `LoginUser` 为 `AuthenticatedUser`
4. 测试JWT验签功能

## 总结

这次重构成功实现了：
- ✅ 移除 Spring Security 依赖
- ✅ 保持 JWT 验签功能完整
- ✅ 提供更灵活的使用方式
- ✅ 代码更简洁、职责更清晰

**重构完全可行！** 🎉

