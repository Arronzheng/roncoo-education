package com.roncoo.education.course.service.biz.pc;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import com.qiniu.common.QiniuException;
import com.roncoo.education.course.service.common.req.FileDeleteREQ;
import com.roncoo.education.course.service.dao.CourseChapterPeriodAuditDao;
import com.roncoo.education.course.service.dao.CourseChapterPeriodDao;
import com.roncoo.education.course.service.dao.CourseVideoDao;
import com.roncoo.education.course.service.dao.impl.mapper.entity.*;
import com.roncoo.education.util.enums.*;
import com.roncoo.education.util.polyv.PolyvUtil;
import com.roncoo.education.util.polyv.UploadFile;
import com.roncoo.education.util.polyv.UploadFileResult;
import com.roncoo.education.util.qiniu.Qiniu;
import com.roncoo.education.util.qiniu.QiniuUtil;
import com.roncoo.education.util.tencentcloud.Tencent;
import com.roncoo.education.util.tencentcloud.TencentUtil;
import com.roncoo.education.util.tools.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.roncoo.education.course.service.dao.FileStorageDao;
import com.roncoo.education.system.common.bean.vo.SysVO;
import com.roncoo.education.system.feign.IBossSys;
import com.roncoo.education.util.aliyun.Aliyun;
import com.roncoo.education.util.aliyun.AliyunUtil;
import com.roncoo.education.util.base.BaseBiz;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.config.SystemUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;

/**
 * 上传接口
 */
@Component
public class PcApiUploadBiz extends BaseBiz {

    @Autowired
    private IBossSys bossSys;
    @Autowired
    private CourseChapterPeriodAuditDao courseChapterPeriodAuditDao;
    @Autowired
    private CourseChapterPeriodDao courseChapterPeriodDao;

    @Autowired
    private CourseVideoDao courseVideoDao;
    @Autowired
    private FileStorageDao fileStorageDao;

    /**
     * 上传图片接口
     */
    public Result<String> uploadPic(MultipartFile picFile, String imgUrl) {
        if (ObjectUtil.isNotNull(picFile)) {
            // 获取系统配置信息
            SysVO sys = bossSys.getSys();
            if (ObjectUtil.isNull(sys)) {
                Result.error("未配置系统配置表");
            }
            Long fileNo = IdWorker.getId();
            if (sys.getFileType().equals(FileTypeEnum.LOCAL.getCode())) {
                // 1、上传到本地
//                File pic = new File(SystemUtil.PIC_STORAGE_PATH + fileNo.toString() + "." + StrUtil.getSuffix(picFile.getOriginalFilename()));// windows系统
                File pic = new File(SystemUtil.PIC_PATH + fileNo.toString() + "." + StrUtil.getSuffix(picFile.getOriginalFilename()));// linux系统
                try {
                    // 判断文件目录是否存在，不存在就创建文件目录
                    if (!pic.getParentFile().exists()) {
                        pic.getParentFile().mkdirs();// 创建父级文件路径
                    }
                    picFile.transferTo(pic);
                    FileStorage fileStorage = new FileStorage();
                    fileStorage.setFileName(picFile.getOriginalFilename());
                    fileStorage.setFileNo(fileNo);
                    fileStorage.setFileSize(picFile.getSize());
                    fileStorage.setFileType(FileStorageTypeEnum.PICTURE.getCode());
                    fileStorage.setFileUrl(pic.toString());
                    fileStorageDao.save(fileStorage);
                    return Result.success(pic.toString());
                } catch (Exception e) {
                    logger.error("上传到本地失败", e);
                    return Result.error("上传文件出错，请重新上传");
                }
            }else if(sys.getFileType().equals(FileTypeEnum.ALIYUN.getCode())){
                //2 上传阿里云
                return Result.success(AliyunUtil.uploadPic(CatalogueEnum.IMAGE, picFile, BeanUtil.copyProperties(bossSys.getSys(), Aliyun.class)));
            }else if(sys.getFileType().equals(FileTypeEnum.QINIU.getCode())){
                //3 上传七牛
                return Result.success(QiniuUtil.uploadPic(CatalogueEnum.IMAGE, imgUrl, picFile,BeanUtil.copyProperties(sys, Qiniu.class)));
            }else{
                //3 上传腾讯
                return Result.success(TencentUtil.uploadFile(CatalogueEnum.IMAGE, imgUrl, picFile,BeanUtil.copyProperties(sys, Tencent.class)));
            }
        }
        return Result.error("请选择上传的图片");
    }

