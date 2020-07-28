package com.roncoo.education.util.pay;

import com.github.wxpay.sdk.WXPayConfig;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class WeixinConfig implements WXPayConfig {

    private byte[] certData;

    @Override
    public String getAppID() {
        return "wxd184c956d9cea260";
    }

    @Override
    public String getMchID() {
        return "1596125071";
    }//商户号

    @Override
    public String getKey() {
        return "y2gJK3bf7r9t35fs8c4W7n1e4V6x8t0q";
    }//API密钥：key为商户平台设置的密钥key

    @Override
    public InputStream getCertStream() {
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    @Override
    public int getHttpConnectTimeoutMs() {
        return 8000;
    }

    @Override
    public int getHttpReadTimeoutMs() {
        return 10000;
    }
}
