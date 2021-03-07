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

    @RequestMapping(value = "/ds0")
    public void ds0() {
        try {
            Connection connection = dataSource.getConnection();
            //userId 为偶数 将插入 ds0
            Random random = new Random();
            int userId = random.nextInt() * 2;
            int order_id = random.nextInt();

            System.out.println("userId :" + userId );
            System.out.println("order_id :" + order_id );
            String sql =
                    "INSERT INTO sc_order (`order_id`, `payment`, `payment_type`, `status`, `payment_time`, `consign_time`, `end_time`, `user_id`, `user_address_id`) VALUES" +
                            " ( "+ order_id +", 1.00, 1, 1, '2021-03-04 22:46:42', '2021-03-04 22:46:39', '2021-03-07 22:46:36', "+ userId +", 1);\n";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @RequestMapping(value = "/ds1")
    public void ds1() {
        try {
            Connection connection = dataSource.getConnection();
            //userId 为奇数将插入 ds1
            Random random = new Random();
            int userId = random.nextInt() * 3;
            int order_id = random.nextInt();

            System.out.println("userId :" + userId );
            System.out.println("order_id :" + order_id );

            String sql =
                    "INSERT INTO sc_order (`order_id`, `payment`, `payment_type`, `status`, `payment_time`, `consign_time`, `end_time`, `user_id`, `user_address_id`) VALUES" +
                            " ( "+ order_id +", 1.00, 1, 1, '2021-03-04 22:46:42', '2021-03-04 22:46:39', '2021-03-07 22:46:36', "+ userId +", 1);";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}