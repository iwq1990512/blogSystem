/*
 *	Copyright © 2013 Changsha Shishuo Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.shishuo.com
 */
package com.cms.action;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.cms.service.ArticleService;
import com.cms.service.FolderService;
import com.cms.service.HeadlineService;
import com.cms.service.TemplateService;

/**
 * 
 * @author Herbert
 * 
 */
public class BaseAction {

	@Autowired
	protected FolderService folderService;

	@Autowired
	protected ArticleService fileService;

	@Autowired
	protected TemplateService themeService;

	@Autowired
	protected HeadlineService headlineService;

	protected final Logger logger = Logger.getLogger(this.getClass());
}
