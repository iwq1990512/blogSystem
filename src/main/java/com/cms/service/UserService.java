package com.cms.service;

import com.cms.entity.User;

/**
 * Created by yuheng on 2015/10/18.
 */
public interface UserService {

    /**
     * 添加
     * @param user
     * @return
     */
    public User insert(User user);

    /**
     * 修改
     * @param user
     */
    public void update(User user);

    /**
     * 获取三方登录user信息
     * @param openId
     * @param sourceType
     * @return
     */
    public User getUserBySourceSys(String openId, int sourceType);

    public User getUserByUid(long uid);
}
