package com.smilcool.server.core.pojo.po;

import lombok.Data;

import java.util.Date;

@Data
public class Role {
    private Integer id;

    private String name;

    private String description;

    private Boolean initial;

    private Integer state;

    private Boolean fixed;

    private String remark;

    private Date createTime;

    private Date updateTime;

    private Boolean deleted;
}