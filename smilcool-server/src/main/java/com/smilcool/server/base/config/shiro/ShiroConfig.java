package com.smilcool.server.base.config.shiro;

import com.smilcool.server.base.config.shiro.filter.CustomFormAuthenticationFilter;
import com.smilcool.server.base.config.shiro.filter.CustomHttpMethodPermissionFilter;
import com.smilcool.server.base.config.shiro.filter.CustomPermissionsAuthorizationFilter;
import com.smilcool.server.base.config.shiro.filter.CustomRolesAuthorizationFilter;
import com.smilcool.server.base.config.shiro.realm.CustomAuthorizingRealm;
import lombok.extern.slf4j.Slf4j;
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
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

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
        // 配置过滤器链映射
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // TODO：从数据库获取，考虑是否单独增加一张表存储 shiro 过滤器链
        // 注册 数据库中所有的权限 及其对应url
        // List<Permission> allPermission = permissionRepository.findAll();//数据库中查询所有权限
        // for (Permission p : allPermission) {
        //     filterMap.put(p.getUrl(), "perms[" + p.getName() + "]");    //拦截器中注册所有的权限
        // }
        // filterMap.put("/static/**", "anon");    //公开访问的资源
        // filterMap.put("/open/api/**", "anon");  //公开接口地址
        // filterMap.put("/logout", "logout");     //配置登出页,shiro已经帮我们实现了跳转
        // filterMap.put("/**", "authc");          //所有资源都需要经过验证

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
