package com.smilcool.server.core.controller;

import com.smilcool.server.common.dto.Result;
import com.smilcool.server.common.pojo.vo.UserVO;
import com.smilcool.server.core.model.User;
import com.smilcool.server.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
