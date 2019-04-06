package com.smilcool.server.base.config.shiro.filter;

import com.smilcool.server.common.dto.Result;
import com.smilcool.server.common.util.HttpServletResponseUtil;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * 原 FromAuthenticationFilter 要求用户通过身份验证以继续请求，如果未通过，
 * 则重定向到配置的 loginUrl 来登录。在前后端分离的项目中，页面跳转应该是前
 * 端来控制的，所以这里重写 FormAuthenticationFilter 的 onAccessDenied 方
 * 法，在身份验证未通过时，直接向前端返回错误信息，不进行重定向
 *
 * @author Angus
 * @date 2019/4/6
 */
public class CustomFormAuthenticationFilter extends FormAuthenticationFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponseUtil.sendJson((HttpServletResponse) response,
                HttpServletResponse.SC_UNAUTHORIZED,
                Result.error(401, "身份验证失败，请重新登录"));
        return false;
    }
}
