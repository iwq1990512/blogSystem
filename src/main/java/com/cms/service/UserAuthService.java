package com.cms.service;

import com.cms.entity.UserAuthToken;

import java.util.Map;

/**
 * Created by yuheng on 2015/10/19.
 */
public interface UserAuthService {
    /**
     * 三方认证登录, 这个接口只允许第三方登录用户使用, 调用方需要通过其他接口获取三方用户的uid
     *
     * @param uid
     * @param params
     *            额外信息, mac, deviceId, source
     * @throws ServiceException
     *             查找不到用户时
     */
    public UserAuthToken loginThirdParty(long uid, Map<String, Object> params) throws ServiceException;
}
