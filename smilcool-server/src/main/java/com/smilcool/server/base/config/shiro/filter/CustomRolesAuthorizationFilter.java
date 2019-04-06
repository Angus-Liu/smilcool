package com.smilcool.server.base.config.shiro.filter;

import com.smilcool.server.common.dto.Result;
import com.smilcool.server.common.util.HttpServletResponseUtil;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 原 RolesAuthorizationFilter 要求当前用户具有映射值指定的角色，否则
 * 拒绝访问，根据用户登录情况重定向到 loginUrl 或 unauthorizedUrl。这
 * 里重写 RolesAuthorizationFilter 的 onAccessDenied 方法，在角色验证
 * 未通过时，直接向前端返回错误信息，不进行重定向
 *
 * @author Angus
 * @date 2019/4/6
 */
public class CustomRolesAuthorizationFilter extends RolesAuthorizationFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        HttpServletResponseUtil.sendJson((HttpServletResponse) response,
                HttpServletResponse.SC_FORBIDDEN,
                Result.error(403, "用户无相关角色，禁止访问"));
        return false;
    }
}
