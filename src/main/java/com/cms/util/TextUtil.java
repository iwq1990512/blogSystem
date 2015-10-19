package com.cms.util;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.*;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;
import java.util.Vector;
import org.apache.commons.lang3.StringUtils;

public class TextUtil {
    public static final String UTF8_BOM = toString(new byte[]{(byte)-17, (byte)-69, (byte)-65});
    public static final String HTML_SPACE = "&nbsp;";
    private static final String STRING_WHEN_NULL = new String(new char[]{'\u0001', '\u0002', '\u0003', '\u0004', '\u0005'});
    private static final String[] XML_ESCAPE_FROM = new String[]{"&", "<", ">", "\"", "\'"};
    private static final String[] XML_ESCAPE_TO = new String[]{"&amp;", "&lt;", "&gt;", "&quot;", "&apos;"};

    public TextUtil() {
    }

    public static String toJsonString(Map<String, String> map) {
        if(map != null && !map.isEmpty()) {
            String result = "{";

            String key;
            for(Iterator var3 = map.keySet().iterator(); var3.hasNext(); result = result + "\"" + (String)map.get(key) + "\",") {
                key = (String)var3.next();
                result = result + "\"" + key + "\":";
            }

            result = result.substring(0, result.length() - 1) + "}";
            return result;
        } else {
            return "{}";
        }
    }

    public static byte[] toAsciiBytes(String s) {
        return toBytes(s, "us-ascii");
    }

    public static byte[] toBytes(String s) {
        return toBytes(s, (String)null);
    }

    public static byte[] toBytes(String s, String charset) {
        if(charset == null) {
            charset = "UTF-8";
        }

        try {
            return s.getBytes(charset);
        } catch (UnsupportedEncodingException var3) {
            throw new RuntimeException(var3);
        }
    }

    public static String toString(byte[] bs) {
        return toString(bs, (String)null);
    }

    public static String toString(byte[] bs, String charset) {
        if(charset == null) {
            charset = "UTF-8";
        }

        try {
            return new String(bs, charset);
        } catch (UnsupportedEncodingException var3) {
            throw new RuntimeException(var3);
        }
    }

    public static String substring(String str, int start, int end) {
        if(str == null) {
            return null;
        } else {
            int len = str.length();
            if(start >= len) {
                return "";
            } else {
                end = Math.min(end, len);
                return str.substring(start, end);
            }
        }
    }

    public static String escapseXmlChinese(String xml) {
        if(xml == null) {
            return "";
        } else {
            StringBuffer b = new StringBuffer(512);

            for(int i = 0; i < xml.length(); ++i) {
                char c = xml.charAt(i);
                if(c > 255) {
                    b.append("&#").append(String.valueOf(c)).append(";");
                } else {
                    b.append(c);
                }
            }

            return b.toString();
        }
    }

    public static String replace(String str, String oldStr, String newStr) {
        return replaceMuch(str, new String[]{oldStr}, new String[]{newStr});
    }

    public static String removeXmlInvalid(String xml) {
        if(xml == null) {
            return xml;
        } else {
            char[] cs = xml.toCharArray();

            for(int i = 0; i < cs.length; ++i) {
                char c = cs[i];
                if(c >= 0 && c <= 8 || c == 11 || c == 12 || c >= 14 && c <= 31) {
                    cs[i] = 32;
                }
            }

            return new String(cs);
        }
    }

    public static String escapeXmlStr(String xml) {
        return replaceMuch(xml, XML_ESCAPE_FROM, XML_ESCAPE_TO);
    }

    public static String unescapeXmlStr(String xml) {
        return replaceMuch(xml, XML_ESCAPE_TO, XML_ESCAPE_FROM);
    }

    public static String toHTMLString(String standardStr, boolean relNewline, boolean relSpace) {
        if(StringUtils.isEmpty(standardStr)) {
            return "&nbsp;";
        } else {
            ArrayList olds = new ArrayList(Arrays.asList(XML_ESCAPE_TO));
            ArrayList news = new ArrayList(Arrays.asList(XML_ESCAPE_FROM));
            if(relNewline) {
                olds.add("\n");
                news.add("<br>");
            }

            if(relSpace) {
                olds.add(" ");
                news.add("&nbsp;");
            }

            return replaceMuch(standardStr, (String[])olds.toArray(new String[olds.size()]), (String[])news.toArray(new String[news.size()]));
        }
    }

