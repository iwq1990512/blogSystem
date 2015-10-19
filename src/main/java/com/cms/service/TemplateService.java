

package com.cms.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.cms.entity.Folder;
import com.cms.entity.vo.FolderVo;
import com.cms.exception.FolderNotFoundException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.cms.constant.ConfigConstant;
import com.cms.constant.SystemConstant;
import com.cms.exception.TemplateNotFoundException;

/**
 * 模板工具类
 * 
 * @author yuheng
 * 
 */
@Service
public interface TemplateService {


	/**
	 * @return
	 */
	public String get404();

	/**
	 * @return
	 */
	public String get500();

	/**
	 * 得到首页（默认页）模板
	 * 
	 * @return
	 * @throws TemplateNotFoundException
	 */
	public String getDefaultTemplate() throws TemplateNotFoundException;

	/**
	 * 得到文件夹模板
	 * 
	 * @param folderId
	 * @return
	 * @throws TemplateNotFoundException
	 * @throws FolderNotFoundException
	 */
	public String getFolderTemplate(long folderId)
			throws TemplateNotFoundException, FolderNotFoundException;

	/**
	 * 得到文件模板
	 * 
	 * @param folderId
	 * @param articleId
	 * @return
	 * @throws TemplateNotFoundException
	 * @throws FolderNotFoundException
	 */
	public String getArticleTemplate(long folderId, long articleId)
			throws TemplateNotFoundException, FolderNotFoundException ;

	/**
	 * 模板物理地址是否存在
	 * 
	 * @param theme
	 * @return
	 */
	public Boolean isExist(String theme) ;

	// ///////////////////////////////
	// ///// 查詢 ////////
	// ///////////////////////////////
}
