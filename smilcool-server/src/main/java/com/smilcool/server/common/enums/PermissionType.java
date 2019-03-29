package com.smilcool.server.common.enums;

/**
 * @author Angus
 * @date 2019/3/29
 */
public enum PermissionType {
    MENU(1, "菜单"),
    BUTTON(2, "按钮"),
    OTHER(3, "其他"),
    INVALID(-100, "无效类型");

    int type;
    String desc;

    PermissionType(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public static PermissionType of(int type) {
        for (PermissionType permissionType : PermissionType.values()) {
            if (permissionType.type == type) {
                return permissionType;
            }
        }
        return INVALID;
    }

    public static String desc(int type) {
        return of(type).desc;
    }

    public int getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}
