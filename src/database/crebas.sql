/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2013-3-28 17:56:42                           */
/*==============================================================*/


drop table if exists EO_AUTHENTICATION;

drop table if exists EO_CONFIG;

drop table if exists EO_USER;

drop table if exists ET_CONFIG;

drop table if exists ET_CONFIG_ATTR;

drop table if exists ET_GROUP;

drop table if exists ET_LOG;

drop table if exists ET_ROLE;

drop table if exists ET_ROLE_PERMISSION;

drop table if exists ET_SITE;

drop table if exists ET_USER;

drop table if exists ET_USER_ROLE;

drop table if exists ET_USER_SITE;

/*2013-6-15 增加资源表*/
drop table if exists ET_RESOURCE;

/*2014-5-8 增加模型表*/
drop table if exists ET_MODEL;
drop table if exists ET_MODEL_ITEM;

/*==============================================================*/
/* Table: ET_RESOURCE                                           */
/*==============================================================*/
create table ET_RESOURCE
(
   ID					BIGINT(20) not null AUTO_INCREMENT,
   RESOU_NAME			VARCHAR(100) not null comment '资源名称',
   PARENT_ID			BIGINT(20) not null comment '父类资源ID',
   MODLE_NAME			VARCHAR(50) not null comment '模块名称',
   ACTION_NAME			VARCHAR(50) not null comment '方法名称',
   PARAM_DATA			VARCHAR(100) comment '附加参数',
   REMARK				VARCHAR(255) comment '备注',
   PRIORITY             INT(11) not null default 255 comment '排列顺序',
   IS_DISPLAY			TINYINT(1) not null default 1 comment '是否显示，1：显示，0：不显示',
   primary key (ID)
);

alter table ET_RESOURCE comment '资源表';

/*==============================================================*/
/* Table: EO_AUTHENTICATION                                     */
/*==============================================================*/
create table EO_AUTHENTICATION
(
   ID					BIGINT(20) not null AUTO_INCREMENT,
   AUTH_ID				CHAR(32) not null comment '认证ID',
   USER_NAME            VARCHAR(100) not null comment '用户名',
   EMAIL                VARCHAR(100) comment '邮箱',
   LOGIN_TIME           DATETIME not null comment '登陆时间',
   LOGIN_IP             VARCHAR(50) not null comment '登陆IP',
   UPDATE_TIME          DATETIME not null comment '更新时间',
   USER_ID              BIGINT(20) not null comment '用户ID',
   primary key (ID),
   key FK_ET_AUTH_USER (USER_ID)
);

alter table EO_AUTHENTICATION comment '认证信息表';

/*==============================================================*/
/* Table: EO_CONFIG                                             */
/*==============================================================*/
create table EO_CONFIG
(
   ID					BIGINT(20) not null AUTO_INCREMENT,
   CGF_KEY              VARCHAR(50) not null comment '配置KEY',
   CGF_VALUE            VARCHAR(255) comment '配置VALUE',
   primary key (ID)
);

alter table EO_CONFIG comment '配置表';

/*==============================================================*/
/* Table: EO_USER                                               */
/*==============================================================*/
create table EO_USER
(
   ID        			BIGINT(20) not null AUTO_INCREMENT,
   USER_NAME            VARCHAR(100) not null comment '用户名',
   EMAIL                VARCHAR(100) comment '电子邮件',
   PASSWORD             CHAR(32) not null comment '密码',
   REGISTER_TIME        DATETIME not null comment '注册时间',
   REGISTER_IP          VARCHAR(50) not null default '127.0.0.1' comment '注册IP',
   LAST_LOGIN_TIME      DATETIME not null comment '最后登录时间',
   LAST_LOGIN_IP        VARCHAR(50) not null default '127.0.0.1' comment '最后登录IP',
   LOGIN_COUNT          INT(11) not null default 0 comment '登陆次数',
   RESET_KEY            CHAR(32) comment '重置密码KEY',
   RESET_PWD            VARCHAR(10) comment '重置密码VALUE',
   ERROR_TIME           DATETIME comment '登陆错误时间',
   ERROR_COUNT          INT(11) not null default 0 comment '登陆错误次数',
   ERROR_IP             VARCHAR(50) comment '登陆错误IP',
   ACTIVATION           TINYINT(1) not null default 1 comment '激活状态',
   ACTIVATION_CODE      CHAR(32) comment '激活码',
   primary key (ID),
   unique key AK_USERNAME (USER_NAME)
);

