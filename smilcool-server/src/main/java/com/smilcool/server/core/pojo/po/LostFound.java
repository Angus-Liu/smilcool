package com.smilcool.server.core.pojo.po;

import lombok.Data;

import java.util.Date;

@Data
public class LostFound {
    private Integer id;

    private Integer resourceId;

    private Integer type;

    private String title;

    private String name;

    private Date time;

    private String address;

    private String image;

    private String description;

    private String remark;

    private Date createTime;

    private Date updateTime;

    private Boolean deleted;
}