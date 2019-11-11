/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : education_system

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 11/11/2019 09:53:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for msg
-- ----------------------------
DROP TABLE IF EXISTS `msg`;
CREATE TABLE `msg`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `status_id` tinyint(3) UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态(1有效, 0无效)',
  `sort` int(11) NOT NULL DEFAULT 1 COMMENT '排序',
  `msg_type` tinyint(3) NOT NULL DEFAULT 1 COMMENT '短信类型(1系统消息,2其他)',
  `msg_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '短信标题',
  `msg_text` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '短信内容',
  `is_time_send` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否定时发送（1是，0否）',
  `send_time` datetime(0) NULL DEFAULT NULL COMMENT '发送时间',
  `is_send` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否已发送(1是;0否)',
  `is_top` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否置顶(1是;0否)',
  `back_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '后台备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '站内信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of msg
-- ----------------------------
INSERT INTO `msg` VALUES (1103192777748422657, '2019-03-06 15:18:39', '2019-03-06 15:18:39', 1, 1, 1, '测试1', '<p>测试1<br /></p>', 0, NULL, 0, 0, NULL);

-- ----------------------------
-- Table structure for msg_template
-- ----------------------------
DROP TABLE IF EXISTS `msg_template`;
CREATE TABLE `msg_template`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `status_id` tinyint(3) UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态(1有效, 0无效)',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '标题',
  `remark` varchar(1023) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `sort` int(11) NOT NULL DEFAULT 1 COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '消息模板' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of msg_template
-- ----------------------------
INSERT INTO `msg_template` VALUES (1102813675380137986, '2019-03-05 14:12:14', '2019-03-05 14:12:14', 1, '', '尊敬的{{name}}用户，您有一个{{courseName}}课程抵用券即将过期', 1);

-- ----------------------------
-- Table structure for msg_user
-- ----------------------------
DROP TABLE IF EXISTS `msg_user`;
CREATE TABLE `msg_user`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `status_id` tinyint(3) UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态(1有效, 0无效)',
  `sort` int(11) NOT NULL DEFAULT 1 COMMENT '排序',
  `msg_id` bigint(20) NOT NULL COMMENT '短信ID',
  `user_no` bigint(20) NOT NULL DEFAULT 0 COMMENT '用户编号',
  `mobile` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '手机号',
  `msg_type` tinyint(3) NOT NULL DEFAULT 1 COMMENT '短信类型(1系统消息,2其他)',
  `msg_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '短信标题',
  `is_read` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否阅读(1是;0否)',
  `is_top` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否置顶(1是;0否)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '站内信用户记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for nav_bar
-- ----------------------------
DROP TABLE IF EXISTS `nav_bar`;
CREATE TABLE `nav_bar`  (
  `id` bigint(20) NOT NULL DEFAULT 0 COMMENT '主键',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `status_id` tinyint(3) UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态(1有效, 0无效)',
  `sort` int(11) NOT NULL DEFAULT 1 COMMENT '排序',
  `nav_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '导航标题',
  `nav_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '导航url',
  `target` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '跳转方式',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '头部导航' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of nav_bar
-- ----------------------------
INSERT INTO `nav_bar` VALUES (1060096588522270722, '2018-11-07 17:09:32', '2018-11-07 17:09:32', 1, 2, '课程中心', '/list', '_self');
INSERT INTO `nav_bar` VALUES (1064696486139854849, '2018-11-20 09:47:57', '2018-11-20 09:47:57', 1, 3, '首页', '/index', '_self');
INSERT INTO `nav_bar` VALUES (1085443582223257601, '2019-01-16 15:49:36', '2019-01-16 15:49:36', 0, 1, '讲师招募', '/recruit', '_self');

-- ----------------------------
-- Table structure for sys
-- ----------------------------
DROP TABLE IF EXISTS `sys`;
CREATE TABLE `sys`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `status_id` tinyint(3) UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态(1有效, 0无效)',
  `sort` int(11) NOT NULL DEFAULT 1 COMMENT '排序',
  `video_type` tinyint(3) NOT NULL DEFAULT 1 COMMENT '视频存储平台（1保利威视，2七牛）',
  `polyv_useid` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'useid',
  `polyv_writetoken` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'writetoken',
  `polyv_readtoken` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'readtoken',
  `polyv_secretkey` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'secretkey',
  `file_type` tinyint(3) NOT NULL DEFAULT 1 COMMENT '文件存储类型（1阿里云，2七牛）',
  `aliyun_access_key_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'access_key_id',
  `aliyun_access_key_secret` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'access_key_secret',
  `aliyun_oss_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'oss_url',
  `aliyun_oss_bucket` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'oss_bucket',
  `aliyun_oas_vault` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'oas_vault',
  `pay_type` tinyint(3) NOT NULL DEFAULT 1 COMMENT '支付通道（1龙果支付，2其他）',
  `pay_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付请求',
  `pay_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'roncoo_key',
  `pay_secret` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'roncoo_secret',
  `notify_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回调地址',
  `sms_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'sms_code',
  `sign_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '短信签名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys
