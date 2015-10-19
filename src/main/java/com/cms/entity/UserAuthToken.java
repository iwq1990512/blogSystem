package com.cms.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * token����
 *
 * @author yuheng
 */
public class UserAuthToken implements Serializable {
    private static final long serialVersionUID = -8866572769692990091L;
    public String token;
    public Date tokenExpire; // ��λ����
    public String refreshToken;
    public Date refreshTokenExpire; // ��λ����

    @Override
    public String toString() {
        return "UserAuthToken [token=" + this.token + ", tokenExpire=" + this.tokenExpire
                + ", refreshToken=" + this.refreshToken + ", refreshTokenExpire=" + this.refreshTokenExpire + "]";
    }

}
