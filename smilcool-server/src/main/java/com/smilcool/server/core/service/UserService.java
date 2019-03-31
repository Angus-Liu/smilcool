package com.smilcool.server.core.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smilcool.server.core.pojo.form.UserLoginForm;
import com.smilcool.server.core.pojo.form.UserRegisterForm;
import com.smilcool.server.core.pojo.form.UserSearchForm;
import com.smilcool.server.core.pojo.vo.UserVO;

import java.util.List;

/**
 * @author Angus
 * @date 2019/3/20
 */
public interface UserService {

    void checkExist(Integer id);

    UserVO getById(Integer id);

    /**
     * 用户注册
     *
     * @param userLoginForm 用户登录表单
     * @return 用户信息
     */
    UserVO login(UserLoginForm userLoginForm);

    /**
     * 用户注册
     *
     * @param userRegisterForm 用户注册表单的那
     * @return 用户信息
     */
    UserVO register(UserRegisterForm userRegisterForm);

    Page<UserVO> getUsers();

    Page<UserVO> getUsersByCondition(Page page, UserSearchForm userSearchForm);
}
