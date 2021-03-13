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
}