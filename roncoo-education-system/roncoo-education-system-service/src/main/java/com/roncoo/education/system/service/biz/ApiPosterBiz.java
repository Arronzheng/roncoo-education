package com.roncoo.education.system.service.biz;

import com.roncoo.education.system.service.common.bo.PosterBO;
import com.roncoo.education.system.service.common.dto.PosterDTO;
import com.roncoo.education.system.service.common.req.PosterViewREQ;
import com.roncoo.education.system.service.dao.PosterDao;
import com.roncoo.education.system.service.dao.impl.mapper.entity.Poster;
import com.roncoo.education.user.common.bean.vo.UserExtVO;
import com.roncoo.education.user.common.interfaces.BossUserExt;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.tools.BeanUtil;
import com.roncoo.education.util.tools.ImgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class ApiPosterBiz {

    @Autowired
    private PosterDao dao;
    @Autowired
    private BossUserExt userExt;

    public Result<PosterDTO> getByPosterType(PosterBO posterBO) {
        Poster poster = dao.getByPosterType(posterBO.getPosterType());
        if (ObjectUtils.isEmpty(poster)) {
            return Result.error("暂无海报");
        }
        PosterDTO posterDTO = new PosterDTO();
        posterDTO.setPoster(ImgUtil.getImgBase(poster.getPoster())); // 直接转base64
        UserExtVO userExtVO = userExt.getByUserNo(posterBO.getUserNo());
        posterDTO.setHeadImg(ImgUtil.getImgBase(userExtVO.getHeadImgUrl()));// 直接转base64
        return Result.success(posterDTO);
    }
}
