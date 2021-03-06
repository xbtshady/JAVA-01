package com.example.ds.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.example.ds.MultiRouteDataSource;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceComponent {
    @Resource
    private MasterDataSourceConfig masterDataSourceConfig;
    @Resource
    private SlaveDataSourceConfig slaveDataSourceConfig;

    @Bean(name = "master")
    public DataSource masterDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(masterDataSourceConfig.getUrl());
        dataSource.setUsername(masterDataSourceConfig.getUsername());
        dataSource.setPassword(masterDataSourceConfig.getPassword());
        dataSource.setDriverClassName(masterDataSourceConfig.getDriverClassName());
        return dataSource;
    }

    @Bean(name = "slave")
    public DataSource slaveDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(slaveDataSourceConfig.getUrl());
        dataSource.setUsername(slaveDataSourceConfig.getUsername());
        dataSource.setPassword(slaveDataSourceConfig.getPassword());
        dataSource.setDriverClassName(slaveDataSourceConfig.getDriverClassName());
        return dataSource;
    }

    @Primary
    @DependsOn({ "master", "slave"})
    @Bean(name = "multiDataSource")
    public MultiRouteDataSource exampleRouteDataSource() {
        MultiRouteDataSource multiDataSource = new MultiRouteDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("master", masterDataSource());
        targetDataSources.put("slave", slaveDataSource());
        multiDataSource.setTargetDataSources(targetDataSources);
        multiDataSource.setDefaultTargetDataSource(masterDataSource());
        return multiDataSource;
    }
}
