package org.pms.types;

/**
 * 业务常量定义
 *
 * @author alcsyooterranf
 */
public final class BizConstants {

	/**
	 * 安全上下文请求头名称
	 * <p>用于在请求头中传递已认证用户的安全上下文信息（Base64编码的JSON）</p>
	 */
	public static final String SECURITY_CONTEXT_HEADER = "X-Security-Context";

	/**
	 * 默认密码
	 * <p>用于创建用户时的默认密码</p>
	 * TODO: 应该从配置文件读取
	 */
	public static final String DEFAULT_PASSWORD = "123456";
	
	// Database
	// id = 3, name = guest, r_role
	public static final Long DEFAULT_ROLE_ID = 3L;

	// 私有构造函数，防止实例化
	private BizConstants() {
		throw new UnsupportedOperationException("This is a constants class and cannot be instantiated");
	}

}

