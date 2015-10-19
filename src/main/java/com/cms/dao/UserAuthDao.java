package com.cms.dao;

import com.cms.entity.UserAuthTokenRecord;
import org.springframework.stereotype.Repository;

/**
 * Created by yuheng on 2015/10/19.
 */
@Repository
public interface UserAuthDao {

    void insert(UserAuthTokenRecord userAuthTokenRecord);
}
