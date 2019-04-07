package com.smilcool.server.core.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smilcool.server.common.dto.Result;
import com.smilcool.server.core.pojo.form.UserLoginForm;
import com.smilcool.server.core.pojo.form.UserRegisterForm;
import com.smilcool.server.core.pojo.form.UserSearchForm;
import com.smilcool.server.core.pojo.vo.UserVO;
import com.smilcool.server.core.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Angus
 * @date 2019/3/20
 */
@Slf4j
@Api(description = "用户接口", tags = {"1.1"})
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation("用户登录")
    @PostMapping("/user/login")
    public Result<UserVO> login(@RequestBody @Valid UserLoginForm userLoginForm) {
        UserVO user = userService.login(userLoginForm);
        return Result.success(user);
    }

    @ApiOperation("用户注销")
    @PostMapping("/user/logout")
    public Result logout() {
        userService.logout();
        return Result.success();
    }

    @ApiOperation("用户注册")
    @PostMapping("/user/register")
    public Result<UserVO> register(@RequestBody @Valid UserRegisterForm userRegisterForm) {
        UserVO registerUser = userService.register(userRegisterForm);
        return Result.success(registerUser);
    }

    @ApiOperation("用户列表")
    @GetMapping("/user")
    public Result<List<UserVO>> getUserList() {
        List<UserVO> userListPage = userService.getUsers();
        return Result.success(userListPage);
    }

    @ApiOperation("用户列表（分页条件查询）")
    @GetMapping("/user/page")
    public Result<Page<UserVO>> getUserPage(Page page, UserSearchForm userSearchForm) {
        Page<UserVO> userListPage = userService.getUsers(page, userSearchForm);
        return Result.success(userListPage);
    }

    @ApiOperation(value = "用户信息", notes = "通过用户 id 获取用户信息（包括角色与权限信息）")
    @GetMapping("/user/{id}")
    public Result<UserVO> get(@PathVariable("id") Integer id) {
        UserVO user = userService.get(id);
        return Result.success(user);
    }
}
