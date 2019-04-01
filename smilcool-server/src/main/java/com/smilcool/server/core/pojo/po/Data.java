package com.smilcool.server.core.pojo.po;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@lombok.Data
public class Data {
    private Integer id;

    private Integer resourceId;

    private String title;

    private String name;

    private String size;

    private String url;

    private Integer downloads;

    private String description;

    private String remark;

    private Date createTime;

    private Date updateTime;

    private Boolean deleted;
}