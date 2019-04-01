package com.smilcool.server.core.controller;

import com.smilcool.server.common.dto.Result;
import io.swagger.annotations.Api;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 用以解决 Vue Router History 模式刷新问题（暂时）
 * https://router.vuejs.org/zh/guide/essentials/history-mode.html
 *
 * @author Angus
 * @date 2019/3/31
 */
@Api(description = "错误拦截接口", tags = {"5.0"}, hidden = true)
@RestController
public class ErrorsController implements ErrorController {
    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/error")
    public Result handleError(HttpServletRequest request) {
        // 获取 statusCode：401、403、404、500
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        return Result.error(statusCode, "Oops！服务器无法处理你的请求 :(");
    }
}

