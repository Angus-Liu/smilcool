package com.smilcool.server.base.config.mybatis;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MyBatis 配置：
 * ① 扫描 Mapper
 * ② 开启事务
 * ③ 开启分页
 *
 * @author Angus
 * @date 2019/3/20
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.smilcool.server.core.dao")
public class MyBatisConfig {

    /**
     * MyBatis Plus 提供的分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
