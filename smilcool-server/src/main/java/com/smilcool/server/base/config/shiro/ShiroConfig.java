package com.smilcool.server.base.config.shiro;

import com.smilcool.server.base.config.shiro.filter.CustomFormAuthenticationFilter;
import com.smilcool.server.base.config.shiro.filter.CustomHttpMethodPermissionFilter;
import com.smilcool.server.base.config.shiro.filter.CustomPermissionsAuthorizationFilter;
import com.smilcool.server.base.config.shiro.filter.CustomRolesAuthorizationFilter;
import com.smilcool.server.base.config.shiro.util.ShiroUtil;
import com.smilcool.server.core.pojo.po.RuleMap;
import com.smilcool.server.core.service.RuleMapService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.filter.authz.HttpMethodPermissionFilter;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Shiro 配置
 *
 * @author Angus
 * @date 2019/4/5
 */
@Slf4j
@Configuration
public class ShiroConfig {

    @Autowired
    private RuleMapService ruleMapService;

    /**
     * 为替换原过滤器，需要通过 shiroFilterFactoryBean 进行设置。不替换时，
     * 可简化为配置 shiroFilterChainDefinition，自定义过滤器采用 Bean 注入
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
        // 从数据库获取规则映射
        List<RuleMap> ruleMapList = ruleMapService.getRuleMapList();
        // 配置过滤器链映射
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        ruleMapList.forEach(ruleMap -> filterChainDefinitionMap.put(ruleMap.getUrl(), ShiroUtil.buildRule(ruleMap)));
        log.info("filterChainDefinitionMap: {}", filterChainDefinitionMap);
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 使用 Bean 注入时，原过滤器仍然生效，页面依旧会跳转到 /login.jsp，无法达到替代效果
     *
     * @return
     */
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

    /**
     * 开启缓存
     *
     * @return
     */
    // TODO: 2019/4/26 改为 Redis 托管
//    @Bean
//    protected CacheManager cacheManager() {
//        return new MemoryConstrainedCacheManager();
//    }

}
