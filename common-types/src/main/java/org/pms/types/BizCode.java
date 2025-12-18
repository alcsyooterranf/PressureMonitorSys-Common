package org.pms.types;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 业务异常代码枚举
 * 用于统一管理所有业务异常的错误码和错误信息
 *
 * @author alcsyooterranf
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum BizCode {

	// ==================== 基本异常 ====================
	SUCCESS("0000", "成功"),
	UN_ERROR("0001", "未知失败"),
	ILLEGAL_PARAMETER("0002", "非法参数"),
	ILLEGAL_TYPE_CONVERSION("0003", "类型转换异常"),
	PROTOCOL_NOT_SUPPORTED("0004", "不支持的协议"),
	PARAMETER_IS_NULL("0005", "参数不能为空"),
	LOCAL_QUEUE_IS_FULL("0006", "本地队列已满"),

	// ==================== 登录认证 ====================
	LOGIN_SUCCESS("0100", "登录成功"),
	LOGIN_FAIL("0101", "登录失败"),
	INSUFFICIENT_PERMISSIONS("0102", "权限不足"),

	// ==================== Token相关 ====================
	TOKEN_VALIDATE_ERROR("1001", "token验证失败"),
	AUTHORIZATION_HEADER_EMPTY("1002", "请求头部Authorization字段为空"),
	TOKEN_NOT_EXIST("1003", "token不存在"),
	CREATED_TOKEN_NOT_EXIST("1004", "生成的token不存在"),
	TOKEN_HEADER_NOT_EXIST("1005", "token头部不存在"),
	TOKEN_IS_EMPTY("1006", "token为空"),
	TOKEN_HEADER_ERROR("1007", "token头部错误"),
	REFRESH_TOKEN_NOT_EXIST("1008", "refreshToken不存在"),
	TOKEN_EXPIRED("ERR_BIZ_TOKEN_1001", "token已过期"),
	TOKEN_TAMPERED("ERR_BIZ_TOKEN_1002", "token被篡改"),
	TOKEN_PARSE_ERROR("ERR_BIZ_TOKEN_1003", "token解析错误"),
	TOKEN_ISSUER_ERROR("ERR_BIZ_TOKEN_1004", "token签发者错误"),

	// ==================== 权限相关 ====================
	AUTHENTICATED_USER_NOT_EXIST("2001", "认证用户不存在"),
	AUTHENTICATION_FAILURE("2002", "认证失败"),
	USERNAME_OR_PASSWORD_ERROR("2003", "用户名或密码错误"),
	USER_LOGOUT_SUCCESS("2004", "用户登出成功"),
	AUTHORIZATION_FAILURE("2005", "授权失败"),

	// ==================== Redis数据库 ====================
	REDIS_SET_ERROR("5001", "redis存储操作失败"),

	// ==================== MySQL数据库 ====================
	SQL_INDEX_DUPLICATE("6001", "唯一索引冲突"),

	// ==================== 业务通用异常 ====================
	REQUEST_FORWARD_IO_EXCEPTION("7001", "请求转发IO异常"),
	REQUEST_BASE64_DECODE_ERROR("7002", "请求base64解码失败"),
	JSON_PARSE_ERROR("7003", "JSON解析失败"),

	// ==================== 用户相关 ====================
	USER_ID_ERROR("ERR_BIZ_USER_1001", "用户ID不存在"),
	PASSWORD_IS_EMPTY("ERR_BIZ_USER_1002", "密码为空"),
	OLD_PASSWORD_ERROR("ERR_BIZ_USER_1003", "旧密码错误"),
	PASSWORD_IS_SAME("ERR_BIZ_USER_1004", "新密码与原始密码相同"),
	USER_ROLE_ID_ERROR("ERR_BIZ_USER_1005", "用户角色ID不存在"),
	USER_ID_OR_ROLE_ID_ERROR("ERR_BIZ_USER_1006", "用户ID或角色ID不存在"),

	// ==================== 设备相关 ====================
	ABNORMAL_REPORT_ID_ERROR("ERR_BIZ_001", "异常报告ID不存在"),
	DEVICE_ID_ERROR("ERR_BIZ_002", "设备ID不存在"),
	PRODUCT_ID_ERROR("ERR_BIZ_003", "产品ID不存在"),
	DEVICE_SN_ERROR("ERR_BIZ_004", "设备SN不存在"),
	PRODUCT_SN_ERROR("ERR_BIZ_005", "产品SN不存在"),

	// ==================== 数据上报相关 ====================
	DATA_REPORT_PAYLOAD_EMPTY("ERR_BIZ_DATA_REPORT_001", "数据上报payload为空"),
	DATA_REPORT_PARSE_ERROR("ERR_BIZ_DATA_REPORT_002", "数据上报解析模式不支持"),

	// ==================== 指令元数据相关 ====================
	COMMAND_META_UPDATE_FAILED("ERR_BIZ_COMMAND_META_1002", "更新指令元数据失败"),
	COMMAND_META_CREATE_FAILED("ERR_BIZ_COMMAND_META_1003", "创建指令元数据失败"),
	COMMAND_META_UPDATE_ID_NOT_EXIST("ERR_BIZ_COMMAND_META_1005", "更新指令元数据失败，ID不存在"),
	COMMAND_META_UPDATE_EXCEPTION("ERR_BIZ_COMMAND_META_1006", "更新指令元数据异常"),
	COMMAND_META_VERIFY_ID_NOT_EXIST("ERR_BIZ_COMMAND_META_1008", "验证指令元数据失败，ID不存在"),
	COMMAND_META_VERIFY_STATUS_ERROR("ERR_BIZ_COMMAND_META_1009", "只有未验证状态的指令元数据才能被验证"),
	COMMAND_META_VERIFY_FAILED("ERR_BIZ_COMMAND_META_1010", "验证指令元数据失败"),
	COMMAND_META_SERVICE_IDENTIFIER_ERROR("ERR_BIZ_COMMAND_META_2006", "service_identifier字段必须等于payload_schema中的serviceIdentifier"),
	COMMAND_META_PIPELINE_NOT_EXIST("ERR_BIZ_COMMAND_META_3001", "管道ID不存在"),
	COMMAND_META_NOT_EXIST("ERR_BIZ_COMMAND_META_3002", "指令元数据不存在"),
	COMMAND_META_PAYLOAD_SCHEMA_PARSE_ERROR("ERR_BIZ_COMMAND_META_3003", "解析payload_schema异常"),
	COMMAND_META_PAYLOAD_SCHEMA_MISSING_FIELD("ERR_BIZ_COMMAND_META_3004", "payload_schema缺少必需字段"),
	COMMAND_META_INPUT_SCHEMA_FORMAT_ERROR("ERR_BIZ_COMMAND_META_3005", "inputSchema格式错误"),
	COMMAND_META_INPUT_SCHEMA_VALIDATE_ERROR("ERR_BIZ_COMMAND_META_3006", "inputSchema校验异常"),
	COMMAND_META_ARGS_VALIDATE_ERROR("ERR_BIZ_COMMAND_META_3007", "args参数校验失败"),

	// ==================== 指令任务相关 ====================
	COMMAND_TASK_CREATE_FAILED("ERR_BIZ_COMMAND_TASK_5001", "创建指令任务失败"),
	COMMAND_TASK_NOT_EXIST("ERR_BIZ_COMMAND_TASK_5002", "指令任务不存在"),
	COMMAND_EXECUTION_CREATE_FAILED("ERR_BIZ_COMMAND_EXECUTION_5003", "创建指令执行记录失败"),
	COMMAND_ASYNC_SEND_FAILED("ERR_BIZ_COMMAND_ASYNC_5004", "异步指令下发失败"),

	// ==================== 指令状态流转相关 ====================
	COMMAND_STATE_TRANSITION_FAILED("ERR_BIZ_COMMAND_STATE_4001", "指令状态流转失败"),
	COMMAND_STATE_TRANSITION_ILLEGAL("ERR_BIZ_COMMAND_STATE_4002", "非法的状态流转"),
	COMMAND_STATE_FINAL_CANNOT_TRANSITION("ERR_BIZ_COMMAND_STATE_4003", "终态不可流转"),

	;

	private String code;
	private String message;

	/**
	 * 根据code获取枚举
	 *
	 * @param code 错误码
	 * @return BizCode枚举
	 */
	public static BizCode fromCode(String code) {
		if (code == null) {
			return null;
		}
		for (BizCode bizCode : values()) {
			if (bizCode.getCode().equals(code)) {
				return bizCode;
			}
		}
		return null;
	}

}