-- ----------------------------
INSERT INTO `sys` VALUES (1, '1899-12-30 01:00:00', '2019-11-02 08:52:40', 1, 1, 1, '******', '******', '******', '******', 2, 'ttOkSL8cf8nUBA-9UoBHJX_9pVrKQTDzASICH7u6', 'LiJN5fCXMl6m74Ah2tB8PmheLudRFRfM58iIL3n6', 'http://q08d75i85.bkt.clouddn.com/', 'hwxs', NULL, 0, '******', '******', '******', '******', '******', '******');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` bigint(20) UNSIGNED NOT NULL COMMENT '主键',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `user_no` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '操作人',
  `real_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '真实姓名',
  `ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT 'IP地址',
  `operation` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户操作',
  `method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求方法',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求路径',
  `content` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求参数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '后台操作日志表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES (1087612566293417986, '2019-01-22 15:26:55', 2018033111202589416, '超级管理员', '113.111.55.245', '录播课程审核', 'POST', '/course/courseAudit/audit', '{\"pageCurrent\":0,\"pageSize\":0,\"id\":1085453180200448002,\"auditStatus\":1,\"auditOpinion\":\"系统默认通过\"}');
INSERT INTO `sys_log` VALUES (1087983955948609537, '2019-01-23 16:04:09', 2018033111202589416, '超级管理员', '0:0:0:0:0:0:0:1', '站点信息更新', 'POST', '/system/website/updateWebsite', '{\"pageCurrent\":0,\"pageSize\":0,\"id\":934374967448227841,\"websiteTitle\":\"领课教育1\",\"websiteKeyword\":\"领课教育,领课网络,在线教育\",\"websiteDesc\":\"领课在线教育开源系统是基于领课团队多年的在线教育开发和运营经验的产品\",\"copyright\":\"Copyright &copy; 2018-2023 领课教育 版权所有\",\"icp\":\"粤ICP备16009964号-1\",\"prn\":\"粤公网安备 44010602005774号\",\"isEnableStatistics\":1,\"isShowService\":1,\"service1\":\"297115770\",\"service2\":\"3155237748\"}');
INSERT INTO `sys_log` VALUES (1102816207228772353, '2019-03-05 14:20:45', 2018033111202589416, '超级管理员', '113.111.48.159', '录播课程审核', 'POST', '/course/courseAudit/audit', '{\"pageCurrent\":0,\"pageSize\":0,\"id\":1085453180200448002,\"auditStatus\":1,\"auditOpinion\":\"系统默认通过\"}');
INSERT INTO `sys_log` VALUES (1102867757029478401, '2019-03-05 17:47:08', 2018033111202589416, '超级管理员', '0:0:0:0:0:0:0:1', '录播课程审核', 'POST', '/course/courseAudit/audit', '{\"pageCurrent\":0,\"pageSize\":0,\"id\":1080752794290032642,\"auditStatus\":1,\"auditOpinion\":\"系统默认通过\"}');
INSERT INTO `sys_log` VALUES (1102868619776831490, '2019-03-05 17:50:34', 2018033111202589416, '超级管理员', '0:0:0:0:0:0:0:1', '录播课程审核', 'POST', '/course/courseAudit/audit', '{\"pageCurrent\":0,\"pageSize\":0,\"id\":1085453180200448002,\"auditStatus\":1,\"auditOpinion\":\"系统默认通过\"}');
INSERT INTO `sys_log` VALUES (1102869835911081986, '2019-03-05 17:55:24', 2018033111202589416, '超级管理员', '0:0:0:0:0:0:0:1', '录播课程审核', 'POST', '/course/courseAudit/audit', '{\"pageCurrent\":0,\"pageSize\":0,\"id\":1085453180200448002,\"auditStatus\":1,\"auditOpinion\":\"系统默认通过\"}');
INSERT INTO `sys_log` VALUES (1102870024788979713, '2019-03-05 17:56:09', 2018033111202589416, '超级管理员', '0:0:0:0:0:0:0:1', '录播课程审核', 'POST', '/course/courseAudit/audit', '{\"pageCurrent\":0,\"pageSize\":0,\"id\":1085453180200448002,\"auditStatus\":1,\"auditOpinion\":\"系统默认通过\"}');
INSERT INTO `sys_log` VALUES (1102871704779698178, '2019-03-05 18:02:50', 2018033111202589416, '超级管理员', '0:0:0:0:0:0:0:1', '录播课程审核', 'POST', '/course/courseAudit/audit', '{\"pageCurrent\":0,\"pageSize\":0,\"id\":1080759557655564289,\"auditStatus\":1,\"auditOpinion\":\"系统默认通过\"}');
INSERT INTO `sys_log` VALUES (1102872414426574849, '2019-03-05 18:05:39', 2018033111202589416, '超级管理员', '0:0:0:0:0:0:0:1', '录播课程审核', 'POST', '/course/courseAudit/audit', '{\"pageCurrent\":0,\"pageSize\":0,\"id\":1080759102707798018,\"auditStatus\":1,\"auditOpinion\":\"系统默认通过\"}');
INSERT INTO `sys_log` VALUES (1102877039468818433, '2019-03-05 18:24:01', 2018033111202589416, '超级管理员', '0:0:0:0:0:0:0:1', '录播课程审核', 'POST', '/course/courseAudit/audit', '{\"pageCurrent\":0,\"pageSize\":0,\"id\":1080758482462511106,\"auditStatus\":1,\"auditOpinion\":\"系统默认通过\"}');
INSERT INTO `sys_log` VALUES (1102877626100953090, '2019-03-05 18:26:21', 2018033111202589416, '超级管理员', '0:0:0:0:0:0:0:1', '录播课程审核', 'POST', '/course/courseAudit/audit', '{\"pageCurrent\":0,\"pageSize\":0,\"id\":1080757537695535105,\"auditStatus\":1,\"auditOpinion\":\"系统默认通过\"}');
INSERT INTO `sys_log` VALUES (1102878378311626753, '2019-03-05 18:29:21', 2018033111202589416, '超级管理员', '0:0:0:0:0:0:0:1', '录播课程审核', 'POST', '/course/courseAudit/audit', '{\"pageCurrent\":0,\"pageSize\":0,\"id\":1080757024136564738,\"auditStatus\":1,\"auditOpinion\":\"系统默认通过\"}');
INSERT INTO `sys_log` VALUES (1102879222457249793, '2019-03-05 18:32:42', 2018033111202589416, '超级管理员', '0:0:0:0:0:0:0:1', '录播课程审核', 'POST', '/course/courseAudit/audit', '{\"pageCurrent\":0,\"pageSize\":0,\"id\":1080753909630963714,\"auditStatus\":1,\"auditOpinion\":\"系统默认通过\"}');
INSERT INTO `sys_log` VALUES (1102879370209996802, '2019-03-05 18:33:17', 2018033111202589416, '超级管理员', '0:0:0:0:0:0:0:1', '录播课程审核', 'POST', '/course/courseAudit/audit', '{\"pageCurrent\":0,\"pageSize\":0,\"id\":1080749913478467586,\"auditStatus\":1,\"auditOpinion\":\"系统默认通过\"}');
INSERT INTO `sys_log` VALUES (1102879702436622337, '2019-03-05 18:34:36', 2018033111202589416, '超级管理员', '0:0:0:0:0:0:0:1', '录播课程审核', 'POST', '/course/courseAudit/audit', '{\"pageCurrent\":0,\"pageSize\":0,\"id\":1080748086536114177,\"auditStatus\":1,\"auditOpinion\":\"系统默认通过\"}');
INSERT INTO `sys_log` VALUES (1103602638478073857, '2019-03-07 18:25:45', 2018033111202589416, '超级管理员', '116.21.1.86', '系统配置更新', 'POST', '/system/sys/updateSys', '{\"pageCurrent\":0,\"pageSize\":0,\"id\":1,\"videoType\":1,\"polyvUseid\":\"e2fe557cf3\",\"polyvWritetoken\":\"e2e795e5-1129-4a26-adb3-0e962473aa94\",\"polyvReadtoken\":\"18dc4295-adcb-44a8-a989-f5c8402e798d\",\"polyvSecretkey\":\"b9c1rGiA5Y\",\"fileType\":1,\"aliyunAccessKeyId\":\"n3qRxyH2JyF6pFJN\",\"aliyunAccessKeySecret\":\"VUjoE6qO9H7qYKD78YyfF1kgzy6Gun\",\"payType\":1,\"aliyunOssUrl\":\"http://static-dev.roncoo.com/\",\"aliyunOssBucket\":\"roncoo-dev\",\"aliyunOasVault\":\"roncoo_college\",\"payUrl\":\"http://47.101.63.159:6001/cnpPay/initPay\",\"payKey\":\"3de903c57ffa4534ad62d134a504a685\",\"paySecret\":\"7a299f1c72ae466cae161cbcd60060a4\",\"notifyUrl\":\"http://roncoo.vicp.net/api/callback/order/roncoo\"}');
INSERT INTO `sys_log` VALUES (1103603270001844225, '2019-03-07 18:28:16', 2018033111202589416, '超级管理员', '116.21.1.86', '系统配置更新', 'POST', '/system/sys/updateSys', '{\"pageCurrent\":0,\"pageSize\":0,\"id\":1,\"videoType\":1,\"polyvUseid\":\"e2fe557cf3\",\"polyvWritetoken\":\"e2e795e5-1129-4a26-adb3-0e962473aa94\",\"polyvReadtoken\":\"18dc4295-adcb-44a8-a989-f5c8402e798d\",\"polyvSecretkey\":\"b9c1rGiA5Y\",\"fileType\":1,\"aliyunAccessKeyId\":\"n3qRxyH2JyF6pFJN\",\"aliyunAccessKeySecret\":\"VUjoE6qO9H7qYKD78YyfF1kgzy6Gun\",\"payType\":1,\"aliyunOssUrl\":\"http://static-dev.roncoo.com/\",\"aliyunOssBucket\":\"roncoo-dev\",\"aliyunOasVault\":\"roncoo_college\",\"payUrl\":\"http://47.101.63.159:6001/cnpPay/initPay\",\"payKey\":\"3de903c57ffa4534ad62d134a504a685\",\"paySecret\":\"7a299f1c72ae466cae161cbcd60060a4\",\"notifyUrl\":\"http://roncoo.vicp.net/api/callback/order/roncoo\"}');
INSERT INTO `sys_log` VALUES (1103603626370883586, '2019-03-07 18:29:41', 2018033111202589416, '超级管理员', '116.21.1.86', '修改状态', 'POST', '/user/userExt/updateStatusId', '{\"pageCurrent\":0,\"pageSize\":0,\"id\":1098848357166346241,\"statusId\":0}');
INSERT INTO `sys_log` VALUES (1103603651440238594, '2019-03-07 18:29:47', 2018033111202589416, '超级管理员', '116.21.1.86', '修改状态', 'POST', '/user/userExt/updateStatusId', '{\"pageCurrent\":0,\"pageSize\":0,\"id\":1098848357166346241,\"statusId\":1}');
INSERT INTO `sys_log` VALUES (1103603807459958785, '2019-03-07 18:30:24', 2018033111202589416, '超级管理员', '116.21.1.86', '讲师信息修改', 'POST', '/user/lecturerAudit/update', '{\"pageCurrent\":0,\"pageSize\":0,\"id\":1100757103337525250,\"sort\":1,\"lecturerUserNo\":2019022721353527,\"lecturerName\":\"88\",\"lecturerEmail\":\"163services@163.com\",\"introduce\":\"<p>qqqq</p>\",\"lecturerProportion\":70}');
INSERT INTO `sys_log` VALUES (1103841871046336513, '2019-03-08 10:16:23', 2018033111202589416, '超级管理员', '116.21.1.86', '站点信息更新', 'POST', '/system/website/updateWebsite', '{\"pageCurrent\":0,\"pageSize\":0,\"id\":934374967448227841,\"websiteTitle\":\"领课教育\",\"websiteKeyword\":\"领课教育,领课网络,在线教育\",\"websiteDesc\":\"领课在线教育开源系统是基于领课团队多年的在线教育开发和运营经验的产品\",\"copyright\":\"Copyright &copy; 2018-2023 领课教育 版权所有\",\"icp\":\"粤ICP备16009964号-1\",\"prn\":\"粤公网安备 44010602005774号\",\"isEnableStatistics\":1,\"isShowService\":1,\"service1\":\"297115770\",\"service2\":\"3155237748\",\"userAgreement\":\"<p></p>\\n<p></p>\\n<p></p>\\n<p></p>\\n<p></p>\\n<p><br /></p>\\n<p></p>\\n<p><br /></p>\\n<p></p>\\n<p><br /></p>\\n<p></p>\\n<p><br /></p>\\n<p></p>\\n<p><br /></p>\",\"recruitInfo\":\"<p></p>\\n<p></p>\\n<p></p>\\n<p></p>\\n<p></p>\\n<p><br /></p>\\n<p></p>\\n<p><br /></p>\\n<p></p>\\n<p><br /></p>\\n<p></p>\\n<p><br /></p>\\n<p></p>\\n<p><br /></p>\",\"entryAgreement\":\"<p></p>\\n<p></p>\\n<p></p>\\n<p></p>\\n<p></p>\\n<p><br /></p>\\n<p></p>\\n<p><br /></p>\\n<p></p>\\n<p><br /></p>\\n<p></p>\\n<p><br /></p>\\n<p></p>\\n<p><br /></p>\"}');
INSERT INTO `sys_log` VALUES (1103842782971912193, '2019-03-08 10:20:00', 2018033111202589416, '超级管理员', '116.21.1.86', '系统配置更新', 'POST', '/system/sys/updateSys', '{\"pageCurrent\":0,\"pageSize\":0,\"id\":1,\"videoType\":1,\"polyvUseid\":\"e2fe557cf3\",\"polyvWritetoken\":\"e2e795e5-1129-4a26-adb3-0e962473aa94\",\"polyvReadtoken\":\"18dc4295-adcb-44a8-a989-f5c8402e798d\",\"polyvSecretkey\":\"b9c1rGiA5Y\",\"fileType\":1,\"aliyunAccessKeyId\":\"n3qRxyH2JyF6pFJN\",\"aliyunAccessKeySecret\":\"VUjoE6qO9H7qYKD78YyfF1kgzy6Gun\",\"payType\":1,\"aliyunOssUrl\":\"http://static-dev.roncoo.com/\",\"aliyunOssBucket\":\"roncoo-dev\",\"aliyunOasVault\":\"roncoo_college\",\"payUrl\":\"http://47.101.63.159:6001/cnpPay/initPay\",\"payKey\":\"3de903c57ffa4534ad62d134a504a685\",\"paySecret\":\"7a299f1c72ae466cae161cbcd60060a4\",\"notifyUrl\":\"http://roncoo.vicp.net/api/callback/order/roncoo\"}');
INSERT INTO `sys_log` VALUES (1103843308497231874, '2019-03-08 10:22:06', 2018033111202589416, '超级管理员', '116.21.1.86', '讲师信息修改', 'POST', '/user/lecturerAudit/update', '{\"pageCurrent\":0,\"pageSize\":0,\"id\":1100757103337525250,\"sort\":1,\"lecturerUserNo\":2019022721353527,\"lecturerName\":\"88\",\"lecturerEmail\":\"163services@163.com\",\"introduce\":\"<p>qqqq</p>\",\"lecturerProportion\":70}');
INSERT INTO `sys_log` VALUES (1141232745800654849, '2019-06-19 14:36:09', 2018033111202589416, '超级管理员', '192.168.1.253', '修改状态', 'POST', '/user/userExt/updateStatusId', '{\"pageCurrent\":0,\"pageSize\":0,\"id\":1103275392542380034,\"statusId\":0}');
INSERT INTO `sys_log` VALUES (1141232798531444738, '2019-06-19 14:36:22', 2018033111202589416, '超级管理员', '192.168.1.253', '修改状态', 'POST', '/user/userExt/updateStatusId', '{\"pageCurrent\":0,\"pageSize\":0,\"id\":1103275392542380034,\"statusId\":1}');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `status_id` tinyint(3) UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态(1:正常，0:禁用)',
  `sort` int(11) UNSIGNED NOT NULL DEFAULT 1 COMMENT '排序',
  `parent_id` bigint(20) UNSIGNED NOT NULL COMMENT '父ID',
  `menu_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `menu_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '路由路径',
  `api_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '接口URL',
  `menu_icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `menu_type` tinyint(3) NOT NULL DEFAULT 1 COMMENT '菜单类型(1：目录   2：菜单   3：按钮)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1193004997812326403 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单信息' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1153478139284656129, '2019-07-23 09:33:11', '2019-07-23 09:33:11', 1, 6, 0, '订单管理', 'order', '', NULL, '订单管理', 1);
