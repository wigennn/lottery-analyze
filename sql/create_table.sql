CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_name` varchar(20) NOT NULL COMMENT '用户名',
  `phone` varchar(11) NOT NULL COMMENT '手机号',
  `password` varchar(120) NOT NULL COMMENT '密码',
  `name` varchar(50) NOT NULL COMMENT '用户姓名',
  `role_id` tinyint(2) DEFAULT '1' COMMENT '角色id',
  `status` tinyint(2) DEFAULT '0' COMMENT '用户状态 0-正常 1-删除 2-拉黑',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户表';

drop table if exists `daletou`;
CREATE TABLE `daletou` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `num` varchar(6) NOT NULL COMMENT '期号',
  `result` varchar(20) NOT NULL COMMENT '开奖号码',
  `unsort_result` varchar(25) NOT NULL COMMENT '未排序开奖号码',
  `lottery_date` date NOT NULL COMMENT '开奖日期',
  `sale_begin_time` datetime NOT NULL COMMENT '销售开始时间',
  `sale_end_time` datetime NOT NULL COMMENT '销售结束时间',
  `pool_balance` varchar(20) NOT NULL COMMENT '奖池奖金',
  `total_sale_amount` varchar(20) NOT NULL COMMENT '销售额',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_num` (`num`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='大乐透历史开奖表';
