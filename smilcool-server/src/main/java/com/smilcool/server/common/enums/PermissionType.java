package com.smilcool.server.common.enums;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Angus
 * @date 2019/3/29
 */
public enum PermissionType {
    MENU(0, "菜单"),
    BUTTON(1, "按钮"),
    OTHER(2, "其他"),
    INVALID(100, "无效类型");

    int type;
    String desc;

    public static final int[] x = {2};

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

    public static Set<Integer> enums() {
        Set<Integer> typeSet = new HashSet<>();
        Arrays.stream(PermissionType.values())
                .filter(value -> value != INVALID)
                .forEach(value -> typeSet.add(value.type));
        return typeSet;
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
