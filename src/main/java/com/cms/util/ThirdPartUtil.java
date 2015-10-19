package com.cms.util;

/**
 * 第三方相关工具
 * Created by yuheng on 2015/9/23.
 */
public class ThirdPartUtil {

    public static final String SOURCE_QQ = "QQ"; //腾讯qq
    public static final String SOURCE_SINA_WEIBO = "weibo"; //新浪微博

    //qq相关
    public static final String QQ_AUTH_URL_KEY = "qq.auth.url";
    public static final String QQ_AUTH_TOKEN_URL_KEY = "qq.token.url";
    public static final String QQ_AUTH_OPENID_URL_KEY = "qq.openid.url";
    public static final String QQ_AUTH_USER_URL_KEY = "qq.userapi.url";

    //sina weibo 相关
    public static final String SINA_AUTH_APP_KEY = "sina.weibo.appKey";
    public static final String SINA_AUTH_SECRET_KEY = "sina.weibo.appSecret";
    public static final String SINA_AUTH_SCOPE_KEY = "friendships_groups_write"; //好友分组写入接口组

    //第三方登录callback
    public static final String THIRD_PART_CALLBACK_URL_KEY = "thdlogin.callback";
}
