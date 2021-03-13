package com.example.shardingsphere.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;


@RestController
public class TestController {
    @Resource
    private DataSource dataSource;

    @RequestMapping(value = "/ds")
    public void ds0(Integer userId,Integer orderId) {
        try {
            Connection connection = dataSource.getConnection();
            String sql =
                    "INSERT INTO sc_order (`order_id`, `payment`, `payment_type`, `status`, `payment_time`, `consign_time`, `end_time`, `user_id`, `user_address_id`) VALUES" +
                            " ( "+ orderId +", 1.00, 1, 1, '2021-03-04 22:46:42', '2021-03-04 22:46:39', '2021-03-07 22:46:36', "+ userId +", 1);\n";

            System.out.println(sql);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        int i = 0;
        while (i < 16){
            System.out.println("CREATE TABLE `sc_order_" + i++ + "` (\n" +
                    "                              `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',\n" +
                    "                              `order_id` int(10) DEFAULT NULL,\n" +
                    "                              `payment` decimal(20,2) NOT NULL COMMENT '实付金额',\n" +
                    "                              `payment_type` tinyint(1) NOT NULL COMMENT '支付类型，1-在线支付，2-货到付款',\n" +
                    "                              `status` tinyint(1) NOT NULL COMMENT '状态：1-未付款，2-已付款，3-未发货，4-已发货，5-交易成功,7-待评价',\n" +
                    "                              `payment_time` datetime NOT NULL COMMENT '付款时间',\n" +
                    "                              `consign_time` datetime NOT NULL COMMENT '发货时间',\n" +
                    "                              `end_time` datetime NOT NULL COMMENT '交易完成时间',\n" +
                    "                              `user_id` int(10) NOT NULL COMMENT '用户id',\n" +
                    "                              `user_address_id` int(10) NOT NULL COMMENT '地址id',\n" +
                    "                              `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',\n" +
                    "                              `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',\n" +
                    "                              PRIMARY KEY (`id`) USING BTREE,\n" +
                    "                              KEY `status` (`status`) USING BTREE,\n" +
                    "                              KEY `create_time` (`create_time`) USING BTREE,\n" +
                    "                              KEY `user_id` (`user_id`) USING BTREE\n" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='订单表';");
        }
    }
}