package com.smilcool.server.core.pojo.form;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * 用户注册表单
 *
 * @author Angus
 * @date 2019/3/29
 */
@Data
public class UserLogonForm {

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式错误")
    private String email;

    @NotBlank(message = "密码不能为空")
    private String password;
}
