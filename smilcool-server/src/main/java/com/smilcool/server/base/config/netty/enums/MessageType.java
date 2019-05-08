package com.smilcool.server.base.config.netty.enums;

import java.util.NoSuchElementException;

/**
 * Websocket 传输的内容实体对应的动作枚举
 *
 * @author Angus
 * @date 2018/12/20
 */
public enum MessageType {

    CONNECT(0, "连接消息"),
    CHAT(1, "聊天消息"),
    SIGNED(2, "签收消息"),
    KEEP_ALIVE(3, "心跳消息"),
    PULL_FRIEND(4, "拉取好友消息"),
    ;

    public final int type;
    public final String description;

    MessageType(int type, String description) {
        this.type = type;
        this.description = description;
    }

    public static MessageType of(int type) {
        for (MessageType value : MessageType.values()) {
            if (value.type == type) {
                return value;
            }
        }
        throw new NoSuchElementException("没有该消息类型：type = " + type);
    }
}
