package com.smilcool.server.base.config.shiro;

import com.smilcool.server.base.config.shiro.filter.CustomFormAuthenticationFilter;
import com.smilcool.server.base.config.shiro.filter.CustomHttpMethodPermissionFilter;
import com.smilcool.server.base.config.shiro.filter.CustomPermissionsAuthorizationFilter;
import com.smilcool.server.base.config.shiro.filter.CustomRolesAuthorizationFilter;
import com.smilcool.server.base.config.shiro.realm.CustomAuthorizingRealm;
import com.smilcool.server.core.pojo.po.RuleMap;
import com.smilcool.server.core.service.RuleMapService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.filter.authz.HttpMethodPermissionFilter;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author Angus
 * @date 2019/4/5
 */
@Slf4j
@Configuration
public class ShiroConfig {

    @Autowired
    private RuleMapService ruleMapService;

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
     * 配置过滤器链映射
     *
     * @return
     */
    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        // 从数据库获取规则映射
        List<RuleMap> ruleMapList = ruleMapService.getRuleMapList();
        ruleMapList.forEach(ruleMap -> chainDefinition.addPathDefinition(ruleMap.getUrl(), buildRule(ruleMap)));
        return chainDefinition;
    }

    /**
     * 构建过滤规则
     *
     * @param ruleMap
     * @return
     */
    private String buildRule(RuleMap ruleMap) {
        StringBuilder rule = new StringBuilder();
        // 是否需要身份验证
        if (ruleMap.getAuthc()) {
            rule.append("authc");
        } else {
            rule.append("anon");
        }
        // 是否需要角色验证
        if (ruleMap.getUseRoles()) {
            rule.append(",roles[").append(ruleMap.getRoles()).append("]");
        }
        // 是否需要权限验证
        if (ruleMap.getUsePerms()) {
            rule.append(",perms[").append(ruleMap.getPerms()).append("]");
        }
        // 是否需要 HTTP 方法（REST）验证
        if (ruleMap.getUseRest()) {
            rule.append(",rest[").append(ruleMap.getRest()).append("]");
        }
        return rule.toString();
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

    /**
     * 原 FormAuthenticationFilter 过滤器对应标识为 authc
     *
     * @return
     */
    @Bean
    public FormAuthenticationFilter authc() {
        return new CustomFormAuthenticationFilter();
    }

    /**
     * 原 RolesAuthorizationFilter 过滤器对应标识为 roles
     *
     * @return
     */
    @Bean
    public RolesAuthorizationFilter roles() {
        return new CustomRolesAuthorizationFilter();
    }

    /**
     * 原 PermissionsAuthorizationFilter 过滤器对应标识为 perms
     *
     * @return
     */
    @Bean
    public PermissionsAuthorizationFilter perms() {
        return new CustomPermissionsAuthorizationFilter();
    }

    /**
     * 原 HttpMethodPermissionFilter 过滤器对应标识为 rest
     *
     * @return
     */
    @Bean
    public HttpMethodPermissionFilter rest() {
        return new CustomHttpMethodPermissionFilter();
    }
}
