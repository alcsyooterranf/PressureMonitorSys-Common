package com.pms.types;

/**
 * @author alcsyooterranf
 */
public final class Constants {
	
	// TokenUtil
	//private static final Long ACCESS_EXPIRATION = 60 * 60 * 6L; // 6h
	public static final Long ACCESS_EXPIRATION = 5 * 60L; // 5min
	public static final Long REFRESH_EXPIRATION = 60 * 60 * 24 * 7L; // 7天
	public static final String REDIS_KEY_PREFIX_ACCESS = "access_token: ";
	public static final String REDIS_KEY_PREFIX_REFRESH = "refresh_token: ";
	
	// RSAUtil
	// key路径（Gateway专用，Maven运行时工作目录是PressureMonitorSys-app）
	public static final String KEY_PATH = "data/keys";
	public static final String ALGORITHM = "RSA";
	public static final String PUBLIC_KEY_FILENAME = "/publicKey.txt";
	public static final String PRIVATE_KEY_FILENAME = "/privateKey.txt";
	
	// JwtUtil
	public static final String ISS = "PressureMonitorSys-auth";
	public static final String USER_ID = "userId";
	public static final String USER_NAME = "userName";
	public static final String ROLE_NAME = "roleName";
	public static final String AUTHORITIES = "authorities";
	public static final String PERMISSIONS = "permissions";
	
	// BIZ
	public static final String DEFAULT_PASSWORD = "123456";
	
	// Request Header
	public static final String SECURITY_CONTEXT_HEADER = "X-Security-Context";
	public static final String TOKEN_HEADER = "Authorization";
	public static final String TOKEN_PREFIX = "Bearer";
	
	// Database
	// id = 3, name = guest, r_role
	public static final Long DEFAULT_ROLE_ID = 3L;
	
}