INSERT INTO `sys_menu` VALUES (1153478416205189122, '2019-07-23 09:34:17', '2019-07-23 09:34:17', 1, 5, 0, '首页管理', 'homepage', '', NULL, '首页管理', 1);
INSERT INTO `sys_menu` VALUES (1153478559176429569, '2019-07-23 09:34:51', '2019-07-23 09:34:51', 1, 4, 0, '课程管理', 'course', '', NULL, '课程管理', 1);
INSERT INTO `sys_menu` VALUES (1153478694937661442, '2019-07-23 09:35:23', '2019-07-23 09:35:23', 1, 3, 0, '讲师管理', 'lecturer', '', NULL, '讲师管理', 1);
INSERT INTO `sys_menu` VALUES (1153478801917579265, '2019-07-23 09:35:49', '2019-07-23 09:35:49', 1, 2, 0, '学员管理', 'user', '', NULL, '学员管理', 1);
INSERT INTO `sys_menu` VALUES (1153478911053369345, '2019-07-23 09:36:15', '2019-07-23 09:36:15', 1, 1, 0, '系统管理', 'sys', '', NULL, '系统管理', 1);
INSERT INTO `sys_menu` VALUES (1153493010483089409, '2019-07-23 10:32:17', '2019-07-23 10:32:17', 1, 1, 1153478139284656129, '订单列表', '/order/orderInfo', '/course/pc/order/info/list', NULL, '订单列表', 2);
INSERT INTO `sys_menu` VALUES (1153493592497295362, '2019-07-23 10:34:35', '2019-07-23 10:34:35', 1, 3, 1153478416205189122, '首页轮播', '/adv', '', NULL, '首页轮播', 1);
INSERT INTO `sys_menu` VALUES (1153493835884367873, '2019-07-23 10:35:33', '2019-07-23 10:35:33', 1, 1, 1153493592497295362, '广告设置', '/homepage/adv/pc', '/course/pc/adv/list', NULL, '广告设置', 2);
INSERT INTO `sys_menu` VALUES (1153494219218587650, '2019-07-23 10:37:05', '2019-07-23 10:37:05', 1, 2, 1153478416205189122, '专区管理', '/zone', '', NULL, '专区管理', 1);
INSERT INTO `sys_menu` VALUES (1153494438295474177, '2019-07-23 10:37:57', '2019-07-23 10:37:57', 1, 1, 1153494219218587650, '专区设置', '/homepage/zone/pc', '/course/pc/zone/list', NULL, '专区设置', 2);
INSERT INTO `sys_menu` VALUES (1153494698514288641, '2019-07-23 10:38:59', '2019-07-23 10:38:59', 1, 1, 1153478416205189122, '站点管理', '/website', '', NULL, '站点管理', 1);
INSERT INTO `sys_menu` VALUES (1153494935626682369, '2019-07-23 10:39:56', '2019-07-23 10:39:56', 1, 3, 1153494698514288641, '头部导航', '/homepage/website/navBar', '/system/pc/nav/bar/list', NULL, '头部导航列表接口', 2);
INSERT INTO `sys_menu` VALUES (1153495031810461697, '2019-07-23 10:40:18', '2019-07-23 10:40:18', 1, 2, 1153494698514288641, '底部导航', '/homepage/website/websiteNav', '/system/pc/website/nav/list', NULL, '底部导航', 2);
INSERT INTO `sys_menu` VALUES (1153495155055890433, '2019-07-23 10:40:48', '2019-07-23 10:40:48', 1, 1, 1153494698514288641, '友情链接', '/homepage/website/websiteLink', '/system/pc/website/link/list', NULL, '友情链接', 2);
INSERT INTO `sys_menu` VALUES (1153495582782623746, '2019-07-23 10:42:30', '2019-07-23 10:42:30', 1, 2, 1153478559176429569, '课程管理', '/course', '', NULL, '课程管理', 1);
INSERT INTO `sys_menu` VALUES (1153495768179249153, '2019-07-23 10:43:14', '2019-07-23 10:43:14', 1, 1, 1153478559176429569, '分类管理', '/category', '', NULL, '分类管理', 1);
INSERT INTO `sys_menu` VALUES (1153495948102307842, '2019-07-23 10:43:57', '2019-07-23 10:43:57', 1, 2, 1153495582782623746, '课程列表', '/course/course/course', '/course/pc/course/list', NULL, '课程列表', 2);
INSERT INTO `sys_menu` VALUES (1153496094022144002, '2019-07-23 10:44:32', '2019-07-23 10:44:32', 1, 1, 1153495582782623746, '课程审核列表', '/course/course/audit', '/course/pc/course/audit/list', NULL, '课程审核列表', 2);
INSERT INTO `sys_menu` VALUES (1153496241066053634, '2019-07-23 10:45:07', '2019-07-23 10:45:07', 1, 1, 1153495768179249153, '分类列表', '/course/category/category', '/course/pc/course/category/list', NULL, '分类列表', 2);
INSERT INTO `sys_menu` VALUES (1153496406875279362, '2019-07-23 10:45:46', '2019-07-23 10:45:46', 1, 2, 1153478694937661442, '讲师管理', '/lecturer', '', NULL, '讲师管理', 1);
INSERT INTO `sys_menu` VALUES (1153496706587660289, '2019-07-23 10:46:58', '2019-07-23 10:46:58', 1, 1, 1153478694937661442, '讲师分润', '/profit', '', NULL, '讲师分润', 1);
INSERT INTO `sys_menu` VALUES (1153496795896975361, '2019-07-23 10:47:19', '2019-07-23 10:47:19', 1, 2, 1153496406875279362, '讲师列表', '/lecturer/lecturer/lecturer', '/user/pc/lecturer/list', NULL, '讲师列表', 2);
INSERT INTO `sys_menu` VALUES (1153497106191585282, '2019-07-23 10:48:33', '2019-07-23 10:48:33', 1, 1, 1153496406875279362, '讲师审核列表', '/lecturer/lecturer/audit', '/user/pc/lecturer/audit/list', NULL, '讲师审核列表', 2);
INSERT INTO `sys_menu` VALUES (1153497221941792770, '2019-07-23 10:49:01', '2019-07-23 10:49:01', 1, 1, 1153496706587660289, '分润列表', '/lecturer/profit/profit', '/user/pc/lecturer/profit/list', NULL, '分润列表', 2);
INSERT INTO `sys_menu` VALUES (1153498121905213442, '2019-07-23 10:52:35', '2019-07-23 10:52:35', 1, 5, 1153478911053369345, '权限管理', '/pms', '', NULL, '权限管理', 1);
INSERT INTO `sys_menu` VALUES (1153498480237187073, '2019-07-23 10:54:01', '2019-07-23 10:54:01', 1, 3, 1153478911053369345, '站点管理', '/website', '', NULL, '站点管理', 1);
INSERT INTO `sys_menu` VALUES (1153498652677607425, '2019-07-23 10:54:42', '2019-07-23 10:54:42', 1, 2, 1153478911053369345, '平台管理', '/platform', '', NULL, '平台管理', 1);
INSERT INTO `sys_menu` VALUES (1153498794638020609, '2019-07-23 10:55:16', '2019-07-23 10:55:16', 1, 1, 1153478911053369345, '日志查看', '/log', '', NULL, '日志查看', 1);
INSERT INTO `sys_menu` VALUES (1153498940276838401, '2019-07-23 10:55:50', '2019-07-23 10:55:50', 1, 3, 1153498121905213442, '用户管理', '/sys/pms/user', '/system/pc/sys/user/list', NULL, '用户管理', 2);
INSERT INTO `sys_menu` VALUES (1153499292782923778, '2019-07-23 10:57:14', '2019-07-23 10:57:14', 1, 2, 1153498121905213442, '角色管理', '/sys/pms/role', '/system/pc/sys/role/list', NULL, '角色管理', 2);
INSERT INTO `sys_menu` VALUES (1153499423880089601, '2019-07-23 10:57:46', '2019-07-23 10:57:46', 1, 1, 1153498121905213442, '菜单管理', '/sys/pms/menu', '/system/pc/menu/list', NULL, '菜单管理', 2);
INSERT INTO `sys_menu` VALUES (1153500580878848001, '2019-07-23 11:02:21', '2019-07-23 11:02:21', 1, 5, 1153498480237187073, '站点设置', '/sys/website/website', '/system/pc/website/view', NULL, '站点设置', 2);
INSERT INTO `sys_menu` VALUES (1153500772986359810, '2019-07-23 11:03:07', '2019-07-23 11:03:07', 1, 2, 1153498480237187073, '系统设置', '/sys/website/sys', '/system/pc/sys/view', NULL, '系统设置', 2);
INSERT INTO `sys_menu` VALUES (1153501173945044993, '2019-07-23 11:04:43', '2019-07-23 11:04:43', 1, 1, 1153498652677607425, '应用管理', '/sys/platform/platform', '/user/pc/platform/list', NULL, '应用管理', 2);
INSERT INTO `sys_menu` VALUES (1153501424605040641, '2019-07-23 11:05:43', '2019-07-23 11:05:43', 1, 2, 1153498794638020609, '操作日志', '/sys/log/sys', '/system/pc/sys/log/list', NULL, '操作日志', 2);
INSERT INTO `sys_menu` VALUES (1153501541194108929, '2019-07-23 11:06:10', '2019-07-23 11:06:10', 1, 1, 1153498794638020609, '支付日志', '/sys/log/orderPay', '/course/pc/order/pay/list', NULL, '支付日志', 2);
INSERT INTO `sys_menu` VALUES (1154683387156230146, '2019-07-26 17:22:25', '2019-07-26 17:22:25', 1, 1, 1153493010483089409, '订单查看', '', '/course/pc/order/info/view', NULL, '订单查看', 3);
INSERT INTO `sys_menu` VALUES (1154683761573359618, '2019-07-26 17:23:54', '2019-07-26 17:23:54', 1, 1, 1153493010483089409, '统计订单收入情况', '', '/course/pc/order/info/statistical', NULL, NULL, 3);
INSERT INTO `sys_menu` VALUES (1156030262547324930, '2019-07-30 10:34:26', '2019-07-30 10:34:26', 1, 1, 1153493835884367873, '添加', '', '/course/pc/adv/add', NULL, '跳添加页面弹窗', 3);
INSERT INTO `sys_menu` VALUES (1156030361037971458, '2019-07-30 10:34:49', '2019-07-30 10:34:49', 1, 1, 1153493835884367873, '保存', '', '/course/pc/adv/save', NULL, '保存接口', 3);
INSERT INTO `sys_menu` VALUES (1156030992003899394, '2019-07-30 10:37:20', '2019-07-30 10:37:20', 1, 1, 1153493835884367873, '更新', '', '/course/pc/adv/update', NULL, '更新接口', 3);
INSERT INTO `sys_menu` VALUES (1156032108988997634, '2019-07-30 10:41:46', '2019-07-30 10:41:46', 1, 1, 1153494438295474177, '添加', '', '/course/pc/zone/add', NULL, '添加页面', 3);
INSERT INTO `sys_menu` VALUES (1156032296088510465, '2019-07-30 10:42:31', '2019-07-30 10:42:31', 1, 1, 1153494438295474177, '保存', '', '/course/pc/zone/save', NULL, '保存接口', 3);
INSERT INTO `sys_menu` VALUES (1156032474489036801, '2019-07-30 10:43:13', '2019-07-30 10:43:13', 1, 1, 1153494438295474177, '删除', '', '/course/pc/zone/delete', NULL, '删除', 3);
INSERT INTO `sys_menu` VALUES (1156033449756991490, '2019-07-30 10:47:06', '2019-07-30 10:47:06', 1, 1, 1153494438295474177, '修改', '', '/course/pc/zone/view', NULL, '修改页面', 3);
INSERT INTO `sys_menu` VALUES (1156033853009960961, '2019-07-30 10:48:42', '2019-07-30 10:48:42', 1, 1, 1153494438295474177, '更新', '', '/course/pc/zone/update', NULL, '更新接口', 3);
INSERT INTO `sys_menu` VALUES (1156035023040421889, '2019-07-30 10:53:21', '2019-07-30 10:53:21', 1, 1, 1153494438295474177, '专区课程', NULL, '/course/pc/zone/course/list', NULL, '专区课程新窗口打开', 3);
INSERT INTO `sys_menu` VALUES (1156035443800416257, '2019-07-30 10:55:01', '2019-07-30 10:55:01', 1, 1, 1156035023040421889, '添加', '', '/course/pc/course/list', NULL, '添加课程列出接口', 3);
INSERT INTO `sys_menu` VALUES (1156035689204948994, '2019-07-30 10:56:00', '2019-07-30 10:56:00', 1, 1, 1156035023040421889, '保存', '', '/course/pc/zone/course/save', NULL, '保存添加', 3);
INSERT INTO `sys_menu` VALUES (1156036045850812417, '2019-07-30 10:57:25', '2019-07-30 10:57:25', 1, 1, 1156035023040421889, '删除', '', '/course/pc/zone/course/delete', NULL, '专区课程删除接口', 3);
INSERT INTO `sys_menu` VALUES (1156036539059019777, '2019-07-30 10:59:22', '2019-07-30 10:59:22', 1, 1, 1156035023040421889, '修改', '', '/course/pc/zone/course/edit', NULL, '修改弹窗页面', 3);
INSERT INTO `sys_menu` VALUES (1156036653299277825, '2019-07-30 10:59:49', '2019-07-30 10:59:49', 1, 1, 1156035023040421889, '更新', '', '/course/pc/zone/course/update', NULL, '专区课程接口', 3);
INSERT INTO `sys_menu` VALUES (1156036831607529473, '2019-07-30 11:00:32', '2019-07-30 11:00:32', 1, 1, 1153493835884367873, '修改', '', '/course/pc/adv/edit', NULL, '修改页面弹窗', 3);
INSERT INTO `sys_menu` VALUES (1156037020263129089, '2019-07-30 11:01:17', '2019-07-30 11:01:17', 1, 1, 1153493835884367873, '删除', '', '/course/pc/adv/delete', NULL, '删除接口', 3);
INSERT INTO `sys_menu` VALUES (1156039623340466178, '2019-07-30 11:11:38', '2019-07-30 11:11:38', 1, 1, 1153494935626682369, '添加', '', '/system/pc/nav/bar/add', NULL, '添加弹窗', 3);
INSERT INTO `sys_menu` VALUES (1156039959035781122, '2019-07-30 11:12:58', '2019-07-30 11:12:58', 1, 1, 1153494935626682369, '保存', '', '/system/pc/nav/bar/save', NULL, '保存接口', 3);
INSERT INTO `sys_menu` VALUES (1156040289144283137, '2019-07-30 11:14:16', '2019-07-30 11:14:16', 1, 1, 1153494935626682369, '删除', '', '/system/pc/nav/bar/delete', NULL, '删除接口', 3);
INSERT INTO `sys_menu` VALUES (1156040630476742658, '2019-07-30 11:15:38', '2019-07-30 11:15:38', 1, 1, 1153494935626682369, '修改', '', '/system/pc/nav/bar/edit', NULL, '修改弹窗', 3);
INSERT INTO `sys_menu` VALUES (1156040868612546562, '2019-07-30 11:16:34', '2019-07-30 11:16:34', 1, 1, 1153494935626682369, '更新', '', '/system/pc/nav/bar/update', NULL, '更新接口', 3);
INSERT INTO `sys_menu` VALUES (1156041685298061313, '2019-07-30 11:19:49', '2019-07-30 11:19:49', 1, 7, 1153495031810461697, '添加', '', '/system/pc/website/nav/add', NULL, '添加弹窗', 3);
INSERT INTO `sys_menu` VALUES (1156042299306418177, '2019-07-30 11:22:16', '2019-07-30 11:22:16', 1, 6, 1153495031810461697, '保存', '', '/system/pc/website/nav/save', NULL, '保存接口', 3);
INSERT INTO `sys_menu` VALUES (1156042595428474882, '2019-07-30 11:23:26', '2019-07-30 11:23:26', 1, 5, 1153495031810461697, '删除', '', '/system/pc/website/nav/delete', NULL, '删除', 3);
INSERT INTO `sys_menu` VALUES (1156043168856940545, '2019-07-30 11:25:43', '2019-07-30 11:25:43', 1, 4, 1153495031810461697, '修改', '', '/system/pc/website/nav/edit', NULL, '修改弹窗', 3);
INSERT INTO `sys_menu` VALUES (1156043234770427905, '2019-07-30 11:25:59', '2019-07-30 11:25:59', 1, 3, 1153495031810461697, '更新', '', '/system/pc/website/nav/update', NULL, '更新接口', 3);
INSERT INTO `sys_menu` VALUES (1156045293838147585, '2019-07-30 11:34:10', '2019-07-30 11:34:10', 1, 2, 1153495031810461697, '文章管理', NULL, '/system/pc/website/nav/article/view', NULL, '文章管理新窗口', 3);
INSERT INTO `sys_menu` VALUES (1156099141575385090, '2019-07-30 15:08:08', '2019-07-30 15:08:08', 1, 1, 1153495155055890433, '添加', '', '/system/pc/website/link/add', NULL, '添加弹窗', 3);
INSERT INTO `sys_menu` VALUES (1156099301554528257, '2019-07-30 15:08:46', '2019-07-30 15:08:46', 1, 1, 1153495155055890433, '保存', '', '/system/pc/website/link/save', NULL, '保存接口', 3);
INSERT INTO `sys_menu` VALUES (1156099420307857410, '2019-07-30 15:09:14', '2019-07-30 15:09:14', 1, 1, 1153495155055890433, '删除', '', '/system/pc/website/link/delete', NULL, '删除接口', 3);
INSERT INTO `sys_menu` VALUES (1156099620929806338, '2019-07-30 15:10:02', '2019-07-30 15:10:02', 1, 1, 1153495155055890433, '修改', '', '/system/pc/website/link/edit', NULL, '修改弹窗', 3);
INSERT INTO `sys_menu` VALUES (1156099736667430914, '2019-07-30 15:10:30', '2019-07-30 15:10:30', 1, 1, 1153495155055890433, '更新', '', '/system/pc/website/link/update', NULL, '更新接口', 3);
INSERT INTO `sys_menu` VALUES (1156102063474352129, '2019-07-30 15:19:44', '2019-07-30 15:19:44', 1, 1, 1153495948102307842, '修改', '', '/course/pc/course/get', NULL, '修改弹窗', 3);
INSERT INTO `sys_menu` VALUES (1156102212472807425, '2019-07-30 15:20:20', '2019-07-30 15:20:20', 1, 1, 1153495948102307842, '更新', '', '/course/pc/course/update', NULL, '更新接口', 3);
INSERT INTO `sys_menu` VALUES (1156106290552643585, '2019-07-30 15:36:32', '2019-07-30 15:36:32', 1, 1, 1153496094022144002, '修改、审核', '', '/course/pc/course/audit/view', NULL, '修改、弹窗', 3);
INSERT INTO `sys_menu` VALUES (1156106778274701314, '2019-07-30 15:38:29', '2019-07-30 15:38:29', 1, 1, 1153496094022144002, '审核课程', '', '/course/pc/course/audit/audit', NULL, '审核课程', 3);
INSERT INTO `sys_menu` VALUES (1156106947468730369, '2019-07-30 15:39:09', '2019-07-30 15:39:09', 1, 1, 1153496094022144002, '更新', '', '/course/pc/course/audit/update', NULL, '更新', 3);
INSERT INTO `sys_menu` VALUES (1156389609588662274, '2019-07-31 10:22:21', '2019-07-31 10:22:21', 1, 1, 1153496241066053634, '添加', '', '/course/pc/course/category/add', NULL, '添加弹窗', 3);
INSERT INTO `sys_menu` VALUES (1156389834885701634, '2019-07-31 10:23:15', '2019-07-31 10:23:15', 1, 1, 1153496241066053634, '保存', '', '/course/pc/course/category/save', NULL, '保存接口', 3);
INSERT INTO `sys_menu` VALUES (1156390300390531073, '2019-07-31 10:25:06', '2019-07-31 10:25:06', 1, 1, 1153496241066053634, '修改', '', '/course/pc/course/category/view', NULL, '修改弹窗', 3);
INSERT INTO `sys_menu` VALUES (1156390387736911873, '2019-07-31 10:25:26', '2019-07-31 10:25:26', 1, 1, 1153496241066053634, '更新', '', '/course/pc/course/category/update', NULL, '更新', 3);
INSERT INTO `sys_menu` VALUES (1156390708198514689, '2019-07-31 10:26:43', '2019-07-31 10:26:43', 1, 1, 1153496241066053634, '删除', '', '/course/pc/course/category/delete', NULL, '删除', 3);
INSERT INTO `sys_menu` VALUES (1156393404230017026, '2019-07-31 10:37:26', '2019-07-31 10:37:26', 1, 1, 1153496795896975361, '修改', '', '/user/pc/lecturer/view', NULL, '修改、查看、设置分成弹窗', 3);
INSERT INTO `sys_menu` VALUES (1156393596735987714, '2019-07-31 10:38:11', '2019-07-31 10:38:11', 1, 1, 1153496795896975361, '更新', '', '/user/pc/lecturer/update', NULL, '更新', 3);
INSERT INTO `sys_menu` VALUES (1156394505545195522, '2019-07-31 10:41:48', '2019-07-31 10:41:48', 1, 1, 1153497106191585282, '添加', '', '/user/pc/lecturer/audit/add', NULL, '添加弹窗', 3);
INSERT INTO `sys_menu` VALUES (1156395045071101953, '2019-07-31 10:43:57', '2019-07-31 10:43:57', 1, 1, 1153497106191585282, '保存', '', '/user/pc/lecturer/audit/save', NULL, '保存接口', 3);
INSERT INTO `sys_menu` VALUES (1156395484629966849, '2019-07-31 10:45:42', '2019-07-31 10:45:42', 1, 1, 1153497106191585282, '修改', '', '/user/pc/lecturer/audit/view', NULL, '修改、查看弹窗', 3);
INSERT INTO `sys_menu` VALUES (1156395794031190017, '2019-07-31 10:46:55', '2019-07-31 10:46:55', 1, 1, 1153497106191585282, '校验讲师是否存在', '', '/user/pc/lecturer/audit/check', NULL, '校验讲师是否存在', 3);
INSERT INTO `sys_menu` VALUES (1156395965360119810, '2019-07-31 10:47:36', '2019-07-31 10:47:36', 1, 1, 1153497106191585282, '更新', '', '/user/pc/lecturer/audit/update', NULL, '更新弹窗', 3);
INSERT INTO `sys_menu` VALUES (1156396502923091970, '2019-07-31 10:49:44', '2019-07-31 10:49:44', 1, 1, 1153497106191585282, '审核页面', '', '/user/pc/lecturer/audit/isAudit', NULL, '审核弹窗', 3);
INSERT INTO `sys_menu` VALUES (1156396635052056577, '2019-07-31 10:50:16', '2019-07-31 10:50:16', 1, 1, 1153497106191585282, '审核更新', '', '/user/pc/lecturer/audit/audit', NULL, '审核更新', 3);
INSERT INTO `sys_menu` VALUES (1156452734279098369, '2019-07-31 14:33:11', '2019-07-31 14:33:11', 1, 1, 1153497221941792770, '批量标记为已打款', '', '/user/pc/lecturer/profit/batch', NULL, '批量标记为已打款', 3);
INSERT INTO `sys_menu` VALUES (1156453190803922945, '2019-07-31 14:35:00', '2019-07-31 14:35:00', 1, 1, 1153497221941792770, '打款跳页面', '', '/user/pc/lecturer/profit/edit', NULL, '打款跳页面', 3);
INSERT INTO `sys_menu` VALUES (1156453747622944769, '2019-07-31 14:37:13', '2019-07-31 14:37:13', 1, 1, 1153497221941792770, '更新讲师打款状态', '', '/user/pc/lecturer/profit/update', NULL, '更新讲师打款状态', 3);
INSERT INTO `sys_menu` VALUES (1156467834150985729, '2019-07-31 15:33:11', '2019-07-31 15:33:11', 1, 1, 1153498940276838401, '添加', '', '/system/pc/sys/user/add', NULL, '添加弹窗', 3);
INSERT INTO `sys_menu` VALUES (1156467976803459073, '2019-07-31 15:33:45', '2019-07-31 15:33:45', 1, 1, 1153498940276838401, '保存', '', '/system/pc/sys/user/save', NULL, '保存接口', 3);
INSERT INTO `sys_menu` VALUES (1156468115706224642, '2019-07-31 15:34:18', '2019-07-31 15:34:18', 1, 1, 1153498940276838401, '删除', '', '/system/pc/sys/user/delete', NULL, '删除接口', 3);
INSERT INTO `sys_menu` VALUES (1156471160762540033, '2019-07-31 15:46:24', '2019-07-31 15:46:24', 1, 1, 1153498940276838401, '修改', '', '/system/pc/sys/user/view', NULL, '修改弹窗', 3);
INSERT INTO `sys_menu` VALUES (1156471428245889026, '2019-07-31 15:47:28', '2019-07-31 15:47:28', 1, 1, 1153498940276838401, '更新', '', '/system/pc/sys/user/update', NULL, '更新接口', 3);
INSERT INTO `sys_menu` VALUES (1156471546990829570, '2019-07-31 15:47:56', '2019-07-31 15:47:56', 1, 1, 1153498940276838401, '更新密码弹窗', '', '/system/pc/sys/user/password', NULL, '更新密码弹窗', 3);
INSERT INTO `sys_menu` VALUES (1156471749651210241, '2019-07-31 15:48:45', '2019-07-31 15:48:45', 1, 1, 1153498940276838401, '更新密码', '', '/system/pc/sys/user/update/password', NULL, '更新密码接口', 3);
INSERT INTO `sys_menu` VALUES (1156472210034794497, '2019-07-31 15:50:34', '2019-07-31 15:50:34', 1, 1, 1153498940276838401, '设置角色', '', '/system/pc/sys/role/list', NULL, '设置角色弹窗', 3);
INSERT INTO `sys_menu` VALUES (1156472470257803266, '2019-07-31 15:51:36', '2019-07-31 15:51:36', 1, 1, 1153498940276838401, '保存用户角色', '', '/system/pc/sys/role/user/save', NULL, '保存用户角色接口', 3);
INSERT INTO `sys_menu` VALUES (1156472761619324929, '2019-07-31 15:52:46', '2019-07-31 15:52:46', 1, 1, 1153498940276838401, '用户关联的角色', '', '/system/pc/sys/role/user/list', NULL, '获取用户关联的角色', 3);
INSERT INTO `sys_menu` VALUES (1156473195461353473, '2019-07-31 15:54:29', '2019-07-31 15:54:29', 1, 1, 1153498940276838401, '列出用户', '', '/user/pc/user/list', NULL, '列出用户', 3);
INSERT INTO `sys_menu` VALUES (1156473846425722881, '2019-07-31 15:57:04', '2019-07-31 15:57:04', 1, 1, 1153499292782923778, '添加', '', '/system/pc/sys/role/add', NULL, '添加弹窗', 3);
INSERT INTO `sys_menu` VALUES (1156473962389839874, '2019-07-31 15:57:32', '2019-07-31 15:57:32', 1, 1, 1153499292782923778, '保存', '', '/system/pc/sys/role/save', NULL, '保存接口', 3);
INSERT INTO `sys_menu` VALUES (1156474159387910146, '2019-07-31 15:58:19', '2019-07-31 15:58:19', 1, 1, 1153499292782923778, '删除', '', '/system/pc/sys/role/delete', NULL, '删除接口', 3);
INSERT INTO `sys_menu` VALUES (1156475549820657665, '2019-07-31 16:03:51', '2019-07-31 16:03:51', 1, 1, 1153499292782923778, '修改', '', '/system/pc/sys/role/view', NULL, '修改修改弹窗', 3);
INSERT INTO `sys_menu` VALUES (1156475705584525314, '2019-07-31 16:04:28', '2019-07-31 16:04:28', 1, 1, 1153499292782923778, '更新', '', '/system/pc/sys/role/update', NULL, '更新接口', 3);
INSERT INTO `sys_menu` VALUES (1156475975043391490, '2019-07-31 16:05:32', '2019-07-31 16:05:32', 1, 1, 1153499292782923778, '设置权限', '', '/system/pc/menu/list', NULL, '设置权限弹窗', 3);
INSERT INTO `sys_menu` VALUES (1156476159274000386, '2019-07-31 16:06:16', '2019-07-31 16:06:16', 1, 1, 1153499292782923778, '菜单角色关联', '', '/system/pc/menu/role/list', NULL, '菜单角色关联', 3);
INSERT INTO `sys_menu` VALUES (1156476414988132353, '2019-07-31 16:07:17', '2019-07-31 16:07:17', 1, 1, 1153499292782923778, '保存角色菜单关联', '', '/system/pc/menu/role/save', NULL, '角色菜单关联接口', 3);
INSERT INTO `sys_menu` VALUES (1156477233678524418, '2019-07-31 16:10:32', '2019-07-31 16:10:32', 1, 1, 1153499423880089601, '添加', '', '/system/pc/menu/add', NULL, '添加弹窗', 3);
INSERT INTO `sys_menu` VALUES (1156477357225943042, '2019-07-31 16:11:01', '2019-07-31 16:11:01', 1, 1, 1153499423880089601, '保存', '', '/system/pc/menu/save', NULL, '保存接口', 3);
INSERT INTO `sys_menu` VALUES (1156477431565787138, '2019-07-31 16:11:19', '2019-07-31 16:11:19', 1, 1, 1153499423880089601, '删除', '', '/system/pc/menu/delete', NULL, '删除接口', 3);
INSERT INTO `sys_menu` VALUES (1156478453679923201, '2019-07-31 16:15:23', '2019-07-31 16:15:23', 1, 1, 1153499423880089601, '修改', '', '/system/pc/menu/view', NULL, '修改弹窗', 3);
INSERT INTO `sys_menu` VALUES (1156478738817097730, '2019-07-31 16:16:31', '2019-07-31 16:16:31', 1, 1, 1153499423880089601, '更新', '', '/system/pc/menu/update', NULL, '更新', 3);
INSERT INTO `sys_menu` VALUES (1156763603487694849, '2019-08-01 11:08:28', '2019-08-01 11:08:28', 1, 1, 1153501173945044993, '添加', '', '/user/pc/platform/add', NULL, '添加弹窗', 3);
INSERT INTO `sys_menu` VALUES (1156763684647477250, '2019-08-01 11:08:47', '2019-08-01 11:08:47', 1, 1, 1153501173945044993, '保存', '', '/user/pc/platform/save', NULL, '保存接口', 3);
INSERT INTO `sys_menu` VALUES (1156763793531609089, '2019-08-01 11:09:13', '2019-08-01 11:09:13', 1, 1, 1153501173945044993, '修改', '', '/user/pc/platform/view', NULL, '修改、查看弹窗', 3);
INSERT INTO `sys_menu` VALUES (1156763879334486018, '2019-08-01 11:09:34', '2019-08-01 11:09:34', 1, 1, 1153501173945044993, '更新', '', '/user/pc/platform/update', NULL, '更新', 3);
INSERT INTO `sys_menu` VALUES (1156766341252521985, '2019-08-01 11:19:21', '2019-08-01 11:19:21', 1, 1, 1153493010483089409, '讲师查看', '', '/user/pc/lecturer/view', NULL, '讲师查看', 3);
INSERT INTO `sys_menu` VALUES (1156767833694937090, '2019-08-01 11:25:16', '2019-08-01 11:25:16', 1, 1, 1153493010483089409, '更新备注', '', '/course/pc/order/info/update', NULL, '更新接口', 3);
INSERT INTO `sys_menu` VALUES (1156770628078673921, '2019-08-01 11:36:23', '2019-08-01 11:36:23', 1, 1, 1153498480237187073, '系统更新', '', '/system/pc/sys/update', NULL, '系统更新接口', 3);
INSERT INTO `sys_menu` VALUES (1156770804654678017, '2019-08-01 11:37:05', '2019-08-01 11:37:05', 1, 4, 1153498480237187073, '站点富文本上传图片', '', '/course/pc/upload/pic', NULL, '站点富文本上传图片接口', 3);
INSERT INTO `sys_menu` VALUES (1156770958275256321, '2019-08-01 11:37:41', '2019-08-01 11:37:41', 1, 3, 1153498480237187073, '站点更新', '', '/system/pc/website/update', NULL, '站点更新接口', 3);
INSERT INTO `sys_menu` VALUES (1156771497591447553, '2019-08-01 11:39:50', '2019-08-01 11:39:50', 1, 1, 1153495031810461697, '导航文章更新', '', '/system/pc/website/nav/article/update', NULL, '导航文章更新', 3);
INSERT INTO `sys_menu` VALUES (1156846769674223617, '2019-08-01 16:38:56', '2019-08-01 16:38:56', 1, 7, 0, '首页', 'dashboard', '', NULL, '首页', 1);
INSERT INTO `sys_menu` VALUES (1158305741493071874, '2019-08-05 17:16:22', '2019-08-05 17:16:22', 1, 1, 1153493010483089409, '用户查看', '', '/user/pc/user/ext/view', NULL, '用户查看', 3);
INSERT INTO `sys_menu` VALUES (1158310111785873409, '2019-08-05 17:33:44', '2019-08-05 17:33:44', 1, 1, 1153493010483089409, '修改备注', NULL, '/course/pc/order/info/edit', NULL, '修改备注', 3);
INSERT INTO `sys_menu` VALUES (1158323404529299458, '2019-08-05 18:26:33', '2019-08-05 18:26:33', 1, 1, 1153501173945044993, '删除', '', '/user/pc/platform/delete', NULL, '删除接口', 3);
INSERT INTO `sys_menu` VALUES (1160850528846749698, '2019-08-12 17:48:27', '2019-08-12 17:48:27', 1, 1, 1153478801917579265, '学员列表', '/user/ext', '/user/pc/user/ext/list', NULL, '学员列表', 2);
INSERT INTO `sys_menu` VALUES (1160851003339972610, '2019-08-12 17:50:20', '2019-08-12 17:50:20', 1, 1, 1160850528846749698, '修改', '', '/user/pc/user/ext/view', NULL, '修改、查看弹窗', 3);
INSERT INTO `sys_menu` VALUES (1160851295330639874, '2019-08-12 17:51:29', '2019-08-12 17:51:29', 1, 1, 1160850528846749698, '更新', '', '/user/pc/user/ext/update', NULL, '更新接口', 3);
INSERT INTO `sys_menu` VALUES (1160851552827351041, '2019-08-12 17:52:31', '2019-08-12 17:52:31', 1, 1, 1160850528846749698, '学习记录', '/user/ext/studyLog', '/course/pc/course/user/study/log/list', NULL, '用户学习记录', 3);
INSERT INTO `sys_menu` VALUES (1191896171809832962, '2019-11-06 09:52:46', '2019-11-06 09:52:46', 1, 1, 1153495948102307842, '添加', '', '/course/pc/course/save', NULL, '课程添加接口', 3);
INSERT INTO `sys_menu` VALUES (1192651728724348930, '2019-11-08 11:55:05', '2019-11-08 11:55:05', 1, 2, 1153495948102307842, '章节管理', '', '/course/pc/course/chapter/list', NULL, '章节管理列表接口', 3);
INSERT INTO `sys_menu` VALUES (1192717364984991745, '2019-11-08 16:15:54', '2019-11-08 16:15:54', 1, 1, 1192651728724348930, '更新', '', '/course/pc/course/chapter/update', NULL, '章节管理更新接口', 3);
INSERT INTO `sys_menu` VALUES (1192748721886908418, '2019-11-08 18:20:30', '2019-11-08 18:20:30', 1, 1, 1192651728724348930, '添加', '', '/course/pc/course/chapter/save', NULL, '章节添加接口', 3);
INSERT INTO `sys_menu` VALUES (1192748870700814338, '2019-11-08 18:21:05', '2019-11-08 18:21:05', 1, 1, 1192651728724348930, '删除', '', '/course/pc/course/chapter/delete', NULL, '章节删除接口', 3);
INSERT INTO `sys_menu` VALUES (1193003434595225602, '2019-11-09 11:12:38', '2019-11-09 11:12:38', 1, 1, 1153495948102307842, '删除', '', '/course/pc/course/delete', NULL, '课程删除接口', 3);
INSERT INTO `sys_menu` VALUES (1193003666993221634, '2019-11-09 11:13:33', '2019-11-09 11:13:33', 1, 2, 1192651728724348930, '课时管理', '', '/course/pc/course/period/list', NULL, '课时分页列表', 3);
INSERT INTO `sys_menu` VALUES (1193004489890500610, '2019-11-09 11:16:50', '2019-11-09 11:16:50', 1, 1, 1193003666993221634, '修改', '', '/course/pc/course/period/get', NULL, '课时修改弹窗', 3);
INSERT INTO `sys_menu` VALUES (1193004588989321218, '2019-11-09 11:17:13', '2019-11-09 11:17:13', 1, 1, 1193003666993221634, '更新', '', '/course/pc/course/period/update', NULL, '课时更新', 3);
INSERT INTO `sys_menu` VALUES (1193004665099161602, '2019-11-09 11:17:31', '2019-11-09 11:17:31', 1, 1, 1193003666993221634, '添加', '', '/course/pc/course/period/save', NULL, '课时添加', 3);
INSERT INTO `sys_menu` VALUES (1193004743037718529, '2019-11-09 11:17:50', '2019-11-09 11:17:50', 1, 1, 1193003666993221634, '删除', '', '/course/pc/course/period/delete', NULL, '课时删除', 3);
INSERT INTO `sys_menu` VALUES (1193004997812326402, '2019-11-09 11:18:51', '2019-11-09 11:18:51', 1, 1, 1192651728724348930, '修改', '', '/course/pc/course/chapter/get', NULL, '章节修改弹窗', 3);

