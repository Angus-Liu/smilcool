package com.smilcool.server.core.pojo.po;

import lombok.Data;

import java.util.Date;

@Data
public class Article {
    private Integer id;

    private Integer userId;

    private Integer resourceId;

    private String articleCategory;

    private String title;

    private String brief;

    private String cover;

    private String remark;

    private Date createTime;

    private Date updateTime;

    private Boolean deleted;

    private String tags;

    private String markdownContent;

    private String htmlContent;
}