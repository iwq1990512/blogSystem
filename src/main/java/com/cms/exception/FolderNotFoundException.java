
package com.cms.exception;

/**
 * 
 * 目录没有发现异常
 * 
 * @author yuheng
 * 
 */
public class FolderNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FolderNotFoundException(String msg) {
		super(msg);
	}
}