-- ----------------------------
-- Table structure for sys_menu_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu_role`;
CREATE TABLE `sys_menu_role`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `status_id` tinyint(3) UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态(1:正常，0:禁用)',
  `sort` int(11) UNSIGNED NOT NULL DEFAULT 1 COMMENT '排序',
  `menu_id` bigint(20) UNSIGNED NOT NULL COMMENT '菜单ID',
  `role_id` bigint(20) UNSIGNED NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1193005186144964610 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单角色关联表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_menu_role
-- ----------------------------
INSERT INTO `sys_menu_role` VALUES (1190200825090138114, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1156846769674223617, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825102721026, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1158310111785873409, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825102721027, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1158305741493071874, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825111109634, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1156766341252521985, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825123692545, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1154683761573359618, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825127886850, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1154683387156230146, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825132081154, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1153493592497295362, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825132081155, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1153493835884367873, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825144664065, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1156037020263129089, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825144664066, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1156036831607529473, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825153052673, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1156030992003899394, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825157246977, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1156030361037971458, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825161441282, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1156030262547324930, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825165635585, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1156036539059019777, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825169829890, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1156035443800416257, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825174024194, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1156033449756991490, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825174024195, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1156032108988997634, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825182412801, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1156040630476742658, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825186607105, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1156039623340466178, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825190801410, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1156041685298061313, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825194995714, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1156043168856940545, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825194995715, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1156045293838147585, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825207578625, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1156099620929806338, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825207578626, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1156099141575385090, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825215967234, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1156102063474352129, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825220161537, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1156106290552643585, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825224355841, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1156390300390531073, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825224355842, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1156389609588662274, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825232744450, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1156393404230017026, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825236938753, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1156396502923091970, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825236938754, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1156395794031190017, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825245327362, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1156395484629966849, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825249521665, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1156394505545195522, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825253715970, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1156453190803922945, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825257910273, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1160851552827351041, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825257910274, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1160851003339972610, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825266298881, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1156473195461353473, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825266298882, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1156472761619324929, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825274687489, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1156472210034794497, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825278881793, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1156471546990829570, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825283076097, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1156471160762540033, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825283076098, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1156467834150985729, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825291464705, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1156476159274000386, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825295659010, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1156475975043391490, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825299853313, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1156475549820657665, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825304047618, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1156473846425722881, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825308241922, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1156478453679923201, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825312436226, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1156477233678524418, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825316630530, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1153500580878848001, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825320824833, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1153500772986359810, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825325019138, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1156763793531609089, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825329213442, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1156763603487694849, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825337602050, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1153498794638020609, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825337602051, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1153501424605040641, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825345990657, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1153501541194108929, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825345990658, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1153478139284656129, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825358573570, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1153493010483089409, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825358573571, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1153478416205189122, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825366962178, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1153494219218587650, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825371156482, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1153494438295474177, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825375350786, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1156035023040421889, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825379545090, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1153494698514288641, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825383739394, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1153494935626682369, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825387933697, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1153495031810461697, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825392128001, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1153495155055890433, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825396322305, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1153478559176429569, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825404710913, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1153495582782623746, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825413099521, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1153495948102307842, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825413099522, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1153496094022144002, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825421488129, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1153495768179249153, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825425682434, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1153496241066053634, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825429876737, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1153478694937661442, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825434071042, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1153496406875279362, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825438265346, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1153496795896975361, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825442459649, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1153497106191585282, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825446653954, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1153496706587660289, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825450848258, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1153497221941792770, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825455042562, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1153478801917579265, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825459236866, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1160850528846749698, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825463431170, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1153478911053369345, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825467625473, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1153498121905213442, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825471819777, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1153498940276838401, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825480208385, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1153499292782923778, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825480208386, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1153499423880089601, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825488596993, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1153498480237187073, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825492791297, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1153498652677607425, 3);
INSERT INTO `sys_menu_role` VALUES (1190200825496985601, '2019-11-01 17:36:04', '2019-11-01 17:36:04', 1, 1, 1153501173945044993, 3);
INSERT INTO `sys_menu_role` VALUES (1193005185348046850, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156846769674223617, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185360629761, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1153478139284656129, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185364824066, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1153493010483089409, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185373212674, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1158310111785873409, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185381601281, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1158305741493071874, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185385795585, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156767833694937090, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185389989890, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156766341252521985, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185398378497, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1154683761573359618, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185406767105, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1154683387156230146, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185410961410, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1153478416205189122, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185415155714, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1153493592497295362, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185423544321, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1153493835884367873, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185423544322, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156037020263129089, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185431932929, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156036831607529473, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185444515842, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156030992003899394, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185448710146, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156030361037971458, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185448710147, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156030262547324930, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185457098754, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1153494219218587650, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185461293057, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1153494438295474177, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185469681666, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156035023040421889, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185473875969, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156036653299277825, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185478070274, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156036539059019777, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185478070275, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156036045850812417, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185486458881, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156035689204948994, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185486458882, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156035443800416257, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185494847490, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156033853009960961, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185499041794, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156033449756991490, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185511624706, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156032474489036801, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185511624707, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156032296088510465, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185520013314, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156032108988997634, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185524207617, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1153494698514288641, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185528401921, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1153494935626682369, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185532596226, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156040868612546562, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185536790530, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156040630476742658, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185540984834, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156040289144283137, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185549373442, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156039959035781122, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185553567745, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156039623340466178, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185553567746, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1153495031810461697, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185561956354, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156041685298061313, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185566150658, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156042299306418177, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185574539266, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156042595428474882, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185582927873, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156043168856940545, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185587122178, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156043234770427905, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185595510785, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156045293838147585, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185595510786, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156771497591447553, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185603899393, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1153495155055890433, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185612288001, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156099736667430914, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185616482305, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156099620929806338, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185620676609, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156099420307857410, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185629065218, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156099301554528257, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185629065219, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156099141575385090, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185637453825, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1153478559176429569, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185662619649, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1153495582782623746, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185662619650, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1153495948102307842, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185675202562, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1192651728724348930, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185675202563, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1193003666993221634, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185683591169, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1193004743037718529, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185687785474, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1193004665099161602, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185696174082, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1193004588989321218, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185696174083, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1193004489890500610, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185704562690, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1193004997812326402, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185708756993, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1192748870700814338, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185717145601, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1192748721886908418, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185717145602, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1192717364984991745, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185725534209, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1193003434595225602, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185733922818, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1191896171809832962, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185738117122, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156102212472807425, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185742311425, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156102063474352129, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185750700034, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1153496094022144002, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185750700035, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156106947468730369, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185759088641, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156106778274701314, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185763282945, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156106290552643585, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185763282946, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1153495768179249153, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185771671553, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1153496241066053634, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185775865858, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156390708198514689, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185780060162, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156390387736911873, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185788448769, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156390300390531073, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185796837378, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156389834885701634, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185796837379, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156389609588662274, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185805225986, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1153478694937661442, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185809420290, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1153496406875279362, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185813614594, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1153496795896975361, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185813614595, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156393596735987714, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185822003201, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156393404230017026, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185826197506, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1153497106191585282, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185830391809, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156396635052056577, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185834586113, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156396502923091970, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185838780418, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156395965360119810, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185842974722, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156395794031190017, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185851363329, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156395484629966849, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185855557634, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156395045071101953, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185863946241, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156394505545195522, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185863946242, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1153496706587660289, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185872334849, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1153497221941792770, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185880723457, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156453747622944769, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185880723458, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156453190803922945, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185889112066, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156452734279098369, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185893306369, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1153478801917579265, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185893306370, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1160850528846749698, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185901694977, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1160851552827351041, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185905889282, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1160851295330639874, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185910083586, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1160851003339972610, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185910083587, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1153478911053369345, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185922666498, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1153498121905213442, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185922666499, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1153498940276838401, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185931055106, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156473195461353473, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185935249409, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156472761619324929, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185943638018, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156472470257803266, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185952026625, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156472210034794497, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185952026626, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156471749651210241, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185960415234, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156471546990829570, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185964609538, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156471428245889026, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185964609539, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156471160762540033, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185972998146, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156468115706224642, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185977192449, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156467976803459073, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185985581058, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156467834150985729, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185989775362, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1153499292782923778, 1);
INSERT INTO `sys_menu_role` VALUES (1193005185998163969, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156476414988132353, 1);
INSERT INTO `sys_menu_role` VALUES (1193005186002358274, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156476159274000386, 1);
INSERT INTO `sys_menu_role` VALUES (1193005186010746881, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156475975043391490, 1);
INSERT INTO `sys_menu_role` VALUES (1193005186014941186, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156475705584525314, 1);
INSERT INTO `sys_menu_role` VALUES (1193005186019135490, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156475549820657665, 1);
INSERT INTO `sys_menu_role` VALUES (1193005186023329794, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156474159387910146, 1);
INSERT INTO `sys_menu_role` VALUES (1193005186027524097, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156473962389839874, 1);
INSERT INTO `sys_menu_role` VALUES (1193005186031718402, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156473846425722881, 1);
INSERT INTO `sys_menu_role` VALUES (1193005186035912705, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1153499423880089601, 1);
INSERT INTO `sys_menu_role` VALUES (1193005186040107009, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156478738817097730, 1);
INSERT INTO `sys_menu_role` VALUES (1193005186044301313, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156478453679923201, 1);
INSERT INTO `sys_menu_role` VALUES (1193005186048495618, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156477431565787138, 1);
INSERT INTO `sys_menu_role` VALUES (1193005186052689921, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156477357225943042, 1);
INSERT INTO `sys_menu_role` VALUES (1193005186056884225, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156477233678524418, 1);
INSERT INTO `sys_menu_role` VALUES (1193005186065272834, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1153498480237187073, 1);
INSERT INTO `sys_menu_role` VALUES (1193005186073661442, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1153500580878848001, 1);
INSERT INTO `sys_menu_role` VALUES (1193005186077855746, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156770804654678017, 1);
INSERT INTO `sys_menu_role` VALUES (1193005186086244354, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156770958275256321, 1);
INSERT INTO `sys_menu_role` VALUES (1193005186090438657, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1153500772986359810, 1);
INSERT INTO `sys_menu_role` VALUES (1193005186094632961, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156770628078673921, 1);
INSERT INTO `sys_menu_role` VALUES (1193005186094632962, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1153498652677607425, 1);
INSERT INTO `sys_menu_role` VALUES (1193005186103021569, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1153501173945044993, 1);
INSERT INTO `sys_menu_role` VALUES (1193005186103021570, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1158323404529299458, 1);
INSERT INTO `sys_menu_role` VALUES (1193005186111410178, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156763879334486018, 1);
INSERT INTO `sys_menu_role` VALUES (1193005186115604481, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156763793531609089, 1);
INSERT INTO `sys_menu_role` VALUES (1193005186123993090, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156763684647477250, 1);
INSERT INTO `sys_menu_role` VALUES (1193005186128187393, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1156763603487694849, 1);
INSERT INTO `sys_menu_role` VALUES (1193005186128187394, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1153498794638020609, 1);
INSERT INTO `sys_menu_role` VALUES (1193005186136576001, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1153501424605040641, 1);
INSERT INTO `sys_menu_role` VALUES (1193005186144964609, '2019-11-09 11:19:35', '2019-11-09 11:19:35', 1, 1, 1153501541194108929, 1);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `status_id` tinyint(3) UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态(1:正常，0:禁用)',
  `sort` int(11) UNSIGNED NOT NULL DEFAULT 1 COMMENT '排序',
  `role_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色信息' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '2018-02-06 15:47:52', '2018-03-30 15:12:07', 1, 2, '超级管理员', '管理后台全部权限');
