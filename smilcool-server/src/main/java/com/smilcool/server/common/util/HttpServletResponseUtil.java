package com.smilcool.server.common.util;

import cn.hutool.json.JSONUtil;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Angus
 * @date 2019/4/6
 */
public class HttpServletResponseUtil {
    /**
     * 响应客户端状态 JSON 数据
     *
     * @param response
     * @param status
     * @param body
     */
    public static void sendJson(HttpServletResponse response, int status, Object body) {
        response.setStatus(status);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        try (PrintWriter writer = response.getWriter()) {
            writer.print(JSONUtil.toJsonStr(body));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
