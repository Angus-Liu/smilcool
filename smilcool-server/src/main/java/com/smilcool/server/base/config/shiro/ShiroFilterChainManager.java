package com.smilcool.server.base.config.shiro;

import com.smilcool.server.base.config.shiro.util.ShiroUtil;
import com.smilcool.server.common.exception.SmilcoolException;
import com.smilcool.server.core.pojo.po.RuleMap;
import com.smilcool.server.core.service.RuleMapService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Angus
 * @date 2019/6/3
 */
@Slf4j
@Component
public class ShiroFilterChainManager {

    @Autowired
    private RuleMapService ruleMapService;

    @Autowired
    private ShiroFilterFactoryBean shiroFilterFactoryBean;

    /**
     * 刷新过滤器链映射
     */
    public void refreshFilterChains() {
        // 从数据库获取规则映射
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        List<RuleMap> ruleMapList = ruleMapService.getRuleMapList();
        // 构建新的过滤器链映射
        ruleMapList.forEach(ruleMap -> filterChainDefinitionMap.put(ruleMap.getUrl(), ShiroUtil.buildRule(ruleMap)));
        log.info("filterChainDefinitionMap: {}", filterChainDefinitionMap);
        // 更新
        try {
            AbstractShiroFilter shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean.getObject();
            // 获取过滤管理器
            PathMatchingFilterChainResolver filterChainResolver =
                    (PathMatchingFilterChainResolver) shiroFilter.getFilterChainResolver();
            DefaultFilterChainManager filterManager =
                    (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();
            // 清空旧有过滤器链映射
            filterManager.getFilterChains().clear();
            // 构建新的过滤器链映射
            filterChainDefinitionMap.forEach(filterManager::createChain);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SmilcoolException("过滤器链映射刷新失败");
        }
    }
}
