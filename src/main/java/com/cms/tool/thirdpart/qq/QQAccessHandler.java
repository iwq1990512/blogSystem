package com.cms.tool.thirdpart.qq;

import com.cms.constant.SourceType;
import com.cms.entity.User;
import com.cms.tool.thirdpart.BaseAccessConfig;
import com.cms.tool.thirdpart.BaseAccessHandler;
import com.cms.util.HttpClientUtil;
import com.cms.util.PropertyUtils;
import com.cms.util.ThirdPartUtil;
import com.cms.util.Util;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

import java.util.Map;
import java.util.Random;

/**
 * Created by yuheng on 2015/10/15.
 */
public class QQAccessHandler extends BaseAccessHandler{
    private static final Logger log = Logger.getLogger(QQAccessHandler.class);
    private String code;
    private String access_token;
    private String refresh_token;
    private long expires_in;
    private BaseAccessConfig accessConfig;
    public QQAccessHandler(String code, BaseAccessConfig accessConfig) {
        this.code = code;
        this.accessConfig = accessConfig;
    }
    @Override
    public void handleAuth() {
        String tokenUrl = new StringBuilder(PropertyUtils.getValue(ThirdPartUtil.QQ_AUTH_TOKEN_URL_KEY))
                .append(accessConfig.getCallbackUrl(true)).toString();
        tokenUrl = tokenUrl.replace("#code", code);
        String res = HttpClientUtil.sendGet(tokenUrl, 10000, 10000);
        log.info(String.format("QQ第三方登录获取accesstoken url[%s] res[%s]", tokenUrl, res));
        Map<String, String> map = Util.buildMap(res, '&', '=');
        access_token = map.get("access_token");
        refresh_token = map.get("refresh_token");
        expires_in = Long.parseLong(map.get("expires_in"));
    }

    @Override
    public User handleUser() throws Exception {
        JSONObject jsonUser = null;
        String openid = null;
        String openidUrl = PropertyUtils.getValue(ThirdPartUtil.QQ_AUTH_OPENID_URL_KEY);
        openidUrl = openidUrl.replace("#token", access_token);
        String res = HttpClientUtil.sendGet(openidUrl, 10000, 10000); // qq坑爹的返回值callback( {"client_id":"100245713","openid":"4D27CFEAEA0C9CDA6B2F063CA54FADBF"}
        log.info(String.format("QQ第三方登录获取openid url[%s] res[%s]", openidUrl, res));
        try {
            int f = res.indexOf("(");
            int l = res.lastIndexOf(")");
            JSONObject json = JSONObject.fromObject(res.substring(f + 1, l));
            openid = json.getString("openid");
        } catch (Exception e) {
            log.error(String.format("QQ第三方登录解析res[%s]获取openid错误:", res), e);
            throw e;
        }
        //获取userinfo
        String userInfoUrl = PropertyUtils.getValue(ThirdPartUtil.QQ_AUTH_USER_URL_KEY);
        userInfoUrl = userInfoUrl.replace("#token", access_token);
        userInfoUrl = userInfoUrl.replace("#openid", openid);
        String userRes = HttpClientUtil.sendGet(userInfoUrl, 10000, 10000);
        log.info(String.format("QQ第三方登录获取userinfo url[%s] res[%s]", userInfoUrl, userRes));
        try {
            jsonUser = JSONObject.fromObject(userRes);
        } catch(Exception e) {
            log.error(String.format("QQ第三方登录解析res[%s]获取userinfo错误：", res), e);
            throw e;
        }

        try {
            return getUser(jsonUser, openid);
        }catch(Exception e) {
            log.error("QQ第三方登录，构造user异常：", e);
            return null;
        }
    }

    @Override
    public void handleData() {
        super.handleData();
    }

    /**
     * 解析qq返回json，构造帐号
     *
     {
     "ret": 0,
     "msg": "",
     "is_lost":0,
     "nickname": "Mloむscorpio",
     "gender": "男",
     "province": "湖北",
     "city": "武汉",
     "year": "1989",
     "figureurl": "http:\/\/qzapp.qlogo.cn\/qzapp\/100245713\/4D27CFEAEA0C9CDA6B2F063CA54FADBF\/30",
     "figureurl_1": "http:\/\/qzapp.qlogo.cn\/qzapp\/100245713\/4D27CFEAEA0C9CDA6B2F063CA54FADBF\/50",
     "figureurl_2": "http:\/\/qzapp.qlogo.cn\/qzapp\/100245713\/4D27CFEAEA0C9CDA6B2F063CA54FADBF\/100",
     "figureurl_qq_1": "http:\/\/q.qlogo.cn\/qqapp\/100245713\/4D27CFEAEA0C9CDA6B2F063CA54FADBF\/40",
     "figureurl_qq_2": "http:\/\/q.qlogo.cn\/qqapp\/100245713\/4D27CFEAEA0C9CDA6B2F063CA54FADBF\/100",
     "is_yellow_vip": "0",
     "vip": "0",
     "yellow_vip_level": "0",
     "level": "0",
     "is_yellow_year_vip": "0"
     }
     * @param jso
     * @param openId
     * @return
     */
    private User getUser(JSONObject jso, String openId) {
        User user = new User();
        String nick = jso.getString("nickname");
        if (StringUtils.isBlank(nick) || "null".equals(nick)) {
            // 最大长度建议在13位以内
            nick = String.valueOf(new Random().nextInt(Integer.MAX_VALUE));
        }
        user.setName(nick);
        user.setOpenId(openId);
        user.setReExpiresIn(expires_in);
        user.setRefreshToken(refresh_token);
        user.setSourceType(SourceType.SOURCE_QQ);
        user.setToken(access_token);
        return user;
    }
}
