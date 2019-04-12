package com.smilcool.server.core.service;

import com.smilcool.server.core.pojo.form.PermissionAddForm;
import com.smilcool.server.core.pojo.vo.PermissionVO;

import java.util.List;

/**
 * @author Angus
 * @date 2019/3/29
 */
public interface PermissionService {
    void checkExist(Integer id);

    List<PermissionVO> getPermissionList(Integer parentId);

    PermissionVO add(PermissionAddForm form);
}
