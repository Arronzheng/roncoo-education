package com.roncoo.education.util.qiniu;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class Qiniu implements Serializable {

	private static final long serialVersionUID = 1L;

	private String polyvUseid;
	private String polyvWritetoken;
	private String polyvReadtoken;
	private String polyvSecretkey;
	private String aliyunAccessKeyId;
	private String aliyunAccessKeySecret;
	private String aliyunOssUrl;
	private String aliyunOssBucket;
	private String aliyunOasVault;
	private String payUrl;
	private String payKey;
	private String paySecret;
	private String notifyUrl;
	private String smsCode;
	private String signName;
}
