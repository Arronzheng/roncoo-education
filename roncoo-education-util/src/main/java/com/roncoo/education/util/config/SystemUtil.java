package com.roncoo.education.util.config;

import java.io.IOException;
import java.util.Properties;

/**
 * 配置文件读取工具类
 *
 * @author wujing
 */
public final class SystemUtil {

	private SystemUtil() {
	}

	private static final Properties properties = new Properties();

	static {
		try {
			properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("system.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String getProperty(String keyName) {
		return properties.getProperty(keyName, "").trim();
	}

	public static final String ALIYUN_OSS_ENDPOINT = getProperty("aliyun_oss_endpoint");
	public static final String ALIYUN_OAS_URL = getProperty("aliyun_oas_url");

	public static final String PERIOD_VIDEO_PATH = getProperty("period_video_path");
	public static final String PIC_PATH = getProperty("pic_path");

	// 支付回调配置
	public static final String RONCOO_PAY_NOTIFY = getProperty("roncoo_pay_notify");
	public static final String WEIXIN_PAY_NOTIFY = getProperty("weixin_pay_notify");
	public static final String ALI_PAY_NOTIFY = getProperty("ali_pay_notify");

	public static final String POLYV_GETCATAURL = getProperty("polyv_getCataUrl");
	public static final String POLYV_UPLOADVIDEO = getProperty("polyv_uploadVideo");
	public static final String POLYV_CHANGECATAURL = getProperty("polyv_changeCataUrl");
	public static final String POLYV_DELVIDEOBYID = getProperty("polyv_delVideoById");
	public static final String POLYV_DELETEVIDEO = getProperty("polyv_deleteVideo");
	public static final String POLYV_QUESTION = getProperty("polyv_question");
	public static final String POLYV_CODESECRETKEY = getProperty("polyv_codeSecretKey");
	public static final String POLYV_GETTOKEN = getProperty("polyv_getToken");

	public static final String TENCENT_DELETEVIDEO = getProperty("tencent_deleteVideo");

	public static final String LECTURER_DEFAULT_PROPORTION = getProperty("lecturer_default_proportion");

	public static final String CONTENT_LENGTH = getProperty("content_length");

	public static final String TEST_COURSE = getProperty("test_course");

	public static final String DOC_STORAGE_PATH = getProperty("doc_storage_path");
	public static final String PIC_STORAGE_PATH = getProperty("pic_storage_path");
	public static final String VIDEO_STORAGE_PATH = getProperty("video_storage_path");

	//微信登录配置
	public static final String APP_ID = getProperty("app_id");
	public static final String APP_SECRET = getProperty("app_secret");
	public static final String TOKEN = getProperty("token");
	public static final String ACCESS_TOKEN_URL = getProperty("access_token_url");
	public static final String OAUTH_ACCESS_TOKEN_URL = getProperty("oauth_access_token_url");
	public static final String USER_INFO_URL = getProperty("user_info_url");
	public static final String REDIRECT_URI = getProperty("redirect_uri");
	public static final String JSAPI_TICKET_URL = getProperty("jsapi_ticket_url");
	public static final String CREATE_MENU_URL = getProperty("create_menu_url");

}
