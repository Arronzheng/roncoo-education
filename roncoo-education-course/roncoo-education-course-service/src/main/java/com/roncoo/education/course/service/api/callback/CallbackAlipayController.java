package com.roncoo.education.course.service.api.callback;

import com.alipay.api.AlipayClient;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayUtil;
import com.roncoo.education.course.service.biz.callback.CallbackAlipayBiz;
import com.roncoo.education.course.service.common.bo.auth.AuthOrderPayBO;
import com.roncoo.education.course.service.dao.CourseAuditDao;
import com.roncoo.education.course.service.dao.OrderInfoDao;
import com.roncoo.education.course.service.dao.OrderPayDao;
import com.roncoo.education.course.service.dao.impl.mapper.entity.Course;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseAudit;
import com.roncoo.education.course.service.dao.impl.mapper.entity.OrderInfo;
import com.roncoo.education.course.service.dao.impl.mapper.entity.OrderPay;
import com.roncoo.education.user.common.bean.qo.LecturerExtQO;
import com.roncoo.education.user.common.bean.vo.LecturerExtVO;
import com.roncoo.education.user.common.bean.vo.LecturerVO;
import com.roncoo.education.user.common.bean.vo.SvipOrderVO;
import com.roncoo.education.user.feign.IBossLecturer;
import com.roncoo.education.user.feign.IBossLecturerExt;
import com.roncoo.education.user.feign.IBossSvipOrder;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.enums.OrderStatusEnum;
import com.roncoo.education.util.enums.PayTypeEnum;
import com.roncoo.education.util.enums.ResultEnum;
import com.roncoo.education.util.pay.AlipayUtil;
import com.roncoo.education.util.pay.WeixinConfig;
import com.roncoo.education.util.pay.WeixinPayUtil;
import com.roncoo.education.util.tools.JSONUtil;
import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping(value = "/course/api/callback")
public class CallbackAlipayController extends BaseController {

    @Autowired
    private CallbackAlipayBiz biz;

    @PostMapping("/alipay/notify")
    public String alipayNotify(HttpServletRequest request){
        return biz.alipayNotify(request);
    }

    @GetMapping("/alipay/return")
    public String alipayReturn(HttpServletRequest request){
        boolean signVerified = AlipayUtil.checkSign(request.getParameterMap());
        if(signVerified){
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no"));
            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no"));
            //付款金额
            String total_amount = new String(request.getParameter("total_amount"));
            return ("success");
        }else{
            System.out.println("验证失败,不去更新状态");
            return ("fail");
        }
    }

    @PostMapping("/weixin/notify")
    public String weixinNotify(HttpServletRequest request){
        return biz.weixinNotify(request);
    }

}
