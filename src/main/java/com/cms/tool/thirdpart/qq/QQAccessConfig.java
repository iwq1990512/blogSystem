package com.cms.tool.thirdpart.qq;

import com.cms.tool.thirdpart.BaseAccessConfig;
import com.cms.util.PropertyUtils;
import com.cms.util.ThirdPartUtil;

import java.util.Map;

/**
 * Created by yuheng on 2015/10/15.
 */
public class QQAccessConfig extends BaseAccessConfig {
    public QQAccessConfig(Map<String, String> params) {
        super(params);
    }
    @Override
    public String getAuthUrl() {
        StringBuilder urlBase = new StringBuilder(PropertyUtils.getValue(ThirdPartUtil.QQ_AUTH_URL_KEY)).append(getCallbackUrl(true));
        return urlBase.toString();
    }
}
