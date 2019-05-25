package com.smilcool.server.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smilcool.server.core.pojo.form.UserUpdateForm;
import com.smilcool.server.core.pojo.po.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper extends BaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /* 以下是自定义内容 */

    int updateByPrimaryKeyAndForm( @Param("id") Integer id, @Param("form") UserUpdateForm form);

    User selectByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    User selectByUsername(String username);

    User selectByEmail(String email);

    List<User> select();

    List<Integer> selectUserIdListAll();

    Page<User> selectByCondition(Page page, @Param("user") User condition);
}