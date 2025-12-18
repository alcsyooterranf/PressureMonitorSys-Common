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
public enum WsCode {
	
	// 基本异常
	SUCCESS("0000", "成功"),
	
	;
	
	private String code;
	private String message;
	
}

