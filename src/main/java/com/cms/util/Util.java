package com.cms.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.util.*;

/**
 * Created by yuheng on 2015/10/18.
 */
public class Util {

    public static Map buildMap(Object... keyVals) {
        return buildMap((Map)null, keyVals);
    }

    public static Map buildMap(Map holder, Object... keyVals) {
        if(keyVals.length % 2 != 0) {
            throw new RuntimeException("参数为key1,val1,key2,val2...的形式,并且必须是偶数个参数!");
        } else {
            if(holder == null) {
                holder = new HashMap();
            }

            int i = 0;

            for(int n = keyVals.length / 2; i < n; ++i) {
                int k = i << 1;
                ((Map)holder).put(keyVals[k], keyVals[k + 1]);
            }

            return (Map)holder;
        }
    }

    public static Map<String, String> buildMap(String str) {
        return buildMap(str, ',', ':');
    }

    public static Map<String, String> buildMap(String str, char valsSep, char kvSep) {
        return buildMap(str, valsSep, kvSep, false);
    }

    public static Map<String, String> buildMap(String str, char valsSep, char kvSep, boolean allowKeyWithNokvSep) {
        if(str != null && str.length() != 0) {
            ArrayList items = new ArrayList();
            char[] m = str.toCharArray();
            StringBuffer sb = new StringBuffer();

            for(int i = 0; i < m.length; ++i) {
                if(valsSep == m[i]) {
                    if(i == 0) {
                        continue;
                    }

                    if(m[i - 1] != 92) {
                        items.add(sb.toString());
                        sb.setLength(0);
                        continue;
                    }

                    sb.setLength(sb.length() - 1);
                }

                sb.append(m[i]);
            }

            if(sb.length() > 0) {
                items.add(sb.toString());
            }

            LinkedHashMap var9 = new LinkedHashMap(items.size());
            Iterator var10 = items.iterator();

            while(var10.hasNext()) {
                String var11 = (String)var10.next();
                int p = var11.indexOf(kvSep);
                if(p > 0) {
                    var9.put(var11.substring(0, p), var11.substring(p + 1));
                } else if(allowKeyWithNokvSep) {
                    var9.put(var11, (Object)null);
                }
            }

            return var9;
        } else {
            return null;
        }
    }

    public static String encode(byte[] bytes) {
        return (new BASE64Encoder()).encode(bytes);
    }

    public static byte[] decode(String base64Str) {
        try {
            return (new BASE64Decoder()).decodeBuffer(base64Str);
        } catch (IOException var2) {
            throw new RuntimeException(var2);
        }
    }
}
