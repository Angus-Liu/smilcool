package com.smilcool.server.core.controller;

import com.smilcool.server.core.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Angus
 * @date 2019/4/26
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/cache")
    public Object getCache(@RequestParam String key) {
        Object res = testService.getCache(key);
        return res;
    }

    @PutMapping("/cache")
    public Object updateCache(@RequestParam String key) {
        Object res = testService.updateCache(key);
        return res;
    }

    @GetMapping("/ws")
    public String wsurl(@Value("${netty.port}") Integer port) throws UnknownHostException {
        log.info("LocalHost: {}", InetAddress.getLocalHost());
        return "ws://" + InetAddress.getLocalHost().getHostAddress() + ":" + port + "/ws";
    }

}
