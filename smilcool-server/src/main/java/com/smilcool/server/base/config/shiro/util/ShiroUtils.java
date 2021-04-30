package com.smilcool.server.base.config.shiro.util;

import com.smilcool.server.core.pojo.po.RuleMap;

/**
 * Shiro Utils
 */
public class ShiroUtils {
    /**
     * 构建过滤规则
     */
    public static String buildRule(RuleMap ruleMap) {
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
}
