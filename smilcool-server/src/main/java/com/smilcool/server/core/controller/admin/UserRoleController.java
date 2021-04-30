package com.smilcool.server.core.controller.admin;

import com.smilcool.server.core.pojo.dto.Result;
import com.smilcool.server.core.pojo.form.UserRoleAddForm;
import com.smilcool.server.core.pojo.vo.UserRoleVO;
import com.smilcool.server.core.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * 用户角色接口
 */
@AllArgsConstructor
@RestController
public class UserRoleController {

    private final UserService userService;

    /**
     * 用户角色列表
     */
    @GetMapping("/user-role")
    public Result<List<UserRoleVO>> list() {
        List<UserRoleVO> userRoleList = userService.list();
        return Result.success(userRoleList);
    }

    /**
     * 用户角色添加
     */
    @PostMapping("/user-role")
    public Result<UserRoleVO> add(@RequestBody @Valid UserRoleAddForm userRoleAddForm) {
        UserRoleVO userRole = userService.add(userRoleAddForm);
        return Result.success(userRole);
    }
}
