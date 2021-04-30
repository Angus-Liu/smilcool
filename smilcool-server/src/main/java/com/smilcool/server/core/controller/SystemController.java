package com.smilcool.server.core.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 系统接口
 */
@RestController
@RequestMapping("/sys")
public class SystemController {

    @Value("${smilcool.netty-port}")
    private int port;

    /**
     * 获取 WebSocket Server URL
     */
    @GetMapping("/ws")
    public String webSocketServerUrl() throws UnknownHostException {
        return "ws://" + InetAddress.getLocalHost().getHostAddress() + ":" + port + "/ws";
    }

}
