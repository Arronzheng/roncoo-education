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
        return "b91d31e906321813f7adcf489c3809df";
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
