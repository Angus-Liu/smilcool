package com.smilcool.server.core.dao;

import com.smilcool.server.SmilcoolServerApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * @author Angus
 * @date 2019/4/5
 */
public class RolePermissionMapperTest extends SmilcoolServerApplicationTests {
    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Test
    public void testSelectPermissionName() {
        Set<String> permissionNameSet = rolePermissionMapper.selectPermissionNameByUsername("admin");
        System.out.println("permissionNameSet = " + permissionNameSet);
    }
}