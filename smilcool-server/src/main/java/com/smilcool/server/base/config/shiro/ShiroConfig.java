package com.smilcool.server.base.config.shiro;

import com.smilcool.server.base.config.shiro.filter.CustomFormAuthenticationFilter;
import com.smilcool.server.base.config.shiro.filter.CustomHttpMethodPermissionFilter;
import com.smilcool.server.base.config.shiro.filter.CustomPermissionsAuthorizationFilter;
import com.smilcool.server.base.config.shiro.filter.CustomRolesAuthorizationFilter;
import com.smilcool.server.base.config.shiro.util.ShiroUtils;
import com.smilcool.server.core.pojo.po.RuleMap;
import com.smilcool.server.core.service.RuleMapService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.boot.autoconfigure.ShiroAnnotationProcessorAutoConfiguration;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.filter.authz.HttpMethodPermissionFilter;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Shiro 配置因 Service 层未使用接口，出现代理方式冲突（JDK Dynamic / CGLib）问题，关掉
 * Shiro 的注解支持（本项目未用到），参考：
 * <a href="https://juejin.cn/post/6844904127525306382">Spring Boot 加入 Shiro 导致 AOP 失效的坑</a>
 * <a href="https://zhuanlan.zhihu.com/p/143126919">Shiro 权限注解与 AOP 冲突问题探究</a>
 */
@Slf4j
@AllArgsConstructor
@Configuration
@EnableAutoConfiguration(exclude = {ShiroAnnotationProcessorAutoConfiguration.class})
public class ShiroConfig {

    private final RuleMapService ruleMapService;

    /**
     * 为替换原过滤器，需要通过 shiroFilterFactoryBean 进行设置。不替换时，可简化
     * 为配置 shiroFilterChainDefinition，自定义过滤器采用 Bean 注入简化配置参考
     * 官网：https://shiro.apache.org/spring-boot.html
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
        ruleMapList.forEach(ruleMap -> filterChainDefinitionMap.put(ruleMap.getUrl(), ShiroUtils.buildRule(ruleMap)));
        log.info("filterChainDefinitionMap: {}", filterChainDefinitionMap);
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 使用 Bean 注入时，原过滤器仍然生效，页面依旧会跳转到 /login.jsp，无法达到替代效果
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
     * // TODO: angus on 2021/5/6 待处理用户角色和权限动态变化问题，设置缓存过期/刷新
     *
     * @return
     */
    // TODO: 2019/4/26 改为 Redis 托管
//    @Bean
//    protected CacheManager cacheManager() {
//        return new MemoryConstrainedCacheManager();
//    }

}
