package com.cms.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yuheng on 2015/10/19.
 */
public class CookieUtils {

    public static void setCookie(HttpServletResponse res, String name, String val, int age) {
        Cookie cookie = new Cookie(name, val);
        cookie.setMaxAge(age);
        cookie.setPath("/");
        res.addCookie(cookie);
    }

}
