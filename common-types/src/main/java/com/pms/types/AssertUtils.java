package com.pms.types;

import org.apache.commons.lang3.ObjectUtils;

import java.util.Objects;

/**
 * 断言工具类
 *
 * @author wangxi
 * @date 2021/9/26 上午9:23
 */
public final class AssertUtils {
	
	public static void isTrue(boolean expression, Object... o) {
		isTrue(expression, null, o);
	}
	
	public static void isTrue(boolean expression, ResponseCode responseCode, Object... o) {
		if (!expression) {
			StringBuilder msg = new StringBuilder();
			if (ObjectUtils.isEmpty(responseCode)) {
				for (Object o1 : o) {
					msg.append(o1.toString());
				}
				throw new AppException("9999", msg.toString());
			}
			throw new AppException(responseCode);
		}
	}
	
	public static void isFalse(boolean expression, Object... o) {
		isFalse(expression, null, o);
	}
	
	public static void isFalse(boolean expression, ResponseCode responseCode, Object... o) {
		isTrue(!expression, responseCode, o);
	}
	
	public static void equals(Object o1, Object o2, Object... o) {
		equals(o1, o2, null, o);
	}
	
	public static void equals(Object o1, Object o2, ResponseCode responseCode, Object... o) {
		if (o1 == null || o2 == null) {
			isTrue(false, ResponseCode.PARAMETER_IS_NULL);
		}
		isTrue(Objects.equals(o1, o2), responseCode, o);
	}
	
	/**
	 * 判断为null，不是则抛异常
	 *
	 * @param o
	 * @param responseCode
	 * @param objects
	 */
	public static void isNull(Object o, ResponseCode responseCode, Object... objects) {
		if (!ObjectUtils.isEmpty(o)) {
			isTrue(false, responseCode, objects);
		}
	}
	
	public static void isNull(Object o, Object... objects) {
		isNull(o, null, objects);
	}
	
	/**
	 * 判断不是null，否则抛出异常
	 *
	 * @param o
	 * @param objects
	 */
	public static void notNull(Object o, Object... objects) {
		notNull(o, null, objects);
	}
	
	/**
	 * 判断不是null，否则抛出异常
	 *
	 * @param o
	 * @param responseCode
	 * @param objects
	 */
	public static void notNull(Object o, ResponseCode responseCode, Object... objects) {
		if (ObjectUtils.isEmpty(o)) {
			isTrue(false, responseCode, objects);
		}
	}
	
}

