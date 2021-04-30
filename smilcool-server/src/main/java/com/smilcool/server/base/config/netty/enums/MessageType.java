package com.smilcool.server.base.config.netty.enums;

import lombok.AllArgsConstructor;

import java.util.NoSuchElementException;

/**
 * WebSocket 传输的内容实体对应的动作枚举
 */
@AllArgsConstructor
public enum MessageType {

    /**
     * 连接消息
     */
    CONNECT(0),
    /**
     * 聊天消息
     */
    CHAT(1),
    /**
     * 签收消息
     */
    SIGNED(2),
    /**
     * 心跳消息
     */
    KEEP_ALIVE(3),
    /**
     * 拉取好友消息
     */
    PULL_FRIEND(4),
    ;

    public final int type;

    public static MessageType of(int type) {
        for (MessageType value : MessageType.values()) {
            if (value.type == type) {
                return value;
            }
        }
        throw new NoSuchElementException("没有该消息类型：type = " + type);
    }
}
