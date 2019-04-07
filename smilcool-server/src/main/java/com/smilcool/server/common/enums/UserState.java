package com.smilcool.server.common.enums;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 状态：0-未激活，1-正常，2-停用
 *
 * @author Angus
 * @date 2019/3/20
 */
public enum UserState {
    INACTIVATED(0, "未激活"),
    NORMAL(1, "正常"),
    DISABLE(2, "停用"),
    INVALID(100, "无效状态");

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

    public static Set<Integer> enums() {
        Set<Integer> stateSet = new HashSet<>();
        Arrays.stream(UserState.values())
                .filter(value -> value != INVALID)
                .forEach(value -> stateSet.add(value.state));
        return stateSet;
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
