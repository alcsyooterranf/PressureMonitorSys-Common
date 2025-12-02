package com.pms.types;

import lombok.Setter;

import java.io.Serial;
import java.util.Objects;

/**
 * @author alcsyooterranf
 */
@Setter
public final class AppException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 5317680961212299217L;

    /** 异常码 */
    private String code;

    /** 异常信息 */
    private String message;

    public AppException(String code) {
        this.code = code;
    }

    public AppException(String code, Throwable cause) {
        this.code = code;
        this.message = cause.getMessage();
        super.initCause(cause);
    }

    public AppException(String code, String message) {
        this.code = code;
        this.message = message;
        super.initCause(new Throwable(message));
    }

    public AppException(ResponseCode responseCode) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
        super.initCause(new Throwable(responseCode.getMessage()));
    }

    public AppException(ResponseCode responseCode, Throwable cause) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
        super.initCause(cause);
    }

    public AppException(String code, String message, Throwable cause) {
        this.code = code;
        this.message = message;
        super.initCause(cause);
    }

    @Override
    public String toString() {
        return "com.example.common.types.AppException{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }
	
	@Override
    public String getMessage() {
        return message;
    }
	
	@Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
			return false;
		}
        AppException that = (AppException) o;
        return Objects.equals(code, that.code) && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, message);
    }
}

