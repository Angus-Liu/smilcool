package com.smilcool.server.core.service;

import com.smilcool.server.core.pojo.form.UserRoleAddForm;
import com.smilcool.server.core.pojo.po.Role;
import com.smilcool.server.core.pojo.vo.UserRoleVO;

import java.util.List;
import java.util.Set;

/**
 * @author Angus
 * @date 2019/3/29
 */
public interface UserRoleService {

    /**
     * 获取角色
     *
     * @param userId 用户 id
     * @return 角色列表
     */
    List<Role> getRoles(Integer userId);

    /**
     * 获取角色名
     *
     * @param userId 用户 ID
     * @return 角色名集合
     */
    Set<String> getRoleNames(Integer userId);

    /**
     * 获取角色名
     *
     * @param username 用户名
     * @return 角色名集合
     */
    Set<String> getRoleNames(String username);

    /**
     * 获取角色描述
     *
     * @param userId 用户 id
     * @return 角色描述集合
     */
    Set<String> getRoleDescriptions(Integer userId);

    List<UserRoleVO> list();

    UserRoleVO add(UserRoleAddForm userRoleAddForm);

    /**
     * 为用户添加默认角色
     *
     * @param userId
     */
    void addInitialRole(Integer userId);
}
