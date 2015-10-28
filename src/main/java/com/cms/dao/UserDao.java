package com.cms.dao;

import com.cms.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by yuheng on 2015/10/18.
 */
@Repository
public interface UserDao {

    /**
     * 添加用户
     * @param user
     * @return
     */
    public int add(User user);

    /**
     * 删除
     * @param user
     * @return
     */
    public int delete(User user);

    /**
     * 通过id获取用户
     * @param userId
     * @return
     */
    public User getUserById(@Param("userId")long userId);

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
    public User getUserBySourceSys(@Param("openId")String openId, @Param("sourceType")int sourceType);
}
