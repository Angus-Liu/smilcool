# 用户表（user）
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id`          int(11)       NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username`    varchar(50)   NOT NULL COMMENT '用户名',
  `password`    varchar(80)   NOT NULL COMMENT '密码（加密）',
  `nickname`    varchar(50)   NOT NULL DEFAULT '换个好听的昵称' COMMENT '昵称',
  `avatar`      varchar(255)  NOT NULL DEFAULT '/local-storage/avatar/default-avatar.jpg' COMMENT '头像URL',
  `sex`         varchar(5)    NOT NULL DEFAULT '保密' COMMENT '性别：男、女、保密',
  `birthday`    datetime      NOT NULL DEFAULT '1970-01-01' COMMENT '生日',
  `sign`        varchar(255)  NOT NULL DEFAULT '一句话介绍自己' COMMENT '签名',
  `intro`       varchar(1000) NOT NULL DEFAULT '这个人很神秘，什么也没写' COMMENT '简介',
  `grade`       varchar(10)   NOT NULL DEFAULT '未填写' COMMENT '年级',
  `college`     varchar(50)   NOT NULL DEFAULT '未填写' COMMENT '学院',
  `major`       varchar(50)   NOT NULL DEFAULT '未填写' COMMENT '专业',
  `phone`       varchar(20)   NOT NULL DEFAULT '未填写' COMMENT '手机',
  `email`       varchar(50)   NOT NULL COMMENT '邮箱',
  `state`       int(1)        NOT NULL DEFAULT '0' COMMENT '状态：0-未激活，1-正常，2-停用',
  `remark`      varchar(255)           DEFAULT NULL COMMENT '备注',
  `create_time` datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted`     tinyint(1)    NOT NULL DEFAULT '0' COMMENT '软删除：0-未删除，1-已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`),
  UNIQUE KEY `uk_email` (`email`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COMMENT ='用户表';

# 用户信息表（user_info）
# TODO

# 角色表（roles）
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id`          int(11)      NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name`        varchar(20)  NOT NULL COMMENT '角色名',
  `description` varchar(255) NOT NULL COMMENT '角色描述',
  `initial`     tinyint(1)   NOT NULL DEFAULT '0' COMMENT '是否为初始化角色：0-不是，1-是',
  `state`       int(1)       NOT NULL DEFAULT '1' COMMENT '状态：0-停用，1-正常',
  `fixed`       tinyint(1)   NOT NULL DEFAULT '0' COMMENT '固定不可修改：0-否，1-是',
  `remark`      varchar(255)          DEFAULT NULL COMMENT '备注',
  `create_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted`     tinyint(1)   NOT NULL DEFAULT '0' COMMENT '软删除：0-未删除，1-已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`name`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COMMENT ='角色表';

INSERT INTO `role`(`id`, `name`, `description`, `state`, `fixed`, `remark`)
VALUES (1, 'role_admin', '管理员', 1, 1, '管理员能够登录后台');

INSERT INTO `role`(`id`, `name`, `description`, `initial`, `state`, `fixed`, `remark`)
VALUES (2, 'role_normal', '普通用户', 1, 1, 1, '普通用户能够访问前台');

# 权限表（permissions）
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id`          int(11)      NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `parent_id`   int(11)               DEFAULT NULL COMMENT '父级ID',
  `name`        varchar(255) NOT NULL COMMENT '权限名',
  `description` varchar(255) NOT NULL COMMENT '权限描述',
  `url`         varchar(255) NOT NULL COMMENT '请求地址',
  `type`        int(1)       NOT NULL DEFAULT '0' COMMENT '类型：0-菜单，1-按钮，2-其他',
  `seq`         int(11)      NOT NULL DEFAULT '0' COMMENT '排序值',
  `state`       int(1)       NOT NULL DEFAULT '1' COMMENT '状态：0-停用，1-正常',
  `remark`      varchar(255)          DEFAULT NULL COMMENT '备注',
  `create_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted`     tinyint(1)   NOT NULL DEFAULT '0' COMMENT '软删除：0-未删除，1-已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`name`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COMMENT ='权限表';

# 用户角色表（user_role）
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id`          int(11)    NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id`     int(11)    NOT NULL COMMENT '用户ID',
  `role_id`     int(11)    NOT NULL COMMENT '角色ID',
  `state`       int(1)     NOT NULL DEFAULT '1' COMMENT '状态：0-停用，1-正常',
  `create_time` datetime   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted`     tinyint(1) NOT NULL DEFAULT '0' COMMENT '软删除：0-未删除，1-已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_user_role` (`user_id`, `role_id`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COMMENT ='用户角色表';

# 角色权限表（role_permission）
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id`            int(11)    NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_id`       int(11)    NOT NULL COMMENT '角色ID',
  `permission_id` int(11)    NOT NULL COMMENT '权限ID',
  `state`         int(1)     NOT NULL DEFAULT '1' COMMENT '状态：0-停用，1-正常',
  `create_time`   datetime   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time`   datetime   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted`       tinyint(1) NOT NULL DEFAULT '0' COMMENT '软删除：0-未删除，1-已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_role_permission` (`role_id`, `permission_id`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COMMENT ='角色权限表';

# 权限控制规则映射表
DROP TABLE IF EXISTS `rule_map`;
CREATE TABLE `rule_map` (
  `id`          int(11)      NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `url`         varchar(255) NOT NULL COMMENT '请求地址',
  `description` varchar(255) NOT NULL COMMENT '描述',
  `type`        int(1)       NOT NULL DEFAULT '0' COMMENT '类型：0-菜单，1-按钮，2-其他',
  `authc`       tinyint(1)   NOT NULL DEFAULT '0' COMMENT '是否需要认证',
  `use_roles`   tinyint(1)   NOT NULL DEFAULT '0' COMMENT '使用角色规则',
  `roles`       varchar(255)          DEFAULT NULL COMMENT '角色策略',
  `use_perms`   tinyint(1)   NOT NULL DEFAULT '0' COMMENT '使用权限规则',
  `perms`       varchar(255)          DEFAULT NULL COMMENT '权限策略',
  `use_rest`    tinyint(1)   NOT NULL DEFAULT '0' COMMENT '使用HTTP方法规则',
  `rest`        varchar(255)          DEFAULT NULL COMMENT 'HTTP方法策略',
  `seq`         int(11)      NOT NULL DEFAULT '0' COMMENT '排序值',
  `state`       int(1)       NOT NULL DEFAULT '1' COMMENT '状态：0-停用，1-正常',
  `remark`      varchar(255)          DEFAULT NULL COMMENT '备注',
  `create_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted`     tinyint(1)   NOT NULL DEFAULT '0' COMMENT '软删除：0-未删除，1-已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_url` (`url`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COMMENT ='权限控制策略表';

# 资源表（resource）
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `id`                int(11)     NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id`           int(11)     NOT NULL COMMENT '发布用户ID',
  `resource_dic_type` varchar(20) NOT NULL COMMENT '资源所属字典类型',
  `resource_dic_item` varchar(20) NOT NULL COMMENT '资源所属字典项目',
  `zan_count`         int(11)     NOT NULL DEFAULT '0' COMMENT '点赞数',
  `pv_count`          int(11)     NOT NULL DEFAULT '0' COMMENT '浏览量',
  `comment_count`     int(11)     NOT NULL DEFAULT '0' COMMENT '评论数',
  `state`             int(1)      NOT NULL DEFAULT '1' COMMENT '状态：0-停用，1-正常',
  `remark`            varchar(255)         DEFAULT NULL COMMENT '备注',
  `create_time`       datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time`       datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted`           tinyint(1)  NOT NULL DEFAULT '0' COMMENT '软删除：0-未删除，1-已删除',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_resource_dic_type` (`resource_dic_type`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COMMENT ='资源表';

# 动态表（moment）
DROP TABLE IF EXISTS `moment`;
CREATE TABLE `moment` (
  `id`              int(11)       NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id`         int(11)       NOT NULL COMMENT '发布用户ID',
  `resource_id`     int(11)       NOT NULL COMMENT '资源ID',
  `moment_category` varchar(20)   NOT NULL COMMENT '动态类别，字典类型：moment-category',
  `content`         varchar(1000) NOT NULL DEFAULT '' COMMENT '内容',
  `images`          json                   DEFAULT NULL COMMENT '图片',
  `state`           int(1)        NOT NULL DEFAULT '1' COMMENT '状态：0-停用，1-正常',
  `remark`          varchar(255)           DEFAULT NULL COMMENT '备注',
  `create_time`     datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time`     datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted`         tinyint(1)    NOT NULL DEFAULT '0' COMMENT '软删除：0-未删除，1-已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_resource_id` (`resource_id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_moment_category` (`moment_category`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COMMENT ='动态表';

# 文章表（article）
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id`               int(11)      NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id`          int(11)      NOT NULL COMMENT '发布用户ID',
  `resource_id`      int(11)      NOT NULL COMMENT '资源ID',
  `article_category` varchar(20)  NOT NULL COMMENT '文章类别，字典类型：article-category',
  `title`            varchar(255) NOT NULL DEFAULT '' COMMENT '标题',
  `brief`            varchar(255) NOT NULL DEFAULT '' COMMENT '简介',
  `tags`             json                  DEFAULT NULL COMMENT '标签',
  `cover`            varchar(255)          DEFAULT NULL COMMENT '封面',
  `markdown_content` text                  DEFAULT NULL COMMENT '内容（markdown格式）',
  `html_content`     text                  DEFAULT NULL COMMENT '内容（html格式）',
  `state`            int(1)       NOT NULL DEFAULT '1' COMMENT '状态：0-停用，1-正常',
  `remark`           varchar(255)          DEFAULT NULL COMMENT '备注',
  `create_time`      datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time`      datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted`          tinyint(1)   NOT NULL DEFAULT '0' COMMENT '软删除：0-未删除，1-已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_resource_id` (`resource_id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_article_category` (`article_category`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COMMENT ='文章表';

# 文件表（file）
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file` (
  `id`             int(11)       NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id`        int(11)       NOT NULL COMMENT '发布用户ID',
  `resource_id`    int(11)       NOT NULL COMMENT '资源ID',
  `file_category`  varchar(20)   NOT NULL COMMENT '文件类别，字典类型：file-category',
  `title`          varchar(255)  NOT NULL DEFAULT '' COMMENT '标题',
  `description`    varchar(1000) NOT NULL DEFAULT '' COMMENT '描述',
  `name`           varchar(255)  NOT NULL DEFAULT '未知' COMMENT '文件名',
  `size`           varchar(10)   NOT NULL DEFAULT '未知大小' COMMENT '文件大小',
  `url`            varchar(255)  NOT NULL COMMENT '文件链接',
  `download_count` int(11)       NOT NULL DEFAULT '0' COMMENT '下载量',
  `state`          int(1)        NOT NULL DEFAULT '1' COMMENT '状态：0-停用，1-正常',
  `remark`         varchar(255)           DEFAULT NULL COMMENT '备注',
  `create_time`    datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time`    datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted`        tinyint(1)    NOT NULL DEFAULT '0' COMMENT '软删除：0-未删除，1-已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_resource_id` (`resource_id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_file_category` (`file_category`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COMMENT ='文件表';

# 失物寻物表（lost_found）
DROP TABLE IF EXISTS `lost_found`;
CREATE TABLE `lost_found` (
  `id`                  int(11)       NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id`             int(11)       NOT NULL COMMENT '发布用户ID',
  `resource_id`         int(11)       NOT NULL COMMENT '资源ID',
  `lost_found_category` varchar(20)   NOT NULL COMMENT '失物寻物类别，字典类型：lost-found-category',
  `title`               varchar(255)  NOT NULL DEFAULT '' COMMENT '标题',
  `description`         varchar(1000) NOT NULL DEFAULT '' COMMENT '描述',
  `item_name`           varchar(255)  NOT NULL DEFAULT '' COMMENT '物品名称',
  `time`                datetime      NOT NULL COMMENT '拾取/丢失时间',
  `address`             varchar(255)  NOT NULL COMMENT '拾取/丢失地点',
  `images`              json                   DEFAULT NULL COMMENT '图片',
  `state`               int(1)        NOT NULL DEFAULT '1' COMMENT '状态：0-停用，1-正常',
  `remark`              varchar(255)           DEFAULT NULL COMMENT '备注',
  `create_time`         datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time`         datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted`             tinyint(1)    NOT NULL DEFAULT '0' COMMENT '软删除：0-未删除，1-已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_resource_id` (`resource_id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_lost_found_category` (`lost_found_category`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COMMENT ='失物寻物表';

# 二手交易表（second_hand）
DROP TABLE IF EXISTS `second_hand`;
CREATE TABLE `second_hand` (
  `id`                   int(11)       NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id`              int(11)       NOT NULL COMMENT '发布用户ID',
  `resource_id`          int(11)       NOT NULL COMMENT '资源ID',
  `second_hand_category` varchar(20)   NOT NULL COMMENT '二手交易类别，字典类型：second-hand-category',
  `title`                varchar(255)  NOT NULL DEFAULT '' COMMENT '标题',
  `name`                 varchar(255)  NOT NULL DEFAULT '' COMMENT '物品名称',
  `price`                double(8, 2)  NOT NULL DEFAULT '0.00' COMMENT '物品价格',
  `address`              varchar(255)  NOT NULL COMMENT '交易地址',
  `images`               json                   DEFAULT NULL COMMENT '图片',
  `description`          varchar(1000) NOT NULL DEFAULT '' COMMENT '描述',
  `state`                int(1)        NOT NULL DEFAULT '1' COMMENT '状态：0-停用，1-正常',
  `remark`               varchar(255)           DEFAULT NULL COMMENT '备注',
  `create_time`          datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time`          datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted`              tinyint(1)    NOT NULL DEFAULT '0' COMMENT '软删除：0-未删除，1-已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_resource_id` (`resource_id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_second_hand_category` (`second_hand_category`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COMMENT ='二手交易表';

# 评论表（comment）
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id`            int(11)       NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `parent_id`     int(11)                DEFAULT NULL COMMENT '父级ID',
  `user_id`       int(11)       NOT NULL COMMENT '评论用户ID',
  `resource_id`   int(11)       NOT NULL COMMENT '评论资源ID',
  `reply_user_id` int(11)                DEFAULT NULL COMMENT '回复用户ID',
  `content`       varchar(1000) NOT NULL DEFAULT '' COMMENT '内容',
  `state`         int(1)        NOT NULL DEFAULT '1' COMMENT '状态：0-停用，1-正常',
  `remark`        varchar(255)           DEFAULT NULL COMMENT '备注',
  `create_time`   datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time`   datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted`       tinyint(1)    NOT NULL DEFAULT '0' COMMENT '软删除：0-未删除，1-已删除',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_resource_id` (`resource_id`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COMMENT ='评论表';

# 点赞表（zan）
DROP TABLE IF EXISTS `zan`;
CREATE TABLE `zan` (
  `id`          int(11)    NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id`     int(11)    NOT NULL COMMENT '点赞用户ID',
  `resource_id` int(11)    NOT NULL COMMENT '点赞资源ID',
  `state`       int(1)     NOT NULL DEFAULT '1' COMMENT '状态：0-取消，1-赞同',
  `remark`      varchar(255)        DEFAULT NULL COMMENT '备注',
  `create_time` datetime   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted`     tinyint(1) NOT NULL DEFAULT '0' COMMENT '软删除：0-未删除，1-已删除',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_resource_id` (`resource_id`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COMMENT ='点赞表';

# 好友表（friend）
DROP TABLE IF EXISTS `friend`;
CREATE TABLE `friend` (
  `id`             int(11)    NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id`        int(11)    NOT NULL COMMENT '用户ID',
  `friend_user_id` int(11)    NOT NULL COMMENT '好友ID',
  `state`          int(1)     NOT NULL DEFAULT '1' COMMENT '状态：0-停用，1-正常',
  `remark`         varchar(255)        DEFAULT NULL COMMENT '备注',
  `create_time`    datetime   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time`    datetime   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted`        tinyint(1) NOT NULL DEFAULT '0' COMMENT '软删除：0-未删除，1-已删除',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COMMENT ='好友表';

# 消息表（message）
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id`              int(11)    NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `send_user_id`    int(11)    NOT NULL COMMENT '发送用户ID',
  `receive_user_id` int(11)    NOT NULL COMMENT '接收用户ID',
  `type`            int(1)     NOT NULL DEFAULT '0' COMMENT '消息类型：0-连接，1-聊天，2-签收，3-心跳，4-拉取好友',
  `content`         text       NOT NULL COMMENT '内容',
  `state`           int(1)     NOT NULL DEFAULT '0' COMMENT '状态：0-未签收，1-已签收',
  `create_time`     datetime   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time`     datetime   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted`         tinyint(1) NOT NULL DEFAULT '0' COMMENT '软删除：0-未删除，1-已删除',
  PRIMARY KEY (`id`),
  KEY `idx_send_user_id` (`send_user_id`),
  KEY `idx_receive_user_id` (`receive_user_id`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COMMENT ='消息表';

# 字典类型表（dic_type）
DROP TABLE IF EXISTS `dic_type`;
CREATE TABLE `dic_type` (
  `id`          int(11)     NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name`        varchar(20) NOT NULL COMMENT '字典类型名',
  `code`        varchar(20) NOT NULL COMMENT '字典类型码',
  `seq`         int(11)     NOT NULL DEFAULT '0' COMMENT '排序值',
  `state`       int(1)      NOT NULL DEFAULT '1' COMMENT '状态：0-停用，1-正常',
  `remark`      varchar(255)         DEFAULT NULL COMMENT '备注',
  `create_time` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted`     tinyint(1)  NOT NULL DEFAULT '0' COMMENT '软删除：0-未删除，1-已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`name`),
  UNIQUE KEY `uk_code` (`code`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COMMENT ='字典类型表';

# 字典项目表（dictionary_item）
DROP TABLE IF EXISTS `dic_item`;
CREATE TABLE `dic_item` (
  `id`            int(11)     NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `dic_type_code` varchar(20) NOT NULL COMMENT '字典类型码',
  `name`          varchar(20) NOT NULL COMMENT '字典项目名',
  `code`          varchar(20) NOT NULL COMMENT '字典项目码',
  `seq`           int(11)     NOT NULL DEFAULT '0' COMMENT '排序值',
  `fixed`         tinyint(1)  NOT NULL DEFAULT '0' COMMENT '固定不可修改：0-否，1-是',
  `state`         int(1)      NOT NULL DEFAULT '1' COMMENT '状态：0-停用，1-正常',
  `remark`        varchar(255)         DEFAULT NULL COMMENT '备注',
  `create_time`   datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time`   datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted`       tinyint(1)  NOT NULL DEFAULT '0' COMMENT '软删除：0-未删除，1-已删除',
  PRIMARY KEY (`id`),
  KEY `idx_dic_type_code` (`dic_type_code`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COMMENT ='字典项目表';

# 系统参数表（sys_param）
DROP TABLE IF EXISTS `sys_param`;
CREATE TABLE `sys_param` (
  `id`          int(11)      NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name`        varchar(255) NOT NULL COMMENT '参数名',
  `value`       varchar(255) NOT NULL COMMENT '参数值',
  `seq`         int(11)      NOT NULL DEFAULT '0' COMMENT '排序值',
  `fixed`       tinyint(1)   NOT NULL DEFAULT '0' COMMENT '固定不可修改：0-否，1-是',
  `state`       int(1)       NOT NULL DEFAULT '1' COMMENT '状态：0-停用，1-正常',
  `remark`      varchar(255)          DEFAULT NULL COMMENT '备注',
  `create_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted`     tinyint(1)   NOT NULL DEFAULT '0' COMMENT '软删除：0-未删除，1-已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`name`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COMMENT ='系统参数表';