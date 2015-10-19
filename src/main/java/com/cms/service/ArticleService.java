
package com.cms.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cms.constant.ArticleConstant;
import com.cms.constant.FolderConstant;
import com.cms.dao.ArticleDao;
import com.cms.entity.Article;
import com.cms.entity.Folder;
import com.cms.entity.vo.ArticleVo;
import com.cms.entity.vo.FolderVo;
import com.cms.entity.vo.PageVo;
import com.cms.exception.ArticleNotFoundException;
import com.cms.exception.FolderNotFoundException;
import com.cms.exception.UploadException;
import com.cms.util.MediaUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * 文章服务
 * 
 * @author Zhangjiale
 * 
 */
public interface ArticleService {

	// ///////////////////////////////
	// ///// 增加 ////////
	// ///////////////////////////////

	/**
	 * @param folderId
	 * @param adminId
	 * @param title
	 * @param summary
	 * @param status
	 * @param content
	 * @param file
	 * @param createTime
	 * @return
	 * @throws FolderNotFoundException
	 * @throws UploadException
	 * @throws IOException
	 */
	public Article addArticle(long folderId, long adminId, String title,
			String summary, ArticleConstant.Status status,String content,
			MultipartFile file, String createTime)throws FolderNotFoundException, UploadException,
			IOException ;

	// ///////////////////////////////
	// ///// 刪除 ////////
	// ///////////////////////////////

	/**
	 * 删除文件
	 *
	 * @return boolean
	 */
	public boolean deleteArticleById(long articleId);

	// ///////////////////////////////
	// ///// 修改 ////////
	// ///////////////////////////////

	/**
	 * 修改文件
	 * @param folderId
	 * @param adminId
	 * @param status
	 * @return
	 * @throws UploadException
	 * @throws ParseException
	 * @throws IOException
	 * @throws FolderNotFoundException 
	 */
	public Article updateArticle(long articleId, long folderId,
			long adminId, String title, String summary,
			String content, ArticleConstant.Status status,MultipartFile file,
			String time) throws UploadException, IOException, FolderNotFoundException;
	/**
	 * 更新浏览人数
	 * 
	 * @param articleId
	 * @param nowViewCount
	 * 
	 */
	public void updateViewCount(long articleId, int nowViewCount) ;
	// ///////////////////////////////
	// ///// 查詢 ////////
	// ///////////////////////////////

	/**
	 * 得到文件
	 *
	 * @return File
	 * @throws ArticleNotFoundException
	 */
	public ArticleVo getArticleById(long articleId) throws ArticleNotFoundException;

	/**
	 * 得到目录的显示的文件分页
	 * 
	 * @param folderId
	 * @return pageVo
	 * @throws FolderNotFoundException
	 */
	public PageVo<ArticleVo> getArticlePageByFolderId(long folderId,
			int pageNum, int rows) throws FolderNotFoundException ;

	/**
	 * 获取某种文件的分页
	 *
	 * @param pageNum
	 * @return PageVo<File>
	 * @throws FolderNotFoundException
	 * 
	 */
	public PageVo<ArticleVo> getArticlePageByFolderId(long adminId,
			long folderId, ArticleConstant.check check, int pageNum) throws FolderNotFoundException ;
	/**
	 * @param adminId
	 * @param folderId
	 * @param offset
	 * @param rows
	 * @return
	 * @throws FolderNotFoundException
	 */
	public List<ArticleVo> getArticleListByAdminIdAndFolderId(long adminId,
			long folderId, ArticleConstant.check check,
			long offset, long rows) throws FolderNotFoundException ;

	/**
	 * @param adminId
	 * @param folderId
	 * @return
	 * @throws FolderNotFoundException
	 */
	public int getArticleCountByAdminIdAndFolderId(long adminId,
			long folderId, ArticleConstant.check check) throws FolderNotFoundException ;
	/**
	 * @param folderId
	 * @return
	 * @throws FolderNotFoundException
	 */
	public int getArticleCountByFolderId(long folderId) throws FolderNotFoundException ;

	public void updateCheck(long articleId,
			ArticleConstant.check check) ;

	/**
	 * @param path
	 * @param offset
	 * @param rows
	 * @return
	 */
	public List<ArticleVo> getArticleListOfDisplayByPath(String path,
			int offset, int rows) ;
}
