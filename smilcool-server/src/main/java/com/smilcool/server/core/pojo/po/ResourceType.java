package com.smilcool.server.core.pojo.po;

import lombok.Data;

import java.util.Date;

@Data
public class ResourceType {
    private Integer id;

    private Integer parentId;

    private String name;

    private String tag;

    private String description;

    private Integer seq;

    private Integer state;

    private String remark;

    private Date createTime;

    private Date updateTime;

    private Boolean deleted;
}