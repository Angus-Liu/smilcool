package com.smilcool.server.common.enums;

import java.util.Arrays;

/**
 * @author Angus
 * @date 2019/3/21
 */
public enum CommonState {
    Normal(1, "正常"),
    Disable(-1, "停用"),
    INVALID(-100, "无效状态"),
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

    public static boolean check(int state) {
        for (CommonState commonState : CommonState.values()) {
            if (commonState.state == state) {
                return true;
            }
        }
        return false;
    }

    public static int[] enums() {
        return Arrays.stream(CommonState.values())
                .filter(value -> value != INVALID)
                .mapToInt(CommonState::getState)
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
