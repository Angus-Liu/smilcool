package com.smilcool.server.base.config.shiro.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 原 PermissionsAuthorizationFilter 要求当前用户具有映射值指定的权限，否则拒绝访问，根据
 * 用户登录情况重定向到 loginUrl 或 unauthorizedUrl。未设置 unauthorizedUrl 时，会直接
 * 返回 401 Unauthorized 错误。这里重写 PermissionsAuthorizationFilter 的 onAccessDenied
 * 方法，在权限验证未通过时，直接向前端返回错误信息，不进行重定向
 */
public class CustomPermissionsAuthorizationFilter extends PermissionsAuthorizationFilter {
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        Subject subject = getSubject(request, response);
        if (!subject.isAuthenticated()) {
            WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            WebUtils.toHttp(response).sendError(HttpServletResponse.SC_FORBIDDEN, "用户无相关权限，禁止访问");
        }
        return false;
    }
}
