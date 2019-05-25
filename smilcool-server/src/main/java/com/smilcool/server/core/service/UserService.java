package com.smilcool.server.core.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smilcool.server.core.pojo.form.UserLoginForm;
import com.smilcool.server.core.pojo.form.UserQueryForm;
import com.smilcool.server.core.pojo.form.UserRegisterForm;
import com.smilcool.server.core.pojo.form.UserUpdateForm;
import com.smilcool.server.core.pojo.po.User;
import com.smilcool.server.core.pojo.vo.UserVO;

import java.util.List;
import java.util.Set;

/**
 * @author Angus
 * @date 2019/3/20
 */
public interface UserService {
    Integer currentUserId();

    User getCurrentUser();

    void checkExist(Integer id);

    User getUser(Integer id);

    /**
     * 通过用户名和密码获取用户
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    User getUser(String username, String password);

    UserVO getUserVO(Integer id);


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
     * 用户注册
     *
     * @param form 用户注册表单的那
     * @return 用户信息
     */
    UserVO register(UserRegisterForm form);

    UserVO updateUser(UserUpdateForm form);

    List<UserVO> listUserVO();

    Page<UserVO> pageUserVO(Page page, UserQueryForm form);

    Set<String> getRoles(Integer id);

    Set<String> getRoles(String username);

    Set<String> getPermissions(Integer id);

    Set<String> getPermissions(String username);

}
