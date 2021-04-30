package com.smilcool.server.core.pojo.po;

import lombok.Data;

import java.util.Date;

@Data
public class File {
    private Integer id;

    private Integer userId;

    private Integer resourceId;

    private String fileCategory;

    private String title;

    private String description;

    private String name;

    private String size;

    private String url;

    private Integer downloadCount;

    private String remark;

    private Date createTime;

    private Date updateTime;

    private Boolean deleted;
}