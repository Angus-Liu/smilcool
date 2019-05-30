package com.smilcool.server.core.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Angus
 * @date 2019/5/20
 */
@Api(tags = "4.0", description = "系统接口")
@RestController
@RequestMapping("/sys")
public class SystemController {

    @Value("${smilcool.netty-port}")
    private int port;

    @ApiOperation("获取 WebSocket Server URL")
    @GetMapping("/ws")
    public String webSocketServerUrl() throws UnknownHostException {
        return "ws://" + InetAddress.getLocalHost().getHostAddress() + ":" + port + "/ws";
    }

}
