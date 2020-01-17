package com.roncoo.education.system.service.common.req;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 系统配置表-查看
 */
@Data
@Accessors(chain = true)
public class SysUpdateRESQ implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键id")
	@JsonSerialize(using = ToStringSerializer.class)
	private Long id;
	/**
	 * 视频存储平台（1保利威视，2七牛）
	 */
	@ApiModelProperty(value = "视频存储平台（1保利威视，2腾讯）")
	private Integer videoType;
	/**
	 * useid
	 */
	@ApiModelProperty(value = "useid")
	private String polyvUseid;
	/**
	 * writetoken
	 */
	@ApiModelProperty(value = "writetoken")
	private String polyvWritetoken;
	/**
	 * readtoken
	 */
	@ApiModelProperty(value = "readtoken")
	private String polyvReadtoken;
	/**
	 * secretkey
	 */
	@ApiModelProperty(value = "secretkey")
	private String polyvSecretkey;
	/**
	 * 文件存储类型（1阿里云，2七牛）
	 */
	@ApiModelProperty(value = "文件存储类型（1阿里云，2七牛，4腾讯）")
	private Integer fileType;
	/**
	 * access_key_id
	 */
	@ApiModelProperty(value = "access_key_id")
	private String aliyunAccessKeyId;
	/**
	 * access_key_secret
	 */
	@ApiModelProperty(value = "access_key_secret")
	private String aliyunAccessKeySecret;
	/**
	 * aliyunOssUrl
	 */
	@ApiModelProperty(value = "aliyunOssUrl")
	private String aliyunOssUrl;
	/**
	 * aliyunOssBucket
	 */
	@ApiModelProperty(value = "aliyunOssBucket")
	private String aliyunOssBucket;
	/**
	 * smsCode
	 */
	@ApiModelProperty(value = "smsCode")
	private String smsCode;
	/**
	 * 短信签名
	 */
	@ApiModelProperty(value = "短信签名")
	private String signName;
	/**
	 * 短信应用appid
	 */
	@ApiModelProperty(value = "短信应用appid")
	private Integer msgAppid;
	/**
	 * 短信应用appkey
	 */
	@ApiModelProperty(value = "短信应用appkey")
	private String msgAppkey;
	/**
	 * 腾讯cos区域，阿里的oas_vault
	 */
	@ApiModelProperty(value = "oas_vault")
	private String aliyunOasVault;
	/**
	 * 支付通道（1龙果支付，2其他）
	 */
	@ApiModelProperty(value = "支付通道（1龙果支付，2其他）")
	private Integer payType;
	/**
	 * roncooKey
	 */
	@ApiModelProperty(value = "roncooKey")
	private String payKey;
	/**
	 * roncooSecret
	 */
	@ApiModelProperty(value = "roncooSecret")
	private String paySecret;
	/**
	 * 支付请求
	 */
	@ApiModelProperty(value = "支付请求")
	private String payUrl;
	/**
	 * 回调地址
	 */
	@ApiModelProperty(value = "回调地址")
	private String notifyUrl;
}
