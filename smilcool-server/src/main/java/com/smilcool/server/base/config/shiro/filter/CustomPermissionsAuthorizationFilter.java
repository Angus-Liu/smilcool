package com.smilcool.server.base.config.shiro.filter;

import com.smilcool.server.common.dto.Result;
import com.smilcool.server.common.util.HttpServletResponseUtil;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 原 PermissionsAuthorizationFilter 要求当前用户具有映射值指定的权限，
 * 否则拒绝访问，根据用户登录情况重定向到 loginUrl 或 unauthorizedUrl。
 * 未设置 unauthorizedUrl 时，会直接向返回 401 Unauthorized 错误。这里
 * 重写 PermissionsAuthorizationFilter 的 onAccessDenied 方法，在权限
 * 验证未通过时，直接向前端返回错误信息，不进行重定向
 *
 * @author Angus
 * @date 2019/4/6
 */
public class CustomPermissionsAuthorizationFilter extends PermissionsAuthorizationFilter {
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        HttpServletResponseUtil.sendJson((HttpServletResponse) response,
                HttpServletResponse.SC_FORBIDDEN,
                Result.error(403, "用户无相关权限，禁止访问"));
        return false;
    }
}
