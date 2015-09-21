package com.cms.dao;

import java.util.Date;
import java.util.List;

import com.cms.constant.GuestbookConstant;
import com.cms.entity.Guestbook;
import com.cms.entity.vo.GuestbookVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestbookDao {

	public int addGuestbook(Guestbook guestbook);

	public int updateReplyById(@Param("reply") String reply,
			@Param("guestbookId") long guestbookId,
			@Param("status") GuestbookConstant.status status,
			@Param("replyTime") Date replyTime);

	public GuestbookVo getGuestbookById(@Param("guestbookId") long guestbookId);

	public List<GuestbookVo> getGuestbookList(
			@Param("status") GuestbookConstant.status status,
			@Param("offset") long offset, @Param("rows") long rows);

	public int getGuestbookCountList(
			@Param("status") GuestbookConstant.status status);

	public int updateStatusById(
			@Param("status") GuestbookConstant.status status,
			@Param("guestbookId") long guestbookId);
}
