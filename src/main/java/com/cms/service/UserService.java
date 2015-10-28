package com.cms.service;

import com.cms.dao.UserDao;
import com.cms.entity.User;
import com.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by yuheng on 2015/10/18.
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    /**
     * 添加
     * @param user
     * @return
     */
    public User insert(User user){
        user.setCreateTime(new Date());
        userDao.add(user);
        return userDao.getUserById(user.getUserId());
    }

    /**
     * 修改
     * @param user
     */
    public void update(User user){
        userDao.update(user);
    }

    /**
     * 获取三方登录user信息
     * @param openId
     * @param sourceType
     * @return
     */
    public User getUserBySourceSys(String openId, int sourceType){
        return userDao.getUserBySourceSys(openId, sourceType);
    }

    public User getUserByUid(long uid) {
        return userDao.getUserById(uid);
    }
}
