package com.smilcool.server.common.enums;

/**
 * @author Angus
 * @date 2019/3/29
 */
public enum RoleEnum {
    NORMAL(0, "普通用户"),
    SUPER_ADMIN(1, "超级管理员"),
    ADMIN(2, "管理员");

    Integer id;
    String name;

    RoleEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
