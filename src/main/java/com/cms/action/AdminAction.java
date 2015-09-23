
package com.cms.action;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.cms.constant.SystemConstant;
import com.cms.entity.vo.JsonVo;
import com.cms.service.AdminService;
import com.cms.util.HttpUtils;

/**
 * @author yuheng
 * 
 */

@Controller
@RequestMapping("/admin")
public class AdminAction extends BaseAction {

	/**
	 * Kaptcha 验证码
	 */
	@Autowired
	private DefaultKaptcha captchaProducer;

	@Autowired
	private AdminService adminService;

	@RequestMapping(value = "/login.htm", method = RequestMethod.GET)
	public String login(HttpServletRequest request, ModelMap modelMap) {
		return "/manage/login";
	}

	@RequestMapping(value = "/logout.htm", method = RequestMethod.GET)
	public String adminLogout(HttpServletRequest request, ModelMap modelMap) {
		request.getSession().removeAttribute(SystemConstant.SESSION_ADMIN);
		return "redirect:" + HttpUtils.getBasePath(request);
	}

	@ResponseBody
	@RequestMapping(value = "/login.json", method = RequestMethod.POST)
	public JsonVo<String> adminLogin(@RequestParam(value = "name") String name,
			@RequestParam(value = "password") String password,
			@RequestParam(value = "captcha") String captcha,
			HttpServletRequest request, ModelMap modelMap) {
		JsonVo<String> json = new JsonVo<String>();

		try {
			String kaptcha = (String) request.getSession().getAttribute(
					com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
			if (StringUtils.isBlank(password)) {
				json.getErrors().put("password", "密码不能为空");
			} else if (password.length() < 6 && password.length() > 30) {
				json.getErrors().put("password", "密码最少6个字符，最多30个字符");
			}
			// 校验验证码
			if (StringUtils.isNotBlank(kaptcha)
					&& kaptcha.equalsIgnoreCase(captcha)) {

			} else {
				json.getErrors().put("captcha", "验证码错误");
			}
			json.check();

			adminService.adminLogin(name, password, request);

		} catch (Exception e) {
			// 异常，重置验证码
			request.getSession().removeAttribute(
					com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
			json.setResult(false);
			json.getErrors().put("password", "邮箱或密码错误");
			json.setMsg("change_captcha");
		}
		return json;
	}

	/**
	 * 生成验证码
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "captcha.htm", method = RequestMethod.GET)
	public void captcha(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control",
				"no-store, no-cache, must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");
		response.setContentType("image/jpeg");
		String capText = captchaProducer.createText();
		request.getSession().setAttribute(
				com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY, capText);
		BufferedImage bi = captchaProducer.createImage(capText);
		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(bi, "jpg", out);
		try {
			out.flush();
		} finally {
			out.close();
		}
	}
}
