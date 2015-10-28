package com.cms.tool.thirdpart;

import com.cms.util.PropertyUtils;
import com.cms.util.ThirdPartUtil;
import javafx.application.Application;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 第三方系统访问配置
 */
public class BaseAccessConfig implements IAccessConfig {
    protected Map<String, String> params;

    public BaseAccessConfig(Map<String, String> params) {
        this.params = params;
    }
    @Override
    public String getAppid() {
        return null;
    }

    @Override
    public String getAppkey() {
        return null;
    }

    @Override
    public String getSecretkey() {
        return null;
    }

    @Override
    public String getAuthUrl() {
        return null;
    }

    @Override
    public String getUserApiUrl() {
        return null;
    }

    @Override
    public String getDataApiUrl() {
        return null;
    }

    @Override
    public String getCallbackUrl(boolean encode) {
        String cburl = PropertyUtils.getValue(ThirdPartUtil.THIRD_PART_CALLBACK_URL_KEY);
        Map<String,String> m = new HashMap<String, String>(params);
        String source = m.get("source");
        String cb = cburl.replace("#s", m.remove("source"));
        String key = null;
        String value = null;
        StringBuilder urlsb = new StringBuilder(cb);
        for(Map.Entry<String, String> e : m.entrySet()) {
            key = e.getKey();
            value = e.getValue();
            if(StringUtils.isBlank(key) || StringUtils.isBlank(value)) {
            }else {
                urlsb.append("&").append(key).append("=").append(value);
            }
        }
        try {
            return URLEncoder.encode(urlsb.toString(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return urlsb.toString();
        }
    }
}
