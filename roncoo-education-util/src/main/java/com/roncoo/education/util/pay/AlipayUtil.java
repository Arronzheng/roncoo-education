package com.roncoo.education.util.pay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePrecreateModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.roncoo.education.util.tools.IdWorker;
import com.roncoo.education.util.tools.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;

public final class AlipayUtil {

    public AlipayUtil() {
    }

    private static final Logger logger = LoggerFactory.getLogger(AlipayUtil.class);

    public static AlipayClient clientInit(){
        AlipayClient alipayClient = new DefaultAlipayClient(
                "https://openapi.alipaydev.com/gateway.do",
                "2016101400687185",
                "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCwCU6O73wo4JtwGKMT+JoiVPlu56hei3pGDM5LY5gA5a6WFvCm05GkLDARJT7Lg/x55y1qpwnzOcgmx4/xQqhVsfzMAzOt2K98esV7jpIvZ0PFjwpXiQCuazbjsPm+jClIF4CGyzdYcAk9HtcKg0bNvGPGC482CPZ/EIKNcmbD28CmoMIvtAHhO8Rsyc+o/ttNcdSEHZNW/xELJ5KcmDGMlQ1GRZGForetbDkBOFu6uycyGbPWnzD8hmpv8DRRewu2IJlzeBT+N/o/p85uzeiGxJwDNInboWkKlAQpEVLqadVbhMeNg0h+KcvHvTJqF1Sy6VcZfL1HII7xpoEI5Gd7AgMBAAECggEAZWKm1RnG54Ac5d26prjqhNVt51sSlIk+PfQIKFFO7U1bVOQXa+20FtrAQE+0L+BvXAIyHRfhWi0wQm1zUJ90dg6Rqyo47Go8/7Nx46yEMzsFRFYSvcGRG+Nx9Icr/3uYFwY6JugJkyzLlWEattpOwBU9XYWw0I0u8pWeqzp1R11m6J4ZkgpieQYTgFbXksS2+AikpahLhMwhXJnMfM1mNH93n87/JqrqG83iXNdgeBBIpTJ6XZ0uz6SuedqH/9BE7uleOEu6ZdNUcaaDvpk/kaGQr54RpgWhNS23Gn4u6v/Z+vKv/iJ8rQCxqNnTC43Fx3usHjQuBYSnRKAWkRxVaQKBgQDmaVmY6QuaLJuyC+6QE253TUXFcJxsMw0pXA/bRWmMeE2EELjnOnTKqwA1wMECgJ5PWL5NIdGMDqg1fki07XSyivl0z/pdC2BaX6jcwH0oKIRP8JvKLxgQ9AUQ1K6YdpmGI6cJqsW4MQ1VikFunaSBDNB8Wv1NyMqQRCWstv7ZnQKBgQDDlg8XGSz4M2yqLZ7FTQ8GmIUewwFPoyFK0RI3D9GsGYLHbbr0frKeCaGxpD/+4g8cYalD/ljxtOSTG0cNs9nusO7NG5thPvnfotN65NvzjHOfth70x3lgeqxbXN5SBfKphtJjc3PM0R0YTQS/r5q7XAYf0sEyGgbvMtJTKYPl9wKBgHLMuKx8KKC3axep5EH6vIZKhOHbusQaJ4uc5/WbPvyTECQl5MqokHpN+vvNPx1NEhN8psxtdidvQhXtBrLGwOcYYTP3T8m+GlLM6Kica39vuITP3/87uaTwv/ljzl3+cX2pE9e4M20TW45sJS/E8aquxM3A1AZGbc61dYP5ZT7tAoGBAIDTc0cUlxE41gdVmzF76zy981jquw9YdNJR2XyZF9MSzphLR4xJc58y2uUmOdzJy4g3L4WQn8c9Dh1zJx74asCUnAZPe83W7EEIf+F/ovzNc08sZ0F1rhYFV0eqaOl/73EIU8WbwxeSWWarPEiCAxoLqCEH8abti8m8INsUCkPLAoGAe7H+r8kmMqi/smYa5+FQzXgl4zA6JOZ+WQ4xig1ErNDjC51IxzDBEthsYcWXlLbCYoWZHl2qss9j/Ta5C+XYns3PlS6XBINZLttM8HaxdaFVSI4R3XGJqTyYmRRLBkGtRntm8r/1KTKobwbgeB2XxypdTgtGIDthMW+kS9ZCVWE=",
                "json",
                "UTF-8",
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAm4DR0Tq9B5Js3c/4EZZ87qHqzv32GuDAkKzmZmQXNFcAiMws0O8WZsZbNL52Q0ntHDqb0j/fhdS/k4IlQ1qKb7qwlPRfjIDTekS4GE0Bv1XmYF+Cai4qRCf5O6Zgb1jjRFllsI8J1mxwpu1TWMTEVfmjU1mRB24Rp05JVBt5bDm20mvN8dVc4kWA9o/sCRS4Ize6yOf3SB2U154jsjXiRv2SLXJcrHZ5LDnBAahQYepAGqRrD1Kvy7YGigUq9MzKHc+SY9w3LskSZ3CneznYocMt1r6seoy0EjDwcXSTukQeluk8aMpjTd8WVE40/qOBWut8gTb8C35zqR0SrtfChwIDAQAB",
                "RSA2");
        return alipayClient;
    }

