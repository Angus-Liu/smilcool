package com.smilcool.server.base.config.netty.enums;

import java.util.NoSuchElementException;

/**
 * @author Angus
 * @date 2019/5/8
 */
public enum MessageState {
    UNSIGNED(0, "未签收"),
    SIGNED(1, "已签收"),
    ;

    public final int state;
    public final String description;

    MessageState(int state, String description) {
        this.state = state;
        this.description = description;
    }

    public static MessageState of(int state) {
        for (MessageState value : MessageState.values()) {
            if (value.state == state) {
                return value;
            }
        }
        throw new NoSuchElementException("没有该签收状态：state = " + state);
    }
}
