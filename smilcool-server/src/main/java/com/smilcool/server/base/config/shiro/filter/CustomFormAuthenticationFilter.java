package com.smilcool.server.base.config.shiro.filter;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * 原 FromAuthenticationFilter 要求用户通过身份验证以继续请求，如果未通过，则重定向到配置的
 * loginUrl（默认值为 login.jsp）来登录。在前后端分离的项目中，页面跳转应该是前端来控制的，所
 * 以这里重写 FormAuthenticationFilter 的 onAccessDenied 方法，在身份验证未通过时，直接
 * 向前端返回错误信息，不进行重定向
 */
public class CustomFormAuthenticationFilter extends FormAuthenticationFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
        return false;
    }
}
