/*
 Navicat Premium Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : liu

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 16/07/2023 13:50:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_address
-- ----------------------------
DROP TABLE IF EXISTS `sys_address`;
CREATE TABLE `sys_address`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_address
-- ----------------------------
INSERT INTO `sys_address` VALUES (1, '杭州');
INSERT INTO `sys_address` VALUES (2, '洛阳');
INSERT INTO `sys_address` VALUES (3, '成都');
INSERT INTO `sys_address` VALUES (4, '合肥');
INSERT INTO `sys_address` VALUES (5, '长江');
INSERT INTO `sys_address` VALUES (6, '河南');
INSERT INTO `sys_address` VALUES (7, '河北');
INSERT INTO `sys_address` VALUES (8, '武汉');
INSERT INTO `sys_address` VALUES (9, '安徽');
INSERT INTO `sys_address` VALUES (10, '黄河');

-- ----------------------------
-- Table structure for sys_article
-- ----------------------------
DROP TABLE IF EXISTS `sys_article`;
CREATE TABLE `sys_article`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '内容',
  `user_id` int NULL DEFAULT NULL COMMENT '发布人',
  `time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '发布时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_article
-- ----------------------------
INSERT INTO `sys_article` VALUES (4, '测试', '# 我是xxx\n## *~~++我是xxxx++~~*\n> 我是xxx的引用\n[百度](www.baidu.com)\n```java\npublic String Hello(){\n     return \"我是xxx\"\n}\n```\n![千库网_寒露节气秋天秋季_元素编号13335545.png](http://localhost:8111/images/千库网_寒露节气秋天秋季_元素编号13335545.png)', 1, '2023-07-04 00:57:08');
INSERT INTO `sys_article` VALUES (5, '第二个', '# xxx的文章\n```java\npublic static final String CODE_500 = \"500\";\n```\n> 去往百度\n[百度](www.baidu.com)\n![20201282863486.jpg](http://localhost:8111/images/20201282863486.jpg)', 95, '2023-07-04 01:32:20');
INSERT INTO `sys_article` VALUES (6, '管理员的文章', '# 文章来了\n[百度](www.baidu.com)\n++~~作者是: xxxxx~~++中划线++~~~~++*斜体*\n>** 没有好看的图片**\n\n# 此处是图片\n![20201282863486.jpg](http://localhost:8111/images/20201282863486.jpg)', 1, '2023-07-15 09:25:53');
INSERT INTO `sys_article` VALUES (7, '炸弹猫的文章', '```java\npublic static String name(String name){\n	return name;\n}\n```\n![java.jpg](http://localhost:8111/images/java.jpg)', 50, '2023-07-06 06:32:42');

-- ----------------------------
-- Table structure for sys_classifier
-- ----------------------------
DROP TABLE IF EXISTS `sys_classifier`;
CREATE TABLE `sys_classifier`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '文件类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_classifier
-- ----------------------------
INSERT INTO `sys_classifier` VALUES (1, '图片');
INSERT INTO `sys_classifier` VALUES (2, '音乐');
INSERT INTO `sys_classifier` VALUES (3, '视频');

-- ----------------------------
-- Table structure for sys_comment
-- ----------------------------
DROP TABLE IF EXISTS `sys_comment`;
CREATE TABLE `sys_comment`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '评论内容',
  `user_id` int NULL DEFAULT NULL COMMENT '评论人id',
  `time` timestamp(0) NULL DEFAULT NULL COMMENT '评论时间',
  `pid` int NULL DEFAULT NULL COMMENT '父id',
  `origin_id` int NULL DEFAULT NULL COMMENT '最上级评论id',
  `article_id` int NULL DEFAULT NULL COMMENT '关联文章的id',
  `pname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '给谁回复',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_comment
-- ----------------------------
INSERT INTO `sys_comment` VALUES (2, '好文章', 1, '2023-07-13 13:51:06', NULL, NULL, 5, NULL);
INSERT INTO `sys_comment` VALUES (3, '特别好', 1, '2023-07-13 13:51:47', NULL, NULL, 5, NULL);
INSERT INTO `sys_comment` VALUES (4, '真棒', 1, '2023-07-13 13:52:09', NULL, NULL, 5, NULL);
INSERT INTO `sys_comment` VALUES (6, '这是我的文章', 1, '2023-07-13 14:19:36', NULL, NULL, 4, NULL);
INSERT INTO `sys_comment` VALUES (8, '我又来了', 95, '2023-07-13 14:42:59', NULL, NULL, 5, NULL);
INSERT INTO `sys_comment` VALUES (25, '我来了', 122, '2023-07-14 07:37:13', 6, 6, 4, NULL);
INSERT INTO `sys_comment` VALUES (26, '你好啊！！！！！！', 122, '2023-07-14 07:43:03', NULL, NULL, 4, NULL);
INSERT INTO `sys_comment` VALUES (28, '管理员好啊', 95, '2023-07-15 01:20:26', 27, 8, 5, '管理员');
INSERT INTO `sys_comment` VALUES (29, '来人', 1, '2023-07-15 01:37:58', NULL, NULL, 6, NULL);
INSERT INTO `sys_comment` VALUES (30, '我的文章', 50, '2023-07-15 06:33:06', NULL, NULL, 7, NULL);
INSERT INTO `sys_comment` VALUES (31, '我来了', 50, '2023-07-15 06:33:18', 29, 29, 6, NULL);
INSERT INTO `sys_comment` VALUES (32, '我也来了\n', 111, '2023-07-15 06:34:11', 31, 29, 6, '炸弹猫');
INSERT INTO `sys_comment` VALUES (33, '作者好', 111, '2023-07-15 06:34:41', 28, 8, 5, '李白');
INSERT INTO `sys_comment` VALUES (34, '管理员来了', 1, '2023-07-15 06:41:37', NULL, NULL, 7, NULL);

-- ----------------------------
-- Table structure for sys_course
-- ----------------------------
DROP TABLE IF EXISTS `sys_course`;
CREATE TABLE `sys_course`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '课程名称',
  `score` int NULL DEFAULT NULL COMMENT '学分',
  `times` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '上课时间',
  `state` tinyint(1) NULL DEFAULT NULL COMMENT '是否开课',
  `teacher_id` int NULL DEFAULT NULL COMMENT '授课老师id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_course
-- ----------------------------
INSERT INTO `sys_course` VALUES (4, '大学英语', 10, '30', 1, 111);
INSERT INTO `sys_course` VALUES (5, '大学体育', 10, '30', 1, 94);
INSERT INTO `sys_course` VALUES (6, '大学高数', 12, '40', 0, 111);
INSERT INTO `sys_course` VALUES (7, '大学物理', 8, '20', 1, 111);

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '名称',
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '内容',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '类型'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('place', 'el-icon-place', 'icon');
INSERT INTO `sys_dict` VALUES ('menu', 'el-icon-s-grid', 'icon');
INSERT INTO `sys_dict` VALUES ('chat', 'el-icon-chat-dot-round', 'icon');
INSERT INTO `sys_dict` VALUES ('custom', 'el-icon-s-custom', 'icon');
INSERT INTO `sys_dict` VALUES ('house', 'el-icon-house', 'icon');
INSERT INTO `sys_dict` VALUES ('document', 'el-icon-document-copy', 'icon');
INSERT INTO `sys_dict` VALUES ('picture', 'el-icon-picture', 'icon');
INSERT INTO `sys_dict` VALUES ('headset', 'el-icon-headset', 'icon');
INSERT INTO `sys_dict` VALUES ('caret', 'el-icon-caret-right', 'icon');
INSERT INTO `sys_dict` VALUES ('dom', 'el-icon-document', 'icon');
INSERT INTO `sys_dict` VALUES ('user', 'el-icon-user', 'icon');
INSERT INTO `sys_dict` VALUES ('setting', 'el-icon-setting', 'icon');
INSERT INTO `sys_dict` VALUES ('solid', 'el-icon-user-solid', 'icon');
INSERT INTO `sys_dict` VALUES ('star', 'el-icon-star-on', 'icon');
INSERT INTO `sys_dict` VALUES ('loading', 'el-icon-loading', 'icon');
INSERT INTO `sys_dict` VALUES ('sugar', 'el-icon-sugar', 'icon');
INSERT INTO `sys_dict` VALUES ('rain', 'el-icon-heavy-rain', 'icon');
INSERT INTO `sys_dict` VALUES ('lightning', 'el-icon-lightning', 'icon');
INSERT INTO `sys_dict` VALUES ('sunny', 'el-icon-sunny', 'icon');
INSERT INTO `sys_dict` VALUES ('goods', 'el-icon-s-goods', 'icon');
INSERT INTO `sys_dict` VALUES ('map', 'el-icon-map-location', 'icon');

-- ----------------------------
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '文件名称',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '文件类型',
  `size` bigint NULL DEFAULT NULL COMMENT '文件大小',
  `sing_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '歌手名',
  `song_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '歌名',
  `song_length` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '歌曲时长',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '下载链接',
  `is_delete` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  `enable` tinyint(1) NULL DEFAULT 1 COMMENT '是否禁用链接',
  `md5` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '文件md5',
  `classifier_id` int NULL DEFAULT 1 COMMENT '文件类型id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 65 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_file
-- ----------------------------
INSERT INTO `sys_file` VALUES (1, '20201282863486.jpg', 'jpg', 34, NULL, NULL, NULL, 'http://localhost:8111/images/20201282863486.jpg', 0, 1, '2458f9d6b1951395934e1590ccd2d0cc', 1);
INSERT INTO `sys_file` VALUES (6, '千库网_寒露节气二十四节气树木公园_元素编号13343189.png', 'png', 7271, NULL, NULL, NULL, 'http://localhost:8111/images/千库网_寒露节气二十四节气树木公园_元素编号13343189.png', 0, 1, 'dd5122d34e6f9de17d25c22c2dc3b739', 1);
INSERT INTO `sys_file` VALUES (14, 'Reゼロから始める異世界生活.mp4', 'mp4', 80282, NULL, NULL, NULL, 'http://localhost:8111/images/Reゼロから始める異世界生活.mp4', 1, 1, 'ef55f45ad8c5afd0d033da587dd7d833', 3);
INSERT INTO `sys_file` VALUES (15, '鬼滅の刃 OP 紅蓮華 LiSA.mp4', 'mp4', 70589, NULL, NULL, NULL, 'http://localhost:8111/images/鬼滅の刃 OP 紅蓮華 LiSA.mp4', 0, 1, '879ebbf8e9b00dde8963300aa9989727', 3);
INSERT INTO `sys_file` VALUES (27, '千库网_寒露节气秋天秋季_元素编号13335545.png', 'png', 3103, NULL, NULL, NULL, 'http://localhost:8111/images/千库网_寒露节气秋天秋季_元素编号13335545.png', 0, 1, 'd9a0f3b5e50d699bb4d41679ac77d6c4', 1);
INSERT INTO `sys_file` VALUES (32, 'R-C.jpg', 'jpg', 132, NULL, NULL, NULL, 'http://localhost:8111/images/R-C.jpg', 0, 1, '213fc1c6b6048c092bc0c6252dcbaf43', 1);
INSERT INTO `sys_file` VALUES (33, '画江湖之不良人 第4季 第01话 .mp4', 'mp4', 304411, NULL, NULL, NULL, 'http://localhost:8111/images/画江湖之不良人 第4季 第01话 .mp4', 0, 1, '35c5f13993416b15eb8d4339b18d09c0', 3);
INSERT INTO `sys_file` VALUES (34, '画江湖之不良人 第4季 第02话 .mp4', 'mp4', 338156, NULL, NULL, NULL, 'http://localhost:8111/images/画江湖之不良人 第4季 第02话 .mp4', 0, 1, 'b6ea6c4590459ca240c22bbbd5ebce16', 3);
INSERT INTO `sys_file` VALUES (35, '画江湖之不良人 第4季 第03话 .mp4', 'mp4', 294439, NULL, NULL, NULL, 'http://localhost:8111/images/画江湖之不良人 第4季 第03话 .mp4', 0, 1, 'b43b3bd14711de85a62be0a3b97dd961', 3);
INSERT INTO `sys_file` VALUES (36, '画江湖之不良人 第4季 第04话 .mp4', 'mp4', 292654, NULL, NULL, NULL, 'http://localhost:8111/images/画江湖之不良人 第4季 第04话 .mp4', 0, 1, '7f0f8e626652d8178939ccf063400d31', 3);
INSERT INTO `sys_file` VALUES (37, '画江湖之不良人 第4季 第05话 .mp4', 'mp4', 334042, NULL, NULL, NULL, 'http://localhost:8111/images/画江湖之不良人 第4季 第05话 .mp4', 0, 1, 'b96194de686e316e8887fccd08d43954', 3);
INSERT INTO `sys_file` VALUES (38, '画江湖之不良人 第4季 第06话 .mp4', 'mp4', 142275, NULL, NULL, NULL, 'http://localhost:8111/images/画江湖之不良人 第4季 第06话 .mp4', 0, 1, '6dfb36a1a7433841f02204ed17752e21', 3);
INSERT INTO `sys_file` VALUES (39, '画江湖之不良人 第4季 第07话 .mp4', 'mp4', 291686, NULL, NULL, NULL, 'http://localhost:8111/images/画江湖之不良人 第4季 第07话 .mp4', 0, 1, '918f4a3dc0729f50e7bf9b246a233d05', 3);
INSERT INTO `sys_file` VALUES (40, '画江湖之不良人 第4季 第08话 .mp4', 'mp4', 286797, NULL, NULL, NULL, 'http://localhost:8111/images/画江湖之不良人 第4季 第08话 .mp4', 0, 1, 'f14da6798a7745768ebdcdd66007576d', 3);
INSERT INTO `sys_file` VALUES (41, '画江湖之不良人 第4季 第09话 .mp4', 'mp4', 304035, NULL, NULL, NULL, 'http://localhost:8111/images/画江湖之不良人 第4季 第09话 .mp4', 0, 1, '30ba9e322664e0afb6f1e558c4d679d0', 3);
INSERT INTO `sys_file` VALUES (42, '画江湖之不良人 第4季 第10话 .mp4', 'mp4', 339061, NULL, NULL, NULL, 'http://localhost:8111/images/画江湖之不良人 第4季 第10话 .mp4', 0, 1, 'ac4114b460be9312351fbdc00e259181', 3);
INSERT INTO `sys_file` VALUES (43, 'OIP-C.jpg', 'jpg', 22, NULL, NULL, NULL, 'http://localhost:8111/images/OIP-C.jpg', 0, 1, '190391261ad60c49765fdcacdba15bd5', 1);
INSERT INTO `sys_file` VALUES (45, 'bj.jpg', 'jpg', 276, NULL, NULL, NULL, 'http://localhost:8111/images/bj.jpg', 0, 1, 'be2c0cf36c83dc4fc983ddff34b41f58', 1);
INSERT INTO `sys_file` VALUES (46, '桃花诺.mp3', 'mp3', 3502, 'G.E.M.邓紫棋', '桃花诺', '3:39', 'http://localhost:8111/images/桃花诺.mp3', 0, 1, '71d16e2eb5339280420738d6d014452a', 2);
INSERT INTO `sys_file` VALUES (48, '爱人错过.mp3', 'mp3', 4575, '告五人', '爱人错过', '4:52', 'http://localhost:8111/images/爱人错过.mp3', 0, 1, '3a7791b23e7eda9ee94e3153338cb146', 2);
INSERT INTO `sys_file` VALUES (49, '虞兮叹.mp3', 'mp3', 3320, '闻人听書_', '虞兮叹', '3:30', 'http://localhost:8111/images/虞兮叹.mp3', 0, 1, '3efc3f417ea1bfab173efdd41fc5e2f8', 2);
INSERT INTO `sys_file` VALUES (50, '向云端.mp3', 'mp3', 3966, '小霞、海洋Bo', '向云端', '4:11', 'http://localhost:8111/images/向云端.mp3', 0, 1, '1255d8534e497dbfc4ac275b4d7b88b2', 2);
INSERT INTO `sys_file` VALUES (52, '不问ciaga(不问别离).mp3', 'mp3', 2586, '指尖笑', '不问ciaga(不问别离)', '2:43', 'http://localhost:8111/images/不问ciaga(不问别离).mp3', 0, 1, '8eb4302aa5a8bd2e941cec7fe2f09818', 2);
INSERT INTO `sys_file` VALUES (57, '微信图片_20220930113747.jpg', 'jpg', 159, NULL, NULL, NULL, 'http://localhost:8111/images/微信图片_20220930113747.jpg', 0, 1, '49fafe87634b9766aa1b6856fa635bc9', 1);
INSERT INTO `sys_file` VALUES (58, '千库网_寒露白色手绘棉花和睡觉的女孩免扣_元素编号11292892.png', 'png', 886, NULL, NULL, NULL, 'http://localhost:8111/images/千库网_寒露白色手绘棉花和睡觉的女孩免扣_元素编号11292892.png', 0, 1, '9a9e756b98c7153b604bdcb09ebe59f4', 1);
INSERT INTO `sys_file` VALUES (59, '千库网_二十四节气寒露重阳节菊花茶_元素编号12897382.png', 'png', 1789, NULL, NULL, NULL, 'http://localhost:8111/images/千库网_二十四节气寒露重阳节菊花茶_元素编号12897382.png', 0, 1, '6424318612f9b824d442e2b6b2e8d8ad', 1);
INSERT INTO `sys_file` VALUES (60, '千库网_白露寒露芦苇秋天秋季_元素编号12891742.png', 'png', 9416, NULL, NULL, NULL, 'http://localhost:8111/images/千库网_白露寒露芦苇秋天秋季_元素编号12891742.png', 0, 1, '87b3048965bda28ee474d3930b8e382c', 1);
INSERT INTO `sys_file` VALUES (61, '千库网_寒露节日秋季作物_元素编号12593123.png', 'png', 2594, NULL, NULL, NULL, 'http://localhost:8111/images/千库网_寒露节日秋季作物_元素编号12593123.png', 0, 1, '0a5d26774c5d82aec2017460a1ebcc7f', 1);
INSERT INTO `sys_file` VALUES (62, 'java.jpg', 'jpg', 4, NULL, NULL, NULL, 'http://localhost:8111/images/java.jpg', 0, 1, '302dd40c519935658c41dfdc0e881827', 1);
INSERT INTO `sys_file` VALUES (63, '山茶花读不懂白玫瑰.mp3', 'mp3', 3154, 'Lil笑笑', '山茶花读不懂白玫瑰', '3:19', 'http://localhost:8111/images/山茶花读不懂白玫瑰.mp3', 0, 1, '59314f54d3fad064eec8670d42648bc6', 2);
INSERT INTO `sys_file` VALUES (64, 'Reゼロから始める異世界生活.mp4', 'mp4', 80282, NULL, NULL, NULL, 'http://localhost:8111/images/Reゼロから始める異世界生活.mp4', 0, 1, 'ef55f45ad8c5afd0d033da587dd7d833', 3);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '名称',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '路径',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '图标',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '描述',
  `pid` int NULL DEFAULT NULL COMMENT '父级id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '主页', '/home', 'el-icon-house', '一级菜单', NULL);
INSERT INTO `sys_menu` VALUES (2, '系统管理', NULL, 'el-icon-s-grid', '一级菜单', NULL);
INSERT INTO `sys_menu` VALUES (3, '用户管理', '/user', 'el-icon-s-custom', '二级菜单', 2);
INSERT INTO `sys_menu` VALUES (5, '文件管理', NULL, 'el-icon-document', '二级菜单', 2);
INSERT INTO `sys_menu` VALUES (6, '所有文件', '/file', 'el-icon-document-copy', '三级菜单', 5);
INSERT INTO `sys_menu` VALUES (7, '图片管理', '/picture', 'el-icon-picture', '三级菜单', 5);
INSERT INTO `sys_menu` VALUES (8, '音乐管理', '/music', 'el-icon-headset', '三级菜单', 5);
INSERT INTO `sys_menu` VALUES (9, '视频管理', '/video', 'el-icon-caret-right', '三级菜单', 5);
INSERT INTO `sys_menu` VALUES (10, '菜单管理', '/menu', 'el-icon-s-grid', '二级菜单', 2);
INSERT INTO `sys_menu` VALUES (11, '个人中心', NULL, 'el-icon-user-solid', '一级菜单', NULL);
INSERT INTO `sys_menu` VALUES (12, '个人信息', '/person', 'el-icon-s-custom', '二级菜单', 11);
INSERT INTO `sys_menu` VALUES (13, '管理员', NULL, 'el-icon-user', '一级菜单', NULL);
INSERT INTO `sys_menu` VALUES (14, '角色管理', '/role', 'el-icon-user-solid', '二级菜单', 13);
INSERT INTO `sys_menu` VALUES (15, '角色设置', '/roleSetting', 'el-icon-setting', '二级菜单', 13);
INSERT INTO `sys_menu` VALUES (16, '图标管理', '/dictIcon', 'el-icon-sugar', '二级菜单，项目中的图标', 2);
INSERT INTO `sys_menu` VALUES (17, '选课系统', NULL, 'el-icon-loading', '一级菜单', NULL);
INSERT INTO `sys_menu` VALUES (18, '课程管理', '/course', 'el-icon-s-goods', '二级菜单，所有的课程', 17);
INSERT INTO `sys_menu` VALUES (19, '教师管理', '/teacher', 'el-icon-user-solid', '二级菜单，教师', 17);
INSERT INTO `sys_menu` VALUES (20, '学生管理', '/student', 'el-icon-sugar', '二级菜单，学生', 17);
INSERT INTO `sys_menu` VALUES (21, '缺德地图', '/mapContainer', 'el-icon-map-location', '地图', NULL);
INSERT INTO `sys_menu` VALUES (22, '文章管理', '/article', 'el-icon-picture', '文章，多级评论', NULL);
INSERT INTO `sys_menu` VALUES (23, '修改密码', '/password', 'el-icon-sunny', '密码修改只能通过这里', 11);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '描述',
  `sole_key` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '唯一标识',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '管理员', '管理员', 'ROLE_ADMIN');
INSERT INTO `sys_role` VALUES (2, '普通用户', '普通用户', 'ROLE_USER');
INSERT INTO `sys_role` VALUES (3, '游客', '游客', 'ROLE_VISIT');
INSERT INTO `sys_role` VALUES (4, '会员', '会员', 'ROLE_MEMBER');
INSERT INTO `sys_role` VALUES (5, '学生', '学生', 'ROLE_STUDENT');
INSERT INTO `sys_role` VALUES (6, '老师', '老师', 'ROLE_TEACHER');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` int NOT NULL COMMENT '角色id',
  `menu_id` int NULL DEFAULT NULL COMMENT '菜单id'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (2, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2);
INSERT INTO `sys_role_menu` VALUES (2, 5);
INSERT INTO `sys_role_menu` VALUES (2, 6);
INSERT INTO `sys_role_menu` VALUES (2, 7);
INSERT INTO `sys_role_menu` VALUES (2, 11);
INSERT INTO `sys_role_menu` VALUES (2, 12);
INSERT INTO `sys_role_menu` VALUES (2, 23);
INSERT INTO `sys_role_menu` VALUES (2, 17);
INSERT INTO `sys_role_menu` VALUES (2, 18);
INSERT INTO `sys_role_menu` VALUES (2, 21);
INSERT INTO `sys_role_menu` VALUES (2, 22);
INSERT INTO `sys_role_menu` VALUES (3, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2);
INSERT INTO `sys_role_menu` VALUES (3, 5);
INSERT INTO `sys_role_menu` VALUES (3, 6);
INSERT INTO `sys_role_menu` VALUES (3, 7);
INSERT INTO `sys_role_menu` VALUES (3, 11);
INSERT INTO `sys_role_menu` VALUES (3, 12);
INSERT INTO `sys_role_menu` VALUES (3, 23);
INSERT INTO `sys_role_menu` VALUES (3, 17);
INSERT INTO `sys_role_menu` VALUES (3, 18);
INSERT INTO `sys_role_menu` VALUES (3, 21);
INSERT INTO `sys_role_menu` VALUES (3, 22);
INSERT INTO `sys_role_menu` VALUES (4, 1);
INSERT INTO `sys_role_menu` VALUES (4, 2);
INSERT INTO `sys_role_menu` VALUES (4, 5);
INSERT INTO `sys_role_menu` VALUES (4, 6);
INSERT INTO `sys_role_menu` VALUES (4, 7);
INSERT INTO `sys_role_menu` VALUES (4, 8);
INSERT INTO `sys_role_menu` VALUES (4, 9);
INSERT INTO `sys_role_menu` VALUES (4, 16);
INSERT INTO `sys_role_menu` VALUES (4, 11);
INSERT INTO `sys_role_menu` VALUES (4, 12);
INSERT INTO `sys_role_menu` VALUES (4, 23);
INSERT INTO `sys_role_menu` VALUES (4, 17);
INSERT INTO `sys_role_menu` VALUES (4, 18);
INSERT INTO `sys_role_menu` VALUES (4, 19);
INSERT INTO `sys_role_menu` VALUES (4, 20);
INSERT INTO `sys_role_menu` VALUES (4, 21);
INSERT INTO `sys_role_menu` VALUES (4, 22);
INSERT INTO `sys_role_menu` VALUES (5, 1);
INSERT INTO `sys_role_menu` VALUES (5, 2);
INSERT INTO `sys_role_menu` VALUES (5, 5);
INSERT INTO `sys_role_menu` VALUES (5, 6);
INSERT INTO `sys_role_menu` VALUES (5, 7);
INSERT INTO `sys_role_menu` VALUES (5, 8);
INSERT INTO `sys_role_menu` VALUES (5, 9);
INSERT INTO `sys_role_menu` VALUES (5, 16);
INSERT INTO `sys_role_menu` VALUES (5, 11);
INSERT INTO `sys_role_menu` VALUES (5, 12);
INSERT INTO `sys_role_menu` VALUES (5, 23);
INSERT INTO `sys_role_menu` VALUES (5, 17);
INSERT INTO `sys_role_menu` VALUES (5, 18);
INSERT INTO `sys_role_menu` VALUES (5, 19);
INSERT INTO `sys_role_menu` VALUES (5, 20);
INSERT INTO `sys_role_menu` VALUES (5, 21);
INSERT INTO `sys_role_menu` VALUES (5, 22);
INSERT INTO `sys_role_menu` VALUES (6, 1);
INSERT INTO `sys_role_menu` VALUES (6, 11);
INSERT INTO `sys_role_menu` VALUES (6, 12);
INSERT INTO `sys_role_menu` VALUES (6, 23);
INSERT INTO `sys_role_menu` VALUES (6, 17);
INSERT INTO `sys_role_menu` VALUES (6, 18);
INSERT INTO `sys_role_menu` VALUES (6, 19);
INSERT INTO `sys_role_menu` VALUES (6, 20);
INSERT INTO `sys_role_menu` VALUES (6, 21);
INSERT INTO `sys_role_menu` VALUES (6, 22);
INSERT INTO `sys_role_menu` VALUES (1, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2);
INSERT INTO `sys_role_menu` VALUES (1, 3);
INSERT INTO `sys_role_menu` VALUES (1, 5);
INSERT INTO `sys_role_menu` VALUES (1, 6);
INSERT INTO `sys_role_menu` VALUES (1, 7);
INSERT INTO `sys_role_menu` VALUES (1, 8);
INSERT INTO `sys_role_menu` VALUES (1, 9);
INSERT INTO `sys_role_menu` VALUES (1, 10);
INSERT INTO `sys_role_menu` VALUES (1, 16);
INSERT INTO `sys_role_menu` VALUES (1, 11);
INSERT INTO `sys_role_menu` VALUES (1, 12);
INSERT INTO `sys_role_menu` VALUES (1, 23);
INSERT INTO `sys_role_menu` VALUES (1, 13);
INSERT INTO `sys_role_menu` VALUES (1, 14);
INSERT INTO `sys_role_menu` VALUES (1, 15);
INSERT INTO `sys_role_menu` VALUES (1, 17);
INSERT INTO `sys_role_menu` VALUES (1, 18);
INSERT INTO `sys_role_menu` VALUES (1, 19);
INSERT INTO `sys_role_menu` VALUES (1, 20);
INSERT INTO `sys_role_menu` VALUES (1, 21);
INSERT INTO `sys_role_menu` VALUES (1, 22);

-- ----------------------------
-- Table structure for sys_student_course
-- ----------------------------
DROP TABLE IF EXISTS `sys_student_course`;
CREATE TABLE `sys_student_course`  (
  `student_id` int NOT NULL COMMENT '学生id',
  `course_id` int NOT NULL COMMENT '课程id',
  PRIMARY KEY (`student_id`, `course_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_student_course
-- ----------------------------
INSERT INTO `sys_student_course` VALUES (122, 4);
INSERT INTO `sys_student_course` VALUES (122, 5);
INSERT INTO `sys_student_course` VALUES (122, 7);
INSERT INTO `sys_student_course` VALUES (123, 4);
INSERT INTO `sys_student_course` VALUES (123, 5);
INSERT INTO `sys_student_course` VALUES (125, 4);
INSERT INTO `sys_student_course` VALUES (125, 5);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '昵称',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '电话',
  `address_id` int NULL DEFAULT 1 COMMENT '地址',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `avatar_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '角色',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 128 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', 'admin', '管理员', 'admin@gmail.com', '18336797332', 6, '2023-01-12 12:02:12', 'http://localhost:8111/images/20201282863486.jpg', 'ROLE_ADMIN');
INSERT INTO `sys_user` VALUES (2, 'zhuazxc', 'zhuazxc', '猪八戒', 'zhu@163.com', '15739673634', 6, '2023-07-01 13:01:24', 'http://localhost:8111/images/千库网_寒露白色手绘棉花和睡觉的女孩免扣_元素编号11292892.png', 'ROLE_USER');
INSERT INTO `sys_user` VALUES (11, 'sunwu', 'sunwu', '孙悟空', 'sun@163.com', '1472957294', 3, '2023-11-12 14:00:05', 'http://localhost:8111/images/千库网_二十四节气寒露重阳节菊花茶_元素编号12897382.png', 'ROLE_USER');
INSERT INTO `sys_user` VALUES (50, 'zhadan', 'zhadan', '炸弹猫', 'shenmengxi@gmial.com', '18023824816', 3, '2023-01-13 22:07:19', 'http://localhost:8111/images/千库网_寒露节气二十四节气树木公园_元素编号13343189.png', 'ROLE_MEMBER');
INSERT INTO `sys_user` VALUES (51, 'lixin', 'lixin', '李信', 'lixin@qq.com', '18012382421', 4, '2023-06-13 22:07:26', 'http://localhost:8111/images/千库网_白露寒露芦苇秋天秋季_元素编号12891742.png', 'ROLE_USER');
INSERT INTO `sys_user` VALUES (94, 'qin', 'qin', '秦teacher', '秦@qq.com', '18838612388', 9, '2023-08-17 09:55:18', 'http://localhost:8111/images/R-C.jpg', 'ROLE_TEACHER');
INSERT INTO `sys_user` VALUES (95, 'libai', 'libai', '李白', 'libai@gmail.com', '199285124751', 2, '2023-09-17 10:09:18', 'http://localhost:8111/images/R-C.jpg', 'ROLE_USER');
INSERT INTO `sys_user` VALUES (111, 'liu', 'liu', 'HaoYu', 'liu030919@gmail.com', '18336797332', 6, '2023-12-28 17:21:04', 'http://localhost:8111/images/千库网_寒露节日秋季作物_元素编号12593123.png', 'ROLE_TEACHER');
INSERT INTO `sys_user` VALUES (118, 'liu1', 'liu1', 'nickname', 'email', 'phone', 9, '2023-07-02 18:03:49', 'http://localhost:8111/images/千库网_寒露节气秋天秋季_元素编号13335545.png', 'ROLE_VISIT');
INSERT INTO `sys_user` VALUES (122, 'lisi', 'lisi', '李四', 'lisilisi@qq.com', '18814247392', 6, '2023-07-04 08:33:22', 'http://localhost:8111/images/OIP-C.jpg', 'ROLE_STUDENT');
INSERT INTO `sys_user` VALUES (123, 'wang', 'wang', '老王', 'wang@qq.com', '1895245923', 8, '2023-07-06 17:09:54', 'http://localhost:8111/images/R-C.jpg', 'ROLE_STUDENT');
INSERT INTO `sys_user` VALUES (125, 'xiaoming', 'xiaoming', '小明', 'libai@gmail.com', '199285124751', 2, '2023-07-06 17:17:55', 'http://localhost:8111/images/微信图片_20220930113747.jpg', 'ROLE_STUDENT');

SET FOREIGN_KEY_CHECKS = 1;
