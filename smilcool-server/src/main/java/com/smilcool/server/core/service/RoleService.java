package com.smilcool.server.core.service;

import com.smilcool.server.core.pojo.form.RoleAddForm;
import com.smilcool.server.core.pojo.vo.RoleVO;

import java.util.List;

/**
 * @author Angus
 * @date 2019/3/28
 */
public interface RoleService {

    void checkExist(Integer id);

    RoleVO getById(Integer id);

    RoleVO add(RoleAddForm roleAddForm);

    List<RoleVO> list();
}
