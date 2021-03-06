package com.example.ds;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan(basePackages = "com.example.ds.mapper")
public class DsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DsApplication.class, args);
    }

}