INSERT INTO `sys_role` VALUES (3, '2018-12-28 18:23:38', '2018-12-28 18:23:38', 1, 1, '测试人员', '该用户仅有查看功能');

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `status_id` tinyint(3) UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态(1:正常，0:禁用)',
  `sort` int(11) UNSIGNED NOT NULL DEFAULT 1 COMMENT '排序',
  `role_id` bigint(20) UNSIGNED NOT NULL COMMENT '角色ID',
  `user_id` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1145889062897147907 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色用户关联表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------
INSERT INTO `sys_role_user` VALUES (1145639840180342785, '2019-07-01 18:28:22', '2019-07-01 18:28:22', 1, 1, 1, 1);
INSERT INTO `sys_role_user` VALUES (1145639840188731394, '2019-07-01 18:28:22', '2019-07-01 18:28:22', 1, 1, 3, 1);
INSERT INTO `sys_role_user` VALUES (1145889062897147906, '2019-07-02 10:58:42', '2019-07-02 10:58:42', 1, 1, 3, 2);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `status_id` tinyint(3) UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态(1:正常，0:禁用)',
  `sort` int(11) UNSIGNED NOT NULL DEFAULT 1 COMMENT '排序',
  `user_no` bigint(20) NOT NULL COMMENT '用户编号',
  `mobile` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '手机',
  `real_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '真实姓名',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `AK_phone`(`mobile`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '后台用户信息' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, '2018-03-31 11:22:37', '2018-05-10 15:28:40', 1, 1, 2018033111202589416, '18800000000', '超级管理员', '超级管理员');
INSERT INTO `sys_user` VALUES (2, '2018-12-28 16:57:47', '2018-12-28 16:57:47', 1, 1, 2018112209531803, '13800000000', '普通用户', '测试demo用户');

-- ----------------------------
-- Table structure for website
-- ----------------------------
DROP TABLE IF EXISTS `website`;
CREATE TABLE `website`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `status_id` tinyint(3) UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态(1有效, 0无效)',
  `sort` int(11) NOT NULL DEFAULT 1 COMMENT '排序',
  `logo_ico` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'org_logo_ico',
  `logo_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'org_logo_img',
  `website_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '站点标题',
  `website_keyword` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '站点关键词',
  `website_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '站点描述',
  `copyright` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '站点版权',
  `icp` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备案号',
  `prn` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公安备案号',
  `weixin` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '站点微信',
  `weixin_xcx` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '小程序二维码',
  `weibo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '站点微博',
  `is_enable_statistics` tinyint(3) UNSIGNED NOT NULL DEFAULT 1 COMMENT '是否开启统计',
  `statistics_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '统计代码',
  `is_show_service` tinyint(3) UNSIGNED NOT NULL DEFAULT 1 COMMENT '是否显示客服信息',
  `service1` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客服信息1',
  `service2` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客服信息2',
  `user_agreement` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '用户协议',
  `recruit_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '招募标题',
  `recruit_info` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '招募信息',
  `entry_agreement` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '入驻协议',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '站点信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of website
-- ----------------------------
INSERT INTO `website` VALUES (934374967448227841, '2018-11-09 17:36:44', '1899-12-30 01:00:00', 1, 1, 'http://static-dev.roncoo.com/system/d73a99a804a14f0d8ddb2c2f6fd80e39.ico', 'http://static-dev.roncoo.com/system/028912caa6af43f1be9929c60e093d84.png', '千名营销教育', '千名营销教育', '千名营销教育', 'Copyright &copy; 2018-2023 千名营销教育 版权所有', '粤ICP备16009964号-1', '粤公网安备 44010602005774号', 'http://static-dev.roncoo.com/system/e66eedab4c6641a19fe74a55a47a4d76.jpg', 'http://static-dev.roncoo.com/system/d1abbf37031a470dab1ce7b2ca641d55.jpg', '', 1, '', 1, '297115770', '3155237748', '<p><br></p>', '', '<p><br></p>', '<p><br></p>');

-- ----------------------------
-- Table structure for website_link
-- ----------------------------
DROP TABLE IF EXISTS `website_link`;
CREATE TABLE `website_link`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `status_id` tinyint(3) UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态(1有效, 0无效)',
  `sort` int(11) NOT NULL DEFAULT 1 COMMENT '排序',
  `link_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `link_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '链接',
  `link_target` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '跳转方式(_blank，_self)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '站点友情链接' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for website_nav
-- ----------------------------
DROP TABLE IF EXISTS `website_nav`;
CREATE TABLE `website_nav`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `status_id` tinyint(3) UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态(1有效, 0无效)',
  `sort` int(11) NOT NULL DEFAULT 1 COMMENT '排序',
  `parent_id` bigint(20) UNSIGNED NOT NULL COMMENT '父ID',
  `nav_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '导航名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '站点导航' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of website_nav
