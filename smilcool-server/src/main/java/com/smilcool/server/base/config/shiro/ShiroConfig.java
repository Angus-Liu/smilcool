package com.smilcool.server.base.config.shiro;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Angus
 * @date 2019/4/5
 */
@Configuration
public class ShiroConfig {

    @Bean
    public Realm realm() {
        return new ShiroRealm();
    }


    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        // all other paths require a logged in user
        chainDefinition.addPathDefinition("/user/login", "anon");
        chainDefinition.addPathDefinition("/user/register", "anon");
        chainDefinition.addPathDefinition("/user", "authc");
        chainDefinition.addPathDefinition("/user/page", "authc");
        chainDefinition.addPathDefinition("/**", "anon");
        return chainDefinition;
    }
}
