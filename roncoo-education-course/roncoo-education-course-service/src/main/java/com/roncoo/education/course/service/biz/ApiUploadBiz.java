/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.course.service.biz;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import com.roncoo.education.util.qiniu.Qiniu;
import com.roncoo.education.util.qiniu.QiniuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.roncoo.education.course.service.dao.CourseChapterPeriodAuditDao;
import com.roncoo.education.course.service.dao.CourseChapterPeriodDao;
import com.roncoo.education.course.service.dao.CourseVideoDao;
import com.roncoo.education.course.service.dao.FileStorageDao;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseChapterPeriod;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseChapterPeriodAudit;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseVideo;
import com.roncoo.education.course.service.dao.impl.mapper.entity.FileStorage;
import com.roncoo.education.system.common.bean.vo.SysVO;
import com.roncoo.education.system.feign.IBossSys;
import com.roncoo.education.util.aliyun.Aliyun;
import com.roncoo.education.util.aliyun.AliyunUtil;
import com.roncoo.education.util.base.BaseBiz;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.config.SystemUtil;
import com.roncoo.education.util.enums.FileStorageTypeEnum;
import com.roncoo.education.util.enums.FileTypeEnum;
import com.roncoo.education.util.enums.PlatformEnum;
import com.roncoo.education.util.enums.VideoStatusEnum;
import com.roncoo.education.util.polyv.PolyvUtil;
import com.roncoo.education.util.polyv.UploadFile;
import com.roncoo.education.util.polyv.UploadFileResult;
import com.roncoo.education.util.tools.BeanUtil;
import com.roncoo.education.util.tools.IdWorker;
import com.roncoo.education.util.tools.StrUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;

/**
 * 上传接口
 * 
 * @author wuyun
 */
@Component
public class ApiUploadBiz extends BaseBiz {

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
	 * 上传视频接口
	 * 
	 * @author wuyun
	 */
	public Result<String> uploadVideo(MultipartFile videoFile) {
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

		Long videoNo = IdWorker.getId(); // 当作存储到本地的文件名，方便定时任务的处理

		// 1、上传到本地
		File targetFile = new File(
				SystemUtil.PERIOD_VIDEO_PATH + videoNo.toString() + "." + StrUtil.getSuffix(fileName));
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

		// 新增课程视频表信息
		CourseVideo courseVideo = new CourseVideo();
		courseVideo.setVideoNo(videoNo);
		courseVideo.setGmtCreate(null);
		courseVideo.setGmtModified(null);
		courseVideo.setVideoName(fileName);
		courseVideo.setVideoStatus(VideoStatusEnum.WAIT.getCode());
		int result = courseVideoDao.save(courseVideo);

		if (result > 0) {
			callbackExecutor.execute(new Runnable() {
				@Override
				public void run() {
					// 2、异步上传到保利威视
					UploadFile uploadFile = new UploadFile();
					uploadFile.setTitle(fileName);
					uploadFile.setDesc(fileName);
					uploadFile.setTag(videoFile.getOriginalFilename());
					uploadFile.setCataid(1L);

					// 获取系统配置信息
					SysVO sys = bossSys.getSys();

					UploadFileResult result = PolyvUtil.uploadFile(targetFile, uploadFile, sys.getPolyvWritetoken());
					if (result == null) {
						// 上传异常，不再进行处理，定时任务会继续进行处理
						return;
					}

					courseVideo.setVideoLength(result.getDuration());
					courseVideo.setVideoVid(result.getVid());
					courseVideo.setVideoStatus(VideoStatusEnum.SUCCES.getCode());
					courseVideoDao.updateById(courseVideo);

					// 3、异步上传到阿里云
					String videoOasId = AliyunUtil.uploadDoc(PlatformEnum.COURSE, targetFile,
							BeanUtil.copyProperties(sys, Aliyun.class));
					courseVideo.setVideoOasId(videoOasId);
					courseVideoDao.updateById(courseVideo);

					// 根据视频编号、课时ID查询课程视频信息
					CourseVideo courseVideo = courseVideoDao.getByVideoNoAndPeriodId(videoNo, Long.valueOf(0));

					// 根据视频编号更新视频信息
					List<CourseVideo> list = courseVideoDao.listByVideoNo(videoNo);
					for (CourseVideo video : list) {
						video.setVideoLength(courseVideo.getVideoLength());
						video.setVideoVid(courseVideo.getVideoVid());
						video.setVideoStatus(VideoStatusEnum.SUCCES.getCode());
						video.setVideoOasId(courseVideo.getVideoOasId());
						courseVideoDao.updateById(video);
					}

					// 更新课时审核表视频信息
					List<CourseChapterPeriodAudit> periodAuditList = courseChapterPeriodAuditDao.listByVideoNo(videoNo);
					for (CourseChapterPeriodAudit periodAudit : periodAuditList) {
						periodAudit.setVideoName(courseVideo.getVideoName());
						periodAudit.setVideoLength(courseVideo.getVideoLength());
						periodAudit.setVideoVid(courseVideo.getVideoVid());
						courseChapterPeriodAuditDao.updateById(periodAudit);
					}
					// 更新课时视频信息
					List<CourseChapterPeriod> periodList = courseChapterPeriodDao.listByVideoNo(videoNo);
					for (CourseChapterPeriod period : periodList) {
						period.setVideoName(courseVideo.getVideoName());
						period.setVideoLength(courseVideo.getVideoLength());
						period.setVideoVid(courseVideo.getVideoVid());
						courseChapterPeriodDao.updateById(period);
					}
					// 4、成功删除本地文件
					if (targetFile.isFile() && targetFile.exists()) {
						targetFile.delete();
					}
				}
			});
		} else {
			return Result.error("系统异常，请重试");
		}
		return Result.success(String.valueOf(courseVideo.getVideoNo()));
	}

