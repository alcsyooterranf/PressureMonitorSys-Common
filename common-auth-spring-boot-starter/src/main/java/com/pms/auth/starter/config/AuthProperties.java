package com.pms.auth.starter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 认证模块配置属性
 * 使用者需要在 application.yml 中配置：
 * <pre>
 * pms:
 *   auth:
 *     public-key-path: /path/to/public.key
 * </pre>
 *
 * @author alcsyooterranf
 * @version 1.0
 * @since 2025/11/28
 */
@Data
@ConfigurationProperties(prefix = "pms.auth")
public class AuthProperties {

    /**
     * RSA公钥文件路径
     * 用于JWT验签
     */
    private String publicKeyPath = "data/keys/publicKey.txt";

    /**
     * Auth认证服务地址
     * 用于Feign RPC调用获取公钥等接口
     */
    private String serviceUrl = "http://localhost:8093";

}

