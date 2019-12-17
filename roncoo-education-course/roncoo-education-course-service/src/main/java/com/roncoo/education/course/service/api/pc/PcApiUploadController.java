/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.course.service.api.pc;

import com.roncoo.education.course.service.common.req.FileDeleteREQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.roncoo.education.course.service.biz.pc.PcApiUploadBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 上传接口
 * 
 */
@RestController
@RequestMapping(value = "/course/pc/api/upload")
public class PcApiUploadController extends BaseController {

	@Autowired
	private PcApiUploadBiz biz;

	/**
	 * 上传视频接口
	 *
	 * @param videoFile
	 * @param url  原视频路径
	 */
	@ApiOperation(value = "上传视频接口", notes = "上传视频")
	@RequestMapping(value = "/video", method = RequestMethod.POST)
	public Result<String> uploadVideo(@RequestParam(value = "videoFile", required = false) MultipartFile videoFile, @RequestParam(value = "url", required = false) String url) {
		return biz.uploadVideo(videoFile,url);
	}

	/**
	 * 上传图片接口
	 * 
	 * @param picFile
	 * @param imgUrl  原图片路径
	 */
	@ApiOperation(value = "上传图片接口", notes = "上传图片")
	@RequestMapping(value = "/pic", method = RequestMethod.POST)
	public Result<String> uploadPic(@RequestParam(value = "picFile", required = false) MultipartFile picFile, @RequestParam(value = "imgUrl", required = false)  String imgUrl) {
		return biz.uploadPic(picFile,imgUrl);
	}

	/**
	 * 上传文档接口
	 * 
	 * @param docFile
	 */
	@ApiOperation(value = "上传文档接口", notes = "上传文档")
	@RequestMapping(value = "/doc", method = RequestMethod.POST)
	public Result<String> uploadDoc(@RequestParam(name = "docFile", required = false) MultipartFile docFile, @RequestParam(name = "url", required = false) String url) {
		return biz.uploadDoc(docFile, url);
	}

	/**
	 * 删除文件接口
	 *
	 * @param fileDeleteREQ
	 */
	@ApiOperation(value = "删除文件接口", notes = "删除文件")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Result<Integer> deleteFile(@RequestBody FileDeleteREQ fileDeleteREQ) {
		return biz.deleteFile(fileDeleteREQ);
	}

}
