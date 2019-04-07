package com.smilcool.server.common.enums;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Angus
 * @date 2019/3/21
 */
public enum CommonState {
    Disable(0, "停用"),
    Normal(1, "正常"),
    INVALID(100, "无效状态"),
    ;

    int state;
    String desc;

    CommonState(int state, String desc) {
        this.state = state;
        this.desc = desc;
    }

    public static CommonState of(int state) {
        for (CommonState commonState : CommonState.values()) {
            if (commonState.state == state) {
                return commonState;
            }
        }
        return INVALID;
    }

    public static Set<Integer> enums() {
        Set<Integer> stateSet = new HashSet<>();
        Arrays.stream(CommonState.values())
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