-- ----------------------------
INSERT INTO `website_nav` VALUES (1060100633685209089, '2018-11-07 17:25:36', '2018-11-07 17:25:36', 1, 2, 0, '商务合作');
INSERT INTO `website_nav` VALUES (1060107291291365377, '2018-11-07 17:52:03', '2018-11-07 17:52:03', 1, 1, 1060100633685209089, '课程合作');
INSERT INTO `website_nav` VALUES (1060115285643177985, '2018-11-07 18:23:49', '2018-11-07 18:23:49', 1, 1, 1060100633685209089, '项目合作');
INSERT INTO `website_nav` VALUES (1060708747425882114, '2018-11-09 09:42:04', '2018-11-09 09:42:04', 1, 1, 0, '协议相关');
INSERT INTO `website_nav` VALUES (1060708775632576514, '2018-11-09 09:42:11', '2018-11-09 09:42:11', 1, 1, 1060708747425882114, '用户协议');
INSERT INTO `website_nav` VALUES (1080719070034886658, '2019-01-03 14:54:38', '2019-01-03 14:54:38', 1, 1, 1060708747425882114, '讲师协议');
INSERT INTO `website_nav` VALUES (1191284524686708738, '2019-11-04 17:22:18', '2019-11-04 17:22:18', 1, 1, 1060100633685209089, '嗯嗯嗯');

