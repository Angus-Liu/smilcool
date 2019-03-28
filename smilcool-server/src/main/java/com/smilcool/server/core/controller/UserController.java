package com.smilcool.server.core.controller;

import com.smilcool.server.common.dto.Result;
import com.smilcool.server.core.pojo.vo.UserVO;
import com.smilcool.server.common.util.BindingResultUtil;
import com.smilcool.server.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
        UserVO loginUser = userService.login(username, password);
        return Result.success(loginUser);
    }

    @PostMapping("/register")
    public Result register(@RequestBody @Valid UserVO userVO, BindingResult bindingResult) {
        BindingResultUtil.validate(bindingResult);
        UserVO registerUser = userService.register(userVO);
        return Result.success(registerUser);
    }

    @GetMapping("/list")
    public Result list() {
        List<UserVO> users = userService.list();
        return Result.success(users);
    }
}
