package com.roncoo.education.util.polyv;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 获取课时code值实体类
 *
 * @author forest
 */
@Data
@Accessors(chain = true)
public class PolyvSign implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 课时vid
	 */
	private String vid;

	/**
	 * 播放ip
	 */
	private String ip;

	/**
	 * 用户编号
	 */
	private Long userNo;

}
