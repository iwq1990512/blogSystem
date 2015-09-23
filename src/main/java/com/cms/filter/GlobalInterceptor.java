/*
 *	Copyright © 2013 Changsha Shishuo Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.shishuo.com
 */

package com.cms.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.cms.constant.ConfigConstant;
import com.cms.service.ConfigService;
import com.cms.util.HttpUtils;

/**
 * 系统参数配置拦截器
 * @author yuheng
 * 
 */
@Component
public class GlobalInterceptor implements HandlerInterceptor {

	@Autowired
	private ConfigService configService;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (null == modelAndView) {
			return;
		}
		// 系统配置参数
		String basePath = HttpUtils.getBasePath(request);
		modelAndView.addObject("BASE_PATH", basePath);
		modelAndView.addObject("UPLOAD_BASE_PATH", basePath + "/upload");
		modelAndView.addObject("TEMPLATE_BASE_PATH",basePath + "/static/template/" + configService.getStringByKey(ConfigConstant.TEMPLATE));
		modelAndView.addObject("seo_title", configService.getStringByKey("seo_title"));
		modelAndView.addObject("seo_description", configService.getStringByKey("seo_description"));
		modelAndView.addObject("seo_headline", configService.getStringByKey("seo_headline"));
		MDC.put("ip", HttpUtils.getIp(request));
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