alter table EO_USER comment '用户表';

/*==============================================================*/
/* Table: EO_FTP                                                */
/*==============================================================*/
create table EO_FTP
(
   ID					BIGINT(20) not null AUTO_INCREMENT,
   FTP_NAME				VARCHAR(100) not null comment '名称',
   IP					VARCHAR(50) not null comment 'IP',
   PORT					INT(11) not null default 21 comment '端口号',
   USER_NAME			VARCHAR(100) not null comment '登录名',
   PASSWORD				VARCHAR(100) not null comment '登陆密码',
   ENCODING				VARCHAR(20) not null default 'UTF-8' comment '编码',
   TIME_OUT				INT(11) comment '超时时间',
   FTP_PATH				VARCHAR(255) comment '路径',
   URL					VARCHAR(255) not null comment '访问URL',
   primary key (ID)
);

alter table EO_FTP comment 'FTP表';

/*==============================================================*/
/* Table: ET_CONFIG                                             */
/*==============================================================*/
create table ET_CONFIG
(
   ID        			BIGINT(20) not null AUTO_INCREMENT,
   CONTEXT_PATH         VARCHAR(20) default '/etower' comment '部署路径',
   SERVLET_POINT        VARCHAR(20) comment 'Servlet挂载点',
   PORT                 INT(11) comment '端口',
   DB_FILE_URI          VARCHAR(50) not null default '/dbfile.svl?n=' comment '数据库附件访问地址',
   IS_UPLOAD_TO_DB    	TINYINT(1) not null default 0 comment '上传附件至数据库',
   DEFAULT_IMG          VARCHAR(255) not null default '/r/etower/www/default/no_picture.gif' comment '图片不存在时默认图片',
   LOGIN_URL            VARCHAR(255) not null default '/login.etsp' comment '登陆地址',
   PROCESS_URL          VARCHAR(255) comment '登录后处理地址',
   MARK_ON              TINYINT(1) not null default 1 comment '开启图片水印',
   MARK_WIDTH           INT(11) not null default 120 comment '图片最小宽度',
   MARK_HEIGHT          INT(11) not null default 120 comment '图片最小高度',
   MARK_IMAGE           VARCHAR(100) default '/r/cms/www/watermark.png' comment '图片水印',
   MARK_CONTENT         VARCHAR(100) not null default 'ETOWER' comment '文字水印内容',
   MARK_SIZE            INT(11) not null default 20 comment '文字水印大小',
   MARK_COLOR           VARCHAR(10) not null default '#FF0000' comment '文字水印颜色',
   MARK_ALPHA           INT(11) not null default 50 comment '水印透明度（0-100）',
   MARK_POSITION        INT(11) not null default 1 comment '水印位置(0-5)',
   MARK_OFFSETX        INT(11) not null default 0 comment 'x坐标偏移量',
   MARK_OFFSETY        INT(11) not null default 0 comment 'y坐标偏移量',
   COUNT_CLEAR_TIME     DATE not null comment '计数器清除时间',
   COUNT_COPY_TIME      DATETIME not null comment '计数器拷贝时间',
   DOWNLOAD_CODE        VARCHAR(32) not null default 'ETOWER' comment '下载防盗链md5混淆码',
   DOWNLOAD_TIME        INT(11) not null default 12 comment '下载有效时间（小时）',
   EMAIL_HOST           VARCHAR(50) comment '邮件发送服务器',
   EMAIL_ENCODING       VARCHAR(20) comment '邮件发送编码',
   EMAIL_USERNAME       VARCHAR(100) comment '邮箱用户名',
   EMAIL_PASSWORD       VARCHAR(100) comment '邮箱密码',
   EMAIL_PERSONAL       VARCHAR(100) comment '邮箱发件人',
   primary key (ID)
);

