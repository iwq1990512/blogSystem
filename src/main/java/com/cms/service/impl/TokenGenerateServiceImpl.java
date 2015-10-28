package com.cms.service.impl;

import com.cms.entity.UserAuthToken;
import com.cms.service.TokenGenerateService;
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
@Service("tokenGenerateService")
public class TokenGenerateServiceImpl implements TokenGenerateService{

    private ReentrantLock lock = new ReentrantLock();
    private AtomicLong resetCounter = new AtomicLong(0);
    private HashFunction sha1 = Hashing.sha1();
    private static long TOKEN_DURATION = 60L * 86400L * 1000L;
    private static long REFRESH_TOKEN_DURATION = 2 * 60L * 86400L * 1000L;

    private int resetThreshold = 10000;
    private Charset encoding = Charset.forName("UTF-8");

    private final Logger logger = Logger.getLogger(TokenGenerateServiceImpl.class);

    private SecureRandom random;

    public TokenGenerateServiceImpl() {
        this.resetSecureRandom();
    }
    /**
     * 重新生成token
     *
     * @return
     */
    public UserAuthToken generateToken() {
        UserAuthToken result = new UserAuthToken();
        result.token = this.getToken();
        result.tokenExpire = new Date(System.currentTimeMillis() + TOKEN_DURATION);
        result.refreshToken = this.getToken();
        result.refreshTokenExpire = new Date(System.currentTimeMillis() + REFRESH_TOKEN_DURATION);
        return result;
    }

    private void resetSecureRandom() {
        this.lock.lock();
        try {
            this.random = SecureRandom.getInstance("NativePRNGNonBlocking");
//            this.random = SecureRandom.getInstanceStrong(); // for windows
            this.random.generateSeed(32);
        } catch (NoSuchAlgorithmException e) {
            this.logger.error("can not find algorithm to generate token", e);
        } finally {
            this.lock.unlock();
        }
    }

    public String getToken() {
        if (this.resetCounter.incrementAndGet() > this.resetThreshold) {
            this.resetSecureRandom();
            this.resetCounter.set(0);
        }
        byte[] bytes = new byte[32];
        this.random.nextBytes(bytes);
        byte[] seedBytes = UUID.randomUUID().toString().getBytes(this.encoding);
        byte[] seeds = new byte[seedBytes.length + bytes.length];
        System.arraycopy(bytes, 0, seeds, 0, bytes.length);
        System.arraycopy(seedBytes, 0, seeds, bytes.length - 1, seedBytes.length);
        return this.sha1.hashBytes(bytes).toString();
    }
}
