package com.cms.util;

import com.cms.entity.UserAuthToken;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yuheng on 2015/10/19.
 */
public class CookieUtils {

    public static final String ACCESS_TOKEN_COOKIE_NAME = "access_token";
    public static final String ACCESS_REFRESH_TOKEN_COOKIE_NAME = "refresh_token";

    /**
     * 往cookie写入用户token和refreshToken
     * @param req
     * @param res
     * @param userAuthToken
     */
    public static void setAccessTokenCookie(HttpServletRequest req, HttpServletResponse res, UserAuthToken userAuthToken) {
        if(null == userAuthToken)
            return ;
        int age = (int)((userAuthToken.refreshTokenExpire.getTime() - new Date().getTime()) / 1000);
        setCookie(req, res, ACCESS_TOKEN_COOKIE_NAME, userAuthToken.token, age);
        setCookie(req, res, ACCESS_REFRESH_TOKEN_COOKIE_NAME, userAuthToken.refreshToken, age);

    }


    public static void setCookie(HttpServletResponse res, String name, String val, int age) {
        Cookie cookie = new Cookie(name, val);
        cookie.setMaxAge(age);
        cookie.setPath("/");
        res.addCookie(cookie);
    }

    /**
     * 设置cookie
     * 写入域名为二级域名
     * @param req
     * @param res
     * @param name
     * @param val
     * @param age
     */
    public static void setCookie(HttpServletRequest req, HttpServletResponse res, String name, String val, int age) {
        Cookie cookie = new Cookie(name, val);
        cookie.setMaxAge(age);
        cookie.setPath("/");
        String serverName = req.getServerName();
        String domain = ".wmeup.com";

        try {
            if(StringUtils.isNotBlank(serverName) && !serverName.equalsIgnoreCase("localhost") && !serverName.equalsIgnoreCase("127.0.0.1") && !isIpAddress(serverName)) {
                domain = serverName.substring(serverName.indexOf("."), serverName.length());
                cookie.setDomain(domain);
            }
        } catch (Exception var9) {
            var9.printStackTrace();
        }

        res.addCookie(cookie);
    }
    /**
     * 判断是否是ip地址
     * @param address
     * @return
     */
    public static boolean isIpAddress(String address) {
        String rexp = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
        Pattern pat = Pattern.compile(rexp);
        Matcher mat = pat.matcher(address);
        return mat.find();
    }

}
