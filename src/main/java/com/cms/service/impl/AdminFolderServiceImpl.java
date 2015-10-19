package com.cms.service.impl;

import com.cms.dao.AdminFolderDao;
import com.cms.entity.AdminFolder;
import com.cms.entity.vo.AdminFolderVo;
import com.cms.service.AdminFolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AdminFolderServiceImpl implements AdminFolderService{

	@Autowired
	private AdminFolderDao adminFolderDao;

	@Autowired
	private FolderServiceImpl folderService;

	@CacheEvict(value = "folder", allEntries = true)
	public AdminFolder addAdminFolder(long adminId, long folderId) {
		AdminFolder adminFolder = new AdminFolder();
		adminFolder.setAdminId(adminId);
		adminFolder.setFolderId(folderId);
		adminFolder.setCreateTime(new Date());
		adminFolderDao.addAdminFolder(adminFolder);
		return adminFolder;
	}

	@CacheEvict(value = "folder", allEntries = true)
	public int deleteAdminFolder(long adminId, long folderId) {
		return adminFolderDao.deleteAdminFolder(adminId, folderId);
	}

	public List<AdminFolderVo> getAdminFolderListById(long adminId) {
		List<AdminFolderVo> list = adminFolderDao
				.getAdminFolderListById(adminId);
		return list;
	}

	public AdminFolderVo getAdminFolderById(long adminId, long folderId) {
		return adminFolderDao.getAdminFolderById(adminId, folderId);
	}
}