alter table ET_CONFIG comment '配置表';

/*==============================================================*/
/* Table: ET_CONFIG_ATTR                                        */
/*==============================================================*/
create table ET_CONFIG_ATTR
(
   ID        			BIGINT(20) not null AUTO_INCREMENT,
   ATTR_NAME            VARCHAR(30) comment '名称',
   ATTR_VALUE           VARCHAR(100) comment '值',
   CONFIG_ID            BIGINT(20) not null comment '配置ID',
   PRIMARY KEY (ID),
   key FK_ET_ATTR_CONFIG (CONFIG_ID)
);

alter table ET_CONFIG_ATTR comment '配置属性表';

/*==============================================================*/
/* Table: ET_GROUP                                              */
/*==============================================================*/
create table ET_GROUP
(
   ID        			BIGINT(20) not null AUTO_INCREMENT,
   GROUP_NAME           VARCHAR(50) not null comment '名称',
   PRIORITY             INT(11) not null default 10 comment '排列顺序',
   NEED_CAPTCHA         TINYINT(1) not null default 1 comment '是否需要验证码 1=需要 0=不需要',
   NEED_CHECK           TINYINT(1) not null default 1 comment '是否需要审核 1=需要 0=不需要',
   ALLOW_PER_DAY        INT(11) not null default 4096 comment '每日允许上传KB',
   ALLOW_MAX_FILE       INT(11) not null default 1024 comment '每个文件最大KB',
   ALLOW_SUFFIX         VARCHAR(255) default 'jpg,jpeg,gif,png,bmp' comment '允许上传的后缀',
   IS_REG_DEF 		    TINYINT(1) not null default 0 comment '是否默认会员组 0=不是 1=是',
   primary key (ID)
);

alter table ET_GROUP comment '用户组表';

/*==============================================================*/
/* Table: ET_LOG                                                */
/*==============================================================*/
create table ET_LOG
(
   ID        			BIGINT(20) not null AUTO_INCREMENT,
   CATEGORY             INT(11) not null comment '日志类型',
   LOG_TIME             DATETIME not null comment '日志时间',
   IP                   VARCHAR(50) comment 'IP地址',
   URL                  VARCHAR(255) comment 'URL地址',
   TITLE                VARCHAR(255) comment '日志标题',
   CONTENT              VARCHAR(255) comment '日志内容',
   USER_ID              BIGINT(20) comment '用户ID',
   SITE_ID              BIGINT(20) comment '站点ID',
   primary key (ID),
   key FK_ET_LOG_USER (USER_ID),
   key FK_ET_LOG_SITE (SITE_ID)
);

alter table ET_LOG comment '系统日志表';

/*==============================================================*/
/* Table: ET_ROLE                                               */
/*==============================================================*/
create table ET_ROLE
(
   ID        			BIGINT(20) not null AUTO_INCREMENT,
   ROLE_NAME            VARCHAR(50) comment '角色名称',
   REMARK				VARCHAR(255) comment '角色描述',
   PRIORITY             INT(11) default 10 comment '排列顺序',
   IS_SUPER           	TINYINT(1) default 0 comment '拥有所有权限',
   SITE_ID              BIGINT(20) comment '站点ID',
   primary key (ID),
   key FK_ET_ROLE_SITE (SITE_ID)
);

alter table ET_ROLE comment '角色表';

/*==============================================================*/
/* Table: ET_ROLE_PERMISSION                                    */
/*==============================================================*/
create table ET_ROLE_PERMISSION
(
   ID        			BIGINT(20) not null AUTO_INCREMENT,
   ROLE_ID              BIGINT(20) comment '角色ID',
   ROLE_URL             VARCHAR(100),
   primary key (ID),
   key FK_ET_PERMISSION_ROLE (ROLE_ID)
);

alter table ET_ROLE_PERMISSION comment '角色授权表';

