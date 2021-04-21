package com.smilcool.server.base.config.mvc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * MVC 配置
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${smilcool.local-storage}")
    private String localStorage;

    /**
     * 配置静态资源访问
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 允许直接访问存储在 local storage 中的资源
        registry.addResourceHandler("/local-storage/**")
                .addResourceLocations("file:" + localStorage);
    }
}
