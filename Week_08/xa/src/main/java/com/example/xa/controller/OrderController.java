package com.example.xa.controller;


import io.shardingsphere.transaction.annotation.ShardingTransactionType;
import io.shardingsphere.transaction.api.TransactionType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
public class OrderController {
    private  final JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderController(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    /**
     * addOne数据正常，addTwo数据异常，在不开启XA事务时，addOne数据插入成功，addTwo数据插入失败
     * 开启XA事务后，两者皆失败
     */
    @Transactional
    @RequestMapping(value = "/test")
    @ShardingTransactionType(TransactionType.XA)
    public void ds0() throws Exception {
        String sql =
                "INSERT INTO sc_order (`id`,`order_id`, `payment`, `payment_type`, `status`, `payment_time`, `consign_time`, `end_time`, `user_id`, `user_address_id`) VALUES" +
                        " ( 1,?, 1.00, 1, 1, '2021-03-04 22:46:42', '2021-03-04 22:46:39', '2021-03-07 22:46:36', ?, 1)";
        jdbcTemplate.execute(sql, (PreparedStatementCallback<TransactionType>) preparedStatement -> {
            preparedStatement.setObject(1, 1);
            preparedStatement.setObject(2, 1);
            preparedStatement.executeUpdate();//成功

            preparedStatement.setObject(1, 2);
            preparedStatement.setObject(2, 2);
            preparedStatement.executeUpdate();//失败
            //最终失败
            return null;
        });
    }
}
