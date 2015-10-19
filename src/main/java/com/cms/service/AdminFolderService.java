package com.cms.service;

import java.util.List;

import com.cms.entity.AdminFolder;
import org.springframework.stereotype.Service;

import com.cms.entity.vo.AdminFolderVo;

@Service
public interface AdminFolderService {

	public AdminFolder addAdminFolder(long adminId, long folderId);

	public int deleteAdminFolder(long adminId, long folderId);

	public List<AdminFolderVo> getAdminFolderListById(long adminId);

	public AdminFolderVo getAdminFolderById(long adminId, long folderId);
}
