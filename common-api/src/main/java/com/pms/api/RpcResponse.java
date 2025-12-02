package com.pms.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 统一RPC响应对象
 * 用于Gateway和后端服务之间的RPC通信
 *
 * @author alcsyooterranf
 * @version 1.0
 * @since 2025/11/27
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RpcResponse<T> implements Serializable {
	
	@Serial
	private static final long serialVersionUID = 1L;
	
	/**
	 * 响应码
	 */
	private String code;
	
	/**
	 * 响应消息
	 */
	private String message;
	
	/**
	 * 响应数据
	 */
	private T data;
	
	/**
	 * 成功响应（无数据）
	 */
	public static <T> RpcResponse<T> success() {
		return RpcResponse.<T>builder()
				.code("0000")
				.message("成功")
				.build();
	}
	
	/**
	 * 成功响应（带数据）
	 */
	public static <T> RpcResponse<T> success(T data) {
		return RpcResponse.<T>builder()
				.code("0000")
				.message("成功")
				.data(data)
				.build();
	}
	
	/**
	 * 失败响应
	 */
	public static <T> RpcResponse<T> fail(String message) {
		return RpcResponse.<T>builder()
				.code("0001")
				.message(message)
				.build();
	}
	
	/**
	 * 失败响应（带错误码）
	 */
	public static <T> RpcResponse<T> fail(String code, String message) {
		return RpcResponse.<T>builder()
				.code(code)
				.message(message)
				.build();
	}
	
	/**
	 * 判断是否成功
	 */
	public boolean isSuccess() {
		return "0000".equals(this.code);
	}
	
}

