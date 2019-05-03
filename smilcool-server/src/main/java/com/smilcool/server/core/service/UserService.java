package com.smilcool.server.core.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smilcool.server.core.pojo.form.UserLoginForm;
import com.smilcool.server.core.pojo.form.UserQueryForm;
import com.smilcool.server.core.pojo.form.UserRegisterForm;
import com.smilcool.server.core.pojo.po.User;
import com.smilcool.server.core.pojo.vo.UserDetailInfo;
import com.smilcool.server.core.pojo.vo.UserSimpleInfo;

import java.util.List;
import java.util.Set;

/**
 * @author Angus
 * @date 2019/3/20
 */
public interface UserService {
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

    UserSimpleInfo getUserSimpleInfo(Integer id);

    UserDetailInfo getUserTotalInfo(Integer id);


    /**
     * 用户注册
     *
     * @param userLoginForm 用户登录表单
     * @return 用户信息
     */
    UserDetailInfo login(UserLoginForm userLoginForm);

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
    UserDetailInfo register(UserRegisterForm form);

    List<UserDetailInfo> getUsers();

    Page<UserDetailInfo> getUsers(Page page, UserQueryForm form);

    Set<String> getRoles(Integer id);

    Set<String> getRoles(String username);

    Set<String> getPermissions(Integer id);

    Set<String> getPermissions(String username);
}
