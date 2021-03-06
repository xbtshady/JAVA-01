package com.example.ds.controller;

import com.example.ds.config.DataSourceContext;
import com.example.ds.mapper.OrderMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
public class TestController {
    @Resource
    OrderMapper orderMapper;

    @RequestMapping(value = "/slave")
    public String slave() {
        DataSourceContext.setDataSource("slave");
        return orderMapper.getUserId() + "";
    }

    @RequestMapping(value = "/master")
    public String master() {
        DataSourceContext.setDataSource("master");
        return orderMapper.getUserId() + "";
    }
}