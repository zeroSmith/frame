/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : rdpe

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-04-28 11:55:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for pure_app_system
-- ----------------------------
DROP TABLE IF EXISTS `pure_app_system`;
CREATE TABLE `pure_app_system` (
  `app_system_id` varchar(32) NOT NULL,
  `app_system_name` varchar(128) DEFAULT NULL,
  `server_host` varchar(128) DEFAULT NULL,
  `contextpath` varchar(32) DEFAULT NULL,
  `memo` varchar(512) DEFAULT NULL,
  `ord` decimal(8,0) DEFAULT NULL,
  `tenant_id` varchar(32) DEFAULT NULL,
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_delete` decimal(8,0) DEFAULT NULL,
  PRIMARY KEY (`app_system_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for pure_notice
-- ----------------------------
DROP TABLE IF EXISTS `pure_notice`;
CREATE TABLE `pure_notice` (
  `notice_id` varchar(32) NOT NULL,
  `notice_title` varchar(128) DEFAULT NULL,
  `notice_type` varchar(4) DEFAULT NULL,
  `notice_content` text,
  `state` varchar(4) DEFAULT NULL COMMENT '1：发送成功，-1：发送失败，-2：草稿',
  `from_sign` varchar(4) DEFAULT NULL COMMENT '0：系统公告，1：管理员发送公告',
  `pubdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `pub_user_id` varchar(32) DEFAULT NULL,
  `rec_org_id` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for pure_notice_user_ref
-- ----------------------------
DROP TABLE IF EXISTS `pure_notice_user_ref`;
CREATE TABLE `pure_notice_user_ref` (
  `notice_id` varchar(32) DEFAULT NULL,
  `user_id` varchar(32) DEFAULT NULL,
  `is_read` varchar(4) DEFAULT NULL COMMENT '1：已读，-1：未读'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for pure_opt_type
-- ----------------------------
DROP TABLE IF EXISTS `pure_opt_type`;
CREATE TABLE `pure_opt_type` (
  `opt_type` varchar(32) NOT NULL,
  `opt_name` varchar(128) DEFAULT NULL,
  `ord` decimal(8,0) DEFAULT NULL,
  PRIMARY KEY (`opt_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for pure_org
-- ----------------------------
DROP TABLE IF EXISTS `pure_org`;
CREATE TABLE `pure_org` (
  `org_id` varchar(32) NOT NULL,
  `org_name` varchar(128) DEFAULT NULL,
  `parent_id` varchar(32) DEFAULT NULL,
  `ord` decimal(8,0) DEFAULT NULL,
  `path` varchar(512) DEFAULT NULL,
  `tenant_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`org_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for pure_range
-- ----------------------------
DROP TABLE IF EXISTS `pure_range`;
CREATE TABLE `pure_range` (
  `range_id` varchar(32) NOT NULL,
  `range_name` varchar(32) DEFAULT NULL,
  `parent_id` varchar(32) DEFAULT NULL,
  `path` varchar(512) DEFAULT NULL,
  `ord` decimal(8,0) DEFAULT NULL,
  PRIMARY KEY (`range_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for pure_resources
-- ----------------------------
DROP TABLE IF EXISTS `pure_resources`;
CREATE TABLE `pure_resources` (
  `resources_id` varchar(32) NOT NULL,
  `parent_id` varchar(32) DEFAULT NULL,
  `app_system_id` varchar(32) DEFAULT NULL,
  `resources_type_id` varchar(32) DEFAULT NULL,
  `resources_name` varchar(32) DEFAULT NULL,
  `url` varchar(512) DEFAULT NULL,
  `ext1` varchar(512) DEFAULT NULL,
  `ext2` varchar(512) DEFAULT NULL,
  `ext3` varchar(512) DEFAULT NULL,
  `ext4` varchar(512) DEFAULT NULL,
  `memo` varchar(512) DEFAULT NULL,
  `ord` decimal(8,0) DEFAULT NULL,
  `path` varchar(512) DEFAULT NULL,
  `create_id` varchar(32) DEFAULT NULL,
  `only_read` decimal(8,0) DEFAULT NULL,
  `from_sign` varchar(225) DEFAULT NULL,
  `modify_fields` varchar(225) DEFAULT NULL,
  `tenant_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`resources_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for pure_resources_log
-- ----------------------------
DROP TABLE IF EXISTS `pure_resources_log`;
CREATE TABLE `pure_resources_log` (
  `log_id` varchar(32) NOT NULL,
  `resources_id` varchar(32) DEFAULT NULL,
  `user_id` varchar(32) DEFAULT NULL,
  `log_date` datetime DEFAULT NULL,
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for pure_resources_opt_rel
-- ----------------------------
DROP TABLE IF EXISTS `pure_resources_opt_rel`;
CREATE TABLE `pure_resources_opt_rel` (
  `id` varchar(32) NOT NULL,
  `resources_id` varchar(32) DEFAULT NULL,
  `opt_type` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for pure_resources_type
-- ----------------------------
DROP TABLE IF EXISTS `pure_resources_type`;
CREATE TABLE `pure_resources_type` (
  `resources_type_id` varchar(32) NOT NULL,
  `resources_type_name` varchar(32) DEFAULT NULL,
  `ord` decimal(8,0) DEFAULT NULL,
  PRIMARY KEY (`resources_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for pure_role
-- ----------------------------
DROP TABLE IF EXISTS `pure_role`;
CREATE TABLE `pure_role` (
  `role_id` varchar(32) NOT NULL,
  `parent_id` varchar(32) DEFAULT NULL,
  `role_name` varchar(32) DEFAULT NULL,
  `memo` varchar(512) DEFAULT NULL,
  `ord` decimal(8,0) DEFAULT NULL,
  `tenant_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for pure_role_auth
-- ----------------------------
DROP TABLE IF EXISTS `pure_role_auth`;
CREATE TABLE `pure_role_auth` (
  `id` varchar(32) NOT NULL,
  `role_id` varchar(32) DEFAULT NULL,
  `resources_id` varchar(32) DEFAULT NULL,
  `opt_type` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for pure_tenant
-- ----------------------------
DROP TABLE IF EXISTS `pure_tenant`;
CREATE TABLE `pure_tenant` (
  `tenant_id` varchar(32) NOT NULL,
  `login_id` varchar(32) DEFAULT NULL,
  `tenant_url` varchar(128) DEFAULT NULL,
  `tenant_name` varchar(32) DEFAULT NULL,
  `role_root_id` varchar(32) DEFAULT NULL,
  `org_root_id` varchar(32) DEFAULT NULL,
  `state` varchar(4) DEFAULT NULL,
  `remark` varchar(512) DEFAULT NULL,
  `ord` decimal(8,0) DEFAULT NULL,
  PRIMARY KEY (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for pure_tenant_app_system
-- ----------------------------
DROP TABLE IF EXISTS `pure_tenant_app_system`;
CREATE TABLE `pure_tenant_app_system` (
  `app_system_id` varchar(32) DEFAULT NULL,
  `tenant_id` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for pure_user
-- ----------------------------
DROP TABLE IF EXISTS `pure_user`;
CREATE TABLE `pure_user` (
  `user_id` varchar(32) NOT NULL,
  `login_id` varchar(32) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `user_name` varchar(32) DEFAULT NULL,
  `sex` varchar(4) DEFAULT NULL,
  `emall` varchar(128) DEFAULT NULL,
  `mobile` varchar(32) DEFAULT NULL,
  `telephone` varchar(32) DEFAULT NULL,
  `state` varchar(4) DEFAULT NULL,
  `pwd_state` varchar(4) DEFAULT NULL,
  `memo` varchar(512) DEFAULT NULL,
  `reg_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `creater_id` varchar(32) DEFAULT NULL,
  `org_id` varchar(32) DEFAULT NULL,
  `data_auth` varchar(32) DEFAULT NULL,
  `lock_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `lock_login_times` decimal(8,0) DEFAULT NULL,
  `pwd_valid_state` decimal(8,0) DEFAULT NULL,
  `tenant_admin` decimal(8,0) DEFAULT NULL,
  `pwd_update_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `tenant_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for pure_user_auth
-- ----------------------------
DROP TABLE IF EXISTS `pure_user_auth`;
CREATE TABLE `pure_user_auth` (
  `id` varchar(32) NOT NULL,
  `user_id` varchar(32) DEFAULT NULL,
  `resources_id` varchar(32) DEFAULT NULL,
  `opt_type` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for pure_user_ext
-- ----------------------------
DROP TABLE IF EXISTS `pure_user_ext`;
CREATE TABLE `pure_user_ext` (
  `user_id` varchar(32) NOT NULL,
  `ext_code` varchar(128) NOT NULL,
  `ext_value` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`ext_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for pure_user_extconf
-- ----------------------------
DROP TABLE IF EXISTS `pure_user_extconf`;
CREATE TABLE `pure_user_extconf` (
  `ext_code` varchar(128) NOT NULL,
  `parent_id` varchar(128) DEFAULT NULL,
  `ext_name` varchar(32) DEFAULT NULL,
  `show_mode` varchar(128) DEFAULT NULL,
  `code_table` varchar(128) DEFAULT NULL,
  `code_key` varchar(128) DEFAULT NULL,
  `code_parent_key` varchar(128) DEFAULT NULL,
  `code_desc` varchar(128) DEFAULT NULL,
  `code_ord` decimal(8,0) DEFAULT NULL,
  `multi` decimal(8,0) DEFAULT NULL,
  `ext_ord` decimal(8,0) DEFAULT NULL,
  `is_null` decimal(8,0) DEFAULT NULL,
  `default_value` varchar(128) DEFAULT NULL,
  `default_desc` varchar(128) DEFAULT NULL,
  `childtree_root` varchar(32) DEFAULT NULL,
  `tenant_id` varchar(32) DEFAULT NULL,
  `tenant_name` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`ext_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for pure_user_login_log
-- ----------------------------
DROP TABLE IF EXISTS `pure_user_login_log`;
CREATE TABLE `pure_user_login_log` (
  `user_id` varchar(32) DEFAULT NULL,
  `login_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `login_ip` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for pure_user_org_ref
-- ----------------------------
DROP TABLE IF EXISTS `pure_user_org_ref`;
CREATE TABLE `pure_user_org_ref` (
  `org_id` varchar(32) DEFAULT NULL,
  `user_id` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for pure_user_role
-- ----------------------------
DROP TABLE IF EXISTS `pure_user_role`;
CREATE TABLE `pure_user_role` (
  `role_id` varchar(32) DEFAULT NULL,
  `user_id` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_ds_hdfs
-- ----------------------------
DROP TABLE IF EXISTS `t_ds_hdfs`;
CREATE TABLE `t_ds_hdfs` (
  `id` varchar(32) NOT NULL,
  `ds_name` varchar(256) DEFAULT NULL COMMENT '数据源名称',
  `url` varchar(64) DEFAULT NULL,
  `path` varchar(256) DEFAULT NULL COMMENT '文件路径',
  `create_id` varchar(32) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_ds_kafka
-- ----------------------------
DROP TABLE IF EXISTS `t_ds_kafka`;
CREATE TABLE `t_ds_kafka` (
  `id` varchar(32) NOT NULL,
  `ds_name` varchar(256) DEFAULT NULL COMMENT '数据源名称',
  `topic_name` varchar(128) DEFAULT NULL COMMENT '话题名称',
  `zk_list` varchar(256) DEFAULT NULL COMMENT 'zookeeper参数',
  `broker_list` varchar(256) DEFAULT NULL COMMENT 'broker参数',
  `group_id` varchar(32) DEFAULT NULL COMMENT '消费分组',
  `create_id` varchar(32) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_ds_redis
-- ----------------------------
DROP TABLE IF EXISTS `t_ds_redis`;
CREATE TABLE `t_ds_redis` (
  `id` varchar(32) NOT NULL,
  `ds_name` varchar(256) DEFAULT NULL,
  `host_ip` varchar(32) DEFAULT NULL,
  `host_port` varchar(16) DEFAULT NULL,
  `pwd` varchar(64) DEFAULT NULL,
  `pk` varchar(128) DEFAULT NULL,
  `resultset_type` varchar(16) DEFAULT NULL,
  `create_id` varchar(32) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_event_opt
-- ----------------------------
DROP TABLE IF EXISTS `t_event_opt`;
CREATE TABLE `t_event_opt` (
  `id` varchar(32) NOT NULL,
  `event_id` varchar(32) NOT NULL COMMENT '事件ID',
  `opt_type` varchar(16) DEFAULT NULL COMMENT '操作类型',
  `opt_def` text,
  `opt_def_md5` varchar(128) DEFAULT NULL COMMENT 'opt_def字段md5编码',
  `create_id` varchar(32) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`,`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_event_spark
-- ----------------------------
DROP TABLE IF EXISTS `t_event_spark`;
CREATE TABLE `t_event_spark` (
  `id` varchar(32) NOT NULL,
  `event_name` varchar(255) DEFAULT NULL COMMENT '事件名称',
  `event_type` varchar(255) DEFAULT NULL COMMENT '事件类型',
  `submit_host_id` varchar(64) DEFAULT NULL COMMENT '运行主机id',
  `flow_def` text COMMENT '事件流程定义',
  `execute_def` text COMMENT '执行参数定义',
  `external_main_class` varchar(512) DEFAULT NULL COMMENT '事件主类全包名称',
  `jar_path` varchar(512) DEFAULT NULL,
  `create_id` varchar(32) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_fun_def
-- ----------------------------
DROP TABLE IF EXISTS `t_fun_def`;
CREATE TABLE `t_fun_def` (
  `id` varchar(32) NOT NULL,
  `parent_id` varchar(32) DEFAULT NULL,
  `fun_type_id` varchar(32) DEFAULT NULL,
  `fun_name` varchar(32) DEFAULT NULL,
  `input_param` varchar(512) DEFAULT NULL,
  `output_param` varchar(512) DEFAULT NULL,
  `code_type` varchar(16) DEFAULT NULL,
  `package_name` varchar(512) DEFAULT NULL,
  `method_description` varchar(512) DEFAULT NULL,
  `ord` decimal(8,0) DEFAULT NULL,
  `path` varchar(512) DEFAULT NULL,
  `create_id` varchar(32) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_host_spark
-- ----------------------------
DROP TABLE IF EXISTS `t_host_spark`;
CREATE TABLE `t_host_spark` (
  `id` varchar(32) NOT NULL,
  `host_ip` varchar(32) DEFAULT NULL,
  `host_name` varchar(256) DEFAULT NULL,
  `host_type` varchar(32) DEFAULT NULL,
  `host_username` varchar(256) DEFAULT NULL,
  `host_password` varchar(256) DEFAULT NULL,
  `install_path` varchar(512) DEFAULT NULL,
  `spark_version` varchar(64) DEFAULT NULL COMMENT '版本',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_dict`;
CREATE TABLE `t_sys_dict` (
  `prop_name` varchar(128) NOT NULL DEFAULT '' COMMENT '属性key',
  `prop_value` text COMMENT '属性value',
  `memo` text COMMENT '备注说明',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `creator_id` varchar(32) DEFAULT NULL COMMENT '创建人id',
  PRIMARY KEY (`prop_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
