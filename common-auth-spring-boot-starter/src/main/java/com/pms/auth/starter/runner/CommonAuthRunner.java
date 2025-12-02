package com.pms.auth.starter.runner;

import com.pms.auth.starter.rpc.AuthRpcService;
import com.pms.auth.starter.config.AuthProperties;
import com.pms.auth.starter.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;

/**
 * 公钥加载器
 * 启动时从auth-service通过RPC获取公钥，然后初始化JwtService
 *
 * @author alcsyooterranf
 * @version 1.0
 * @since 2025/11/26
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CommonAuthRunner implements InitializingBean {

	private final AuthProperties authProperties;
	
	private final AuthRpcService authRpcService;

	private final JwtService jwtService;

	/**
	 * 从鉴权中心获取公钥并保存到本地
	 */
	private void loadPublicKeyFromAuthService() {
		String publicKeyPath = authProperties.getPublicKeyPath();
		// 检查本地是否已有公钥
		File publicKeyFile = new File(publicKeyPath);
		if (publicKeyFile.exists()) {
			log.info("本地公钥文件已存在: {}", publicKeyPath);
			return;
		}
		
		// 从auth-service通过RPC获取公钥
		log.info("从auth-service通过RPC获取公钥...");
		
		try {
			// 使用Feign RPC调用获取公钥
			String publicKeyBase64 = authRpcService.getPublicKey();
			
			// 保存公钥到文件
			File keyDir = publicKeyFile.getParentFile();
			if (keyDir != null && !keyDir.exists()) {
				keyDir.mkdirs();
			}
			
			try (FileWriter writer = new FileWriter(publicKeyFile)) {
				writer.write(publicKeyBase64);
			}
			
			log.info("公钥通过RPC获取成功并保存到: {}", publicKeyPath);
		} catch (Exception e) {
			log.error("从auth-service通过RPC获取公钥失败", e);
			throw new RuntimeException("无法获取公钥，服务启动失败", e);
		}
	}
	
	@Override
	public void afterPropertiesSet() {
		// 步骤1：从鉴权中心获取公钥文件
		loadPublicKeyFromAuthService();
		
		// 步骤2：初始化JwtService中的公钥
		jwtService.initKey();
	}
	
}