    public static String escapeHtmlStr(String standardStr) {
        if(StringUtils.isEmpty(standardStr)) {
            return "&nbsp;";
        } else {
            ArrayList olds = new ArrayList(Arrays.asList(XML_ESCAPE_FROM));
            olds.add("\r\n");
            olds.add("\n");
            olds.add(" ");
            ArrayList news = new ArrayList(Arrays.asList(XML_ESCAPE_TO));
            news.add("<br>");
            news.add("<br>");
            news.add("&nbsp;");
            return replaceMuch(standardStr, (String[])olds.toArray(new String[olds.size()]), (String[])news.toArray(new String[news.size()]));
        }
    }

    public static String unescapeHtmlStr(String htmlStr) {
        if(StringUtils.isEmpty(htmlStr)) {
            return "";
        } else {
            ArrayList olds = new ArrayList(Arrays.asList(XML_ESCAPE_TO));
            olds.add("<br>");
            olds.add("<br/>");
            olds.add("&nbsp;");
            ArrayList news = new ArrayList(Arrays.asList(XML_ESCAPE_FROM));
            news.add("\r\n");
            news.add("\r\n");
            news.add(" ");
            return replaceMuch(htmlStr, (String[])olds.toArray(new String[olds.size()]), (String[])news.toArray(new String[news.size()]));
        }
    }

    public static String toHTMLString(String standardStr) {
        return toHTMLString(standardStr, false, false);
    }

    public static String[] split(String src, String delimit) {
        if(src != null && delimit != null) {
            Vector stList = new Vector();
            int curPos = 0;
            int prePos = 0;
            int delimitLen = delimit.length();

            while(true) {
                curPos = src.indexOf(delimit, curPos);
                if(curPos < 0) {
                    if(prePos <= src.length()) {
                        stList.add(src.substring(prePos, src.length()));
                    }

                    String[] ret = new String[stList.size()];
                    stList.toArray(ret);
                    return ret;
                }

                if(curPos >= prePos) {
                    stList.add(src.substring(prePos, curPos));
                }

                curPos += delimitLen;
                prePos = curPos;
            }
        } else {
            return null;
        }
    }

    public static boolean isDoubleByteChar(char c) {
        return c > 126;
    }

    public static int getDoubleByteCount(String instr) {
        if(instr == null) {
            return 0;
        } else {
            int num = 0;

            for(int i = 0; i < instr.length(); ++i) {
                if(isDoubleByteChar(instr.charAt(i))) {
                    ++num;
                }
            }

            return num;
        }
    }

    public static int getRealLen(String instr) {
        return instr == null?0:instr.length() + getDoubleByteCount(instr);
    }

    public static String format(String src, String old, Object... replacement) {
        if(src != null && old != null && replacement.length != 0) {
            StringBuffer replaced = new StringBuffer(src.length());
            boolean pos = false;
            int prePos = 0;
            int count = 0;

            int var8;
            for(int oldLen = old.length(); (var8 = src.indexOf(old, prePos)) != -1 && count < replacement.length; prePos = var8 + oldLen) {
                replaced.append(src, prePos, var8);
                replaced.append(replacement[count++]);
            }

            replaced.append(src, prePos, src.length());
            return replaced.toString();
        } else {
            return src;
        }
    }

    public static String replaceMuch(String src, String[] olds, String[] news) {
        if(src != null && olds != null && news != null) {
            if(olds.length == news.length && olds.length != 0) {
                int prePos = 0;
                TreeSet sortedPos = new TreeSet();
                StringBuffer replaced = new StringBuffer();

                for(int first = 0; first < olds.length; ++first) {
                    olds[first] = olds[first] == null?STRING_WHEN_NULL:olds[first];
                    news[first] = news[first] == null?"":news[first];
                    sortedPos.add(new TextUtil.PosHelper(src.indexOf(olds[first]), olds[first], news[first]));
                }

                while(true) {
                    TextUtil.PosHelper var7 = popFirstPos(sortedPos);
                    if(var7.pos == 2147483647) {
                        replaced.append(src, prePos, src.length());
                        return replaced.toString();
                    }

                    replaced.append(src, prePos, var7.pos);
                    replaced.append(var7.newStr);
                    prePos = var7.pos + var7.oldStr.length();
                    sortedPos.add(new TextUtil.PosHelper(src.indexOf(var7.oldStr, prePos), var7.oldStr, var7.newStr));
                }
            } else {
                throw new IllegalArgumentException("oldStrs和newStrs长度必须相同, 并且长度至少有1!");
            }
        } else {
            return src;
        }
    }

    private static TextUtil.PosHelper popFirstPos(TreeSet posHelper) {
        TextUtil.PosHelper first = (TextUtil.PosHelper)posHelper.first();
        posHelper.remove(first);
        return first;
    }

    public static String replaceHolder(String str, String holderPrefix, String holderSurfix, Map params) {
        return replaceHolder(str, holderPrefix, holderSurfix, params, false);
    }

