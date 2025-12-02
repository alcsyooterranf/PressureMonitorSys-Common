package com.pms.auth.starter.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * common-auth-spring-boot-starter 模块自动配置类
 * Spring Boot 启动时会自动加载此配置，扫描并注册 common-auth-spring-boot-starter 包下的所有组件*
 * 使用者无需手动指定扫描包路径，只需引入此 starter 依赖即可
 * 配置示例 (application.yml):
 * <pre>
 * pms:
 *   auth:
 *     public-key-path: /path/to/public.key
 *     service-url: http://localhost:8093
 * </pre>
 *
 * @author alcsyooterranf
 */
@AutoConfiguration
@ComponentScan(basePackages = "com.pms.auth.starter")
@EnableFeignClients(basePackages = "com.pms.auth.starter.rpc")
@EnableConfigurationProperties(AuthProperties.class)
public class CommonAuthAutoConfiguration {
}

