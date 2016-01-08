package com.cms.action.thirdpart;

import com.cms.action.BaseAction;
import com.cms.entity.User;
import com.cms.entity.UserAuthToken;
import com.cms.service.UserAuthService;
import com.cms.service.UserService;
import com.cms.tool.thirdpart.qq.QQAccessConfig;
import com.cms.tool.thirdpart.qq.QQAccessHandler;
import com.cms.util.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

/**
 * 第三方登录
 * Created by yuheng on 2015/9/23.
 */
@Controller
@RequestMapping("/thirdpart")
public class ThirdLoginAction extends BaseAction {
    private final Logger logger = Logger.getLogger(ThirdLoginAction.class);
    private User user;
    private String source; //登录来源
    private String creurl = ""; //定制第三方登录完成后回到的地址
   // private String crecode = ""; //定制第三方登录完成后回调的地址临时票据

    @Autowired
    private UserService userService;
    @Autowired
    private UserAuthService userAuthService;

    @RequestMapping(value = "/login.htm", method = RequestMethod.GET)
    public String login(HttpServletRequest request , HttpSession session) {
        session.setAttribute("creurl", request.getHeader("Referer"));
        source = request.getParameter("source");
        if (ThirdPartUtil.SOURCE_QQ.equalsIgnoreCase(source)){
            return "redirect:" + loginQQ();
        }
        return null;
    }

    /**
     * 第三方登录回调
     * @return
     */
    @RequestMapping(value = "/thdlogincallback.htm", method = RequestMethod.GET)
    public String thirdLoginCallback(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        creurl = (String)session.getAttribute("creurl");
        session.removeAttribute("creurl");
        source = request.getParameter("source");
        String result = null;
        if(ThirdPartUtil.SOURCE_QQ.equalsIgnoreCase(source)) {
            result = qqCallback(request, response);
        }
        return "redirect:" + result;
    }

    private String qqCallback(HttpServletRequest request, HttpServletResponse response) {
        try {
            QQAccessHandler qqAccessHandler = new QQAccessHandler(request.getParameter("code"),
                    new QQAccessConfig(getParamMap()));
            qqAccessHandler.handleAuth();
            User user = null;
            user = qqAccessHandler.handleUser();
            this.user = user;
        }catch(Exception e) {
            logger.error("QQ第三方登录callback异常", e);
        }
        return loginProcessor(user, request, response);
    }


    private String loginProcessor(User uss, HttpServletRequest request, HttpServletResponse response) {
        String clientIp = HttpClientUtil.getIpAddr(request);
        if(uss == null || StringUtils.isBlank(uss.getOpenId()) || 0 >= uss.getSourceType()) {
            return null;
        }
        int sourceType = uss.getSourceType();
        User cmsUser = null;
        UserAuthToken userAuthToken = null;
        //查询是否之前登录过
        cmsUser = userService.getUserBySourceSys(uss.getOpenId(), uss.getSourceType());

        if(null == cmsUser) {
            cmsUser = this.user;
            //首次登录
            //1 创建用户 重新写入this.user
            this.user = userService.insert(cmsUser);
        }else {
            //非首次登录
            uss.setUserId(cmsUser.getUserId());
            userService.update(uss);
            this.user = uss;
        }
        try {
            userAuthToken = userAuthService.loginThirdPart(user.getUserId());
        }catch(Exception e) {
            logger.error("第三方登录成功后auth验证失败", e);
            return null;
        }
        if(null != userAuthToken) {
            CookieUtils.setAccessTokenCookie(request, response, userAuthToken);
            long uid = userAuthService.checkAuth(userAuthToken.token);
            User user = userService.getUserByUid(uid);
            LoginProcessor.setAutoLoginCookie(user,  response, request);
            return creurl;
        }else {
            return null;
        }
    }

    /**
     * 获取qq登录跳转链接
     * @return
     */
    private String loginQQ() {
        QQAccessConfig qqAccessConfig = new QQAccessConfig(getParamMap());
        return qqAccessConfig.getAuthUrl();
    }

    private Map<String, String> getParamMap() {
        Map<String, String> resultMap = Util.buildMap("source", source);
        return resultMap;
    }

}
