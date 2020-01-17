package com.roncoo.education.util.pay;

import com.github.wxpay.sdk.WXPayConfig;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class WeixinConfig implements WXPayConfig {

    private byte[] certData;

    @Override
    public String getAppID() {
        return "wx8888888888888888";
    }

    @Override
    public String getMchID() {
        return "12888888";
    }//商户号

    @Override
    public String getKey() {
        return "88888888888888888888888888888888";
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
