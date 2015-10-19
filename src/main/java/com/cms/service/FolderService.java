
package com.cms.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.cms.constant.FolderConstant;
import com.cms.dao.FolderDao;
import com.cms.entity.Folder;
import com.cms.entity.vo.FolderVo;
import com.cms.exception.FolderNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cms.entity.vo.AdminFolderVo;

/**
 * 目录服务
 * 
 * @author Zhangjiale
 * 
 */
@Service
public interface FolderService {

	// ///////////////////////////////
	// ///// 增加 ////////
	// ///////////////////////////////

	/**
	 * 增加目录
	 * 
	 * @param fatherId
	 * @param name
	 * @param ename
	 * @param status
	 * @return Folder
	 * @throws FolderNotFoundException
	 */
	@Transactional
	public Folder addFolder(long fatherId, String name, String ename,
			FolderConstant.status status, FolderConstant.check check) throws FolderNotFoundException;

	// ///////////////////////////////
	// ///// 刪除 ////////
	// ///////////////////////////////

	/**
	 * 删除目录
	 * 
	 * @param folderId
	 * @return boolean
	 */
	@CacheEvict(value = "folder", allEntries = true)
	public boolean deleteFolderById(long folderId);

	// ///////////////////////////////
	// ///// 修改 ////////
	// ///////////////////////////////

	/**
	 * 更新目录
	 * 
	 * @param folderId
	 * @param fatherId
	 * @param ename
	 * @param name
	 * @param status
	 * @param type
	 * @param sort
	 * @return folder
	 */
	@CacheEvict(value = "folder", allEntries = true)
	public void updateFolderById(long folderId, String name, String ename,
			FolderConstant.status status, String content, int height, int width);
	/**
	 * 通过指定Id修改其目录的序列
	 * 
	 * @param folderId
	 * @param sort
	 * @return Integer
	 */
	@CacheEvict(value = "folder", allEntries = true)
	public int updateSort(long folderId, int sort);

	/**
	 * 通过指定Id修改其目录的路径
	 * 
	 * @param folderId
	 * @param path
	 * @return Integer
	 */
	public int updatePath(long folderId, String path);

	/**
	 * @param folderId
	 * @param count
	 * @return
	 */
	public int updateCount(long folderId, int count);

	// ///////////////////////////////
	// ///// 查詢 ////////
	// ///////////////////////////////

	/**
	 * 得到指定目录
	 * 
	 * @param folderId
	 * @return Folder
	 * @throws FolderNotFoundException
	 */
	@Cacheable(value = "folder")
	public FolderVo getFolderById(long folderId) throws FolderNotFoundException ;

	/**
	 * 得到所有的四层目录
	 * 
	 * @return
	 * @throws FolderNotFoundException
	 */
	public List<FolderVo> getAllFolderList(long adminId);


	/**
	 * 得到所有子目录
	 * 
	 * @param fatherId
	 * @return List<Folder>
	 */
	@Cacheable(value = "folder")
	public List<FolderVo> getFolderListByFatherId(long fatherId,
			FolderConstant.status status) ;
	/**
	 * 通过ename和fatherId获得目录
	 * 
	 * @param ename
	 * @return
	 * @throws FolderNotFoundException
	 */
	@Cacheable(value = "folder")
	public Folder getFolderByEname(String ename) throws FolderNotFoundException ;

	@Cacheable(value = "folder")
	public boolean isFolderByEname(String ename) ;
	/**
	 * 得到目录的Path
	 * 
	 * @param folderId
	 * @return
	 * @throws FolderNotFoundException
	 */
	@Cacheable(value = "folder")
	public List<FolderVo> getFolderPathListByFolderId(long folderId) throws FolderNotFoundException;

	@CacheEvict(value = "folder", allEntries = true)
	public void updateStatus(long folderId, FolderConstant.status status);

	/**
	 * 判断是不是已经存在的目录英文名
	 * 
	 * @param ename
	 * @return
	 */
	@Cacheable(value = "folder")
	public boolean isFolderEname(String ename) ;

	public long firstFolderId(long folderId) ;

}
