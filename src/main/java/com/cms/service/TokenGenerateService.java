package com.cms.service;

import com.cms.entity.UserAuthToken;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

/**
 * token生成服务
 * @author yuheng
 */
@Service
public interface TokenGenerateService {
    /**
     * 重新生成token
     *
     * @return
     */
    public UserAuthToken generateToken() ;


    public String getToken();
}
