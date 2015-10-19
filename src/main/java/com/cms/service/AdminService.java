
package com.cms.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.cms.entity.vo.PageVo;
import com.cms.util.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cms.constant.SystemConstant;
import com.cms.dao.AdminDao;
import com.cms.entity.Admin;
import com.cms.entity.vo.AdminVo;
import com.cms.exception.AuthException;
import com.cms.util.AuthUtils;

/**
 * 管理员
 * 
 * @author Administrator
 * 
 */
@Service
public interface AdminService {

	/**
	 * 添加管理员
	 * 
	 * @param name
	 * @param password
	 * @return Admin
	 */
	public Admin addAdmin(String name, String password)throws AuthException ;

	// ///////////////////////////////
	// ///// 刪除 ////////
	// ///////////////////////////////

	/**
	 * 删除管理员
	 * 
	 * @param adminId
	 * @return Integer
	 */
	public int deleteAdmin(long adminId);

	// ///////////////////////////////
	// ///// 修改 ////////
	// ///////////////////////////////

	/**
	 * 修改管理员资料
	 * 
	 * @param adminId
	 * @param password
	 * @return Admin
	 * @throws AuthException
	 */

	public void updateAdminByAmdinId(long adminId, String password)throws AuthException ;

	// ///////////////////////////////
	// ///// 查詢 ////////
	// ///////////////////////////////

	/**
	 * 管理员登陆
	 *
	 * @param password
	 * @param request
	 * @throws IOException
	 */
	public void adminLogin(String name, String password,
			HttpServletRequest request)throws AuthException ,
			IOException ;

	/**
	 * 通过Id获得指定管理员资料
	 */
	public Admin getAdminById(long adminId);

	/**
	 * 获得所有管理员的分页数据
	 * 
	 * @param offset
	 * @param rows
	 * @return List<Admin>
	 */
	public List<Admin> getAllList(long offset, long rows);

	/**
	 * 获得所有管理员的数量
	 * 
	 * @return Integer
	 */
	public int getAllListCount();

	/**
	 * 获得所有管理员的分页
	 * 
	 * @return PageVo<Admin>
	 */
	public PageVo<Admin> getAllListPage(int pageNum);

	/**
	 * 通过email获得管理员资料
	 * 
	 * @return Admin
	 */
	public Admin getAdminByName(String name);

	public long getSuperAdminId() ;
}
