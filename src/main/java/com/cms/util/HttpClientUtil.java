package com.cms.util;

import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * http访问工具类
 * Created by yuheng.
 */
public class HttpClientUtil {
    private static final Logger log = Logger.getLogger(HttpClientUtil.class);
    private static PoolingHttpClientConnectionManager connManager = null;
    private static CloseableHttpClient httpclient = null;
    static {
        connManager = new PoolingHttpClientConnectionManager();
        httpclient = HttpClients.custom().setConnectionManager(connManager).build();
        // Create socket configuration
        SocketConfig socketConfig = SocketConfig.custom().setTcpNoDelay(true).build();
        connManager.setDefaultSocketConfig(socketConfig);
        connManager.setMaxTotal(200);
        connManager.setDefaultMaxPerRoute(20);
    }

    /**
     * 发送post请求，如果params为空，则以get方式发送请求
     * @param url
     * @param params
     * @param connectTimeout
     * @param soTimeout
     * @return
     */
    public static String sendPost(String url, Map<String, String> params,
                                  int connectTimeout, int soTimeout) {
        if(null == params || params.isEmpty()) {
            return sendGet(url, connectTimeout, soTimeout);
        }
        String responseString = null;
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(soTimeout)
                .setConnectTimeout(connectTimeout)
                .setConnectionRequestTimeout(connectTimeout).build();
        HttpPost post = new HttpPost(url);
        post.setConfig(requestConfig);
        try {
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            for(Map.Entry<String, String> e : params.entrySet()) {
                if(StringUtils.isBlank(e.getKey()) || StringUtils.isBlank(e.getValue())) {

                }else {
                    nameValuePairs.add(new BasicNameValuePair(e.getKey(), e.getValue()));
                }
            }
            post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            CloseableHttpResponse response = httpclient.execute(post);
            try {
                HttpEntity entity = response.getEntity();
                if(entity != null) {
                    responseString = EntityUtils.toString(entity, "UTF-8");
                }
            }catch(Exception e) {
                e.printStackTrace();
            }finally {
                response.close();
            }
        }catch(Exception e) {
            log.error(new StringBuilder("访问url[POST][").append(url).append("][")
                    .append(JSONObject.fromObject(params).toString()).append("] connecttimeout[")
            .append(connectTimeout).append("] socketTimeout[").append(soTimeout).append("]").toString(), e);
        }finally {
            post.releaseConnection();
        }
        return responseString;
    }

    /**
     * 发送get请求
     * @param url
     * @param connectTimeout
     * @param soTimeout
     * @return
     */
    public static String sendGet(String url, int connectTimeout, int soTimeout) {
        String responseString = null;
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(soTimeout)
                .setConnectTimeout(connectTimeout)
                .setConnectionRequestTimeout(connectTimeout).build();
        HttpGet get = new HttpGet(url);
        get.setConfig(requestConfig);
        try {
            CloseableHttpResponse response = httpclient.execute(get);
            try {
                HttpEntity entity = response.getEntity();
                if(entity != null) {
                    responseString = EntityUtils.toString(entity, "UTF-8");
                }
            }catch(Exception e) {
                e.printStackTrace();
            }finally {
                response.close();
            }
        }catch(SocketTimeoutException e) {
            log.error(new StringBuilder("访问url[GET][").append(url).append("] connetTimeout[").append(connectTimeout + "").append("] soTimeout[").append(soTimeout + "").append("]").toString(), e);
            //e.printStackTrace();
        }catch(Exception e) {
            log.error(new StringBuilder("访问url[GET][").append(url).append("] connetTimeout[").append(connectTimeout + "").append("] soTimeout[").append(soTimeout + "").append("]").toString(), e);
            //e.printStackTrace();
        }finally{
            get.releaseConnection();
        }
        return responseString;
    }

    /**
     * 获取request的完整url
     * @param request
     * @return
     */
    public static String findToUrl(HttpServletRequest request) {
        StringBuffer requestUrl = request.getRequestURL();
        String queryString = request.getQueryString();
        if(StringUtils.isNotBlank(queryString)) {
            requestUrl.append("?").append(queryString);
        }
        try {
            return URLEncoder.encode(requestUrl.toString(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    /**
     * 获取request的目标地址url，拼接上relogin参数
     * @param request
     * @return
     */
    public static String findToUrlRelogin(HttpServletRequest request) {
        String reloginParName = "token_failure=1";
        StringBuffer requestUrl = request.getRequestURL();
        String queryString = request.getQueryString();
        if(queryString.contains(reloginParName)) {
            requestUrl.append("?").append(queryString);
        }else {
            if (StringUtils.isNotBlank(queryString)) {
                requestUrl.append("?").append(queryString).append("&").append(reloginParName);
            } else {
                requestUrl.append("?").append(reloginParName);
            }
        }
        return requestUrl.toString();
    }

    /**
     * 是否包含relogin参数
     * @param request
     * @return
     */
    public static boolean contantsRelogin(HttpServletRequest request) {
        String reloginParName = "relogin";
        Map<String, String[]> parMap = request.getParameterMap();
        if(parMap.containsKey(reloginParName)) {
            return true;
        }else {
            return false;
        }
    }

    /**
     * 获取ip地址
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }

    public static void main(String[] args) {
        /*int CONNECT_TIME = 1000; // 毫秒
        int SO_TIME = 2000; // 毫秒
        String res = HttpClientUtil.sendGet("http://www.baidu.com", CONNECT_TIME, SO_TIME);
        System.out.println(res);
        */
        Map<String, String> params = new HashMap<String, String>();
        params.put("hekko", "geksong");
        String res = HttpClientUtil.sendPost("http://www.wacai.com", params, 10000, 10000);
        System.out.println(res);
    }
}
