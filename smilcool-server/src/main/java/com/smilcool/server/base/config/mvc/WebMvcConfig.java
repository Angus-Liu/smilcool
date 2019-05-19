package com.smilcool.server.base.config.mvc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * MVC 配置
 *
 * @author Autumn
 * @date 2018/9/11
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${smilcool.local-storage}")
    private String localStorage;

    /**
     * 配置静态资源访问
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/local-storage/**")
                .addResourceLocations("file:" + localStorage);
    }
}
