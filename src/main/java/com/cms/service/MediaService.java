

package com.cms.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.cms.dao.MediaDao;
import com.cms.entity.vo.PageVo;
import com.cms.exception.UploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cms.constant.MediaConstant;
import com.cms.entity.Media;
import com.cms.util.MediaUtils;

@Service
public interface MediaService {

	/*
	 * 上传附件
	 */
	public Media addUploadFile(MultipartFile multipartFile, String fileName,
			long kindId, MediaConstant.Kind kind) throws IllegalStateException,
			IOException, UploadException;

	/**
	 * 
	 * 删除附件通过附件ID
	 */
	public void deleteMedia(long attachmentId, String path);

	/**
	 * @param attachmentId
	 * @return
	 */
	public Media getMediaById(long attachmentId);

	/**
	 * @param attachmentId
	 * @param link
	 * @return
	 */
	public int updateLinkByMediaId(long attachmentId, String link);

	public int updateNameByMediaId(long attachmentId, String name) ;

	/**
	 * @param folderId
	 * @return
	 */
	public PageVo<Media> getMediaPageByKindId(long kindId,
			MediaConstant.Kind kind, int rows, int pageNum);

	/**
	 * @param folderId
	 * @return
	 */
	public PageVo<Media> getMediaPageByKindAndType(long kindId,
			MediaConstant.Kind kind, MediaConstant.Type type, int rows,
			int pageNum);

	/**
	 * @param kindId
	 * @param kind
	 * @param stauts
	 * @return
	 */
	public List<Media> getMediaListByKindId(long kindId,
			MediaConstant.Kind kind, int rows) ;
	/**
	 * @param kindId
	 * @param kind
	 * @param stauts
	 * @return
	 */
	public List<Media> getMediaListByKindAndType(long kindId,
			MediaConstant.Kind kind, MediaConstant.Type type, int rows) ;

}
