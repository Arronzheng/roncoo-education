package com.roncoo.education.util.qiniu;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.roncoo.education.util.enums.CatalogueEnum;
import com.roncoo.education.util.enums.PlatformEnum;
import com.roncoo.education.util.tools.IdWorker;
import com.roncoo.education.util.tools.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class QiniuUtil {

    public static String uploadPic(PlatformEnum platformEnum, MultipartFile picFile, Qiniu qiniu) {
        Configuration cfg = new Configuration();
        UploadManager uploadManager = new UploadManager(cfg);
        Long fileNo = IdWorker.getId();
        String key = platformEnum.name().toLowerCase()+"/"+fileNo;
        Auth auth = Auth.create(qiniu.getAliyunAccessKeyId(), qiniu.getAliyunAccessKeySecret());
        String upToken = auth.uploadToken(qiniu.getAliyunOssBucket());
        DefaultPutRet putRet = null;
        {
            try {
                InputStream inputStream = picFile.getInputStream();
                Response response = uploadManager.put(inputStream, key, upToken, null, null);
                //解析上传成功的结果
                putRet = JSONUtil.parseObject(response.bodyString(), DefaultPutRet.class);
            }catch(QiniuException ex){
                Response r = ex.response;
                log.error("上传失败", r.toString());
                return "";
            } catch (IOException e) {
                log.error("上传失败", e);
                return "";
            }
        }
        return qiniu.getAliyunOssUrl()+putRet.key;
    }


    public static String uploadPic(CatalogueEnum catalogueEnum, String imgUrl, MultipartFile picFile, Qiniu qiniu) {
        Configuration cfg = new Configuration();
        UploadManager uploadManager = new UploadManager(cfg);
        Long fileNo = IdWorker.getId();
        String key = catalogueEnum.name().toLowerCase()+"/"+fileNo;
        if(!StringUtils.isEmpty(imgUrl)){
            try {
                deletePic(imgUrl,qiniu);
            } catch (QiniuException e) {
                log.error("上传失败", e.response.toString());
                return "";
            }
        }
        Auth auth = Auth.create(qiniu.getAliyunAccessKeyId(), qiniu.getAliyunAccessKeySecret());
        String upToken = auth.uploadToken(qiniu.getAliyunOssBucket());
        DefaultPutRet putRet = null;
        {
            InputStream inputStream = null;
            try {
                inputStream = picFile.getInputStream();
                Response response = uploadManager.put(inputStream, key, upToken, null, null);
                //解析上传成功的结果
                putRet = JSONUtil.parseObject(response.bodyString(), DefaultPutRet.class);
                return qiniu.getAliyunOssUrl()+putRet.key;
            }catch(QiniuException ex){
                Response r = ex.response;
                log.error("上传失败", r.toString());
                return "";
            } catch (IOException e) {
                log.error("上传失败", e);
                return "";
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                    }
                }
            }
        }
    }

    public static void deletePic(String url, Qiniu qiniu) throws QiniuException {
        Configuration cfg = new Configuration();
        Auth auth = Auth.create(qiniu.getAliyunAccessKeyId(), qiniu.getAliyunAccessKeySecret());
        BucketManager bucketManager = new BucketManager(auth, cfg);
        String key = url.replaceAll(qiniu.getAliyunOssUrl(), "");
        bucketManager.delete(qiniu.getAliyunOssBucket(), key);

    }
}
