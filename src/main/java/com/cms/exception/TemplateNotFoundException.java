
package com.cms.exception;

/**
 * 
 * 目录没有发现异常
 * 
 * @author yuheng
 * 
 */
public class TemplateNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TemplateNotFoundException(String msg) {
		super(msg);
	}
}
