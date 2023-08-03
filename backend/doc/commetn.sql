-- Adminer 4.8.1 MySQL 8.0.32 dump
DROP TABLE IF EXISTS `apitable_datasheet_record_comment`;
CREATE TABLE `apitable_datasheet_record_comment` (
  `id` char(20) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'pk',
  `dst_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'dstID',
  `record_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'RecordID',
  `comment_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'comment_id',
  `comment_msg` json NOT NULL COMMENT 'content',
  `revision` bigint unsigned DEFAULT '0' COMMENT 'revision',
  `is_deleted` tinyint unsigned DEFAULT '0' COMMENT ' (0: No, 1: Yes)',
  `unit_id` bigint DEFAULT NULL COMMENT 'Operation User Unit ID(link#xxxx_unit#id)',
  `created_by` bigint DEFAULT NULL COMMENT 'Creator',
  `updated_by` bigint DEFAULT NULL COMMENT 'Updator',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Create Time',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update Time',
  `remark` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  `tenant_id` char(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '租户编号',

  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_dst_id` (`dst_id`) USING BTREE,
  KEY `idx_record_id` (`record_id`) USING BTREE,
  KEY `idx_comment_id` (`comment_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Workbench - Datasheet Record Comment Table';

INSERT INTO `apitable_datasheet_record_comment` (`id`, `dst_id`, `record_id`, `comment_id`, `comment_msg`, `revision`, `is_deleted`, `unit_id`, `created_by`, `updated_by`, `created_time`, `updated_time`) VALUES
(4649322063704896,	'dstrsF01My05hFywdL',	'recQo0mytGJtz',	'cmtkSGlUjkI1B',	'{\"html\": \"\", \"type\": \"dfs\", \"reply\": {}, \"content\": [{\"type\": \"paragraph\", \"children\": [{\"text\": \"这是一条测试评论\"}]}]}',	36,	0,	1650051215579009026,	1650051214576570369,	NULL,	'2023-08-01 07:24:56',	'2023-08-01 07:24:55'),
(4649325072495424,	'dstrsF01My05hFywdL',	'recQo0mytGJtz',	'cmtDx27uwMAPJ',	'{\"html\": \"\", \"type\": \"dfs\", \"reply\": {}, \"content\": [{\"type\": \"paragraph\", \"children\": [{\"text\": \"；\"}]}]}',	37,	0,	1650051215579009026,	1650051214576570369,	NULL,	'2023-08-01 07:26:28',	'2023-08-01 07:26:27'),
(4649325141308224,	'dstrsF01My05hFywdL',	'recQo0mytGJtz',	'cmtyzKIG70Rpx',	'{\"html\": \"\", \"type\": \"dfs\", \"reply\": {}, \"content\": [{\"type\": \"paragraph\", \"children\": [{\"text\": \"‘阿尔法额外、fw\"}]}]}',	38,	0,	1650051215579009026,	1650051214576570369,	NULL,	'2023-08-01 07:26:30',	'2023-08-01 07:26:29'),
(4649325189346112,	'dstrsF01My05hFywdL',	'recQo0mytGJtz',	'cmtmXHBWuBU4Z',	'{\"html\": \"\", \"type\": \"dfs\", \"reply\": {}, \"content\": [{\"type\": \"paragraph\", \"children\": [{\"text\": \"eawfawe\"}]}]}',	39,	0,	1650051215579009026,	1650051214576570369,	NULL,	'2023-08-01 07:26:31',	'2023-08-01 07:26:31'),
(4649325241447232,	'dstrsF01My05hFywdL',	'recQo0mytGJtz',	'cmtmLVN0Ih0q4',	'{\"html\": \"\", \"type\": \"dfs\", \"reply\": {}, \"content\": [{\"type\": \"paragraph\", \"children\": [{\"text\": \"awefewfawe\"}]}]}',	40,	0,	1650051215579009026,	1650051214576570369,	NULL,	'2023-08-01 07:26:33',	'2023-08-01 07:26:32'),
(4649325281522496,	'dstrsF01My05hFywdL',	'recQo0mytGJtz',	'cmt6o9mICSUTX',	'{\"html\": \"\", \"type\": \"dfs\", \"reply\": {}, \"content\": [{\"type\": \"paragraph\", \"children\": [{\"text\": \"aadfad\"}]}]}',	41,	0,	1650051215579009026,	1650051214576570369,	NULL,	'2023-08-01 07:26:34',	'2023-08-01 07:26:33'),
(4649330740572992,	'dstrsF01My05hFywdL',	'recQo0mytGJtz',	'cmtYiI5xzIJDJ',	'{\"html\": \"\", \"type\": \"dfs\", \"reply\": {\"commentId\": \"cmtkSGlUjkI1B\"}, \"content\": [{\"type\": \"paragraph\", \"children\": [{\"text\": \"你说错了\"}]}]}',	42,	0,	1650051215579009026,	1650051214576570369,	NULL,	'2023-08-01 07:29:21',	'2023-08-01 07:29:20');

-- 2023-08-01 07:32:01