package com.smilcool.server.core.pojo.po;

import lombok.Data;

import java.util.Date;

@Data
public class Friend {
    private Integer id;

    private Integer userId;

    private Integer friendUserId;

    private Date createTime;

    private Boolean deleted;

    public Friend(Integer userId, Integer friendUserId) {
        this.userId = userId;
        this.friendUserId = friendUserId;
    }
}