package com.smilcool.server.base.config.netty.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum MessageState {
    UNSIGNED(0, "未签收"),
    SIGNED(1, "已签收"),
    ;

    public final int state;
    public final String description;
}
