package com.smilcool.server.core.pojo.po;

import lombok.Data;

import java.util.Date;

@Data
public class Moment {
    private Integer id;

    private Integer userId;

    private Integer resourceId;

    private String momentCategory;

    private String content;

    private String remark;

    private Date createTime;

    private Date updateTime;

    private Boolean deleted;

    private String images;
}