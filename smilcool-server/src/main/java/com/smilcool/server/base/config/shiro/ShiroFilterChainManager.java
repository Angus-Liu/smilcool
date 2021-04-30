package com.smilcool.server.base.config.shiro;

import com.smilcool.server.base.config.shiro.util.ShiroUtils;
import com.smilcool.server.core.pojo.po.RuleMap;
import com.smilcool.server.core.service.RuleMapService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Shiro FilterChain Manager
 */
@Slf4j
@Component
@AllArgsConstructor
public class ShiroFilterChainManager {

    private final RuleMapService ruleMapService;
    private final ShiroFilterFactoryBean shiroFilterFactoryBean;

    /**
     * 刷新过滤器链映射
     */
    @SneakyThrows
    public void refreshFilterChains() {
        // 从数据库获取规则映射
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        List<RuleMap> ruleMapList = ruleMapService.getRuleMapList();
        // 构建新的过滤器链映射
        ruleMapList.forEach(ruleMap -> filterChainDefinitionMap.put(ruleMap.getUrl(), ShiroUtils.buildRule(ruleMap)));
        log.info("filterChainDefinitionMap: {}", filterChainDefinitionMap);
        // 更新
        AbstractShiroFilter shiroFilter = (AbstractShiroFilter) Objects.requireNonNull(shiroFilterFactoryBean.getObject());
        // 获取过滤管理器
        PathMatchingFilterChainResolver filterChainResolver =
                (PathMatchingFilterChainResolver) shiroFilter.getFilterChainResolver();
        DefaultFilterChainManager filterManager =
                (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();
        // 清空旧有过滤器链映射
        filterManager.getFilterChains().clear();
        // 构建新的过滤器链映射
        filterChainDefinitionMap.forEach(filterManager::createChain);
    }
}
