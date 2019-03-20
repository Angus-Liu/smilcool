package com.smilcool.server.core.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Angus
 * @date 2019/3/20
 */
@Configuration
@MapperScan(basePackages = "com.smilcool.server.core.dao")
public class MapperConfig {
}
