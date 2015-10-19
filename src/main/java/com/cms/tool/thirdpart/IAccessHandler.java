package com.cms.tool.thirdpart;


import com.cms.entity.User;

/**
 * Created by liubianshi on 15-5-22.
 */
public interface IAccessHandler {
    /**
     * 处理授权
     */
    void handleAuth();
    /**
     * 处理来自第三方系统的用户.获取第三方系统中的用户数据,完成本地注册、映射功能.
     */
    User handleUser()  throws Exception;
    /**
     * 处理来自第三方系统的数据.
     */
    void handleData();
}
