package com.cms.entity;

import java.util.Date;

/**
 * 数据库model
 *
 * @author
 */
public class UserAuthTokenRecord {
    public Long id;
    public Long uid;
    public String token;
    public Date tokenExpire;
    public String refreshToken;
    public Date refreshTokenExpire;
    public int isDelete;
    public Date createTime;
    public Date modifiedTime;

    @Override
    public String toString() {
        return "UserAuthTokenRecord [id=" + this.id + ", uid=" + this.uid + ", token=" + this.token + ", tokenExpire="
                + this.tokenExpire + ", refreshToken=" + this.refreshToken + ", refreshTokenExpire="
                + this.refreshTokenExpire + ", isDelete=" + this.isDelete + ", createTime=" + this.createTime + ", modifiedTime="
                + this.modifiedTime + "]";
    }

}
