package com.roncoo.education.util.tools;

public class UrlUtil {
    public static Long UrlToKey(String url, String ossUrl){
        String key = url.replaceAll(ossUrl, "");
        String l = key.indexOf("/") > 0 ? key.substring((key.lastIndexOf("/") + 1), key.indexOf(".")) : key.substring(0, key.indexOf("."));
        return Long.valueOf(l);
    }
}
