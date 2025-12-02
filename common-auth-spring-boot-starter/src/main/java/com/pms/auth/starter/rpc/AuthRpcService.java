package com.pms.auth.starter.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Auth服务RPC接口
 * 用于Gateway和WS服务调用Auth服务的内部接口
 *
 * @author alcsyooterranf
 * @version 1.0
 * @since 2025/11/26
 */
@FeignClient(name = "auth-service", url = "${pms.auth.service-url:http://localhost:8093}", path = "/rpc/auth")
public interface AuthRpcService {

    /**
     * 获取RSA公钥
     * 用于Gateway和WS服务在启动时获取公钥，用于JWT验签
     *
     * @return Base64编码的RSA公钥字符串
     */
    @GetMapping("/publicKey")
    String getPublicKey();

}

