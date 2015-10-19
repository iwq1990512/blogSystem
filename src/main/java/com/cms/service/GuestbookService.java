package com.cms.service;

import java.util.Date;
import java.util.List;

import com.cms.entity.vo.GuestbookVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cms.constant.GuestbookConstant;
import com.cms.dao.GuestbookDao;
import com.cms.entity.Guestbook;
import com.cms.entity.vo.PageVo;

@Service
public interface GuestbookService {

	public Guestbook addGuestbook(String name, String email, String title,
			String content);
	public int updateReplyByMessageId(String reply, long messageId,
			GuestbookConstant.status status) ;

	public int updateStatusByMessageId(GuestbookConstant.status status,
			long messageId) ;

	public GuestbookVo getGuestbookById(long messageId);

	public List<GuestbookVo> getGuestbookList(GuestbookConstant.status status,
			long offset, long rows) ;

	public int getGuestbookCountList(GuestbookConstant.status status);

	public PageVo<GuestbookVo> getMessageBoardPage(int pageNum,
			GuestbookConstant.status status, String number) ;
}