    /**
     * 上传文档接口
     */
    public Result<String> uploadDoc(MultipartFile docFile, String url) {
        if (ObjectUtil.isNotNull(docFile)) {
            // 获取系统配置信息
            SysVO sys = bossSys.getSys();
            if (ObjectUtil.isNull(sys)) {
                Result.error("未配置系统配置表");
            }
            Long fileNo = IdWorker.getId();
            // 1、上传到本地
            if (sys.getFileType().equals(FileTypeEnum.LOCAL.getCode())) {
//                File doc = new File(SystemUtil.DOC_STORAGE_PATH + fileNo.toString() + "." + StrUtil.getSuffix(docFile.getOriginalFilename()));// windows系统
                File doc = new File(SystemUtil.DOC_PATH + fileNo.toString() + "." + StrUtil.getSuffix(docFile.getOriginalFilename())); // linux系统
                try {
                    // 判断文件目录是否存在，不存在就创建文件目录
                    if (!doc.getParentFile().exists()) {
                        doc.getParentFile().mkdirs();// 创建父级文件路径
                    }
                    docFile.transferTo(doc);
                    FileStorage fileStorage = new FileStorage();
                    fileStorage.setFileName(docFile.getOriginalFilename());
                    fileStorage.setFileNo(fileNo);
                    fileStorage.setFileSize(docFile.getSize());
                    fileStorage.setFileType(FileStorageTypeEnum.DOC.getCode());
                    fileStorage.setFileUrl(doc.toString());
                    fileStorageDao.save(fileStorage);
                    return Result.success(doc.toString());
                } catch (Exception e) {
                    logger.error("上传到本地失败", e);
                    return Result.error("上传文件出错，请重新上传");
                }
            }else if(sys.getFileType().equals(FileTypeEnum.ALIYUN.getCode())){
                //2 上传阿里云
                return Result.success(AliyunUtil.uploadPic(CatalogueEnum.DOC, docFile, BeanUtil.copyProperties(bossSys.getSys(), Aliyun.class)));
            }else if(sys.getFileType().equals(FileTypeEnum.QINIU.getCode())){
                //3 上传七牛
                return Result.success(QiniuUtil.uploadPic(CatalogueEnum.DOC, url, docFile,BeanUtil.copyProperties(sys, Qiniu.class)));
            }else{
                //3 上传腾讯
                return Result.success(TencentUtil.uploadFile(CatalogueEnum.DOC, url, docFile,BeanUtil.copyProperties(sys, Tencent.class)));
            }
        }
        return Result.error("请选择上传的文件");

    }
    /**
     * 上传视频接口
     *
     *
     */
    public Result<String> uploadVideo(MultipartFile videoFile, String url) {
        // 视频上传
        if (videoFile == null || videoFile.isEmpty()) {
            return Result.error("请选择视频进行上传");
        }

        // 获取上传文件的原名
        String fileName = videoFile.getOriginalFilename();
        boolean fileStatus = true;
        List<String> fileTypes = Arrays.asList("avi", "mp4", "flv", "mpg", "mov", "asf", "3gp", "f4v", "wmv",
                "x-ms-wmv\n");
        for (String filetype : fileTypes) {
            // 上传文件的原名+小写+后缀
            if (fileName.toLowerCase().endsWith(filetype)) {
                fileStatus = false;
                break;
            }
        }
        if (fileStatus) {
            return Result.error("上传的视频类型不正确");
        }
        SysVO sys = bossSys.getSys();
        Long videoNo = 0L;
        /*
        * 通过传入上个视频的URL判断是新增课时视频还是修改课时视频
        */
        if(StringUtils.isEmpty(url)){
            videoNo = IdWorker.getId(); // 当作存储到本地的文件名，方便定时任务的处理
        }else{
            //String key = url.replaceAll(sys.getAliyunOssUrl(), "");
            videoNo = UrlUtil.UrlToKey(url,sys.getAliyunOssUrl());//Long.valueOf(key.indexOf("/") > 0 ? key.substring((key.indexOf("/") + 1), key.indexOf(".")) : key.substring(0, key.indexOf(".")));
        }
        // 1、上传到本地
		File targetFile = new File(
				SystemUtil.PERIOD_VIDEO_PATH + videoNo.toString() + "." + StrUtil.getSuffix(fileName));//Linux系统
//        File targetFile = new File(
//                SystemUtil.VIDEO_STORAGE_PATH + videoNo.toString() + "." + StrUtil.getSuffix(fileName));//Windows系统
        targetFile.setLastModified(System.currentTimeMillis());// 设置最后修改时间
        // 判断文件目录是否存在，不存在就创建文件目录
        if (!targetFile.getParentFile().exists()) {
            targetFile.getParentFile().mkdirs();
        }
        try {
            videoFile.transferTo(targetFile);
        } catch (Exception e) {
            logger.error("上传到本地失败", e);
            return Result.error("上传文件出错，请重新上传");
        }
        CourseVideo courseVideo = new CourseVideo();
        courseVideo.setVideoNo(videoNo);
        courseVideo.setGmtCreate(null);
        courseVideo.setGmtModified(null);
        courseVideo.setVideoName(fileName);
        courseVideo.setVideoStatus(VideoStatusEnum.WAIT.getCode());
        int result = 0;
        if(StringUtils.isEmpty(url)){
            // 新增课程视频表信息
            result = courseVideoDao.save(courseVideo);
        }else{
            result = courseVideoDao.updateByExampleSelective(courseVideo);
        }
        if (result > 0) {
            Long finalVideoNo = videoNo;
            callbackExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    // 获取系统配置信息
                    int courseUpdateResult = 0;
                    if(sys.getVideoType().equals(VideoTypeEnum.POLYV.getCode())){
                        // 2、异步上传到保利威视
                        UploadFile uploadFile = new UploadFile();
                        uploadFile.setTitle(fileName);
                        uploadFile.setDesc(fileName);
                        uploadFile.setTag(videoFile.getOriginalFilename());
                        uploadFile.setCataid(1L);

                        //上传保利威视
                        UploadFileResult result = PolyvUtil.uploadFile(targetFile, uploadFile, sys.getPolyvWritetoken());
                        if (result == null) {
                            // 上传异常，不再进行处理，定时任务会继续进行处理
                            return;
                        }
                        // 3、异步上传到阿里云
                        String videoOasId = AliyunUtil.uploadDoc(CatalogueEnum.VIDEO, targetFile,
                                BeanUtil.copyProperties(sys, Aliyun.class));
                        //更新课程视频信息
//                        courseVideo.setVideoNo(result.getDuration());
                        courseVideo.setVideoLength(result.getDuration());
                        courseVideo.setVideoVid(result.getVid());
                        courseVideo.setVideoStatus(VideoStatusEnum.SUCCES.getCode());
//                        courseVideoDao.updateById(courseVideo);
                        courseVideo.setVideoOasId(videoOasId);
                        courseUpdateResult = courseVideoDao.updateByExampleSelective(courseVideo);
                    }else{
                        //上传腾讯云
                        String result = TencentUtil.uploadFile(CatalogueEnum.VIDEO, url, targetFile, BeanUtil.copyProperties(sys, Tencent.class));
                        if (StringUtils.isEmpty(result)) {
                            // 上传异常，不再进行处理，定时任务会继续进行处理
                            return;
                        }
                        //更新课程视频信息
                        courseVideo.setVideoLength(VideoUtil.getVedioTime(targetFile));
                        courseVideo.setVideoStatus(VideoStatusEnum.SUCCES.getCode());
                        courseUpdateResult = courseVideoDao.updateByExampleSelective(courseVideo);
                    }
                    // 根据视频编号、课时ID查询课程视频信息
//                    CourseVideo courseVideo = courseVideoDao.getByVideoNoAndPeriodId(finalVideoNo, Long.valueOf(0));

                    // 根据视频编号更新视频信息
//                    List<CourseVideo> list = courseVideoDao.listByVideoNo(finalVideoNo);
//                    for (CourseVideo video : list) {
//                        video.setVideoLength(courseVideo.getVideoLength());
//                        video.setVideoVid(courseVideo.getVideoVid());
//                        video.setVideoStatus(VideoStatusEnum.SUCCES.getCode());
//                        video.setVideoOasId(courseVideo.getVideoOasId());
//                        courseVideoDao.updateById(video);
//                    }
                    // 更新课时审核表视频信息
                    List<CourseChapterPeriodAudit> periodAuditList = courseChapterPeriodAuditDao.listByVideoNo(finalVideoNo);
                    for (CourseChapterPeriodAudit periodAudit : periodAuditList) {
                        periodAudit.setVideoName(courseVideo.getVideoName());
                        periodAudit.setVideoLength(VideoUtil.getVedioTime(targetFile));
                        periodAudit.setVideoVid(courseVideo.getVideoVid());
                        courseChapterPeriodAuditDao.updateById(periodAudit);
                    }
                    // 更新课时视频信息
                    List<CourseChapterPeriod> periodList = courseChapterPeriodDao.listByVideoNo(finalVideoNo);
                    for (CourseChapterPeriod period : periodList) {
                        period.setVideoName(courseVideo.getVideoName());
                        period.setVideoLength(courseVideo.getVideoLength());
                        period.setVideoVid(courseVideo.getVideoVid());
                        courseChapterPeriodDao.updateById(period);
                    }
                    // 4、成功删除本地文件
                    if (targetFile.isFile() && targetFile.exists() && courseUpdateResult > 0) {
                        Boolean r = targetFile.delete();
                    }
                }
            });
        } else {
            return Result.error("系统异常，请重试");
        }
        return Result.success(sys.getAliyunOssUrl()+CatalogueEnum.VIDEO.name().toLowerCase()+"/"+targetFile.getName());
    }

    public Result<Integer> deleteFile(FileDeleteREQ fileDeleteREQ) {
        SysVO sys = bossSys.getSys();
        if(!StringUtils.isEmpty(fileDeleteREQ.getDocFileUrl())){
            if(sys.getFileType().equals(FileTypeEnum.TENCENT.getCode())){
                TencentUtil.deleteFile(fileDeleteREQ.getDocFileUrl(), BeanUtil.copyProperties(sys, Tencent.class));
            }else if(sys.getFileType().equals(FileTypeEnum.ALIYUN.getCode())){
                AliyunUtil.delete(fileDeleteREQ.getDocFileUrl(), BeanUtil.copyProperties(sys, Aliyun.class));
            }else{
                try {
                    QiniuUtil.deletePic(fileDeleteREQ.getDocFileUrl(), BeanUtil.copyProperties(sys, Qiniu.class));
                } catch (QiniuException e) {
                    e.printStackTrace();
                }
            }
        }
        int result = 0;
        if(!StringUtils.isEmpty(fileDeleteREQ.getVideoFileUrl())){
            if(sys.getVideoType().equals(VideoTypeEnum.TENCENT.getCode())){
                TencentUtil.deleteFile(fileDeleteREQ.getVideoFileUrl(), BeanUtil.copyProperties(sys, Tencent.class));
            }
            Long videoNo = UrlUtil.UrlToKey(fileDeleteREQ.getVideoFileUrl(), sys.getAliyunOssUrl());
            result = courseVideoDao.deleteByVideoNo(videoNo);
        }
        if(result > 0){
            return Result.success(result);
        }else{
            return Result.error(ResultEnum.ERROR);
        }
    }
}