/*==============================================================*/
/* Table: ET_SITE                                               */
/*==============================================================*/
create table ET_SITE
(
   ID        			BIGINT(20) not null AUTO_INCREMENT,
   DOMAIN               VARCHAR(50) not null comment '域名',
   SITE_PATH            VARCHAR(20) not null comment '路径',
   SITE_NAME            VARCHAR(100) not null comment '网站名称',
   SHORT_NAME           VARCHAR(100) not null comment '简短名称',
   PROTOCOL				VARCHAR(20) not null default 'http://' comment '协议',
   DYNAMIC_SUFFIX		VARCHAR(10) not null default '.ehtml' comment '动态页后缀',
   STATIC_SUFFIX		VARCHAR(10) not null default '.html' comment '静态页后缀',
   STATIC_DIR			VARCHAR(50) comment '静态页存放目录',
   IS_INDEX_TO_ROOT		CHAR(1) not null default '0' comment '是否使用将首页放在根目录下',
   IS_STATIC_INDEX		CHAR(1) not null default '0' comment '是否静态化首页',
   LOCALE_ADMIN         VARCHAR(10) not null default 'zh_CN' comment '后台本地化',
   LOCALE_FRONT         VARCHAR(10) not null default 'zh_CN' comment '前台本地化',
   TPL_SOLUTION			VARCHAR(50) not null default 'default' comment '模板方案',
   FINAL_STEP			TINYINT(4) not null default 2 comment '终审级别',
   AFTER_CHECK			TINYINT(4) not null default 2 comment '审核后(1:不能修改删除;2:修改后退回;3:修改后不变)',
   IS_RELATIVE_PATH		CHAR(1) not null default '1' comment '是否使用相对路径',
   IS_RECYCLE_ON		CHAR(1) not null default '1' comment '是否开启回收站',
   DOMAIN_ALIAS			VARCHAR(255) comment '域名别名',
   DOMAIN_REDIRECT		VARCHAR(255) comment '域名重定向',
   CONFIG_ID            BIGINT(20) not null comment '配置ID',
   FTP_ID				BIGINT(20) comment 'FTP上传ID',
   primary key (ID),
   unique key AK_DOMAIL (DOMAIN),
   key FK_ET_SITE_CONFIG (CONFIG_ID),
   key FK_ET_SITE_EO_FTP (FTP_ID)
);

alter table ET_SITE comment '站点表';

/*==============================================================*/
/* Table: ET_USER                                               */
/*==============================================================*/
create table ET_USER
(
   ID        			BIGINT(20) not null AUTO_INCREMENT,
   USER_NAME            VARCHAR(100) not null comment '用户名',
   EMAIL                VARCHAR(100) comment '注册邮箱',
   REGISTER_TIME        DATETIME not null comment '注册时间',
   REGISTER_IP          VARCHAR(50) not null default '127.0.0.1' comment '注册IP',
   LAST_LOGIN_TIME      DATETIME not null comment '最后登录时间',
   LAST_LOGIN_IP        VARCHAR(50) not null default '127.0.0.1' comment '最后登录IP',
   LOGIN_COUNT          INT(11) not null default 0 comment '登陆次数',
   ADMIN_RANK           INT(11) not null default 0 comment '管理员级别',
   UPLOAD_TOTAL			BIGINT(20) not null default 0 comment '上传总大小',
   UPLOAD_SIZE			INT(11) not null default 0 comment '上传大小',
   UPLOAD_DATE			DATE comment '上传日期',
   IS_ADMIN             TINYINT(1) not null default 0 comment '是否管理员',
   IS_VIEWONLY_ADMIN    TINYINT(1) not null default 0 comment '是否只读管理员',
   IS_SELF_ADMIN        TINYINT(1) not null default 0 comment '是否只管理自己的数据',
   IS_DISABLED          TINYINT(1) not null default 0 comment '是否禁用',
   GROUP_ID             BIGINT(20) not null comment '用户组ID',
   primary key (ID),
   unique key AK_USERNAME (USER_NAME),
   key FK_GROUP_ID (GROUP_ID)
);

alter table ET_USER comment '用户表';

