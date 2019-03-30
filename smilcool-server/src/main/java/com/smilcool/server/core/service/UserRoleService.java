package com.smilcool.server.core.service;

import com.smilcool.server.core.pojo.form.UserRoleAddForm;
import com.smilcool.server.core.pojo.po.Role;
import com.smilcool.server.core.pojo.vo.UserRoleVO;

import java.util.List;

/**
 * @author Angus
 * @date 2019/3/29
 */
public interface UserRoleService {

    List<Role> getRoleByUserId(Integer userId);

    List<UserRoleVO> list();

    UserRoleVO add(UserRoleAddForm userRoleAddForm);
}