	/**
	 * 上传图片接口
	 * 
	 * @author wuyun
	 */
	public Result<String> uploadPic(MultipartFile picFile, String imgUrl) {
		if (ObjectUtil.isNotNull(picFile)) {
			// 获取系统配置信息
			SysVO sys = bossSys.getSys();
			if (ObjectUtil.isNull(sys)) {
				Result.error("未配置系统配置表");
			}
			Long fileNo = IdWorker.getId();
			// 1、上传到本地
			if (sys.getFileType().equals(FileTypeEnum.LOCAL.getCode())) {
				File pic = new File(SystemUtil.PIC_STORAGE_PATH + fileNo.toString() + "."
						+ StrUtil.getSuffix(picFile.getOriginalFilename()));
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
				return Result.success(AliyunUtil.uploadPic(PlatformEnum.COURSE, picFile, BeanUtil.copyProperties(bossSys.getSys(), Aliyun.class)));
			}else{
				//3 上传七牛
				return Result.success(QiniuUtil.uploadPic(PlatformEnum.COURSE, imgUrl, picFile,BeanUtil.copyProperties(sys, Qiniu.class)));
			}
		}
		return Result.error("请选择上传的图片");
	}

	/**
	 * 上传文档接口
	 * 
	 * @author wuyun
	 */
	public Result<String> uploadDoc(MultipartFile docFile) {
		if (ObjectUtil.isNotNull(docFile)) {
			// 获取系统配置信息
			SysVO sys = bossSys.getSys();
			if (ObjectUtil.isNull(sys)) {
				Result.error("未配置系统配置表");
			}
			Long fileNo = IdWorker.getId();
			// 1、上传到本地
			if (sys.getFileType().equals(FileTypeEnum.LOCAL.getCode())) {
				File pic = new File(SystemUtil.DOC_STORAGE_PATH + fileNo.toString() + "."
						+ StrUtil.getSuffix(docFile.getOriginalFilename()));
				try {
					// 判断文件目录是否存在，不存在就创建文件目录
					if (!pic.getParentFile().exists()) {
						pic.getParentFile().mkdirs();// 创建父级文件路径
					}
					docFile.transferTo(pic);
					FileStorage fileStorage = new FileStorage();
					fileStorage.setFileName(docFile.getOriginalFilename());
					fileStorage.setFileNo(fileNo);
					fileStorage.setFileSize(docFile.getSize());
					fileStorage.setFileType(FileStorageTypeEnum.DOC.getCode());
					fileStorage.setFileUrl(pic.toString());
					fileStorageDao.save(fileStorage);
					return Result.success(pic.toString());
				} catch (Exception e) {
					logger.error("上传到本地失败", e);
					return Result.error("上传文件出错，请重新上传");
				}
			}
			return Result.success(AliyunUtil.uploadDoc(PlatformEnum.COURSE, docFile,
					BeanUtil.copyProperties(bossSys.getSys(), Aliyun.class)));
		}
		return Result.error("请选择上传的文件");

	}

}