/*==============================================================*/
/* Table: ET_USER_ROLE                                          */
/*==============================================================*/
create table ET_USER_ROLE
(
   ID        			BIGINT(20) not null AUTO_INCREMENT,
   ROLE_ID              INT(11) not null comment '角色ID',
   USER_ID              INT(11) not null comment '用户ID',
   primary key (ID),
   key FK_ROLE_USER (ROLE_ID, USER_ID)
);

alter table ET_USER_ROLE comment '用户角色表';

/*==============================================================*/
/* Table: ET_USER_SITE                                          */
/*==============================================================*/
create table ET_USER_SITE
(
   ID        			BIGINT(20) not null AUTO_INCREMENT,
   CHECK_STEP			TINYINT(4) not null default 1 comment '审核级别',
   ALL_CHANNEL			TINYINT(1) not null default 1 comment '是否拥有所有栏目的权限',
   USER_ID              INT(11) not null comment '用户ID',
   SITE_ID              INT(11) not null comment '站点ID',
   primary key (ID),
   key FK_ROLE_USER (USER_ID, SITE_ID)
);

alter table ET_USER_SITE comment '用户站点表';

/*==============================================================*/
/* Table: ET_MODEL                                              */
/*==============================================================*/
create table ET_MODEL
(
   ID        			BIGINT(20) not null AUTO_INCREMENT,
   MODEL_NAME			VARCHAR(100) not null comment '名称',
   MODEL_PATH			VARCHAR(100) not null comment '路径',
   TPL_CHANNEL_PREFIX	VARCHAR(20) default null comment '栏目模板前缀',
   TPL_CONTENT_PREFIX	VARCHAR(20) default null comment '内容模板前缀',
   TITLE_IMG_WIDTH		INT(11) not null default 139 comment '栏目标题图宽度',
   TITLE_IMG_HEIGHT		INT(11) not null default 139 comment '栏目标题图高度',
   CONTENT_IMG_WIDTH	INT(11) not null default 310 comment '栏目内容图宽度',
   CONTENT_IMG_HEIGHT	INT(11) not null default 310 comment '栏目内容图高度',
   PRIORITY             INT(11) not null default 10 comment '排列顺序',
   HAS_CONTENT			TINYINT(1) not null default 1 comment '是否有内容',
   IS_DISABLED			TINYINT(1) not null default 0 comment '是否禁用',
   IS_DEF				TINYINT(1) not null default 0 comment '是否默认模型',
   primary key (ID)
);

alter table ET_MODEL comment '模型表';

/*==============================================================*/
/* Table: ET_MODEL_ITEM                                         */
/*==============================================================*/
create table ET_MODEL_ITEM
(
	ID        			BIGINT(20) not null AUTO_INCREMENT,
	MODEL_ID			BIGINT(20) not null,
	FIELD				VARCHAR(50) not null comment '字段',
	ITEM_LABEL			VARCHAR(100) not null comment '名称',
	PRIORITY			INT(11) not null default '70' COMMENT '排列顺序',
	DEF_VALUE			VARCHAR(255) default null comment '默认值',
	OPT_VALUE			VARCHAR(255) default null comment '可选项',
	TEXT_SIZE			VARCHAR(20) default null comment '长度',
	AREA_ROWS			VARCHAR(3) default null comment '文本行数',
	AREA_COLS			VARCHAR(3) default null comment '文本列数',
	HELP				VARCHAR(255) default null comment '帮助信息',
	HELP_POSITION		VARCHAR(1) default null comment '帮助位置',
	DATA_TYPE			INT(11) not null default '1' COMMENT '数据类型',
	IS_SINGLE			TINYINT(1) not null default 1 comment '是否独占一行',
	IS_CHANNEL			TINYINT(1) not null default 1 comment '是否栏目模型项',
	IS_CUSTOM			TINYINT(1) not null default 1 comment '是否自定义',
	IS_DISPLAY			TINYINT(1) not null default 1 comment '是否显示',
	primary key (ID),
	key FK_ET_MODEL_ITEM_MODEL (MODEL_ID)
);
alter table ET_MODEL_ITEM comment '模型项表';