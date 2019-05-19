package com.smilcool.server.core.controller.admin;

import com.smilcool.server.core.pojo.dto.Result;
import com.smilcool.server.core.pojo.form.UserRoleAddForm;
import com.smilcool.server.core.pojo.vo.UserRoleVO;
import com.smilcool.server.core.service.UserRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Angus
 * @date 2019/3/29
 */
@Api(tags = "1.4", description = "用户角色接口")
@RestController
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    @ApiOperation("用户角色列表")
    @GetMapping("/user-role")
    public Result<List<UserRoleVO>> list() {
        List<UserRoleVO> userRoleList = userRoleService.list();
        return Result.success(userRoleList);
    }

    @ApiOperation("用户角色添加")
    @PostMapping("/user-role")
    public Result<UserRoleVO> add(@RequestBody @Valid UserRoleAddForm userRoleAddForm) {
        UserRoleVO userRole = userRoleService.add(userRoleAddForm);
        return Result.success(userRole);
    }
}
