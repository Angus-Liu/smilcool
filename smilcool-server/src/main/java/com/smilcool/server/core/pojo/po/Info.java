package com.smilcool.server.core.pojo.po;

import lombok.Data;

import java.util.Date;

@Data
public class Info {
    private Integer id;

    private Integer resourceId;

    private String title;

    private String image;

    private String remark;

    private Date createTime;

    private Date updateTime;

    private Boolean deleted;

    private String content;
}