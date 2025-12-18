package org.pms.types;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 统一响应对象
 *
 * @author alcsyooterranf
 * @version 1.0
 * @since 2025/11/27
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> implements Serializable {
	
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
	
}