    /**
     *交易预下单接口
     * @return String
     */
    public static AlipayTradePrecreateResponse alipay(String orderNo, String productName, BigDecimal productPrice, String payUrl, String notifyUrl, String orderType){
        AlipayClient alipayClient = clientInit();
        AlipayTradePrecreateModel model = new AlipayTradePrecreateModel();
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();//创建API对应的request类
        model.setOutTradeNo(orderNo);//订单号
        // 去除末尾多余的0,避免输出科学计数法
        model.setTotalAmount(productPrice.stripTrailingZeros().toPlainString());//订单金额
        // 把商品名中的特殊字符过滤掉
        String regEx = "[`~!@#$%^&*()+=|{}':;',_\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        productName = Pattern.compile(regEx).matcher(productName).replaceAll("").trim();
        if (productName.length() > 15) {
            model.setSubject(productName.substring(0, 15));
        } else {
            model.setSubject(productName);//订单标题
        }
        model.setBody(orderType);
        model.setTimeoutExpress("30m");//交易超时时间
        model.setQrCodeTimeoutExpress("30m");//二维码失效时间
        //商户外网可以访问的异步地址，不写就是不回调
        request.setNotifyUrl(notifyUrl);//"http://dipzzg.natappfree.cc/course/api/callback/alipay/notify"
        request.setBizModel(model);
        AlipayTradePrecreateResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            logger.error("支付宝下单接口请求失败，resp={}", e.getErrMsg());
            return null;
        }
        return response;
    }

    /**
     * 统一收单线下交易查询接口
     * @return AlipayTradeQueryResponse
     */
    public static AlipayTradeQueryResponse queryOrder(String outTradeNo, String tradeNo){
        AlipayClient alipayClient = clientInit();
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();//创建API对应的request类
        Map<String, String> params = new HashMap<>();
        params.put("out_trade_no",outTradeNo);
        request.setBizContent(JSONUtil.toJSONString(params)); //设置业务参数
        AlipayTradeQueryResponse response = null;
        try {
            response = alipayClient.execute(request);//通过alipayClient调用API，获得对应的response类
        } catch (AlipayApiException e) {
            logger.error("支付宝查询接口请求失败，resp={}", e.getErrMsg());
        }
        return response;
    }

    /**
     * 验签方法
     * @return boolean
     */
    public static boolean checkSign(Map<String,String[]> requestParams){
        Map<String,String> params = new HashMap<String,String>();//将异步通知中收到的所有参数都存放到 map 中
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
//            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        boolean signVerified = false; //调用SDK验证签名
        try {
            signVerified = AlipaySignature.rsaCheckV1(
                    params,
                    "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAm4DR0Tq9B5Js3c/4EZZ87qHqzv32GuDAkKzmZmQXNFcAiMws0O8WZsZbNL52Q0ntHDqb0j/fhdS/k4IlQ1qKb7qwlPRfjIDTekS4GE0Bv1XmYF+Cai4qRCf5O6Zgb1jjRFllsI8J1mxwpu1TWMTEVfmjU1mRB24Rp05JVBt5bDm20mvN8dVc4kWA9o/sCRS4Ize6yOf3SB2U154jsjXiRv2SLXJcrHZ5LDnBAahQYepAGqRrD1Kvy7YGigUq9MzKHc+SY9w3LskSZ3CneznYocMt1r6seoy0EjDwcXSTukQeluk8aMpjTd8WVE40/qOBWut8gTb8C35zqR0SrtfChwIDAQAB",
                    "utf-8",
                    "RSA2");
        } catch (AlipayApiException e) {
            logger.error("支付宝验签接口请求失败，resp={}", e.getErrMsg());
        }
        return signVerified;
    }

}
