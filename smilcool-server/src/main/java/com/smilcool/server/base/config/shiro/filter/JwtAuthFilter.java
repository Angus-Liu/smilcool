package com.smilcool.server.base.config.shiro.filter;

import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * JWT 拦截器
 * https://www.jianshu.com/p/3c51832f1051
 *
 * @author Angus
 * @date 2019/5/1
 */
public class JwtAuthFilter extends BasicHttpAuthenticationFilter {

//    @Override
//    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
//        // 判断请求的请求头是否带上 "Token"
//        if (((HttpServletRequest) request).getHeader("Token") != null) {
//            // 如果存在，则进入 executeLogin 方法执行登入，检查 token 是否正确
//            try {
//                executeLogin(request, response);
//                return true;
//            } catch (Exception e) {
//                //token 错误
//                responseError(response, e.getMessage());
//            }
//        }
//        //如果请求头不存在 Token，则可能是执行登陆操作或者是游客状态访问，无需检查 token，直接返回 true
//        return true;
//    }
//
//    @Override
//    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
//        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//        String token = httpServletRequest.getHeader("Token");
//        JWTToken jwtToken = new JWTToken(token);
//        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
//        getSubject(request, response).login(jwtToken);
//        // 如果没有抛出异常则代表登入成功，返回true
//        return true;
//    }
}
