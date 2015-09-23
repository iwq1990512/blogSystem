/*
 *	Copyright © 2013 Changsha Shishuo Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.shishuo.com
 */

package com.cms.entity;

import java.util.Date;


/**
 * 用户实体
 * 
 * @author yuheng
 * 
 */

public class User {

	/**
	 * 用户ID
	 */
	private long userId;

	/**
	 * 公共用户ID，只有是QQ，微博等其它网站登录时才有sourceAccount
	 */
	private long openId;

	/**
	 * 帐号类型：1：本站 2：QQ 3：微博
	 */
	private int sourceType;

	/**
	 * 用户名
	 */
	private String name;

	/**
	 * 时间
	 */
	private Date createTime;

	/**
	 * 	三方登录token
	 */
	private String token;

	/**
	 * 三方登录refreshToken
	 */
	private String refreshToken;

	/**
	 * token 超时时间
	 */
	private int reExpiresIn;

	/**
	 * refreshToken 超时时间
	 */
	private int refreshExpiredTime;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getOpenId() {
		return openId;
	}

	public void setOpenId(long openId) {
		this.openId = openId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getSourceType() {
		return sourceType;
	}

	public void setSourceType(int sourceType) {
		this.sourceType = sourceType;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public int getReExpiresIn() {
		return reExpiresIn;
	}

	public void setReExpiresIn(int reExpiresIn) {
		this.reExpiresIn = reExpiresIn;
	}

	public int getRefreshExpiredTime() {
		return refreshExpiredTime;
	}

	public void setRefreshExpiredTime(int refreshExpiredTime) {
		this.refreshExpiredTime = refreshExpiredTime;
	}
}
