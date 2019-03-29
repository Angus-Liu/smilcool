package com.smilcool.server.core.pojo.form;

import lombok.Data;

/**
 * 用户登录表单
 *
 * @author Angus
 * @date 2019/3/28
 */
@Data
public class UserLoginForm {
    String username;
    String password;
}
