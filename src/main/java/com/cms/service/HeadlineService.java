package com.cms.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.cms.dao.HeadlineDao;
import com.cms.entity.Headline;
import com.cms.entity.vo.HeadlineVo;
import com.cms.exception.UploadException;
import com.cms.util.MediaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface HeadlineService {

	public Headline addHeadline(MultipartFile multipartFile, String name,
			String url)throws UploadException, IOException;

	public List<HeadlineVo> getHeadlineList();

	public int updateHeadlineById(long headlineId, String name,
			MultipartFile file, String url) throws UploadException, IOException ;
	public HeadlineVo getHeadlineById(long headlineId);

	public void deleteHeadline(long headlineId, String pictureUrl);

	public void updateSortById(long headlineId, int sort);
}
