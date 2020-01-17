package com.roncoo.education.user.service.biz.callback;

import com.roncoo.education.user.service.common.dto.UserLoginDTO;
import com.roncoo.education.user.service.dao.UserDao;
import com.roncoo.education.user.service.dao.UserExtDao;
import com.roncoo.education.user.service.dao.impl.mapper.entity.User;
import com.roncoo.education.user.service.dao.impl.mapper.entity.UserExt;
import com.roncoo.education.util.base.BaseBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.config.SystemUtil;
import com.roncoo.education.util.enums.StatusIdEnum;
import com.roncoo.education.util.tools.HttpUtil;
import com.roncoo.education.util.tools.JWTUtil;
import com.roncoo.education.util.tools.NOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

@Component
public class CallbackOauthBiz extends BaseBiz {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserExtDao userExtDao;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    public Result<UserLoginDTO> getCode(String code, String state) {
        String storage = redisTemplate.opsForValue().get("state");
        if(state == null){
            return Result.error("请求错误！");
        }
        if(!state.equals(storage)){
            return Result.error("请求错误！");
        }
        //1、通过openAppid和openAppsecret和微信返回的code，拼接URL
        String accessTokenUrl = String.format(SystemUtil.ACCESS_TOKEN_URL,SystemUtil.APP_ID,SystemUtil.APP_SECRET,code);

        //2、通过URL再去回调微信接口 (使用了httpclient和gson工具）
        Map<String ,Object> baseMap =  HttpUtil.doGet(accessTokenUrl);

        //3、回调成功后获取access_token和oppid
        if(baseMap == null || baseMap.isEmpty()){ return  Result.error("微信登录失败！"); }
        String accessToken = (String)baseMap.get("access_token");
        String openId  = (String) baseMap.get("openid");

        //4、去数据库查看该用户之前是否已经扫码登陆过（openid是用户唯一标志）
        User user = userDao.findByopenid(openId);
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        if(user!=null) { //如果用户已经存在，直接返回
            userLoginDTO.setUserNo(user.getUserNo());
            userLoginDTO.setMobile(user.getMobile());
            userLoginDTO.setToken(JWTUtil.create(user.getUserNo(), JWTUtil.DATE));
            return Result.success(userLoginDTO);
        }

        //4、access_token和openid拼接URL
        String userInfoUrl = String.format(SystemUtil.USER_INFO_URL,accessToken,openId);

        //5、通过URL再去调微信接口获取用户基本信息
        Map<String ,Object> baseUserMap =  HttpUtil.doGet(userInfoUrl);

        if(baseUserMap == null || baseUserMap.isEmpty()){ return  null; }

        //6、获取用户姓名、性别、城市、头像等基本信息
        String nickname = (String)baseUserMap.get("nickname");
        Double sexTemp  = (Double) baseUserMap.get("sex");
        int sex = sexTemp.intValue();
        String headimgurl = (String)baseUserMap.get("headimgurl");
        try {
            //解决用户名乱码
            nickname = new String(nickname.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //7、新用户存入数据库
        UserExt userExt = new UserExt();
        userExt.setNickname(nickname);
        userExt.setHeadImgUrl(headimgurl);
        userExt.setUserNo(NOUtil.getUserNo());
        userExt.setSex(sex);
        userExt.setGmtCreate(new Date());
        userExtDao.save(userExt);
        user = new User();
        user.setUserNo(userExt.getUserNo());
        user.setStatusId(StatusIdEnum.YES.getCode());
        user.setOpenId(openId);
        userDao.save(user);

        userLoginDTO.setUserNo(user.getUserNo());
        userLoginDTO.setMobile(user.getMobile());
        userLoginDTO.setToken(JWTUtil.create(user.getUserNo(), JWTUtil.DATE));
        return Result.success(userLoginDTO);
    }
}
