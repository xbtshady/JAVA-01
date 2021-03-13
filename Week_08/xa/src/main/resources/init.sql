CREATE TABLE `sc_order_0` (
                              `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
                              `order_id` int(10) DEFAULT NULL,
                              `payment` decimal(20,2) NOT NULL COMMENT '实付金额',
                              `payment_type` tinyint(1) NOT NULL COMMENT '支付类型，1-在线支付，2-货到付款',
                              `status` tinyint(1) NOT NULL COMMENT '状态：1-未付款，2-已付款，3-未发货，4-已发货，5-交易成功,7-待评价',
                              `payment_time` datetime NOT NULL COMMENT '付款时间',
                              `consign_time` datetime NOT NULL COMMENT '发货时间',
                              `end_time` datetime NOT NULL COMMENT '交易完成时间',
                              `user_id` int(10) NOT NULL COMMENT '用户id',
                              `user_address_id` int(10) NOT NULL COMMENT '地址id',
                              `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                              `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                              PRIMARY KEY (`id`) USING BTREE,
                              KEY `status` (`status`) USING BTREE,
                              KEY `create_time` (`create_time`) USING BTREE,
                              KEY `user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='订单表';
CREATE TABLE `sc_order_1` (
                              `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
                              `order_id` int(10) DEFAULT NULL,
                              `payment` decimal(20,2) NOT NULL COMMENT '实付金额',
                              `payment_type` tinyint(1) NOT NULL COMMENT '支付类型，1-在线支付，2-货到付款',
                              `status` tinyint(1) NOT NULL COMMENT '状态：1-未付款，2-已付款，3-未发货，4-已发货，5-交易成功,7-待评价',
                              `payment_time` datetime NOT NULL COMMENT '付款时间',
                              `consign_time` datetime NOT NULL COMMENT '发货时间',
                              `end_time` datetime NOT NULL COMMENT '交易完成时间',
                              `user_id` int(10) NOT NULL COMMENT '用户id',
                              `user_address_id` int(10) NOT NULL COMMENT '地址id',
                              `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                              `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                              PRIMARY KEY (`id`) USING BTREE,
                              KEY `status` (`status`) USING BTREE,
                              KEY `create_time` (`create_time`) USING BTREE,
                              KEY `user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='订单表';


