package org.pms.types;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author alcsyooterranf
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum CommonResponseCode {
	
	// 基本异常
	SUCCESS("0000", "成功"),
	UN_ERROR("0001", "未知失败"),
	ILLEGAL_PARAMETER("0002", "非法参数"),
	ILLEGAL_TYPE_CONVERSION("0003", "类型转换异常"),
	PROTOCOL_NOT_SUPPORTED("0004", "不支持的协议"),
	PARAMETER_IS_NULL("0005", "参数不能为空"),
	LOCAL_QUEUE_IS_FULL("0006", "本地队列已满"),
	
	// 登录
	LOGIN_SUCCESS("0100", "登录成功"),
	LOGIN_FAIL("0101", "登录失败"),
	INSUFFICIENT_PERMISSIONS("0102", "权限不足"),
	
	// token
	TOKEN_VALIDATE_ERROR("1001", "token验证失败"),
	AUTHORIZATION_HEADER_EMPTY("1002", "请求头部Authorization字段为空"),
	TOKEN_NOT_EXIST("1003", "token不存在"),
	CREATED_TOKEN_NOT_EXIST("1004", "生成的token不存在"),
	TOKEN_HEADER_NOT_EXIST("1005", "token头部不存在"),
	TOKEN_IS_EMPTY("1006", "token为空"),
	TOKEN_HEADER_ERROR("1007", "token头部错误"),
	REFRESH_TOKEN_NOT_EXIST("1008", "refreshToken不存在"),
	
	// 权限
	AUTHENTICATED_USER_NOT_EXIST("2001", "认证用户不存在"),
	AUTHENTICATION_FAILURE("2002", "认证失败"),
	USERNAME_OR_PASSWORD_ERROR("2003", "用户名或密码错误"),
	USER_LOGOUT_SUCCESS("2004", "用户登出成功"),
	AUTHORIZATION_FAILURE("2005", "授权失败"),
	
	// redis 数据库
	REDIS_SET_ERROR("5001", "redis存储操作失败"),
	
	// mysql 数据库
	SQL_INDEX_DUPLICATE("6001", "唯一索引冲突"),
	
	// 业务异常
	REQUEST_FORWARD_IO_EXCEPTION("7001", "请求转发IO异常"),
	REQUEST_BASE64_DECODE_ERROR("7002", "请求base64解码失败"),
	JSON_PARSE_ERROR("7003", "JSON解析失败"),
	
	TOKEN_EXPIRED("ERR_BIZ_TOKEN_1001", "token已过期"),
	TOKEN_TAMPERED("ERR_BIZ_TOKEN_1002", "token被篡改"),
	TOKEN_PARSE_ERROR("ERR_BIZ_TOKEN_1003", "token解析错误"),
	TOKEN_ISSUER_ERROR("ERR_BIZ_TOKEN_1004", "token签发者错误"),
	
	USER_ID_ERROR("ERR_BIZ_USER_1001", "用户ID不存在"),
	PASSWORD_IS_EMPTY("ERR_BIZ_USER_1002", "密码为空"),
	OLD_PASSWORD_ERROR("ERR_BIZ_USER_1003", "旧密码错误"),
	PASSWORD_IS_SAME("ERR_BIZ_USER_1004", "新密码与原始密码相同"),
	USER_ROLE_ID_ERROR("ERR_BIZ_USER_1005", "用户角色ID不存在"),
	USER_ID_OR_ROLE_ID_ERROR("ERR_BIZ_USER_1006", "用户ID或角色ID不存在"),
	
	ABNORMAL_REPORT_ID_ERROR("ERR_BIZ_001", "异常报告ID不存在"),
	DEVICE_ID_ERROR("ERR_BIZ_002", "设备ID不存在"),
	PRODUCT_ID_ERROR("ERR_BIZ_003", "产品ID不存在"),
	DEVICE_SN_ERROR("ERR_BIZ_004", "设备SN不存在"),
	PRODUCT_SN_ERROR("ERR_BIZ_005", "产品SN不存在"),
	
	DATA_REPORT_PAYLOAD_EMPTY("ERR_BIZ_DATA_REPORT_001", "数据上报payload为空"),
	DATA_REPORT_PARSE_ERROR("ERR_BIZ_DATA_REPORT_002", "数据上报解析模式不支持"),
	
	COMMAND_STATE_ALTER_SUCCESS("ERR_BIZ_COMMAND_001", "指令状态变更成功"),
	COMMAND_STATE_ALTER_ILLEGAL("ERR_BIZ_COMMAND_002", "指令状态变更非法"),
	COMMAND_STATE_ALTER_FAILED("ERR_BIZ_COMMAND_003", "指令状态变更失败"),
	
	;
	
	private String code;
	private String message;
	
}

