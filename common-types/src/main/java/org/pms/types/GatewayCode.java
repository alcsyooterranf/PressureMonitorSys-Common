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
public enum GatewayCode {
	
	// 基本异常
	SUCCESS("0000", "成功"),
	UN_ERROR("0001", "未知失败"),
	ILLEGAL_PARAMETER("0002", "非法参数"),
	ILLEGAL_TYPE_CONVERSION("0003", "类型转换异常"),
	PROTOCOL_NOT_SUPPORTED("0004", "不支持的协议"),
	PARAMETER_IS_NULL("0005", "参数不能为空"),
	LOCAL_QUEUE_IS_FULL("0006", "本地队列已满"),
	
	// token
	TOKEN_VALIDATE_ERROR("1001", "token验证失败"),
	AUTHORIZATION_HEADER_EMPTY("1002", "请求头部Authorization字段为空"),
	
	// 权限
	AUTHENTICATION_FAILURE("2002", "认证失败"),
	AUTHORIZATION_FAILURE("2005", "授权失败"),
	
	// 业务异常
	REQUEST_FORWARD_IO_EXCEPTION("7001", "请求转发IO异常"),
	REQUEST_BASE64_DECODE_ERROR("7002", "请求base64解码失败"),
	
	DATA_REPORT_PAYLOAD_EMPTY("ERR_BIZ_DATA_REPORT_001", "数据上报payload为空"),
	DATA_REPORT_PARSE_ERROR("ERR_BIZ_DATA_REPORT_002", "数据上报解析模式不支持"),
	
	;
	
	private String code;
	private String message;
	
}

