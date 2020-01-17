package com.roncoo.education.util.tencentcloud;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.*;
import com.qcloud.cos.region.Region;
import com.qcloud.vod.VodUploadClient;
import com.qcloud.vod.model.VodUploadRequest;
import com.qcloud.vod.model.VodUploadResponse;
import com.roncoo.education.util.config.SystemUtil;
import com.roncoo.education.util.enums.CatalogueEnum;
import com.roncoo.education.util.tools.IdWorker;
import com.roncoo.education.util.tools.SHA1Util;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.vod.v20180717.VodClient;
import com.tencentcloudapi.vod.v20180717.models.DeleteMediaRequest;
import com.tencentcloudapi.vod.v20180717.models.DeleteMediaResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Slf4j
public class TencentUtil {
    public static String uploadFile(CatalogueEnum catalogueEnum, String url, MultipartFile file, Tencent tencent) {
        // 1 初始化用户身份信息（secretId, secretKey）。
        String secretId = tencent.getAliyunAccessKeyId();
        String secretKey = tencent.getAliyunAccessKeySecret();
        String bucket = tencent.getAliyunOssBucket();
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置 bucket 的区域, COS 地域的简称请参照/**/ https://cloud.tencent.com/document/product/436/6224
        // clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
        Region region = new Region(tencent.getAliyunOasVault());
        ClientConfig clientConfig = new ClientConfig(region);
        // 3 生成 cos 客户端。
        COSClient cosClient = new COSClient(cred, clientConfig);
        InputStream fileInputStream = null;
        try{
            fileInputStream = file.getInputStream();
            String fileName = file.getOriginalFilename();
            String suffix = fileName.substring(fileName.lastIndexOf("."));
            Long fileNo = IdWorker.getId();
            String key = catalogueEnum.name().toLowerCase()+"/"+fileNo+suffix;
            if(!StringUtils.isEmpty(url)){
                deleteFile(url,tencent);
            }
            PutObjectResult putObjectResult = cosClient.putObject(bucket, key, fileInputStream, new ObjectMetadata());
            String etag = putObjectResult.getETag();

            return tencent.getAliyunOssUrl()+key;
        } catch (IOException e){
            log.error("上传失败", e);
            return "";
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                    // 关闭客户端(关闭后台线程)
                    cosClient.shutdown();
                } catch (IOException e) {
                }
            }
        }
    }

    public static String uploadFile(CatalogueEnum catalogueEnum, String url, File file, Tencent tencent) {
        // 1 初始化用户身份信息（secretId, secretKey）。
        String secretId = tencent.getAliyunAccessKeyId();
        String secretKey = tencent.getAliyunAccessKeySecret();
        String bucket = tencent.getAliyunOssBucket();
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置 bucket 的区域, COS 地域的简称请参照/**/ https://cloud.tencent.com/document/product/436/6224
        // clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
        Region region = new Region(tencent.getAliyunOasVault());
        ClientConfig clientConfig = new ClientConfig(region);
        // 3 生成 cos 客户端。
        COSClient cosClient = new COSClient(cred, clientConfig);
        InputStream fileInputStream = null;
        try{
            fileInputStream = new FileInputStream(file);
            String fileName = file.getName();
    //        String suffix = fileName.substring(fileName.lastIndexOf("."));
    //        Long fileNo = IdWorker.getId();
            String key = catalogueEnum.name().toLowerCase()+"/"+fileName;
            if(!StringUtils.isEmpty(url)){
                deleteFile(url,tencent);
            }
            PutObjectResult putObjectResult = cosClient.putObject(bucket, key, fileInputStream, new ObjectMetadata());
            String etag = putObjectResult.getETag();
            return tencent.getAliyunOssUrl()+key;
        } catch (IOException e){
            log.error("上传失败", e);
            return "";
        }finally {
            try {
                fileInputStream.close();
                // 关闭客户端(关闭后台线程)
                cosClient.shutdown();
            } catch (IOException e) {
                e.printStackTrace();
                return "";
            }
        }
    }
    /*
    * 云点播上传视频
    * */
    public static VodUploadResponse uploadVideo(String url, File file, Tencent tencent) {
        VodUploadClient client = new VodUploadClient(tencent.getAliyunAccessKeyId(), tencent.getAliyunAccessKeySecret());
        VodUploadRequest request = new VodUploadRequest();
        request.setMediaFilePath(file.getPath());
        request.setProcedure("video");
        VodUploadResponse response = null;
        try {
            response = client.upload(tencent.getAliyunOasVault(), request);
        } catch (Exception e) {
            return null;
        }
        return response;
    }

    public static void deleteFile(String url, Tencent tencent){
        String secretId = tencent.getAliyunAccessKeyId();
        String secretKey = tencent.getAliyunAccessKeySecret();
        String bucket = tencent.getAliyunOssBucket();
        String key = url.replaceAll(tencent.getAliyunOssUrl(), "");
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置 bucket 的区域, COS 地域的简称请参照/**/ https://cloud.tencent.com/document/product/436/6224
        // clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
        Region region = new Region(tencent.getAliyunOasVault());
        ClientConfig clientConfig = new ClientConfig(region);
        // 3 生成 cos 客户端。
        COSClient cosClient = new COSClient(cred, clientConfig);
        cosClient.deleteObject(bucket, key);
        cosClient.shutdown();
    }

    /*
     * 云点播删除视频
     * */
    public static DeleteMediaResponse deleteVideo(String fileId, Tencent tencent) {
        try{
            Credential cred = new Credential(tencent.getAliyunAccessKeyId(), tencent.getAliyunAccessKeySecret());
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("vod.tencentcloudapi.com");
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            VodClient client = new VodClient(cred, "ap-chongqing", clientProfile);
            String params = "{\"FileId\":\""+ fileId +"\"}";
            DeleteMediaRequest req = DeleteMediaRequest.fromJsonString(params, DeleteMediaRequest.class);
            DeleteMediaResponse resp = client.DeleteMedia(req);
            System.out.println(DeleteMediaRequest.toJsonString(resp));
            return resp;
        } catch (TencentCloudSDKException e) {
            return null;
        }
    }

    /*
    * 短信发送
    * */
    public static boolean sendMsg(String phone, String code, Tencent tencent){
        // 短信应用 SDK AppID
        int appid = tencent.getMsgAppid(); // SDK AppID 以1400开头
        // 短信应用 SDK AppKey
        String appkey = tencent.getMsgAppkey();
        // 需要发送短信的手机号码
        String phoneNumbers = phone;
        // 短信模板 ID，需要在短信应用中申请
        int templateId = Integer.valueOf(tencent.getSmsCode()); // NOTE: 这里的模板 ID`7839`只是示例，真实的模板 ID 需要在短信控制台中申请
        // 签名
        String smsSign = tencent.getSignName(); // NOTE: 签名参数使用的是`签名内容`，而不是`签名ID`。这里的签名"腾讯云"只是示例，真实的签名需要在短信控制台申请
        try {
            ArrayList<String> params = new ArrayList<String>();
            params.add(code);
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNumbers,templateId, params, smsSign, "", "");
            System.out.println(result);
            if(result.result == 0){
                return true;
            }else{
                log.error("发送失败，code={},message={}", result.result, result.errMsg);
                return false;
            }
        } catch (HTTPException e) {
            // HTTP 响应码错误
            e.printStackTrace();
            log.error("发送失败，code={},message={}", e.getStatusCode(), e.getMessage());
            return false;
        } catch (JSONException e) {
            // JSON 解析错误
            e.printStackTrace();
            log.error("发送失败，message={}", e.getMessage());
            return false;
        } catch (IOException e) {
            // 网络 IO 错误
            e.printStackTrace();
            log.error("发送失败，message={}", e.getMessage());
            return false;
        }
    }
}
