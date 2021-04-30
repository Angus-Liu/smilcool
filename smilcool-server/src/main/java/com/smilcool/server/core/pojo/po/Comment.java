package com.smilcool.server.core.pojo.po;

import lombok.Data;

import java.util.Date;

@Data
public class Comment {
    private Integer id;

    private Integer parentId;

    private Integer userId;

    private Integer resourceId;

    private Integer replyUserId;

    private String content;

    private String remark;

    private Date createTime;

    private Date updateTime;

    private Boolean deleted;
}