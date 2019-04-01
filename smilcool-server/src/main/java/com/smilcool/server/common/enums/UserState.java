package com.smilcool.server.common.enums;

import java.util.Arrays;

/**
 * 状态：0-未激活，1-正常，-1-停用
 *
 * @author Angus
 * @date 2019/3/20
 */
public enum UserState {
    INACTIVATED(0, "未激活"),
    Normal(1, "正常"),
    Disable(-1, "停用"),
    INVALID(-100, "无效状态");

    int state;
    String desc;

    UserState(int state, String desc) {
        this.state = state;
        this.desc = desc;
    }

    public static UserState of(int state) {
        for (UserState userState : UserState.values()) {
            if (userState.state == state) {
                return userState;
            }
        }
        return INVALID;
    }

    public static boolean check(int state) {
        for (UserState userState : UserState.values()) {
            if (userState.state == state) {
                return true;
            }
        }
        return false;
    }

    public static int[] enums() {
        return Arrays.stream(UserState.values())
                .filter(value -> value != INVALID)
                .mapToInt(UserState::getState)
                .toArray();
    }

    public static String desc(int state) {
        return of(state).desc;
    }

    public int getState() {
        return state;
    }

    public String getDesc() {
        return desc;
    }
}