    public static String replaceHolder(String str, String holderPrefix, String holderSurfix, final Map params, boolean recursive) {
        return params == null?str:replaceHolder(str, holderPrefix, holderSurfix, new TextUtil.HolderHandler() {
            public String dealHolder(String holder) {
                Object v = params.get(holder);
                return v == null?null:v.toString();
            }
        }, recursive);
    }

    public static String replaceHolder(String str, String holderPrefix, String holderSurfix, TextUtil.HolderHandler handler) {
        return replaceHolder(str, holderPrefix, holderSurfix, handler, false);
    }

    public static String replaceHolder(String str, String holderPrefix, String holderSurfix, TextUtil.HolderHandler handler, boolean recursive) {
        if(str != null && holderPrefix != null && holderSurfix != null) {
            int len_prefix = holderPrefix.length();
            int len_surfix = holderSurfix.length();
            StringBuffer sb = new StringBuffer();
            int prePos = -len_surfix;
            int curPos = prePos;

            while(true) {
                prePos = str.indexOf(holderPrefix, curPos + len_surfix);
                if(prePos == -1) {
                    sb.append(str, curPos + 1, str.length());
                    break;
                }

                if(curPos < prePos) {
                    sb.append(str, curPos + len_surfix, prePos);
                }

                curPos = str.indexOf(holderSurfix, prePos + len_prefix);
                if(curPos == -1 || curPos < prePos + len_prefix) {
                    sb.append(str, prePos, str.length());
                    break;
                }

                String holder = str.substring(prePos + len_prefix, curPos);
                String val = handler.dealHolder(holder);
                if(val == null) {
                    val = holderPrefix + holder + holderSurfix;
                } else if(recursive) {
                    val = replaceHolder(val, holderPrefix, holderSurfix, handler, recursive);
                }

                sb.append(val);
            }

            return sb.toString();
        } else {
            return str;
        }
    }

    public static String removeNonDisplayable(String str) {
        if(str == null) {
            return null;
        } else {
            int len = str.length();
            StringBuilder sb = new StringBuilder(len);

            for(int i = 0; i < len; ++i) {
                char c = str.charAt(i);
                if(19 == Character.getType(c)) {
                    sb.append('?');
                } else {
                    sb.append(c);
                }
            }

            return sb.toString();
        }
    }

    public static void main(String[] args) {
        System.out.println(validChineseCharacter('點'));
        System.out.println(format("#", "#", new Object[]{Integer.valueOf(133), Integer.valueOf(2), "3", "x"}));
        String x = "<t> \"ddd\" \'hello\'</t>";
        System.out.println(escapeXmlStr(x));
        x = "${dd}dte${dx}";
        final Map m = Util.buildMap(new Object[]{"dd", "-ab${dy}", "dy", "dy", "dx", "-xx${e}", "e", "yzc"});
        System.out.println(replaceHolder(x, "${", "}", new TextUtil.HolderHandler() {
            public String dealHolder(String holder) {
                return TextUtil.replaceHolder((String)m.get(holder), "${", "}", m, true);
            }
        }, true));
    }

    public static boolean validChineseCharacter(char c) {
        String str = "" + c;
        return str.matches("[\\u4e00-\\u9fa5]+");
    }

    public static boolean validLetter(char c) {
        return c >= 97 && c <= 122 || c >= 65 && c <= 90;
    }

    public static boolean validNumber(char c) {
        return Character.isDigit(c);
    }

    public static String removeBOM(String str) {
        if(StringUtils.isBlank(str)) {
            return str;
        } else {
            str = str.replaceAll(String.valueOf('\ufeff'), "");
            return str;
        }
    }

    public static String concat(String... strs) {
        if(strs != null && strs.length != 0) {
            StringBuffer result = new StringBuffer();
            String[] var5 = strs;
            int var4 = strs.length;

            for(int var3 = 0; var3 < var4; ++var3) {
                String str = var5[var3];
                if(!StringUtils.isBlank(str)) {
                    result.append(str);
                }
            }

            return result.toString();
        } else {
            return null;
        }
    }

    public interface HolderHandler {
        String dealHolder(String var1);
    }

    private static class PosHelper implements Comparable {
        public int pos;
        public String oldStr;
        public String newStr;

        public PosHelper(int pos, String oldStr, String newStr) {
            this.pos = pos == -1?2147483647:pos;
            this.oldStr = oldStr;
            this.newStr = newStr;
        }

        public int compareTo(Object o) {
            TextUtil.PosHelper other = (TextUtil.PosHelper)o;
            return this.pos - other.pos;
        }
    }
}
