/*
SQLyog Ultimate v8.32 
MySQL - 5.5.11 : Database - etower
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`etower` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `etower`;

/*Table structure for table `eo_authentication` */

DROP TABLE IF EXISTS `eo_authentication`;

CREATE TABLE `eo_authentication` (
  `AUTHENTICATION_ID` char(32) NOT NULL COMMENT '认证ID',
  `USER_ID` int(11) DEFAULT NULL COMMENT '用户ID',
  `USER_NAME` varchar(100) DEFAULT NULL COMMENT '用户名',
  `EMAIL` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `LOGIN_TIME` datetime DEFAULT NULL COMMENT '登陆时间',
  `LOGIN_IP` varchar(50) DEFAULT NULL COMMENT '登陆IP',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`AUTHENTICATION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='认证信息表';

/*Data for the table `eo_authentication` */

insert  into `eo_authentication`(`AUTHENTICATION_ID`,`USER_ID`,`USER_NAME`,`EMAIL`,`LOGIN_TIME`,`LOGIN_IP`,`UPDATE_TIME`) values ('05e1b7a72674456fb044aa4e5834b529',1,'admin','dalishu@hotmail.com','2013-03-27 21:59:07','127.0.0.1','2013-03-27 21:59:07'),('0ac930a42d8646158f0e5d78c97e0109',1,'admin','dalishu@hotmail.com','2013-03-27 22:01:24','127.0.0.1','2013-03-27 22:01:24'),('0e3d982fa05b4276a9e44709c4f51b18',1,'admin','dalishu@hotmail.com','2013-03-27 16:46:48','127.0.0.1','2013-03-27 16:46:48'),('14c839a301f14222af856c00e83414aa',1,'admin','dalishu@hotmail.com','2013-03-27 23:43:43','127.0.0.1','2013-03-27 23:43:43'),('18c41f4669074bd580159d36ad286024',1,'admin','dalishu@hotmail.com','2013-03-27 11:02:34','127.0.0.1','2013-03-27 11:02:34'),('1a59da45e7434367b4a1ec576dd09b14',1,'admin','dalishu@hotmail.com','2013-03-27 23:11:23','127.0.0.1','2013-03-27 23:11:23'),('1de8dc22de6f4dd6bb3997c040907dba',1,'admin','dalishu@hotmail.com','2013-03-27 17:01:54','127.0.0.1','2013-03-27 17:01:54'),('231cbc2c5b74468c96cb2f29c322197a',1,'admin','dalishu@hotmail.com','2013-03-27 15:10:06','127.0.0.1','2013-03-27 15:10:06'),('26c30c4caa40482f81ee2ad293ff9b18',1,'admin','dalishu@hotmail.com','2013-03-27 23:39:08','127.0.0.1','2013-03-27 23:39:08'),('2c0d2373829e4e709968189dcd876510',1,'admin','dalishu@hotmail.com','2013-03-27 16:53:03','127.0.0.1','2013-03-27 16:53:03'),('2cc08d0c7b424cb9a8060dee29c2df52',2,'test','idalivi@gmail.com','2013-03-28 00:24:46','127.0.0.1','2013-03-28 00:24:46'),('2ce38464243a4353a08cb7fe351e4ea0',1,'admin','dalishu@hotmail.com','2013-03-27 15:28:57','127.0.0.1','2013-03-27 15:28:57'),('30caf29f55e14bf5b10e3cbe79a3da4c',1,'admin','dalishu@hotmail.com','2013-03-28 00:03:16','127.0.0.1','2013-03-28 00:03:16'),('33cb9ec1c33f40dbb37bd67e7dd13710',1,'admin','dalishu@hotmail.com','2013-03-28 14:57:06','127.0.0.1','2013-03-28 14:57:06'),('37d85b54caee4c099d54c176dbab525f',1,'admin','dalishu@hotmail.com','2013-03-27 00:35:34','127.0.0.1','2013-03-27 00:35:34'),('41296452653b482ca3d9313e0582c6b5',1,'admin','dalishu@hotmail.com','2013-03-27 16:10:04','127.0.0.1','2013-03-27 16:10:04'),('45138ac9b8bf41bab83dc57946be91b4',1,'admin','dalishu@hotmail.com','2013-03-27 22:05:15','127.0.0.1','2013-03-27 22:05:15'),('464a66c779154b5ba0743103dc566f35',1,'admin','dalishu@hotmail.com','2013-03-28 00:08:42','127.0.0.1','2013-03-28 00:08:42'),('4dac3ced2483406cbda5dd829616919f',1,'admin','dalishu@hotmail.com','2013-03-27 16:43:23','127.0.0.1','2013-03-27 16:43:23'),('5b1a211c8c364336a493d9943c60dcb7',1,'admin','dalishu@hotmail.com','2013-03-27 22:30:45','127.0.0.1','2013-03-27 22:30:45'),('5cecd03e9b5f4ec697092df62ff06a5e',1,'admin','dalishu@hotmail.com','2013-03-27 23:41:15','127.0.0.1','2013-03-27 23:41:15'),('5e8c1418686643398bb0b98550a9c2f1',1,'admin','dalishu@hotmail.com','2013-03-26 23:51:47','127.0.0.1','2013-03-26 23:51:47'),('5fb1fa72466e49568fdddc1db92e3445',1,'admin','dalishu@hotmail.com','2013-03-27 11:16:19','127.0.0.1','2013-03-27 11:16:19'),('62430c0e6111488b9cadee5da2129a06',1,'admin','dalishu@hotmail.com','2013-03-27 18:12:43','127.0.0.1','2013-03-27 18:12:43'),('671367e16c3c47efa6a220b10fa2cda9',1,'admin','dalishu@hotmail.com','2013-03-27 16:40:35','127.0.0.1','2013-03-27 16:40:35'),('6d7b00e30add4d4aa6154adecc6126ec',1,'admin','dalishu@hotmail.com','2013-03-27 23:47:21','127.0.0.1','2013-03-27 23:47:21'),('6dd66cabca15422f9b5d7a584ae07caf',1,'admin','dalishu@hotmail.com','2013-03-27 16:44:43','127.0.0.1','2013-03-27 16:44:43'),('74ddca29113a4e8f80a71fb7b484ccee',1,'admin','dalishu@hotmail.com','2013-03-27 00:57:53','127.0.0.1','2013-03-27 00:57:53'),('82cb30588dda4b53b3a74724bd31902d',1,'admin','dalishu@hotmail.com','2013-03-27 11:00:19','127.0.0.1','2013-03-27 11:00:19'),('8a3d3bc1d6014acba4331db814828ab6',1,'admin','dalishu@hotmail.com','2013-03-27 11:49:00','127.0.0.1','2013-03-27 11:49:00'),('90339f3c94a746d590be008561b38fdc',1,'admin','dalishu@hotmail.com','2013-03-27 00:39:18','127.0.0.1','2013-03-27 00:39:18'),('90f7e8911859471ca8cbe9d9f9108698',1,'admin','dalishu@hotmail.com','2013-03-27 15:47:38','127.0.0.1','2013-03-27 15:47:38'),('92432b4b350d435fb2c2d190b8785c73',1,'admin','dalishu@hotmail.com','2013-03-27 15:32:38','127.0.0.1','2013-03-27 15:32:38'),('9528bec5f0c14bbca2e39596f07b8923',1,'admin','dalishu@hotmail.com','2013-03-27 00:50:14','127.0.0.1','2013-03-27 00:50:14'),('958282f4345b4bdeb3b849cfc03d08e4',1,'admin','dalishu@hotmail.com','2013-03-27 22:12:48','127.0.0.1','2013-03-27 22:12:48'),('95cf9c9dc0644fa4bcb374045ddc7213',1,'admin','dalishu@hotmail.com','2013-03-27 23:25:15','127.0.0.1','2013-03-27 23:25:15'),('97890376a0df4724a329a0dd5771a8c0',1,'admin','dalishu@hotmail.com','2013-03-27 22:28:39','127.0.0.1','2013-03-27 22:28:39'),('992aa72cfb6942b780e248d0234bd5ec',1,'admin','dalishu@hotmail.com','2013-03-27 00:52:06','127.0.0.1','2013-03-27 00:52:06'),('99edd98e5d0d4d61963d9f3a6c8869f2',1,'admin','dalishu@hotmail.com','2013-03-27 01:05:22','127.0.0.1','2013-03-27 01:05:22'),('9a876f094fde4f1caf0145fae0cfeb9d',1,'admin','dalishu@hotmail.com','2013-03-27 10:53:13','127.0.0.1','2013-03-27 10:53:13'),('9b097ed92aa74ad194037f605a24cf3d',1,'admin','dalishu@hotmail.com','2013-03-28 00:16:36','127.0.0.1','2013-03-28 00:16:36'),('9bbbec0e93f14494bca8431dd4097078',1,'admin','dalishu@hotmail.com','2013-03-27 15:03:02','127.0.0.1','2013-03-27 15:03:02'),('9d00e9ecfdda400192e25cf52153c692',1,'admin','dalishu@hotmail.com','2013-03-27 16:23:00','127.0.0.1','2013-03-27 16:23:00'),('9e2c3b87d5e347b7ab867551d4d401ef',1,'admin','dalishu@hotmail.com','2013-03-27 00:30:14','127.0.0.1','2013-03-27 00:30:14'),('9e62ed7b27cd4560ac42bdf0d6e17fd0',1,'admin','dalishu@hotmail.com','2013-03-27 22:32:53','127.0.0.1','2013-03-27 22:32:53'),('a355b2a7cfb542ae9071c8d53cd73c5d',1,'admin','dalishu@hotmail.com','2013-03-27 16:56:16','127.0.0.1','2013-03-27 16:56:16'),('ab74afce32f9490f9d9043fe898a493f',1,'admin','dalishu@hotmail.com','2013-03-26 23:54:56','127.0.0.1','2013-03-26 23:54:56'),('abb298147f5742589e83feaac98e086c',1,'admin','dalishu@hotmail.com','2013-03-27 00:11:58','127.0.0.1','2013-03-27 00:11:58'),('b0eed5ba45524514b329c684f23e4221',1,'admin','dalishu@hotmail.com','2013-03-27 22:39:01','127.0.0.1','2013-03-27 22:39:01'),('b17978bfc01749978ec9948f93a2d16d',1,'admin','dalishu@hotmail.com','2013-03-27 10:56:11','127.0.0.1','2013-03-27 10:56:11'),('b36025a9e9294b48851df9ea7b1b685f',1,'admin','dalishu@hotmail.com','2013-03-27 23:13:28','127.0.0.1','2013-03-27 23:13:28'),('b7239f2cde14493d86904a5b8c6b597b',1,'admin','dalishu@hotmail.com','2013-03-27 18:11:19','127.0.0.1','2013-03-27 18:11:19'),('ba8ef1e752024bb183e89713a6edd9d3',1,'admin','dalishu@hotmail.com','2013-03-27 16:34:04','127.0.0.1','2013-03-27 16:34:04'),('bd60413464a3495497d69ee633a98103',1,'admin','dalishu@hotmail.com','2013-03-27 18:18:17','127.0.0.1','2013-03-27 18:18:17'),('c588a8a3c29a426daf439f3fe9e80477',1,'admin','dalishu@hotmail.com','2013-03-27 23:04:39','127.0.0.1','2013-03-27 23:04:39'),('cf2a8ac4683346349f860134e440a73b',1,'admin','dalishu@hotmail.com','2013-03-27 21:31:03','127.0.0.1','2013-03-27 21:31:03'),('cfc35a3e66eb4088bcc1cc9cc0cc13d5',1,'admin','dalishu@hotmail.com','2013-03-27 00:36:55','127.0.0.1','2013-03-27 00:36:55'),('d2c0b236c78e461dbdee5a19ae998754',1,'admin','dalishu@hotmail.com','2013-03-27 15:09:01','127.0.0.1','2013-03-27 15:09:01'),('d9ef6eb440954494bad29469a718bdc8',1,'admin','dalishu@hotmail.com','2013-03-27 21:28:38','127.0.0.1','2013-03-27 21:28:38'),('dc172f25d70e48859883ebfb0f423fd1',1,'admin','dalishu@hotmail.com','2013-03-27 15:37:00','127.0.0.1','2013-03-27 15:37:00'),('dcee9f0107454fd38ecd62b3c40b4b46',1,'admin','dalishu@hotmail.com','2013-03-27 15:01:24','127.0.0.1','2013-03-27 15:01:24'),('de2cacfd8e6f488c95e2b89fce9f4803',1,'admin','dalishu@hotmail.com','2013-03-27 10:57:10','127.0.0.1','2013-03-27 10:57:10'),('e00e0060eda946f0901b4d63cac6830a',1,'admin','dalishu@hotmail.com','2013-03-27 23:37:02','127.0.0.1','2013-03-27 23:37:02'),('e0ce6d1876454fe1b37bbd3301f130f9',1,'admin','dalishu@hotmail.com','2013-03-27 21:07:07','127.0.0.1','2013-03-27 21:07:07'),('e16ff6547e314bdf939ae4bce1507710',1,'admin','dalishu@hotmail.com','2013-03-27 14:58:33','127.0.0.1','2013-03-27 14:58:33'),('e28b1c4634c44f40971657b379ec6f67',1,'admin','dalishu@hotmail.com','2013-03-27 15:26:16','127.0.0.1','2013-03-27 15:26:16'),('e2dfa52219974f0ebae12d2bf0d2ef51',1,'admin','dalishu@hotmail.com','2013-03-27 16:35:48','127.0.0.1','2013-03-27 16:35:48'),('ebd5636f81614d4ca09be36bac4055c0',1,'admin','dalishu@hotmail.com','2013-03-27 01:00:17','127.0.0.1','2013-03-27 01:00:17'),('f2fd3f85f11049e98bf67e1fc2ccf10b',1,'admin','dalishu@hotmail.com','2013-03-27 15:05:47','127.0.0.1','2013-03-27 15:05:47'),('f4853301e628483fad754d05a1e26648',1,'admin','dalishu@hotmail.com','2013-03-27 01:02:18','127.0.0.1','2013-03-27 01:02:18'),('f901c846c52049ecb83bb2c86fbdfe66',1,'admin','dalishu@hotmail.com','2013-03-27 21:16:03','127.0.0.1','2013-03-27 21:16:03'),('fbf45cbd63864b40817e115ac779ccd7',1,'admin','dalishu@hotmail.com','2013-03-27 21:11:50','127.0.0.1','2013-03-27 21:11:50'),('fe1e673967974e09808397d473bbea90',1,'admin','dalishu@hotmail.com','2013-03-27 21:23:16','127.0.0.1','2013-03-27 21:23:16');

/*Table structure for table `eo_config` */

DROP TABLE IF EXISTS `eo_config`;

CREATE TABLE `eo_config` (
  `CGF_KEY` varchar(50) NOT NULL COMMENT '配置KEY',
  `CGF_VALUE` varchar(255) DEFAULT NULL COMMENT '配置VALUE',
  PRIMARY KEY (`CGF_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='配置表';

/*Data for the table `eo_config` */

insert  into `eo_config`(`CGF_KEY`,`CGF_VALUE`) values ('email_encoding',''),('email_host','smtp.163.com'),('email_password','1'),('email_personal',''),('email_port',NULL),('email_username','etower@163.com'),('login_error_interval','30'),('login_error_times','3'),('message_subject','ETOWER会员密码找回信息'),('message_text','感谢您使用ETOWER系统会员密码找回功能，请记住以下找回信息：');

/*Table structure for table `eo_user` */

DROP TABLE IF EXISTS `eo_user`;

CREATE TABLE `eo_user` (
  `USER_ID` int(11) NOT NULL,
  `USER_NAME` varchar(100) DEFAULT NULL,
  `EMAIL` varchar(100) DEFAULT NULL,
  `PASSWORD` char(32) DEFAULT NULL,
  `REGISTER_TIME` datetime DEFAULT NULL,
  `REGISTER_IP` varchar(50) DEFAULT NULL,
  `LAST_LOGIN_TIME` datetime DEFAULT NULL,
  `LAST_LOGIN_IP` varchar(50) DEFAULT NULL,
  `LOGIN_COUNT` int(11) DEFAULT '0',
  `RESET_KEY` char(32) DEFAULT NULL,
  `RESET_PWD` varchar(10) DEFAULT NULL,
  `ERROR_TIME` datetime DEFAULT NULL,
  `ERROR_COUNT` int(11) DEFAULT '0',
  `ERROR_IP` varchar(50) DEFAULT NULL,
  `ACTIVATION` tinyint(1) DEFAULT NULL,
  `ACTIVATION_CODE` char(32) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `AK_AK_USERNAME` (`USER_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

/*Data for the table `eo_user` */

insert  into `eo_user`(`USER_ID`,`USER_NAME`,`EMAIL`,`PASSWORD`,`REGISTER_TIME`,`REGISTER_IP`,`LAST_LOGIN_TIME`,`LAST_LOGIN_IP`,`LOGIN_COUNT`,`RESET_KEY`,`RESET_PWD`,`ERROR_TIME`,`ERROR_COUNT`,`ERROR_IP`,`ACTIVATION`,`ACTIVATION_CODE`) values (1,'admin','dalishu@hotmail.com','5f4dcc3b5aa765d61d8327deb882cf99','2011-01-03 00:00:00','127.0.0.1','2013-03-28 14:57:06','127.0.0.1',161,NULL,NULL,NULL,0,NULL,1,NULL),(2,'test','idalivi@gmail.com','5f4dcc3b5aa765d61d8327deb882cf99',NULL,NULL,'2013-03-28 00:24:46','127.0.0.1',1,NULL,NULL,NULL,0,NULL,1,NULL);

/*Table structure for table `et_config` */

DROP TABLE IF EXISTS `et_config`;

CREATE TABLE `et_config` (
  `CONFIG_ID` int(11) NOT NULL COMMENT '配置ID',
  `CONTEXT_PATH` varchar(20) DEFAULT NULL COMMENT '部署路径',
  `PORT` int(11) DEFAULT NULL COMMENT '端口',
  `SERVLET_POINT` varchar(20) DEFAULT NULL COMMENT 'Servlet挂载点',
  `DB_FILE_URL` varchar(50) DEFAULT NULL COMMENT '数据库附件访问地址',
  `IS_UPLOAD_TO_DB` tinyint(1) DEFAULT '0' COMMENT '上传附件至数据库',
  `DEFAULT_IMG` varchar(255) DEFAULT '/r/etower/www/default/no_picture.gif' COMMENT '图片不存在时默认图片',
  `LOGIN_URL` varchar(255) DEFAULT NULL COMMENT '登陆地址',
  `PROCESS_URL` varchar(255) DEFAULT NULL COMMENT '登录后处理地址',
  `MARK_ON` tinyint(1) DEFAULT NULL COMMENT '开启图片水印',
  `MARK_WIDTH` int(11) DEFAULT '120' COMMENT '图片最小宽度',
  `MARK_HEIGHT` int(11) DEFAULT '120' COMMENT '图片最小高度',
  `MARK_IMAGE` varchar(100) DEFAULT '/r/cms/www/watermark.png' COMMENT '图片水印',
  `MARK_CONTENT` varchar(100) DEFAULT 'ETOWER' COMMENT '文字水印内容',
  `MARK_SIZE` int(11) DEFAULT '20' COMMENT '文字水印大小',
  `MARK_COLOR` varchar(10) DEFAULT '#FF0000' COMMENT '文字水印颜色',
  `MARK_ALPHA` int(11) DEFAULT '50' COMMENT '水印透明度（0-100）',
  `MARK_POSITION` int(11) DEFAULT '1' COMMENT '水印位置(0-5)',
  `MARK_OFFSET_X` int(11) DEFAULT '0' COMMENT 'x坐标偏移量',
  `MARK_OFFSET_Y` int(11) DEFAULT '0' COMMENT 'y坐标偏移量',
  `COUNT_CLEAR_TIME` date DEFAULT NULL COMMENT '计数器清除时间',
  `COUNT_COPY_TIME` datetime DEFAULT NULL COMMENT '计数器拷贝时间',
  `DOWNLOAD_CODE` varchar(32) DEFAULT 'ETOWER' COMMENT '下载防盗链md5混淆码',
  `DOWNLOAD_TIME` int(11) DEFAULT '12' COMMENT '下载有效时间（小时）',
  `EMAIL_HOST` varchar(50) DEFAULT NULL COMMENT '邮件发送服务器',
  `EMAIL_ENCODING` varchar(20) DEFAULT NULL COMMENT '邮件发送编码',
  `EMAIL_USERNAME` varchar(100) DEFAULT NULL COMMENT '邮箱用户名',
  `EMAIL_PASSWORD` varchar(100) DEFAULT NULL COMMENT '邮箱密码',
  `EMAIL_PERSONAL` varchar(100) DEFAULT NULL COMMENT '邮箱发件人',
  PRIMARY KEY (`CONFIG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='配置表';

/*Data for the table `et_config` */

insert  into `et_config`(`CONFIG_ID`,`CONTEXT_PATH`,`PORT`,`SERVLET_POINT`,`DB_FILE_URL`,`IS_UPLOAD_TO_DB`,`DEFAULT_IMG`,`LOGIN_URL`,`PROCESS_URL`,`MARK_ON`,`MARK_WIDTH`,`MARK_HEIGHT`,`MARK_IMAGE`,`MARK_CONTENT`,`MARK_SIZE`,`MARK_COLOR`,`MARK_ALPHA`,`MARK_POSITION`,`MARK_OFFSET_X`,`MARK_OFFSET_Y`,`COUNT_CLEAR_TIME`,`COUNT_COPY_TIME`,`DOWNLOAD_CODE`,`DOWNLOAD_TIME`,`EMAIL_HOST`,`EMAIL_ENCODING`,`EMAIL_USERNAME`,`EMAIL_PASSWORD`,`EMAIL_PERSONAL`) values (1,NULL,8080,NULL,NULL,0,'/r/etower/www/default/no_picture.gif',NULL,NULL,NULL,120,120,'/r/cms/www/watermark.png','ETOWER',20,'#FF0000',50,1,0,0,NULL,NULL,'ETOWER',12,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `et_config_attr` */

DROP TABLE IF EXISTS `et_config_attr`;

CREATE TABLE `et_config_attr` (
  `CONFIG_ID` char(10) DEFAULT NULL COMMENT '配置ID',
  `ATTR_NAME` varchar(30) DEFAULT NULL,
  `ATTR_VALUE` varchar(100) DEFAULT NULL,
  KEY `FK_ET_ATTR_CONFIG` (`CONFIG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='配置属性表';

/*Data for the table `et_config_attr` */

/*Table structure for table `et_group` */

DROP TABLE IF EXISTS `et_group`;

CREATE TABLE `et_group` (
  `GROUP_ID` int(11) NOT NULL COMMENT '用户组ID',
  `GROUP_NAME` varchar(50) NOT NULL COMMENT '名称',
  `PRIORITY` int(11) NOT NULL DEFAULT '10' COMMENT '排列顺序',
  `NEED_CAPTCHA` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否需要验证码 1=需要 0=不需要',
  `NEED_CHECK` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否需要审核 1=需要 0=不需要',
  `ALLOW_PER_DAY` int(11) NOT NULL DEFAULT '4096' COMMENT '每日允许上传KB',
  `ALLOW_MAX_FILE` int(11) NOT NULL DEFAULT '1024' COMMENT '每个文件最大KB',
  `ALLOW_SUFFIX` varchar(255) DEFAULT 'jpg,jpeg,gif,png,bmp' COMMENT '允许上传的后缀',
  `IS_REG_DEF` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否默认会员组 0=是 1=不是',
  PRIMARY KEY (`GROUP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户组表';

/*Data for the table `et_group` */

insert  into `et_group`(`GROUP_ID`,`GROUP_NAME`,`PRIORITY`,`NEED_CAPTCHA`,`NEED_CHECK`,`ALLOW_PER_DAY`,`ALLOW_MAX_FILE`,`ALLOW_SUFFIX`,`IS_REG_DEF`) values (1,'普通会员',10,1,1,4096,1024,'jpg,jpeg,gif,png,bmp',1);

/*Table structure for table `et_log` */

DROP TABLE IF EXISTS `et_log`;

CREATE TABLE `et_log` (
  `LOG_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) DEFAULT NULL COMMENT '用户ID',
  `SITE_ID` int(11) DEFAULT NULL COMMENT '站点ID',
  `CATEGORY` int(11) DEFAULT NULL,
  `LOG_TIME` datetime DEFAULT NULL,
  `IP` varchar(50) DEFAULT NULL,
  `URL` varchar(255) DEFAULT NULL,
  `TITLE` varchar(255) DEFAULT NULL,
  `CONTENT` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`LOG_ID`),
  KEY `FK_ET_LOG_USER` (`USER_ID`),
  KEY `FK_ET_LOG_SITE` (`SITE_ID`),
  CONSTRAINT `FK_ET_LOG_REFERENCE_SITE` FOREIGN KEY (`SITE_ID`) REFERENCES `et_site` (`SITE_ID`),
  CONSTRAINT `FK_ET_LOG_REFERENCE_USER` FOREIGN KEY (`USER_ID`) REFERENCES `et_user` (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8 COMMENT='系统日志表';

/*Data for the table `et_log` */

insert  into `et_log`(`LOG_ID`,`USER_ID`,`SITE_ID`,`CATEGORY`,`LOG_TIME`,`IP`,`URL`,`TITLE`,`CONTENT`) values (1,1,NULL,1,'2013-03-26 23:51:47','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(2,1,NULL,1,'2013-03-26 23:54:56','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(3,1,NULL,1,'2013-03-27 00:11:58','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(4,1,NULL,1,'2013-03-27 00:25:50','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(5,1,NULL,1,'2013-03-27 00:27:22','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(6,1,NULL,1,'2013-03-27 00:30:14','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(7,1,NULL,1,'2013-03-27 00:35:35','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(8,1,NULL,1,'2013-03-27 00:36:55','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(9,1,NULL,1,'2013-03-27 00:39:18','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(10,1,NULL,1,'2013-03-27 00:50:14','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(11,1,NULL,1,'2013-03-27 00:52:06','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(12,1,NULL,1,'2013-03-27 00:57:53','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(13,1,NULL,1,'2013-03-27 01:00:17','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(14,1,NULL,1,'2013-03-27 01:02:18','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(15,1,NULL,1,'2013-03-27 01:05:22','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(16,1,NULL,1,'2013-03-27 10:53:13','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(17,1,NULL,1,'2013-03-27 10:56:11','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(18,1,NULL,1,'2013-03-27 10:57:10','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(19,1,NULL,1,'2013-03-27 11:00:19','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(20,1,NULL,1,'2013-03-27 11:02:34','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(21,1,NULL,1,'2013-03-27 11:16:19','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(22,1,NULL,1,'2013-03-27 11:49:00','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(23,1,NULL,1,'2013-03-27 14:58:33','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(24,1,NULL,1,'2013-03-27 15:01:24','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(25,1,NULL,1,'2013-03-27 15:03:02','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(26,1,NULL,1,'2013-03-27 15:05:47','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(27,1,NULL,1,'2013-03-27 15:08:29','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(28,1,NULL,1,'2013-03-27 15:09:01','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(29,1,NULL,1,'2013-03-27 15:10:06','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(30,1,NULL,1,'2013-03-27 15:26:16','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(31,1,NULL,1,'2013-03-27 15:28:58','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(32,1,NULL,1,'2013-03-27 15:32:38','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(33,1,NULL,1,'2013-03-27 15:37:00','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(34,1,NULL,1,'2013-03-27 15:47:38','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(35,1,NULL,1,'2013-03-27 16:10:04','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(36,1,NULL,1,'2013-03-27 16:23:00','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(37,1,NULL,1,'2013-03-27 16:34:04','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(38,1,NULL,1,'2013-03-27 16:35:48','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(39,NULL,NULL,2,'2013-03-27 16:40:26','127.0.0.1','/etadmin/etower/login.do','登录失败','username=admmin;password=password'),(40,1,NULL,1,'2013-03-27 16:40:35','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(41,1,NULL,1,'2013-03-27 16:43:23','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(42,1,NULL,1,'2013-03-27 16:44:43','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(43,1,NULL,1,'2013-03-27 16:46:49','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(44,1,NULL,1,'2013-03-27 16:53:03','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(45,1,NULL,1,'2013-03-27 16:56:16','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(46,1,NULL,1,'2013-03-27 17:01:54','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(47,1,NULL,1,'2013-03-27 17:05:44','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(48,1,NULL,1,'2013-03-27 18:11:19','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(49,1,NULL,1,'2013-03-27 18:12:43','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(50,1,NULL,1,'2013-03-27 18:15:22','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(51,1,NULL,1,'2013-03-27 18:18:17','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(52,1,NULL,1,'2013-03-27 21:07:07','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(53,1,NULL,1,'2013-03-27 21:11:50','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(54,1,NULL,1,'2013-03-27 21:16:03','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(55,1,NULL,1,'2013-03-27 21:23:16','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(56,1,NULL,1,'2013-03-27 21:28:38','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(57,1,NULL,1,'2013-03-27 21:31:03','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(58,1,NULL,1,'2013-03-27 21:59:07','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(59,1,NULL,1,'2013-03-27 22:01:24','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(60,1,NULL,1,'2013-03-27 22:05:15','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(61,1,NULL,1,'2013-03-27 22:12:48','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(62,NULL,NULL,2,'2013-03-27 22:28:17','127.0.0.1','/etadmin/etower/login.do','登录失败','username=admin;password=dks'),(63,1,NULL,1,'2013-03-27 22:28:40','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(64,1,NULL,1,'2013-03-27 22:30:45','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(65,1,NULL,1,'2013-03-27 22:32:53','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(66,1,NULL,1,'2013-03-27 22:39:01','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(67,1,NULL,1,'2013-03-27 23:04:39','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(68,1,NULL,1,'2013-03-27 23:11:23','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(69,1,NULL,1,'2013-03-27 23:13:28','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(70,1,NULL,1,'2013-03-27 23:25:15','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(71,1,NULL,1,'2013-03-27 23:37:02','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(72,1,NULL,1,'2013-03-27 23:39:08','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(73,1,NULL,1,'2013-03-27 23:41:15','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(74,1,NULL,1,'2013-03-27 23:43:43','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(75,1,NULL,1,'2013-03-27 23:47:21','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(76,1,NULL,1,'2013-03-28 00:03:16','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(77,1,NULL,1,'2013-03-28 00:08:42','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(78,1,NULL,1,'2013-03-28 00:15:24','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(79,1,NULL,1,'2013-03-28 00:16:05','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(80,1,NULL,1,'2013-03-28 00:16:36','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(81,2,NULL,1,'2013-03-28 00:24:46','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL),(82,1,NULL,1,'2013-07-17 16:14:59','192.168.113.1','/etower/etadmin/etower/login.do','登录成功',NULL),(83,1,NULL,1,'2013-03-28 14:57:06','127.0.0.1','/etadmin/etower/login.do','登录成功',NULL);

/*Table structure for table `et_role` */

DROP TABLE IF EXISTS `et_role`;

CREATE TABLE `et_role` (
  `ROLE_ID` int(11) NOT NULL COMMENT '角色ID',
  `SITE_ID` int(11) DEFAULT NULL COMMENT '站点ID',
  `ROLE_NAME` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `PRIORITY` int(11) DEFAULT '10' COMMENT '排列顺序',
  `IS_SUPER` tinyint(1) DEFAULT '0' COMMENT '拥有所有权限',
  PRIMARY KEY (`ROLE_ID`),
  KEY `FK_ROLE_SITE_ID` (`SITE_ID`),
  CONSTRAINT `FK_ET_ROLE_REFERENCE_SITE` FOREIGN KEY (`SITE_ID`) REFERENCES `et_site` (`SITE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

/*Data for the table `et_role` */

insert  into `et_role`(`ROLE_ID`,`SITE_ID`,`ROLE_NAME`,`PRIORITY`,`IS_SUPER`) values (1,1,'管理员',10,1),(2,2,'Demo管理员',10,0);

/*Table structure for table `et_role_permission` */

DROP TABLE IF EXISTS `et_role_permission`;

CREATE TABLE `et_role_permission` (
  `PERM_ID` int(11) NOT NULL,
  `ROLE_ID` int(11) DEFAULT NULL COMMENT '角色ID',
  `ROLE_URL` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`PERM_ID`),
  KEY `FK_ET_PERMISSION_ROLE` (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色授权表';

/*Data for the table `et_role_permission` */

/*Table structure for table `et_site` */

DROP TABLE IF EXISTS `et_site`;

CREATE TABLE `et_site` (
  `SITE_ID` int(11) NOT NULL COMMENT '站点ID',
  `CONFIG_ID` int(11) NOT NULL COMMENT '配置ID',
  `DOMAIN` varchar(50) DEFAULT NULL COMMENT '域名',
  `SITE_PATH` varchar(20) DEFAULT NULL COMMENT '路径',
  `SITE_NAME` varchar(100) DEFAULT NULL COMMENT '网站名称',
  `SHORT_NAME` varchar(100) DEFAULT NULL COMMENT '简短名称',
  `LOCALE_ADMIN` varchar(10) DEFAULT NULL COMMENT '后台本地化',
  `LOCALE_FRONT` varchar(10) DEFAULT NULL COMMENT '前台本地化',
  PRIMARY KEY (`SITE_ID`),
  UNIQUE KEY `AK_DOMAIL` (`DOMAIN`),
  KEY `FK_ET_SITE_CONFIG` (`CONFIG_ID`),
  CONSTRAINT `FK_ET_SITE_REFERENCE_CONFIG` FOREIGN KEY (`CONFIG_ID`) REFERENCES `et_config` (`CONFIG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='站点表';

/*Data for the table `et_site` */

insert  into `et_site`(`SITE_ID`,`CONFIG_ID`,`DOMAIN`,`SITE_PATH`,`SITE_NAME`,`SHORT_NAME`,`LOCALE_ADMIN`,`LOCALE_FRONT`) values (1,1,'127.0.0.1','www','ETower Web Application','ETower','zh_CN','zh_CN'),(2,1,'localhost','demo','Demo Web Application','Demo','zh_CN','zh_CN');

/*Table structure for table `et_user` */

DROP TABLE IF EXISTS `et_user`;

CREATE TABLE `et_user` (
  `USER_ID` int(11) NOT NULL COMMENT '用户ID',
  `GROUP_ID` int(11) NOT NULL COMMENT '用户组ID',
  `USER_NAME` varchar(100) DEFAULT NULL COMMENT '用户名',
  `EMAIL` varchar(100) DEFAULT NULL COMMENT '注册邮箱',
  `REGISTER_TIME` datetime DEFAULT NULL COMMENT '注册时间',
  `REGISTER_IP` varchar(50) DEFAULT '127.0.0.1' COMMENT '注册IP',
  `LAST_LOGIN_TIME` datetime DEFAULT NULL COMMENT '最后登录时间',
  `LAST_LOGIN_IP` varchar(50) DEFAULT '127.0.0.1' COMMENT '最后登录IP',
  `LOGIN_COUNT` int(11) DEFAULT '0' COMMENT '登陆次数',
  `ADMIN_RANK` int(11) DEFAULT '0' COMMENT '管理员级别',
  `IS_ADMIN` tinyint(1) DEFAULT '0' COMMENT '管理员级别',
  `IS_VIEWONLY_ADMIN` tinyint(1) DEFAULT '0' COMMENT '是否只读管理员',
  `IS_SELF_ADMIN` tinyint(1) DEFAULT '0' COMMENT '是否只管理自己的数据',
  `IS_DISABLED` tinyint(1) DEFAULT '0' COMMENT '是否禁用',
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `AK_USERNAME` (`USER_NAME`),
  KEY `FK_GROUP_ID` (`GROUP_ID`),
  CONSTRAINT `FK_ET_USER_REFERENCE_GROUP` FOREIGN KEY (`GROUP_ID`) REFERENCES `et_group` (`GROUP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

/*Data for the table `et_user` */

insert  into `et_user`(`USER_ID`,`GROUP_ID`,`USER_NAME`,`EMAIL`,`REGISTER_TIME`,`REGISTER_IP`,`LAST_LOGIN_TIME`,`LAST_LOGIN_IP`,`LOGIN_COUNT`,`ADMIN_RANK`,`IS_ADMIN`,`IS_VIEWONLY_ADMIN`,`IS_SELF_ADMIN`,`IS_DISABLED`) values (1,1,'管理员','dalishu@hotmail.com',NULL,NULL,'2013-03-28 14:57:06','127.0.0.1',0,0,1,0,0,0),(2,1,'Demo管理员','idalivi@gmail.com',NULL,'127.0.0.1','2013-03-28 00:24:46','127.0.0.1',0,0,1,0,0,0);

/*Table structure for table `et_user_role` */

DROP TABLE IF EXISTS `et_user_role`;

CREATE TABLE `et_user_role` (
  `ROLE_ID` int(11) NOT NULL COMMENT '角色ID',
  `USER_ID` int(11) NOT NULL COMMENT '用户ID',
  PRIMARY KEY (`ROLE_ID`,`USER_ID`),
  KEY `FK_ROLE_USER` (`USER_ID`),
  CONSTRAINT `FK_ET_USER_ROLE_REFERENCE_USER` FOREIGN KEY (`USER_ID`) REFERENCES `et_user` (`USER_ID`),
  CONSTRAINT `FK_ET_USER_ROLE_REFERENCE_ROLE` FOREIGN KEY (`ROLE_ID`) REFERENCES `et_role` (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';

/*Data for the table `et_user_role` */

insert  into `et_user_role`(`ROLE_ID`,`USER_ID`) values (1,1),(2,2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
