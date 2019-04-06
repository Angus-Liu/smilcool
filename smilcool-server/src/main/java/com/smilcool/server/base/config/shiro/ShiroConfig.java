package com.smilcool.server.base.config.shiro;

import com.smilcool.server.base.config.shiro.filter.CustomFormAuthenticationFilter;
import com.smilcool.server.base.config.shiro.filter.CustomHttpMethodPermissionFilter;
import com.smilcool.server.base.config.shiro.filter.CustomPermissionsAuthorizationFilter;
import com.smilcool.server.base.config.shiro.filter.CustomRolesAuthorizationFilter;
import com.smilcool.server.base.config.shiro.realm.CustomAuthorizingRealm;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.filter.authz.HttpMethodPermissionFilter;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Angus
 * @date 2019/4/5
 */
@Configuration
public class ShiroConfig {

    /**
     * 设置自定义 Realm
     *
     * @return
     */
    @Bean
    public Realm realm() {
        return new CustomAuthorizingRealm();
    }

    /**
     * 设置自定义过滤器时，需要通过 shiroFilterFactoryBean 进行设置。
     * 不设置过滤器时，可以简化为只配置 shiroFilterChainDefinition
     * 简化配置参考官网：https://shiro.apache.org/spring-boot.html
     *
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 设置过滤器
        Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
        filters.put("authc", authc());
        filters.put("perms", perms());
        filters.put("rest", rest());
        filters.put("roles", roles());
        // 配置过滤器链
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // TODO：从数据库获取
        filterChainDefinitionMap.put("/user/login", "anon");
        filterChainDefinitionMap.put("/user/register", "anon");
        filterChainDefinitionMap.put("/user", "authc, rest[user]");
        filterChainDefinitionMap.put("/user/page", "authc, roles[admin]");
        filterChainDefinitionMap.put("/role", "authc, perms[role]");
        filterChainDefinitionMap.put("/**", "anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 开启缓存
     *
     * @return
     */
    @Bean
    protected CacheManager cacheManager() {
        return new MemoryConstrainedCacheManager();
    }

    private FormAuthenticationFilter authc() {
        return new CustomFormAuthenticationFilter();
    }

    private RolesAuthorizationFilter roles() {
        return new CustomRolesAuthorizationFilter();
    }

    private PermissionsAuthorizationFilter perms() {
        return new CustomPermissionsAuthorizationFilter();
    }

    private HttpMethodPermissionFilter rest() {
        return new CustomHttpMethodPermissionFilter();
    }
}
