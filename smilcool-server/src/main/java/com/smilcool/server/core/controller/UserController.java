package com.smilcool.server.core.controller;

import com.smilcool.server.common.dto.Result;
import com.smilcool.server.core.pojo.form.UserLoginForm;
import com.smilcool.server.core.pojo.form.UserRegisterForm;
import com.smilcool.server.core.pojo.vo.UserVO;
import com.smilcool.server.common.util.BindingResultUtil;
import com.smilcool.server.core.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Angus
 * @date 2019/3/20
 */
@Api(description = "用户接口")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public Result<UserVO> login(@RequestBody UserLoginForm userLoginForm) {
        UserVO loginUser = userService.login(userLoginForm);
        return Result.success(loginUser);
    }

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public Result<UserVO> register(@RequestBody @Valid UserRegisterForm userRegisterForm, BindingResult bindingResult) {
        BindingResultUtil.validate(bindingResult);
        UserVO registerUser = userService.register(userRegisterForm);
        return Result.success(registerUser);
    }

    @ApiOperation("用户列表")
    @GetMapping("/list")
    public Result<List<UserVO>> list() {
        List<UserVO> userList = userService.list();
        return Result.success(userList);
    }
}
