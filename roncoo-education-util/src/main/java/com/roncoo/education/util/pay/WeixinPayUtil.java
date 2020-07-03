package com.roncoo.education.util.pay;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import com.roncoo.education.util.tools.IpUtil;
import com.roncoo.education.util.tools.NOUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class WeixinPayUtil {

    private static final Logger logger = LoggerFactory.getLogger(WeixinPayUtil.class);

    /*
    * 统一下单：
    */
    public static Map<String, String> weixinPay(String out_trade_no, String body, BigDecimal total_fee, String notify_url, String tradeType, String orderType, String openId) throws Exception {
        WeixinConfig config = new WeixinConfig();
        WXPay wxpay = new WXPay(config, WXPayConstants.SignType.MD5, true);

        Map<String, String> data = new HashMap<String, String>();
//      data.put("sign",getSignKey(config, wxpay));//签名
        data.put("body", body);//商品描述(标题)
        data.put("out_trade_no", out_trade_no);//商户订单号
        data.put("total_fee", total_fee.stripTrailingZeros().toPlainString());//订单总金额，单位为分
        data.put("spbill_create_ip", IpUtil.getLocalIp4Address().toString());//用户的客户端IP,Native支付填调用微信支付API的机器IP
        data.put("notify_url", notify_url);//回调地址
        data.put("trade_type", tradeType);  // 交易类型：JSAPI -JSAPI支付 NATIVE -Native支付 APP -APP支付
        data.put("attach", orderType);  // 附加数据,原样返回
        if ("JSAPI".equals(tradeType) && !StringUtils.isEmpty(openId)) {
            data.put("openid", openId); // JSAPI支付，此参数必传
        }
        return wxpay.unifiedOrder(data);
    }
    /*
     *订单查询：
     */
    public static Map<String, String> orderQuery(String out_trade_no){
        WeixinConfig config = new WeixinConfig();
        WXPay wxpay = new WXPay(config, WXPayConstants.SignType.MD5, true);
        Map<String, String> data = new HashMap<String, String>();
        data.put("out_trade_no", out_trade_no);
        Map<String, String> resp = null;
        try {
            resp = wxpay.orderQuery(data);
            System.out.println(resp);
        } catch (Exception e) {
            logger.error("微信订单查询接口请求失败，resp={}", e.getMessage());
            return resp;
        }
        return resp;
    }
    /*
     *申请退款
     */
    public static Map<String, String> refund(String outTradeNo, BigDecimal totalFee, BigDecimal refundFee) throws Exception {
        WeixinConfig config = new WeixinConfig();
        WXPay wxpay = new WXPay(config, WXPayConstants.SignType.MD5, false);
        Map<String, String> data = new HashMap<String, String>();
        data.put("out_trade_no",outTradeNo); // 商户订单号
        data.put("out_refund_no", NOUtil.getSerialNumber().toString()); // 商户退款单号
        data.put("total_fee",totalFee.toPlainString()); // 订单金额
        data.put("refund_fee",refundFee.toPlainString()); // 退款金额
        return wxpay.refund(data);
    }

    /*
     *验证签名
     *收到回调通知第一步就是验签
     * param:notifyData,支付结果通知的xml格式数据
     */
//    public static boolean checkSign(String notifyData){
//        WeixinConfig config = new WeixinConfig();
//        WXPay wxpay = new WXPay(config);
//        boolean result = false;
//        try {
//            Map<String, String> notifyMap = WXPayUtil.xmlToMap(notifyData);  // 转换成map
//            result = wxpay.isPayResultNotifySignatureValid(notifyMap);
//        } catch (Exception e) {
//            logger.error("微信验签接口请求失败，resp={}", e.getMessage());
//        }
//        return result;
////        if (result) {
////            // 签名正确
////            // 进行处理。
////            // 注意特殊情况：订单已经退款，但收到了支付结果成功的通知，不应把商户侧订单状态从退款改成支付成功
////        } else {
////            // 签名错误，如果数据里没有sign字段，也认为是签名错误
////        }
//    }

    /**
     * 获取微信通知请求参数
     * @param request
     * @return
     */
    public static String readData(HttpServletRequest request){
        BufferedReader br = null;
        try {
            StringBuilder ret;
            br = request.getReader();
            String line = br.readLine();
            if(line != null) {
                ret = new StringBuilder();
                ret.append(line);
            }else{
                return "";
            }
            while ((line = br.readLine()) != null) {
                ret.append('\n').append(line);
            }
            return ret.toString();
        }catch (IOException e) {
            logger.error("微信验签接口请求失败，resp={}", e.getMessage());
            return "";
        }finally {
            if(br != null) {
                try {
                    br.close();
                    return "";
                }catch (IOException e){
                    logger.error("微信验签接口请求失败，resp={}", e.getMessage());
                    return "";
                }
            }
        }
    }

    /**
     *微信沙箱环境获取key
     * @param config
     * @param wxPay
     * @return
     */
    public static String getSignKey(WeixinConfig config, WXPay wxPay){
        // 获取sandbox_signkey
        Map<String, String> param1 = new HashMap<String, String>();
        param1.put("mch_id", config.getMchID());// 商户号
        param1.put("nonce_str", WXPayUtil.generateNonceStr());// 随机字符串
        try {
            param1.put("sign", WXPayUtil.generateSignature(param1, config.getKey()));// 沙盒测试貌似只支持MD5加密
            String xml = wxPay.requestWithoutCert("https://api.mch.weixin.qq.com/sandboxnew/pay/getsignkey",
                    param1, config.getHttpConnectTimeoutMs(), config.getHttpReadTimeoutMs());
//            xml = WXPayUtil.mapToXml(param1);
            Map<String, String> result1 = WXPayUtil.xmlToMap(xml);
            System.out.println("sandbox_signkey:"+result1.get("sandbox_signkey"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
