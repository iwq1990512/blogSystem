package com.cms.service.impl;

import com.cms.dao.UserAuthDao;
import com.cms.entity.UserAuthToken;
import com.cms.entity.UserAuthTokenRecord;
import com.cms.exception.AuthException;
import com.cms.service.UserAuthService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

/**
 * Created by yuheng on 2015/10/19.
 */
@Service("userAuthService")
public class UserAuthServiceImpl implements UserAuthService{
    private final Logger logger = Logger.getLogger(UserAuthServiceImpl.class);

    @Autowired
    private TokenGenerateServiceImpl tokenGenerateService;
    @Autowired
    private UserAuthDao userAuthDao;

    @Override
    public UserAuthToken loginThirdPart(long uid) throws AuthException {
        UserAuthToken userAuthToken = new UserAuthToken();
        userAuthToken = this.tokenGenerateService.generateToken();
        this.insertToken(uid, userAuthToken);
        this.logger.info("login with third:{}, userAuthToken: {}" + uid + userAuthToken);
        return userAuthToken;
    }

    @Override
    public Long checkAuth(String token) {
        return null;
    }

    /**
     * 保存token到数据库
     *
     * @param uid
     * @param token
     *            可选选项, 会保存入库
     * @return
     */
    private void insertToken(long uid, UserAuthToken token) {
        Calendar cal = Calendar.getInstance();
        UserAuthTokenRecord record = new UserAuthTokenRecord();
        record.createTime = cal.getTime();
        record.modifiedTime = cal.getTime();
        record.isDelete = 0;
        record.refreshToken = token.refreshToken;
        record.refreshTokenExpire = token.refreshTokenExpire;
        record.token = token.token;
        record.tokenExpire = token.tokenExpire;
        record.uid = uid;
        this.userAuthDao.insert(record);

    }

}
