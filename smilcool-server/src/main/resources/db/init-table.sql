# 用户表（user）
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id`          int(11)       NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username`    varchar(50)   NOT NULL COMMENT '用户名',
  `password`    varchar(80)   NOT NULL COMMENT '密码（加密）',
  `nickname`    varchar(50)   NOT NULL DEFAULT '换个好听的昵称' COMMENT '昵称',
  `avatar`      varchar(255)  NOT NULL DEFAULT 'default-avatar.jpg' COMMENT '头像URL',
  `sex`         varchar(5)    NOT NULL DEFAULT '保密' COMMENT '性别：男、女、保密',
  `birthday`    datetime      NOT NULL DEFAULT '1970-01-01' COMMENT '生日',
  `sign`        varchar(255)  NOT NULL DEFAULT '一句话介绍自己' COMMENT '签名',
  `intro`       varchar(1000) NOT NULL DEFAULT '这个人很神秘，什么也没写' COMMENT '简介',
  `grade`       varchar(10)   NOT NULL DEFAULT '未填写' COMMENT '年级',
  `college`     varchar(50)   NOT NULL DEFAULT '未填写' COMMENT '学院',
  `major`       varchar(50)   NOT NULL DEFAULT '未填写' COMMENT '专业',
  `phone`       varchar(20)            DEFAULT NULL COMMENT '手机',
  `email`       varchar(50)   NOT NULL COMMENT '邮箱',
  `state`       int(1)        NOT NULL DEFAULT '0' COMMENT '状态：0-未激活，1-正常，2-停用',
  `remark`      varchar(255)           DEFAULT NULL COMMENT '备注',
  `create_time` datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted`     tinyint(1)    NOT NULL DEFAULT '0' COMMENT '软删除：0-未删除，1-已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`),
  UNIQUE KEY `uk_phone` (`phone`),
  UNIQUE KEY `uk_email` (`email`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COMMENT ='用户表';

# 用户信息表（user_info）
# TODO

# 角色表（roles）
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id`          int(11)      NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `name`        varchar(20)  NOT NULL COMMENT '角色名',
  `description` varchar(255) NOT NULL COMMENT '角色描述',
  `default`     tinyint(1)   NOT NULL DEFAULT '0' COMMENT '是否为默认角色：0-不是，1-是',
  `state`       int(1)       NOT NULL DEFAULT '1' COMMENT '状态：0-停用，1-正常',
  `remark`      varchar(255)          DEFAULT NULL COMMENT '备注',
  `create_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted`     tinyint(1)   NOT NULL DEFAULT '0' COMMENT '软删除：0-未删除，1-已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`name`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COMMENT ='角色表';

INSERT INTO `role`(`id`, `name`, `description`, `default`, `state`, `remark`)
VALUES (0, 'normal', '普通用户', 1, 1, '注册用户默认角色为普通用户');

INSERT INTO `role`(`id`, `name`, `description`, `state`, `remark`)
VALUES (1, 'super-admin', '超级管理员', 1, '超级管理员由系统内定，具有至高无上的权利');

INSERT INTO `role`(`id`, `name`, `description`, `state`, `remark`)
VALUES (2, 'admin', '管理员', 1, '管理员由超级管理员指定，具有相关操作权利');

INSERT INTO `role`(`id`, `name`, `description`, `state`, `remark`)
VALUES (9, 'member', '会员', 1, '会员比普通用户多了一些特殊权利');

# 权限表（permissions）
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id`          int(11)      NOT NULL AUTO_INCREMENT COMMENT '权限ID',
  `parent_id`   int(11)               DEFAULT NULL COMMENT '父级ID',
  `name`        varchar(255) NOT NULL COMMENT '权限名',
  `description` varchar(255) NOT NULL COMMENT '权限描述',
  `url`         varchar(255) NOT NULL COMMENT '请求地址',
  `type`        int(1)       NOT NULL DEFAULT '0' COMMENT '类型：0-菜单，1-按钮，2-其他',
  `seq`         int(11)      NOT NULL DEFAULT '0' COMMENT '顺序',
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
  `id`          int(11)    NOT NULL AUTO_INCREMENT COMMENT '用户角色ID',
  `user_id`     int(11)    NOT NULL COMMENT '用户ID',
  `role_id`     int(11)    NOT NULL DEFAULT '0' COMMENT '角色ID',
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
  `id`            int(11)    NOT NULL AUTO_INCREMENT COMMENT '角色权限ID',
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
  `id`          int(11)      NOT NULL AUTO_INCREMENT COMMENT '规则映射ID',
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
  `seq`         int(11)      NOT NULL DEFAULT '0' COMMENT '顺序',
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

# 资源类目表（resource_type）
DROP TABLE IF EXISTS `resource_type`;
CREATE TABLE `resource_type` (
  `id`          int(11)       NOT NULL AUTO_INCREMENT COMMENT '资源类目ID',
  `parent_id`   int(11)                DEFAULT NULL COMMENT '父级ID',
  `name`        varchar(20)   NOT NULL COMMENT '资源类目名',
  `url`         varchar(255)  NOT NULL COMMENT '链接',
  `description` varchar(1000) NOT NULL DEFAULT '' COMMENT '描述',
  `seq`         int(11)       NOT NULL DEFAULT '0' COMMENT '顺序',
  `state`       int(1)        NOT NULL DEFAULT '1' COMMENT '状态：0-停用，1-正常',
  `remark`      varchar(255)           DEFAULT NULL COMMENT '备注',
  `create_time` datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted`     tinyint(1)    NOT NULL DEFAULT '0' COMMENT '软删除：0-未删除，1-已删除',
  PRIMARY KEY (`id`),
  KEY `idx_parent_id` (`parent_id`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COMMENT ='资源类目表';

# 资源表（resource）
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `id`               int(11)    NOT NULL AUTO_INCREMENT COMMENT '资源ID',
  `user_id`          int(11)    NOT NULL COMMENT '发布用户ID',
  `resource_type_id` int(11)    NOT NULL COMMENT '资源类目ID',
  `zan`              int(11)    NOT NULL DEFAULT '0' COMMENT '点赞数',
  `pv`               int(11)    NOT NULL DEFAULT '0' COMMENT '浏览量',
  `state`            int(1)     NOT NULL DEFAULT '1' COMMENT '状态：0-停用，1-正常',
  `remark`           varchar(255)        DEFAULT NULL COMMENT '备注',
  `create_time`      datetime   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time`      datetime   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted`          tinyint(1) NOT NULL DEFAULT '0' COMMENT '软删除：0-未删除，1-已删除',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_resource_type_id` (`resource_type_id`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COMMENT ='资源表';

# 文章表（article）
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id`               int(11)      NOT NULL AUTO_INCREMENT COMMENT '文章ID',
  `user_id`          int(11)      NOT NULL COMMENT '发布用户ID',
  `resource_id`      int(11)      NOT NULL COMMENT '资源ID',
  `title`            varchar(255) NOT NULL DEFAULT '呀，忘记写标题了' COMMENT '标题',
  `intro`            varchar(255) NOT NULL DEFAULT '咦？什么简介也没有呢' COMMENT '简介',
  `label`            varchar(255) NOT NULL DEFAULT '[]' COMMENT '标签',
  `image`            varchar(255)          DEFAULT NULL COMMENT '主图',
  `markdown_content` text                  DEFAULT NULL COMMENT '内容（markdown格式）',
  `html_content`     text                  DEFAULT NULL COMMENT '内容（html格式）',
  `remark`           varchar(255)          DEFAULT NULL COMMENT '备注',
  `create_time`      datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time`      datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted`          tinyint(1)   NOT NULL DEFAULT '0' COMMENT '软删除：0-未删除，1-已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_resource_id` (`resource_id`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COMMENT ='文章表';

# 资料表（data）
DROP TABLE IF EXISTS `data`;
CREATE TABLE `data` (
  `id`          int(11)       NOT NULL AUTO_INCREMENT COMMENT '资料ID',
  `resource_id` int(11)       NOT NULL COMMENT '资源ID',
  `title`       varchar(255)  NOT NULL DEFAULT '' COMMENT '标题',
  `name`        varchar(255)  NOT NULL DEFAULT '未知' COMMENT '资料名',
  `size`        varchar(10)   NOT NULL DEFAULT '未知大小' COMMENT '资料大小',
  `url`         varchar(255)  NOT NULL COMMENT '资料链接',
  `downloads`   int(11)       NOT NULL DEFAULT '0' COMMENT '下载量',
  `description` varchar(1000) NOT NULL DEFAULT '' COMMENT '描述',
  `remark`      varchar(255)           DEFAULT NULL COMMENT '备注',
  `create_time` datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted`     tinyint(1)    NOT NULL DEFAULT '0' COMMENT '软删除：0-未删除，1-已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_resource_id` (`resource_id`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COMMENT ='资料表';

# 失物寻物表（lost_found）
DROP TABLE IF EXISTS `lost_found`;
CREATE TABLE `lost_found` (
  `id`          int(11)       NOT NULL AUTO_INCREMENT COMMENT '失物寻物ID',
  `resource_id` int(11)       NOT NULL COMMENT '资源ID',
  `type`        int(1)        NOT NULL DEFAULT '1' COMMENT '类型：1-失物招领，2-寻物启示',
  `title`       varchar(255)  NOT NULL DEFAULT '' COMMENT '标题',
  `name`        varchar(255)  NOT NULL DEFAULT '' COMMENT '物品名称',
  `time`        datetime      NOT NULL COMMENT '丢失/拾取时间',
  `address`     varchar(255)  NOT NULL COMMENT '丢失/拾取地址',
  `image`       varchar(255)           DEFAULT NULL COMMENT '主图',
  `description` varchar(1000) NOT NULL DEFAULT '' COMMENT '描述',
  `remark`      varchar(255)           DEFAULT NULL COMMENT '备注',
  `create_time` datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted`     tinyint(1)    NOT NULL DEFAULT '0' COMMENT '软删除：0-未删除，1-已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_resource_id` (`resource_id`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COMMENT ='失物寻物表';

# 二手物品表（second_hand）
DROP TABLE IF EXISTS `second_hand`;
CREATE TABLE `second_hand` (
  `id`          int(11)       NOT NULL AUTO_INCREMENT COMMENT '二手物品ID',
  `resource_id` int(11)       NOT NULL COMMENT '资源ID',
  `title`       varchar(255)  NOT NULL DEFAULT '' COMMENT '标题',
  `name`        varchar(255)  NOT NULL DEFAULT '' COMMENT '物品名称',
  `price`       double(8, 2)  NOT NULL DEFAULT '0.00' COMMENT '物品价格',
  `address`     varchar(255)  NOT NULL COMMENT '交易地址',
  `image`       varchar(255)           DEFAULT NULL COMMENT '主图',
  `description` varchar(1000) NOT NULL DEFAULT '' COMMENT '描述',
  `remark`      varchar(255)           DEFAULT NULL COMMENT '备注',
  `create_time` datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted`     tinyint(1)    NOT NULL DEFAULT '0' COMMENT '软删除：0-未删除，1-已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_resource_id` (`resource_id`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COMMENT ='二手物品表';

# 评论表（comment）
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id`            int(11)       NOT NULL AUTO_INCREMENT COMMENT '评论ID',
  `parent_id`     int(11)                DEFAULT NULL COMMENT '父级ID',
  `user_id`       int(11)       NOT NULL COMMENT '评论用户ID',
  `resource_id`   int(11)       NOT NULL COMMENT '评论资源ID',
  `reply_user_id` int(11)                DEFAULT NULL COMMENT '回复用户ID',
  `content`       varchar(1000) NOT NULL DEFAULT '' COMMENT '内容',
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
  `id`          int(11)    NOT NULL AUTO_INCREMENT COMMENT '评论ID',
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