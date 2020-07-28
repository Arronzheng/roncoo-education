package com.roncoo.education.system.service.biz.pc;

import com.qiniu.common.QiniuException;
import com.roncoo.education.system.common.bean.vo.SysVO;
import com.roncoo.education.system.service.common.req.*;
import com.roncoo.education.system.service.common.resq.PosterPageRESQ;
import com.roncoo.education.system.service.common.resq.PosterViewRESQ;
import com.roncoo.education.system.service.dao.PosterDao;
import com.roncoo.education.system.service.dao.SysDao;
import com.roncoo.education.system.service.dao.impl.mapper.entity.Poster;
import com.roncoo.education.system.service.dao.impl.mapper.entity.PosterExample;
import com.roncoo.education.system.service.dao.impl.mapper.entity.Sys;
import com.roncoo.education.util.aliyun.Aliyun;
import com.roncoo.education.util.aliyun.AliyunUtil;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.enums.FileTypeEnum;
import com.roncoo.education.util.enums.ResultEnum;
import com.roncoo.education.util.qiniu.Qiniu;
import com.roncoo.education.util.qiniu.QiniuUtil;
import com.roncoo.education.util.tencentcloud.Tencent;
import com.roncoo.education.util.tencentcloud.TencentUtil;
import com.roncoo.education.util.tools.BeanUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 广告信息
 *
 */
@Component
public class PcApiPosterBiz {

    @Autowired
    private PosterDao dao;

    @Autowired
    private SysDao sysDao;

    public Result<Page<PosterPageRESQ>> list(PosterPageREQ req) {
        PosterExample example = new PosterExample();
        PosterExample.Criteria c = example.createCriteria();
        if (req.getStatusId() != null) {
            c.andStatusIdEqualTo(req.getStatusId());
        }
        if (req.getPosterType() != null) {
            c.andPosterTypeEqualTo(req.getPosterType());
        }
        example.setOrderByClause(" status_id desc, id desc ");
        Page<Poster> page = dao.listForPage(req.getPageCurrent(), req.getPageSize(), example);
        return Result.success(PageUtil.transform(page, PosterPageRESQ.class));
    }

    /**
     *
     * 添加Poster
     *
     * @param req
     * @return
     */
    public Result<Integer> save(PosterSaveREQ req) {
        Poster record = BeanUtil.copyProperties(req, Poster.class);
        int results = dao.save(record);
        if (results > 0) {
            return Result.success(results);
        }
        return Result.error(ResultEnum.COURSE_SAVE_FAIL);
    }

    /**
     * 更新Poster信息
     *
     * @param req
     * @return
     */
    public Result<Integer> update(PosterUpdateREQ req) {
        if (StringUtils.isEmpty(req.getId())) {
            return Result.error("ID不能为空");
        }
        Poster poster = dao.getById(req.getId());
        if (ObjectUtil.isNull(poster)) {
            return Result.error("找不到广告信息");
        }
        if (StringUtils.hasText(poster.getPoster()) && StringUtils.hasText(req.getPoster()) && !req.getPoster().equals(poster.getPoster())) {
            Sys sys = sysDao.getSys();
            if(sys.getFileType().equals(FileTypeEnum.ALIYUN.getCode())){
                AliyunUtil.delete(poster.getPoster(), BeanUtil.copyProperties(sys, Aliyun.class));
            }else if(sys.getFileType().equals(FileTypeEnum.QINIU.getCode())){
                try {
                    QiniuUtil.deletePic(poster.getPoster(), BeanUtil.copyProperties(sys, Qiniu.class));
                } catch (QiniuException e) {
                    return Result.error(e.code(),e.response.toString());
                }
            }else{
                TencentUtil.deleteFile(poster.getPoster(), BeanUtil.copyProperties(sys, Tencent.class));
            }
        }
        Poster record = BeanUtil.copyProperties(req, Poster.class);
        int results = dao.updateById(record);
        if (results > 0) {
            return Result.success(results);
        }
        return Result.error(ResultEnum.COURSE_UPDATE_FAIL);
    }

    /**
     * 删除Poster信息
     *
     * @param req
     * @return
     */
    public Result<Integer> delete(PosterDeleteREQ req) {
        if (StringUtils.isEmpty(req.getId())) {
            return Result.error("ID不能为空");
        }
        Poster poster = dao.getById(req.getId());
        if (ObjectUtil.isNull(poster)) {
            return Result.error("找不到广告信息");
        }
        if (!StringUtils.isEmpty(poster.getPoster())) {
            Sys sys = sysDao.getSys();
            if(sys.getFileType().equals(FileTypeEnum.ALIYUN.getCode())){
                AliyunUtil.delete(poster.getPoster(), BeanUtil.copyProperties(sys, Aliyun.class));
            }else if(sys.getFileType().equals(FileTypeEnum.QINIU.getCode())){
                try {
                    QiniuUtil.deletePic(poster.getPoster(), BeanUtil.copyProperties(sys, Qiniu.class));
                } catch (QiniuException e) {
                    return Result.error(e.code(),e.response.toString());
                }
            }else{
                TencentUtil.deleteFile(poster.getPoster(), BeanUtil.copyProperties(sys, Tencent.class));
            }
        }
        int results = dao.deleteById(req.getId());
        if (results > 0) {
            return Result.success(results);
        }
        return Result.error(ResultEnum.SYSTEM_DELETE_FAIL);
    }

    /**
     * Poster查看
     *
     * @param req
     * @return
     */
    public Result<PosterViewRESQ> view(PosterViewREQ req) {
        if (StringUtils.isEmpty(req.getId())) {
            return Result.error("ID不能为空");
        }
        Poster record = dao.getById(req.getId());
        return Result.success(BeanUtil.copyProperties(record, PosterViewRESQ.class));
    }

}
