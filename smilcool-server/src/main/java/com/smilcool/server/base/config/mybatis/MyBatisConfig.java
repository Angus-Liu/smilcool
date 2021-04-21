package com.smilcool.server.base.config.mybatis;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MyBatis 配置：
 * 1.扫描 Mapper
 * 2.开启事务
 * 3.开启分页、自定义排序规则
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.smilcool.server.core.dao")
public class MyBatisConfig {

    /**
     * MyBatis Plus 提供的分页、自定义排序规则插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
