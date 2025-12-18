package org.pms.types;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author alcsyooterranf
 * @program PressureMonitorSys-auth
 * @description 返回码
 * @create 2025/12/14
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum AuthCode {
	
	// 基本异常
	SUCCESS("0000", "成功"),
	
	// 登录
	LOGIN_SUCCESS("0100", "登录成功"),
	LOGIN_FAIL("0101", "登录失败"),
	
	// token
	CREATED_TOKEN_NOT_EXIST("1004", "生成的token不存在"),
	REFRESH_TOKEN_NOT_EXIST("1008", "refreshToken不存在"),
	
	// 权限
	AUTHENTICATED_USER_NOT_EXIST("2001", "认证用户不存在"),
	
	TOKEN_EXPIRED("ERR_BIZ_TOKEN_1001", "token已过期"),
	TOKEN_TAMPERED("ERR_BIZ_TOKEN_1002", "token被篡改"),
	TOKEN_PARSE_ERROR("ERR_BIZ_TOKEN_1003", "token解析错误"),
	TOKEN_ISSUER_ERROR("ERR_BIZ_TOKEN_1004", "token签发者错误"),
	
	;
	
	private String code;
	private String message;
}
