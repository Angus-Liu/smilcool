package com.smilcool.server.base.config.shiro.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 原 RolesAuthorizationFilter 要求当前用户具有映射值指定的角色，否则拒绝访问，根据用户登录
 * 情况重定向到 loginUrl 或 unauthorizedUrl。未设置 unauthorizedUrl 时，会直接返回 401
 * Unauthorized 错误。这里重写 RolesAuthorizationFilter 的 onAccessDenied 方法，在角
 * 色验证未通过时，直接向前端返回错误信息，不进行重定向
 */
public class CustomRolesAuthorizationFilter extends RolesAuthorizationFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        Subject subject = getSubject(request, response);
        if (!subject.isAuthenticated()) {
            WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            WebUtils.toHttp(response).sendError(HttpServletResponse.SC_FORBIDDEN, "用户无相关角色，禁止访问");
        }
        return false;
    }
}
