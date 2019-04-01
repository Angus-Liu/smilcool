package com.smilcool.server.core.pojo.po;

import lombok.Data;

import java.util.Date;

@Data
public class Permission {
    private Integer id;

    private Integer parentId;

    private String name;

    private String description;

    private String url;

    private Integer type;

    private Integer seq;

    private Integer state;

    private String remark;

    private Date createTime;

    private Date updateTime;

    private Boolean deleted;
}