package com.cms.util;

import org.apache.commons.lang3.ArrayUtils;

import java.security.MessageDigest;

/**
 * Created by yuheng on 2015/10/19.
 */
public class CryptUtil {
    public static final byte[] CYPHER_SPICE_DEFAULT = TextUtil.toAsciiBytes("e65b79e@4#d$2%9^0&f*b8ac3f?&)$%(*@!~!");

    public CryptUtil() {
    }

    public static String encrypt(String origOrEncoded, boolean encrypt) {
        byte spice_len = 5;
        byte[] bs2;
        byte[] spice1;
        if(encrypt) {
            String bs1 = String.valueOf(System.currentTimeMillis());
            bs1 = bs1.substring(bs1.length() - spice_len);
            spice1 = TextUtil.toBytes(bs1);
            bs2 = cypher(TextUtil.toBytes(origOrEncoded), spice1);
            bs2 = ArrayUtils.addAll(bs2, spice1);
            bs2 = cypher(bs2, CYPHER_SPICE_DEFAULT);
            return EncodeUtil.encodeURL(Util.encode(bs2));
        } else {
            byte[] bs = Util.decode(EncodeUtil.decodeURL(origOrEncoded));
            if(bs.length <= spice_len) {
                return null;
            } else {
                bs = cypher(bs, CYPHER_SPICE_DEFAULT);
                Object spice = null;
                System.arraycopy(bs, bs.length - spice_len, spice1 = new byte[spice_len], 0, spice_len);
                bs2 = new byte[bs.length - spice_len];
                System.arraycopy(bs, 0, bs2, 0, bs2.length);
                return TextUtil.toString(cypher(bs2, spice1));
            }
        }
    }

    public static byte[] cypher(byte[] bs, int len, String spice) {
        byte[] sps = spice == null?CYPHER_SPICE_DEFAULT:TextUtil.toAsciiBytes(spice);
        return cypher(bs, len, sps);
    }

    public static byte[] cypher(byte[] bs, int len, byte[] spice) {
        if(spice == null) {
            spice = CYPHER_SPICE_DEFAULT;
        }

        len = Math.min(len < 0?bs.length:len, bs.length);
        int i = 0;

        for(int j = 0; i < len; ++j) {
            if(j >= spice.length) {
                j = 0;
            }

            bs[i] ^= spice[j];
            ++i;
        }

        return bs;
    }

    public static byte[] cypher(byte[] bs, String spice) {
        return cypher(bs, bs.length, spice);
    }

    public static byte[] cypher(byte[] bs, byte[] spice) {
        return cypher(bs, bs.length, spice);
    }

    public static String toMD5(String str) {
        if(str == null) {
            return null;
        } else {
            try {
                MessageDigest e = MessageDigest.getInstance("MD5");
                byte[] bs = e.digest(str.getBytes("utf-8"));
                StringBuffer sb = new StringBuffer(bs.length * 2);

                for(int i = 0; i < bs.length; ++i) {
                    sb.append(Integer.toHexString((bs[i] & 240) >> 4));
                    sb.append(Integer.toHexString(bs[i] & 15));
                }

                return sb.toString();
            } catch (Exception var5) {
                return str;
            }
        }
    }

    public static String sha1(String str) {
        if(str == null) {
            return null;
        } else {
            try {
                MessageDigest e = MessageDigest.getInstance("SHA-1");
                byte[] bs = e.digest(str.getBytes("utf-8"));
                StringBuffer sb = new StringBuffer(bs.length * 2);

                for(int i = 0; i < bs.length; ++i) {
                    sb.append(Integer.toHexString((bs[i] & 240) >> 4));
                    sb.append(Integer.toHexString(bs[i] & 15));
                }

                return sb.toString();
            } catch (Exception var5) {
                return str;
            }
        }
    }
}

