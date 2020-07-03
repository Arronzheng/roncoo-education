package com.roncoo.education.user.service.common;

import com.roncoo.education.user.service.common.dto.JssdkConfigDTO;
import com.roncoo.education.user.service.common.wechat.AccessToken;
import com.roncoo.education.user.service.common.wechat.JsapiTicket;
import com.roncoo.education.util.base.BaseBiz;
import com.roncoo.education.util.config.SystemUtil;
import com.roncoo.education.util.tools.HttpUtil;
import com.roncoo.education.util.tools.SHA1Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Formatter;
import java.util.Map;
import java.util.UUID;

@Component
public class WeChatUtil {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 验证微信签名
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    public static boolean check(String signature, String timestamp, String nonce){
        String[] strs = new String[]{SystemUtil.TOKEN, timestamp, nonce};
        Arrays.sort(strs);
        String str = strs[0]+strs[1]+strs[2];
        String mysig = SHA1Util.encryption(str);
        if(mysig.equals(signature)){
            return true;
        }
        return false;
    }

    /**
     * 微信签名算法
     * @param jsapi_ticket
     * @param url
     * @return Map<String, String>
     */
    public static JssdkConfigDTO sign(String jsapi_ticket, String url){
        String nonce_str = UUID.randomUUID().toString();
        String timestamp = Long.toString(System.currentTimeMillis() / 1000);
        String string1;
        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapi_ticket +
                  "&noncestr=" + nonce_str +
                  "&timestamp=" + timestamp +
                  "&url=" + url;
        System.out.println(string1);

        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String[] apiList = {"editAddress","openAddress","updateTimelineShareData","updateAppMessageShareData","onMenuShareTimeline", "onMenuShareAppMessage", "onMenuShareQQ", "onMenuShareWeibo", "onMenuShareQZone", "startRecord", "stopRecord", "onVoiceRecordEnd", "playVoice", "pauseVoice", "stopVoice", "onVoicePlayEnd", "uploadVoice", "downloadVoice", "chooseImage", "previewImage", "uploadImage", "downloadImage", "translateVoice", "getNetworkType", "openLocation", "getLocation", "hideOptionMenu", "showOptionMenu", "hideMenuItems", "showMenuItems", "hideAllNonBaseMenuItem", "showAllNonBaseMenuItem", "closeWindow", "scanQRCode", "chooseWXPay", "openProductSpecificView", "addCard", "chooseCard"};

        JssdkConfigDTO jssdkConfigDTO = new JssdkConfigDTO();
        jssdkConfigDTO.setAppId(SystemUtil.APP_ID)
                    .setDebug(true)
//                    .setJsapi_ticket(jsapi_ticket)
                    .setJsApiList(Arrays.asList(apiList))
                    .setNonceStr(nonce_str)
                    .setSignature(signature)
                    .setTimestamp(timestamp)
                    .setUrl(url);
        return jssdkConfigDTO;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

}
