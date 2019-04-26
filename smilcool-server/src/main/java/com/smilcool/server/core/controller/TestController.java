package com.smilcool.server.core.controller;

import com.smilcool.server.core.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Angus
 * @date 2019/4/26
 */
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

}