-- ----------------------------
-- Table structure for website_nav_article
-- ----------------------------
DROP TABLE IF EXISTS `website_nav_article`;
CREATE TABLE `website_nav_article`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `status_id` tinyint(3) UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态(1有效, 0无效)',
  `sort` int(11) NOT NULL DEFAULT 1 COMMENT '排序',
  `nav_id` bigint(20) NOT NULL COMMENT '导航ID',
  `art_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文章标题',
  `art_pic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章图片',
  `art_desc` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文章描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '站点导航文章' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of website_nav_article
-- ----------------------------
INSERT INTO `website_nav_article` VALUES (1060123213131111, '1899-12-30 01:00:00', '1899-12-30 01:00:00', 1, 1, 1060107291291365400, '用户协议', 'http://static-dev.roncoo.com/course/ec0ae2ff117c4cd7b63e42c1110e25f2.png', '1');
INSERT INTO `website_nav_article` VALUES (1060233213232111, '1899-12-30 01:00:00', '1899-12-30 01:00:00', 1, 1, 1060708775632576500, '平台合作', 'http://static-dev.roncoo.com/course/8fdd0e9ebe83416fa35e9eb8f25426b5.jpg', '1');
INSERT INTO `website_nav_article` VALUES (1060354353322411, '1899-12-30 01:00:00', '1899-12-30 01:00:00', 1, 1, 1060115285643178000, '商务合作', 'http://static-dev.roncoo.com/course/b290902a59294ec1885df4a381dd51fd.jpg', '1');
INSERT INTO `website_nav_article` VALUES (1060109903055106050, '2018-11-07 18:02:26', '2018-11-07 18:02:26', 1, 1, 1060107291291365377, '讲师招募', 'http://static-dev.roncoo.com/course/8fdd0e9ebe83416fa35e9eb8f25426b5.jpg', '<p>平台合作</p>\n<p>想试水尝尝网络讲师的滋味？快加入龙果学院讲师队伍吧！&nbsp;<br /><strong>我们希望你：</strong>&nbsp;<br />- 热衷于互联网上分享技术经验，解答技术难题的IT精英；&nbsp;<br />- 有3年以上的大型项目开发经验；&nbsp;<br /></p>\n<p>- 精通JAVA开发、Python、大数据开发、人工智能、go语言开发任意一项即可！</p>\n<p><strong>你的收获：</strong></p>\n<p>- 额外收入，技术、知识兑现且可持续收益；<br />- 技术的沉淀与分享；<br />- 迅速增长的粉丝及业内知名度，帮你打造在线教育领域个人品牌。</p>\n<p><strong>讲师合作联系方式</strong>&nbsp;：<br />讲师招聘负责人：陈老师&nbsp;<br />联系电话：18027263931&nbsp;<br />联系QQ：2955237748&nbsp;<br />联系邮箱：chenbs@roncoo.com&nbsp;<br />官方微信公众号：RonCoo_com</p>\n<p>讲师在线申请地址：<a href=\"http://qa.www.roncoo.com/recruit\">http://qa.www.roncoo.com/recruit</a></p>');
INSERT INTO `website_nav_article` VALUES (1060342529602899969, '2018-11-08 09:26:48', '2018-11-08 09:26:48', 1, 1, 1060115285643177985, '项目合作', 'http://static-dev.roncoo.com/course/b290902a59294ec1885df4a381dd51fd.jpg', '<p></p>\n<p><strong>商务及项目合作</strong></p>\n<p>吴老师（吴水成）</p>\n<p>手机：18926215592</p>\n<p>微信：wushuicheng</p>\n<p>邮箱：wusc@roncoo.com</p>\n<p><br /></p>\n<p><strong>广州市领课网络科技有限公司</strong><br />地址：广州市天河区珠江新城华强路3号富力盈力大厦北塔1606</p>\n<p>电话：020-87603362</p>\n<p>邮箱：service@roncoo.com</p>\n<p></p>\n<p><br /></p>');
INSERT INTO `website_nav_article` VALUES (1060708890749444097, '2018-11-09 09:42:38', '2018-11-09 09:42:38', 1, 1, 1060708775632576514, '用户协议', 'http://static-dev.roncoo.com/course/ec0ae2ff117c4cd7b63e42c1110e25f2.png', '<p></p>\n<p></p>\n<p></p>\n<p></p>\n<p></p>\n<p></p>\n<h2>一、用户协议总则</h2>\n<p>1、&nbsp;&nbsp;&nbsp;&nbsp; 本协议双方为<u>&nbsp; &nbsp;领课教育&nbsp; &nbsp;</u>和<u>&nbsp; &nbsp;</u><u>领课教育</u><u>网站的注册用户（以下简称用户）&nbsp;&nbsp;</u>。</p>\n<p>2、&nbsp;&nbsp;&nbsp;&nbsp; 用户在注册前请仔细阅读本协议的条款，并按照页面上的提示完成全部注册程序。</p>\n<p>3、&nbsp;&nbsp;&nbsp;&nbsp; 用户在进行注册过程中点击“同意”按钮，即表示用户已充分知悉和完全接受本协议项下全部条款，进而与甲方达成本协议。</p>\n<p>4、&nbsp;&nbsp;&nbsp;&nbsp; 甲方网站有权不时对本协议项下相关规则作出修改或补充，并于网站公布。用户若继续使用即视为您接受修订后的本协议条款。</p>\n<p><br /></p>\n<h2>二、用户服务使用说明</h2>\n<p>1、用户在注册时应按照注册提示填写准确的用户名、密码及真实的手机号码等相关个人资料，符合完整、准确的要求。</p>\n<p>2、用户一旦注册成功，便成为甲方网站合法的注册用户，将获得本网站的一个用户账号和相应密码，用户可随时修改自己的用户密码。用户应对其账号和密码安全负全部责任，并应对其用户名下所进行的所有行为和事件承担相应的法律责任。</p>\n<p>3、用户同意接受甲方网站通过或其他方式向用户发送有关商业信息。</p>\n<p>4、甲方网站不对用户所发布信息的删除或储存失败负责。</p>\n<p>5、甲方网站不提供账号删除服务，如果用户需要删除账号，请直接放弃使用即可。</p>\n<p>6、甲方网站有判定用户的行为是否符合本网站服务条款要求的权利，如果用户违背了服务条款的规定，本网站有权对其用户所提供的网络服务进行中断或停止使用。</p>\n<p>7、用户不得以任何非法目的或其它方式对甲方网站的个人用户账号进行转让、转借、倒卖、账号共享等行为（用户账号仅限由其本人使用）。</p>\n<p><br /></p>\n<h2>三、协议内容的变更和修订</h2>\n<p>1、甲方网站有权在必要时修改服务条款，修改后的协议可以在甲方网站上查看。</p>\n<p>2、用户如果不同意甲方所改动的内容，可自行停止使用本站网络服务。</p>\n<p>3、如果用户继续享用本站网络服务，则视为同意接受本网站服务条款的变动。</p>\n<p>4、甲方网站可随时根据实际情况中断或终止一项或多项网络服务而无需对任何用户或第三方承担任何责任，如用户对一项或多项网络服务的中断或终止有异议，可以行使如下权利：</p>\n<p>（1）&nbsp; 自行停止使用甲方网站的网络服务。</p>\n<p>（2）&nbsp; 通知甲方网站停止对该用户的服务。 结束用户服务后，用户使用网络服务的权利立即终止，从终止时起，用户没有权利再进行处理任何未完成的信息或服务，甲方网站也没有义务为其传送任何未处理的信息或未完成的服务给用户或任何第三方。</p>\n<p><br /></p>\n<h2>四、用户隐私保护</h2>\n<p>甲方网站将严格履行用户个人隐私保密义务，承诺不公开、编辑或透露用户个人信息，但以下特殊情况除外：</p>\n<p>1、经注册用户事先许可授权；</p>\n<p>2、遵守国家法律法规或配合相关政府部门的要求；</p>\n<p>3、遵从甲方网站合法服务程序；</p>\n<p>4、为维护社会公众利益以及甲方网站的合法权益所必须。</p>\n<p><br /></p>\n<h2>五、注册用户的权利与义务</h2>\n<p>1、注册用户在使用甲方网站服务时，必须遵守中华人民共和国相关法律法规的规定，用户应同意将不会利用本服务进行任何违法或不正当的活动，否则用户将自行承担由此产生的一切法律责任。</p>\n<p>2、用户在账号使用过程中不得上载、展示、张贴、传播或以其它方式传送含有下列内容之一的信息：</p>\n<p>（1）&nbsp; 危害国家安全，泄露国家秘密，颠覆国家政权，破坏国家统一的；</p>\n<p>（2）&nbsp; 损害国家荣誉和利益的；</p>\n<p>（3）&nbsp; 煽动民族仇恨、民族歧视、破坏民族团结的；</p>\n<p>（4）&nbsp; 破坏国家宗教政策，宣扬邪教和封建迷信的；</p>\n<p>（5）&nbsp; 散布谣言，扰乱社会秩序，破坏社会稳定的；</p>\n<p>（6）&nbsp; 散布淫秽、色情、赌博、暴力、凶杀、恐怖或者教唆犯罪的；</p>\n<p>（7）&nbsp; 侮辱或者诽谤他人，侵害他人合法权利的；</p>\n<p>（8）&nbsp; 含有虚假、有害、胁迫、侵害他人隐私、骚扰、侵害、中伤、粗俗、猥亵、或其它道德上令人反感的内容。</p>\n<p>3、不得为任何非法目的而使用网络服务系统。</p>\n<p>4、不得利用甲方网站服务故意制作、传播计算机病毒等破坏性程序，或其他从事任何危害计算机信息网络安全的行为。</p>\n<p>5、若用户行为违反上述约定，甲方网站有权作出独立判断并立即取消用户的服务账号，用户应对自己网上的行为承担一切法律责任，甲方网站的系统记录有可能作为用户违反法律的证据提交给相关主管部门。</p>\n<p>6、用户应同意保障和维护甲方网站全体成员及其他用户的利益，如因违反本协议或违反有关的法律法规而给甲方网站或任何第三者造成损失，用户应承担因此产生的法律责任。</p>\n<p><br /></p>\n<h2>五、甲方网络服务内容的所有权</h2>\n<p>1、甲方网站定义的网络服务内容包括但不限于：教学视频、资料、源码、文字、软件、声音、图片、商标等。该等内容均受《著作权法》、《商标法》、《专利法》、《计算机软件保护条例》及其他相关法律法规的保护。</p>\n<p>2、甲方网站所有的文章版权归原文作者和甲方网站共同所有，任何人需要转载本网站版内的文章，必须事先取得原文作者和甲方网站的授权同意。</p>\n<p>3、未经甲方网站或其他有权第三方的事先许可授权，用户不得对包括但不限于：教学视频、学习软件、学习资料、源码、图片、音频内容等在内的任何内容进行翻录、复制、发行、破解、信息网络传播或其他违反知识产权相关法律、法规的行为，否则所导致的一切民事、行政或刑事责任，由用户自行承担。</p>\n<p><br /></p>\n<h2>六、免责声明</h2>\n<p>1、用户同意承担使用甲方网站服务所存在的一切风险以及因使用网络服务而产生的一切后果，甲方网站对用户不承担任何责任。</p>\n<p>2、甲方网站不担保服务一定能满足用户的要求，也不担保服务不会中断，亦对服务的及时性，安全性及可能发生的技术错误均不作任何担保。</p>\n<p>3、任何由于黑客攻击、计算机病毒侵入或发作、政府管制、硬件故障、不可抗力等非甲方故意或严重过失而造成的用户个人资料泄露、丢失、被盗用、被篡改或服务暂定或终止的，对用户可能造成的风险或损失，甲方不承担法律责任。</p>\n<p><br /></p>\n<h2>七、其他约定</h2>\n<p>1、用户同意因本平台服务产生的任何争议均适用中华人民共和国法律，相关争议任何一方可向甲方住所地人民法院提起诉讼解决。</p>\n<p>2、本协议中的标题仅为方便而设，不影响对于条款本身的解释。本协议中的任何条款无论因何种原因完全或部分无效或不具有执行力，其余条款仍应具有约束力。</p>\n<p>（完）</p>\n<p></p>\n<p><br /></p>\n<p></p>\n<p><br /></p>\n<p></p>\n<p><br /></p>\n<p></p>\n<p><br /></p>\n<p></p>\n<p><br /></p>');
INSERT INTO `website_nav_article` VALUES (1080719257771933698, '2019-01-03 14:55:22', '2019-01-03 14:55:22', 1, 1, 1080719070034886658, '讲师协议', NULL, '<p></p>\n<p></p>\n<p></p>\n<p></p>\n<h3>一、合作协议总则</h3>\n<p>1、 本协议双方为甲方和乙方<u>（以下简称“讲师”或“您”）</u>。</p>\n<p>2、 乙方在注册前请仔细阅读本协议的条款，并按照页面上的提示完成全部注册程序。</p>\n<p>3、 乙方在进行注册过程中点击“同意”按钮，即表示您已充分知悉和完全接受本协议项下全部条款，进而与甲方达成本协议。</p>\n<p>4、 甲方有权不时对本协议项下各类业务规则作出修改或补充，并通过甲方网站公布。您若继续使用即视为您接受修订后的本协议条款。否则,您有权在确保用户权益的情况下终止与甲方的合作。</p>\n<p><br /></p>\n<h3>二、合作内容</h3>\n<p>1、乙方作为甲方旗下:<u>&nbsp; &nbsp;&nbsp;</u><u>领课教育&nbsp; &nbsp;</u><u>&nbsp;</u>的签约讲师，基于甲方&nbsp;&nbsp;&nbsp;&nbsp;的在线教育平台，为甲方的用户提供远程培训课程及答疑服务。乙方主要工作包括录制课程和定期在线答疑。</p>\n<p>2、甲方提供对乙方的课程信息（讲师）展示和推广服务，提供对课程播放的技术维护、服务器宽带、客服销售支持工作。</p>\n<p><br /></p>\n<h3>三、分成比例与结算方式</h3>\n<p>1、乙方享有对提供的课程的定价权，甲方可提出定价参考建议。</p>\n<p>2、甲方收取合乙方所发布的课程销售总额的<u>&nbsp;<b>&nbsp;40&nbsp;&nbsp;&nbsp;</b></u><b>%&nbsp;</b>作为报酬。</p>\n<p>3、乙方提供的课程在甲方平台上销售收入和销售分成以甲方系统中实际记录的数据为准,若有异议的双方可协商处理,并于协商之日起7个工作日内予以确认。双方无法协商一致的，任何一方可按本协议争议解决条款维护权益。</p>\n<p>4、乙方分成收益的结算周期为1个月,&nbsp;甲方于每个月的前10个工作日（法定节假日顺延）汇总统计乙方的应得收益，经乙方确认无误之日起（以电子邮件或微信的形式确认），甲方在3个工作日内将款项支付至乙方指定银行账户。</p>\n<p>5、乙方的银行卡信息若变更需及时通知甲方,否则造成的损失由乙方负责。</p>\n<p><br /></p>\n<h3>四、课程内容规定</h3>\n<p>1、乙方提供的录制课程不得含有下列内容：</p>\n<p>（1）&nbsp; 违反宪法确定的基本原则的。</p>\n<p>（2）&nbsp; 危害国家的统一、主权和领土完整的。</p>\n<p>（3）&nbsp; 泄漏国家秘密、危害国家安全或者损害国家的荣誉和利益的。</p>\n<p>（4）&nbsp; 煽动民族仇恨、分裂和歧视，侵害少数民族风俗习惯，破坏民族团结的。</p>\n<p>（5）&nbsp; 宣扬邪教、迷信的，扰乱社会秩序，破坏社会稳定的。</p>\n<p>（6）&nbsp; 宣扬淫秽、赌博或者渲染暴力、教唆犯罪的，危害社会公德和民族优秀文化传统的。</p>\n<p>（7）&nbsp; 侮辱或者诽谤他人，侵害他人合法权益的。</p>\n<p>（8）&nbsp; 有法律、行政法规和国家规定禁止的其他内容的。</p>\n<p>2、如因乙方课程内容违法违规造成的一切损失和责任，由乙方自行独立承担。如甲方发现乙方课程存在前述不合法不合规的情况，有权告知乙方并建议调整及整改；乙方应积极配合，主动整改。自收到甲方建议之日起7个工作日内未整改完毕的，甲方有权单方解除本合同。</p>\n<p><br /></p>\n<h3>五、课程交付规则</h3>\n<p>1、乙方提交课程大纲经甲方审核通过之日起（以电子邮件或微信的形式确认），乙方应在1个月内提交第一次课时内容（首次提交的课时内容不得少于全部课时内容的50%），并承诺保证以后每周至少提交3个课时以上的内容，全部课时必须在3个月以内完成提交。</p>\n<p>2、如乙方由于不可抗力导致不能按时提交/更新课程时，须在出现该原因之日起5日内通知甲方，双方另行约定课程的更新/交付日期。</p>\n<p>3、如果因乙方个人原因导致无法按时完成全部课程时，乙方需提前7个工作日与甲方进行协商沟通,&nbsp;双方另行约定课程的更新/交付日期。</p>\n<p>4、如果在双方另行约定更新/交付日期到期时，乙方仍不能更新/交付课件，甲方有权选择下线该课程，单方解除合作关系而不承担任何违约责任；乙方同意对已上线的课程收入所得进行冻结，用以垫付用户/学员退款（退款金额全部由乙方承担）；如果乙方课程收入所得无法满足向用户/学员退款的金额，不足部分由乙方承担差额补足责任，并对由此给甲方造成的损失承担法律责任。</p>\n<p><br /></p>\n<h3>六、课程发布规则</h3>\n<p>1、乙方提供的课程中，必须有1个课时（包含1个课时）以上的免费课时。</p>\n<p>2、乙方提交课程之日起7个工作日内，甲方应完成课程审核，并发布上线，如审核未通过将会在审核结束后3个工作日内以电子邮件方式通知乙方。</p>\n<p>3、如因任何一方出现不可抗力而需要推迟课程发布日期时，存在不可抗力的一方应向另一方及时说明，双方另行协商确定课程发布日期。</p>\n<p>4、如甲方由于不可抗力停止该课程的合作，甲方应按照本合作协议项下的分成比例条款向乙方支付该课程剩余销售所得。</p>\n<p><br /></p>\n<h3>七、播放权与版权规则</h3>\n<p>1、乙方提供给甲方在线教育平台所发布的课程以及课件（包括但不限于文档），在本协议有效期内，甲方拥有课程及课件的播放权、使用权。本协议有效期届满（或经双方协商一致同意提前终止合作协议后），为保证已付费用户利益，甲方仍保留该课程对已付费用户的播放权，但不会再销售该课程。</p>\n<p>2、如出现因乙方提供的课程涉嫌违反相关法律法规规定，有可能导致甲方承担法律责任、经济或名誉损失的，甲方有权决定停播此课程。</p>\n<p>3、如发现乙方提供的课程在其它网站（包含乙方自己的网站）销售价格低于在甲方网站上销售的价格，甲方有权决定停播此课程。课程停播后，如果出现需要向付费用户退款的情况，乙方同意将乙方课程收入所得用以垫付用户/学员退款（退款金额由乙方承担）；如果乙方课程收入所得无法满足向用户/学员退款的金额，不足部分由乙方承担差额补足责任，并对由此给甲方造成的损失承担法律责任。</p>\n<p>4、除本条第1点约定的播放权和使用权外，本课程的版权归乙方所有。</p>\n<p>5、乙方承诺向甲方提供的课程不存在与第三方的任何版权纠纷或潜在争议。否则，由此带来的一切法律后果由乙方自行独立承担。</p>\n<p><br /></p>\n<h3>八、服务规则</h3>\n<p>1、乙方按照提交的课程大纲录制作品，并在甲乙双方商定时间内交付/更新。</p>\n<p>2、课程上线后，乙方（讲师）应在48小时内线上回复付费用户与该课程有关的疑问。</p>\n<p>3、本协议有效期届满（或经双方协商一致同意提前终止合作后），乙方仍需回复付费用户与该课程有关的疑问。</p>\n<p><br /></p>\n<h3>九、保密规则</h3>\n<p>1、甲乙双方应对因履行本协议而取得的对方的各种形式的技术或商业信息保密（包括本协议内容本身），法律法规另有规定的除外；未经一方事先书面允许，另一方不得将双方合作信息，如价格，销售分成比例等透露给第三方。</p>\n<p><br /></p>\n<h3>十、其他约定</h3>\n<p>1、本协议项下任何争议，双方应友好协商解决；协商不成的，任何一方均可向甲方所在地人民法院提起诉讼。</p>\n<p>2、课程的具体录制内容等其他条款，双方在《课程制作规范》进行约定。《课程制作规范》和本协议具有同等的法律效力。</p>\n<p>3、自乙方点击同意接受本协议全部条款之时生效。</p>\n<p></p>\n<p><br /></p>\n<p></p>\n<p><br /></p>\n<p></p>\n<p><br /></p>\n<p></p>\n<p><br /></p>');

SET FOREIGN_KEY_CHECKS = 1;
