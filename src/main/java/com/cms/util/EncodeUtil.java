package com.cms.util;

import org.apache.commons.httpclient.util.URIUtil;
/**
 * Created by yuheng on 2015/10/19.
 */


public class EncodeUtil {
    public EncodeUtil() {
    }

    public static String encodeURL(String url) {
        return encodeURL(url, "UTF-8");
    }

    public static String encodeURL(String url, String encoding) {
        try {
            return URIUtil.encodeWithinQuery(url, encoding);
        } catch (Exception var3) {
            throw new RuntimeException(var3);
        }
    }

    public static String decodeURL(String url) {
        return decodeURL(url, "UTF-8");
    }

    public static String decodeURL(String url, String encoding) {
        try {
            return URIUtil.decode(url, encoding);
        } catch (Exception var3) {
            throw new RuntimeException(var3);
        }
    }
}
