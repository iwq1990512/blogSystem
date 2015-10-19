package com.cms.service;

import com.cms.entity.UserAuthToken;
import com.cms.exception.AuthException;

import java.util.Map;

/**
 * Created by yuheng on 2015/10/19.
 */
public interface UserAuthService {
    /**
     * 三方认证登录, 这个接口只允许第三方登录用户使用, 调用方需要通过其他接口获取三方用户的uid
     *
     * @param uid
     * @throws AuthException
     *             查找不到用户时
     */
    public UserAuthToken loginThirdPart(long uid) throws AuthException;

    /**
     * 检测token是否合法, 如果合法, 返回uid
     *
     * @param token
     * @return
     * @throws AuthException
     *             token无效或过期
     */
    public Long checkAuth(String token);

}
