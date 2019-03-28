package com.smilcool.server.core.controller;

import com.smilcool.server.common.dto.Result;
import com.smilcool.server.common.util.BindingResultUtil;
import com.smilcool.server.core.pojo.form.RoleAddForm;
import com.smilcool.server.core.pojo.vo.RoleVO;
import com.smilcool.server.core.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Angus
 * @date 2019/3/28
 */
@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/roles")
    public Result add(@RequestBody @Valid RoleAddForm roleAddForm, BindingResult bindingResult) {
        BindingResultUtil.validate(bindingResult);
        RoleVO roleVO = roleService.add(roleAddForm);
        return Result.success(roleVO);
    }

    @GetMapping("/roles")
    public Result list() {
        List<RoleVO> roles = roleService.list();
        return Result.success(roles);
    }

}
