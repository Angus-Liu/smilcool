package com.smilcool.server.core.pojo.po;

import lombok.Data;

import java.util.Date;

@Data
public class Message {
    private Integer id;

    private Integer sendUserId;

    private Integer receiveUserId;

    private Integer type;

    private Integer state;

    private Date createTime;

    private Date updateTime;

    private Boolean deleted;

    private String content;
}