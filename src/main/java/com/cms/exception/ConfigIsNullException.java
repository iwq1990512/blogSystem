
package com.cms.exception;

/**
 * 
 * 系统配置Key获得的Value为空
 * 
 * @author yuheng
 * 
 */
public class ConfigIsNullException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConfigIsNullException(String msg) {
		super(msg);
	}
}
