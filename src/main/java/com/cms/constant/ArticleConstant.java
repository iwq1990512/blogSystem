

package com.cms.constant;

/**
 * 文件常量
 * 
 * @author yuheng
 * 
 */
public class ArticleConstant {

	/**
	 * 文件状态
	 * 
	 * @author yuheng
	 * 
	 */
	public static enum Status {
		/**
		 * 隐藏
		 */
		hidden, /**
		 * /** 公开的
		 */
		display,
	};

	/**
	 * 审核
	 * 
	 * @author yuheng
	 * 
	 */
	public static enum check {
		/**
		 * 已审核
		 */
		yes, /**
		 * /** 审核失败
		 */
		no, /**
		 * /** 未审核
		 */
		init,
	};
}
