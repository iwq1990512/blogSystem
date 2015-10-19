package com.cms.tool.thirdpart;

/**
 * 第三方系统访问配置
 */
public interface IAccessConfig {
    /**
     * 在第三方系统中的应用ID
     * @return
     */
    public String getAppid();
    /**
     * 在第三方系统中的应用key
     * @return
     */
    public String getAppkey();
    /**
     * 在第三方系统中的secret key
     * @return
     */
    public String getSecretkey();
    /**
     * 第三方系统的认证url
     * @return
     */
    public String getAuthUrl();
    /**
     * 第三方系统的用户Api url
     * @return
     */
    public String getUserApiUrl();
    /**
     * 第三方系统的数据Api url
     * @return
     */
    public String getDataApiUrl();

    /**
     * 回调地址
     * @param encode
     * @return
     */
    public String getCallbackUrl(boolean encode);
}
