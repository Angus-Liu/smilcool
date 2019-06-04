package com.smilcool.server.core.service;

import com.smilcool.server.core.pojo.form.RoleAddForm;
import com.smilcool.server.core.pojo.form.RoleUpdateForm;
import com.smilcool.server.core.pojo.po.Role;
import com.smilcool.server.core.pojo.vo.RoleVO;

import java.util.List;

/**
 * @author Angus
 * @date 2019/3/28
 */
public interface RoleService {

    void verify(Integer id);

    /**
     * 获取默认角色
     *
     * @return
     */
    Role getInitialRole();

    RoleVO getRoleVO(Integer id);

    RoleVO add(RoleAddForm form);

    List<RoleVO> listRoleVO();

    RoleVO update(RoleUpdateForm form);
}
