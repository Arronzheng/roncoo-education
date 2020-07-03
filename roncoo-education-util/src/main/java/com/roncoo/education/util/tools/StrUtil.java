package com.roncoo.education.util.tools;

import java.util.Random;

import com.xiaoleilu.hutool.crypto.SecureUtil;

/**
 * @author wujing
 */
public final class StrUtil {

	private StrUtil() {
	}

	public static String getSuffix(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".") + 1);
	}

	public static String getPrefix(String fileName) {
		return fileName.substring(0, fileName.lastIndexOf("."));
	}

	/**
	 * @return
	 */
	public static String getRandom(int bound) {
		Random ra = new Random();
		String result = "";
		for (int i = 1; i <= bound; i++) {
			result += ra.nextInt(10);
		}
		return result;
	}

	public static String get32UUID() {
		return SecureUtil.simpleUUID();
	}

	/**
	 *
	 * @return 返回随机字母数字组合字符串
	 */
	public static String getCode() {
		int length = 6; // 随机字符长度
		String val = "";
		Random random = new Random();

		//参数length，表示生成几位随机数
		for (int i = 0; i < length; i++) {
			//随机数由0-9，a-z,A-Z组成，数字占10个，字母占52个，数字、字母占比1:5（标准的应该是10:52）
			//random.nextInt(6) 0-5中6个数取一个
			String charOrNum = (random.nextInt(6)+6) % 6 >=1 ? "char" : "num";
			//输出字母还是数字
			if ("char".equalsIgnoreCase(charOrNum)) {
				//输出是大写字母还是小写字母，输出比例为1:1
				int temp = random.nextInt(2) % 2 == 0 ? 97 : 65;
				//char（65）-char(90) 为大写字母A-Z；char(97)-char(122)为小写字母a-z
				val += (char) (random.nextInt(26) + temp);
			} else if ("num".equalsIgnoreCase(charOrNum)) {
				val += String.valueOf(random.nextInt(10));
			}
		}
		return val;
	}

}
