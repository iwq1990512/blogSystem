
package com.cms.service.impl;

import com.cms.constant.SystemConstant;
import com.cms.dao.AdminDao;
import com.cms.entity.Admin;
import com.cms.entity.vo.AdminVo;
import com.cms.entity.vo.PageVo;
import com.cms.exception.AuthException;
import com.cms.service.AdminService;
import com.cms.util.AuthUtils;
import com.cms.util.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * 管理员
 * 
 * @author Administrator
 * 
 */
@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDao adminDao;

	// ///////////////////////////////
	// ///// 增加 ////////
	// ///////////////////////////////

	/**
	 * 添加管理员
	 *
	 * @param name
	 * @param password
	 * @return Admin
	 */
	public Admin addAdmin(String name, String password)
			throws AuthException {
		Date now = new Date();
		Admin admin = new Admin();
		admin.setName(name);
		admin.setPassword(AuthUtils.getPassword(password));
		admin.setCreateTime(now);
		adminDao.addAdmin(admin);
		return admin;
	}

	// ///////////////////////////////
	// ///// 刪除 ////////
	// ///////////////////////////////

	/**
	 * 删除管理员
	 * 
	 * @param adminId
	 * @return Integer
	 */
	public int deleteAdmin(long adminId) {
		return adminDao.deleteAdmin(adminId);
	}

	// ///////////////////////////////
	// ///// 修改 ////////
	// ///////////////////////////////

	/**
	 * 修改管理员资料
	 * @param adminId
	 * @param password
	 * @throws AuthException
	 */
	public void updateAdminByAmdinId(long adminId, String password)
			throws AuthException {
		String pwd = AuthUtils.getPassword(password);
		adminDao.updateAdminByadminId(adminId, pwd);
	}

	// ///////////////////////////////
	// ///// 查詢 ////////
	// ///////////////////////////////

	/**
	 * 管理员登陆
	 * 
	 * @param name
	 * @param password
	 * @param request
	 * @throws IOException
	 */
	public void adminLogin(String name, String password,
			HttpServletRequest request) throws AuthException,
			IOException {
		AdminVo admin = adminDao.getAdminByName(name);
		if (admin == null) {
			throw new AuthException("邮箱或密码错误");
		}
		String loginPassword = AuthUtils.getPassword(password);
		if (loginPassword.equals(admin.getPassword())) {
			HttpSession session = request.getSession();
			admin.setPassword("");
			if (name.equals(PropertyUtils
					.getValue("cms.admin"))) {
				admin.setAdmin(true);
			} else {
				admin.setAdmin(false);
			}
			session.setAttribute(SystemConstant.SESSION_ADMIN,
					admin);
		} else {
			throw new AuthException("邮箱或密码错误");
		}
	}

	/**
	 * 通过Id获得指定管理员资料
	 */
	public Admin getAdminById(long adminId) {
		return adminDao.getAdminById(adminId);
	}

	/**
	 * 获得所有管理员的分页数据
	 * 
	 * @param offset
	 * @param rows
	 * @return List<Admin>
	 */
	public List<Admin> getAllList(long offset, long rows) {
		return adminDao.getAllList(offset, rows);
	}

	/**
	 * 获得所有管理员的数量
	 * 
	 * @return Integer
	 */
	public int getAllListCount() {
		return adminDao.getAllListCount();
	}

	/**
	 * 获得所有管理员的分页
	 * 
	 * @param pageNum
	 * @return PageVo<Admin>
	 */
	public PageVo<Admin> getAllListPage(int pageNum) {
		PageVo<Admin> pageVo = new PageVo<Admin>(pageNum);
		pageVo.setRows(20);
		List<Admin> list = this.getAllList(pageVo.getOffset(),
				pageVo.getRows());
		pageVo.setList(list);
		pageVo.setCount(this.getAllListCount());
		return pageVo;
	}

	/**
	 * 通过email获得管理员资料
	 * 
	 * @param name
	 * @return Admin
	 */
	public Admin getAdminByName(String name) {
		return adminDao.getAdminByName(name);
	}

	public long getSuperAdminId() {
		Admin admin = getAdminByName(PropertyUtils
				.getValue("cms.admin"));
		return admin.getAdminId();
	}
}
