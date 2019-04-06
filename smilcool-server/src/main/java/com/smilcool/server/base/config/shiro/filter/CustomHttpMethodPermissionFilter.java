package com.smilcool.server.base.config.shiro.filter;

import com.smilcool.server.common.dto.Result;
import com.smilcool.server.common.util.HttpServletResponseUtil;
import org.apache.shiro.web.filter.authz.HttpMethodPermissionFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Angus
 * @date 2019/4/6
 */
public class CustomHttpMethodPermissionFilter extends HttpMethodPermissionFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        HttpServletResponseUtil.sendJson((HttpServletResponse) response,
                HttpServletResponse.SC_FORBIDDEN,
                Result.error(403, "用户无相关 REST 权限，禁止访问"));
        return false;
    }
}
