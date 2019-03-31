package com.smilcool.server.base.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 扫描 Mapper、开启事务
 *
 * @author Angus
 * @date 2019/3/20
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.smilcool.server.core.dao")
public class MapperConfig {
}
