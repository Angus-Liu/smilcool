package com.smilcool.server.core.pojo.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;


/**
 * 用户登录表单
 *
 * @author Angus
 * @date 2019/3/28
 */
@Data
public class UserLoginForm {
    @NotBlank(message = "用户名不能为空")
    String username;
    @NotBlank(message = "密码不能为空")
    String password;
}
