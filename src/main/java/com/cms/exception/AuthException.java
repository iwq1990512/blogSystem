
package com.cms.exception;

/**
 * 
 * auth验证异常类
 * 
 * @author yuheng
 * 
 */
public class AuthException extends Exception {

	private static final long serialVersionUID = 1L;

	public AuthException(String msg) {
		super(msg);
	}
}
