package com.smilcool.server.base.config.shiro.filter;

import com.smilcool.server.common.dto.Result;
import com.smilcool.server.common.util.HttpServletResponseUtil;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.HttpMethodPermissionFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 原 HttpMethodPermissionFilter 要求用户具有映射值及 HTTP 方法对应权限，
 * 否则拒绝访问，根据用户登录情况重定向到 loginUrl 或 unauthorizedUrl。未
 * 设置 unauthorizedUrl 时，会直接向返回 401 Unauthorized 错误。这里重写
 * HttpMethodPermissionFilter 的 onAccessDenied 方法，在权限验证未通过
 * 时，直接向前端返回错误信息，不进行重定向
 * <br/>
 * 表示 HTTP 方法的操作：
 * POST -> CREATE_ACTION = "create";
 * GET ->   READ_ACTION = "read";
 * PUT -> UPDATE_ACTION = "update";
 * DELETE -> DELETE_ACTION = "delete";
 * <br/>
 * 例：
 * /user - rest[user]
 * ANY /user - user
 * POST /user - user:create
 * GET  /user - user:read
 * PUT  /user - user:update
 * DELETE /user - user:delete
 *
 * @author Angus
 * @date 2019/4/6
 */
public class CustomHttpMethodPermissionFilter extends HttpMethodPermissionFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        Subject subject = getSubject(request, response);
        if (subject.getPrincipal() == null) {
            HttpServletResponseUtil.sendJson((HttpServletResponse) response,
                    HttpServletResponse.SC_UNAUTHORIZED,
                    Result.error(401, "身份验证失败，请重新登录"));
        } else {
            HttpServletResponseUtil.sendJson((HttpServletResponse) response,
                    HttpServletResponse.SC_FORBIDDEN,
                    Result.error(403, "用户无相关 HTTP 方法权限，禁止访问"));
        }
        return false;
    }
}
