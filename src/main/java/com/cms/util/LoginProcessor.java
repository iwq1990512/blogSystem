package com.cms.util;

import com.cms.entity.User;
import jdk.nashorn.internal.runtime.GlobalConstants;
import org.apache.commons.lang3.ArrayUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 登录处理器
 * Created by yuheng on 2015/10/19.
 */
public class LoginProcessor {

    private static final String USER = "user";
    /**
     *
     * @param usr
     * @param res
     */
    //设置自动登录COOKIE. 参数usr为null表示清除cookie
    @Deprecated
    public static void setAutoLoginCookie(User usr, HttpServletResponse res) {
        int age = 0;
        String val = "";
        if(usr != null) {
            age = 365 * 24 * 60 * 60;
            val = makeCookieUserStr(usr);
        }
        CookieUtils.setCookie(res, USER, val, age);
    }


    /**
     * user  cookie
     * @param user
     * @return
     */
    public static String makeCookieUserStr(User user) {
        String value = "";
        if (user != null) {
            String cookieContent = user.getUserId() + GlobalConstants.COOKIE_SEP
                    + new Date().getTime();
            if (user != null) {
                value = CryptUtil.encrypt(cookieContent, true);
            }
        }
        return value;
    }


}
