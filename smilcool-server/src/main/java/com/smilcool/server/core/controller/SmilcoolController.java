package com.smilcool.server.core.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Angus
 * @date 2019/3/20
 */
@RestController
public class SmilcoolController {

    @RequestMapping("/smilcool")
    public String smilcool(String username, String password) {
        return "Hello, " + username + "! This is Smilcool. Your password is " + password;
    }
}
