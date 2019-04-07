package com.smilcool.server.core.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smilcool.server.core.pojo.form.UserLoginForm;
import com.smilcool.server.core.pojo.form.UserRegisterForm;
import com.smilcool.server.core.pojo.form.UserSearchForm;
import com.smilcool.server.core.pojo.po.User;
import com.smilcool.server.core.pojo.vo.UserVO;

import java.util.List;
import java.util.Set;

/**
 * @author Angus
 * @date 2019/3/20
 */
public interface UserService {

    void checkExist(Integer id);

    UserVO get(Integer id);

    /**
     * 用户注册
     *
     * @param userLoginForm 用户登录表单
     * @return 用户信息
     */
    UserVO login(UserLoginForm userLoginForm);

    /**
     * 用户注销
     */
    void logout();

    /**
     * 通过用户名和密码获取用户
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    User get(String username, String password);

    /**
     * 用户注册
     *
     * @param userRegisterForm 用户注册表单的那
     * @return 用户信息
     */
    UserVO register(UserRegisterForm userRegisterForm);

    List<UserVO> getUsers();

    Page<UserVO> getUsers(Page page, UserSearchForm userSearchForm);

    Set<String> getRoles(Integer id);

    Set<String> getRoles(String username);

    Set<String> getPermissions(Integer id);

    Set<String> getPermissions(String username);
}
