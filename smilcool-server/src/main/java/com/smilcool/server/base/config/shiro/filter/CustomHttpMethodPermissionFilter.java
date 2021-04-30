package com.smilcool.server.base.config.shiro.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.HttpMethodPermissionFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 原 HttpMethodPermissionFilter 要求用户具有映射值及 HTTP 方法对应权限，否则拒绝访问，根
 * 据用户登录情况重定向到 loginUrl 或 unauthorizedUrl。未设置 unauthorizedUrl 时，会直接
 * 向返回 401 Unauthorized 错误。这里重写 HttpMethodPermissionFilter 的 onAccessDenied
 * 方法，在权限验证未通过时，直接向前端返回错误信息，不进行重定向
 * <br/>
 * 表示 HTTP 方法的操作：
 * <li>POST -> CREATE_ACTION = "create";</li>
 * <li>GET ->   READ_ACTION = "read";</li>
 * <li>PUT -> UPDATE_ACTION = "update";</li>
 * <li>DELETE -> DELETE_ACTION = "delete";</li>
 * <br/>
 * 例：
 * <li>/user - rest[user]</li>
 * <li>ANY /user - user</li>
 * <li>POST /user - user:create</li>
 * <li>GET  /user - user:read</li>
 * <li>PUT  /user - user:update</li>
 * <li>DELETE /user - user:delete</li>
 */
public class CustomHttpMethodPermissionFilter extends HttpMethodPermissionFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        Subject subject = getSubject(request, response);
        if (!subject.isAuthenticated()) {
            WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            WebUtils.toHttp(response).sendError(HttpServletResponse.SC_FORBIDDEN, "用户无相关 HTTP 方法权限，禁止访问");
        }
        return false;
    }
}
