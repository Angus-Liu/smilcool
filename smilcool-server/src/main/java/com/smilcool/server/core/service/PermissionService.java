package com.smilcool.server.core.service;

import com.smilcool.server.core.pojo.form.PermissionAddForm;
import com.smilcool.server.core.pojo.vo.PermissionVO;

import java.util.List;

/**
 * @author Angus
 * @date 2019/3/29
 */
public interface PermissionService {
    List<PermissionVO> list();

    PermissionVO add(PermissionAddForm permissionAddForm);
}
