# common-auth-core

纯Java的JWT验签模块，**不依赖Spring Security**，适用于Gateway、WebSocket等需要JWT验证的服务。

## 特性

- ✅ 纯Java实现，无Spring Security依赖
- ✅ 基于RSA公钥验签（不负责签发token）
- ✅ 支持从JWT提取用户信息和权限
- ✅ 轻量级，易于集成

## 模块结构

```
common-auth-core/
├── model/
│   ├── AuthenticatedUser.java    # 认证用户模型（不依赖UserDetails）
│   └── UserAggregate.java         # 用户基本信息
├── service/
│   └── JwtVerifier.java           # JWT验证服务（静态方法）
└── utils/
    ├── JwtUtil.java               # JWT工具类（底层验签）
    └── RSAUtil.java               # RSA公钥加载工具
```

## 依赖

在你的项目 `pom.xml` 中添加：

```xml
<dependency>
    <groupId>com.pms</groupId>
    <artifactId>common-auth-core</artifactId>
    <version>${project.version}</version>
</dependency>
```

## 使用方式

### 1. 启动时初始化公钥

服务启动时，需要主动获取公钥并初始化：

```java
import service.com.pms.auth.JwtVerifier;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthConfig {
	
	@Bean
	public ApplicationRunner initPublicKey() {
		return args -> {
			// 方式1：从auth-service通过HTTP获取公钥
			String publicKeyBase64 = httpClient.get("http://auth-service:8093/rpc/auth/publicKey");
			
			// 方式2：从配置中心获取
			// String publicKeyBase64 = configService.getPublicKey();
			
			// 保存到本地文件
			Path keyPath = Paths.get("data/keys/publicKey.txt");
			Files.createDirectories(keyPath.getParent());
			Files.writeString(keyPath, publicKeyBase64);
			
			// 初始化JwtVerifier
			JwtVerifier.initKey();
			log.info("JWT公钥初始化完成");
		};
	}

}
```

### 2. 验证JWT Token

```java
import service.com.pms.auth.JwtVerifier;
import model.com.pms.auth.AuthenticatedUser;

// 从请求头获取token
String token = request.getHeader("Authorization").substring(7); // 去掉 "Bearer "
		
		// 验证并获取用户信息
		AuthenticatedUser user = JwtVerifier.getAuthenticatedUser(token);
		
		// 获取用户信息
		Long userId = user.getUserId();
		String username = user.getUsername();
		String roleName = user.getRoleName();
		List<String> authorities = user.getAuthorities();

// 权限检查
if(user.
		
		hasRole("admin")){
		// 管理员操作
		}
		
		if(user.
		
		hasAuthority("user:write")){
		// 有写权限
		}
```

### 3. WebSocket场景示例

```java
@Component
public class MyWebSocketHandler extends TextWebSocketHandler {
    
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 从query参数获取token
        String token = extractToken(session);
        
        // 验证token
        String jti = JwtVerifier.validateToken(token);
        
        // 获取用户信息
        AuthenticatedUser user = JwtVerifier.getAuthenticatedUser(token);
        
        // 权限检查
        if (!user.hasRole("admin") && !user.hasRole("operator")) {
            session.close(CloseStatus.NOT_ACCEPTABLE.withReason("权限不足"));
            return;
        }
        
        // 保存session
        sessionManager.addSession(user.getUserId(), session);
    }
}
```

### 4. Gateway场景示例

```java
@Component
public class AuthFilter implements GlobalFilter {
    
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = extractToken(exchange);
        
        try {
            // 验证token
            JwtVerifier.validateToken(token);
            
            // 获取用户信息
            AuthenticatedUser user = JwtVerifier.getAuthenticatedUser(token);
            
            // 将用户信息传递给下游服务
            exchange.getRequest().mutate()
                .header("X-User-Id", user.getUserId().toString())
                .header("X-Username", user.getUsername())
                .build();
                
        } catch (AppException e) {
            // token验证失败
            return unauthorized(exchange);
        }
        
        return chain.filter(exchange);
    }
}
```

## API说明

### JwtVerifier

| 方法 | 说明 |
|-----|------|
| `initKey()` | 初始化公钥（启动时调用一次） |
| `getAuthenticatedUser(token)` | 获取认证用户对象 |
| `validateToken(token)` | 验证token，返回JTI（如果是refreshToken） |
| `getUserAggregate(token)` | 获取用户基本信息 |
| `getClaims(token)` | 获取JWT Claims |
| `getJTI(token)` | 获取JWT ID |

### AuthenticatedUser

| 方法 | 说明 |
|-----|------|
| `getUserId()` | 获取用户ID |
| `getUsername()` | 获取用户名 |
| `getRoleName()` | 获取角色名 |
| `getAuthorities()` | 获取权限列表 |
| `hasRole(role)` | 检查是否拥有指定角色 |
| `hasAuthority(permission)` | 检查是否拥有指定权限 |
| `isEnabled()` | 账号是否启用 |

## 异常处理

所有JWT验证异常都会抛出 `AppException`，包含以下错误码：

- `TOKEN_EXPIRED` - token已过期
- `TOKEN_TAMPERED` - token被篡改（签名验证失败）
- `TOKEN_PARSE_ERROR` - token解析错误
- `TOKEN_ISSUER_ERROR` - token签发者错误

## 注意事项

1. **公钥初始化**：必须在使用前调用 `JwtVerifier.initKey()`
2. **公钥文件路径**：默认为 `data/keys/publicKey.txt`，可在 `Constants.KEY_PATH` 中修改
3. **线程安全**：所有方法都是静态的，线程安全
4. **不负责签发**：此模块只负责验签，token签发由auth-service完成

## 迁移指南

### 从 LoginUser 迁移到 AuthenticatedUser

```java
// 旧代码（依赖Spring Security）
LoginUser loginUser = jwtService.getLoginUserFromToken(token);
Collection<? extends GrantedAuthority> authorities = loginUser.getAuthorities();

// 新代码（纯Java）
AuthenticatedUser user = JwtVerifier.getAuthenticatedUser(token);
List<String> authorities = user.getAuthorities();
```

### 从 JwtService 迁移到 JwtVerifier

```java
// 旧代码（Spring Bean）
@Autowired
private JwtService jwtService;
LoginUser user = jwtService.getLoginUserFromToken(token);

// 新代码（静态方法）
AuthenticatedUser user = JwtVerifier.getAuthenticatedUser(token);
```

