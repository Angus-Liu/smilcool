package com.smilcool.server.core.controller;

import com.smilcool.server.common.dto.Result;
import com.smilcool.server.core.pojo.vo.UserVO;
import com.smilcool.server.common.util.BindingResultUtil;
import com.smilcool.server.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Angus
 * @date 2019/3/20
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/login")
    public Result login(String username, String password) {
        UserVO userVO = userService.login(username, password);
        return Result.success(userVO);
    }

    @PostMapping("/register")
    public Result register(@RequestBody @Valid UserVO userVO, BindingResult bindingResult) {
        BindingResultUtil.validate(bindingResult);
        userService.register(userVO);
        return Result.success();
    }
}
