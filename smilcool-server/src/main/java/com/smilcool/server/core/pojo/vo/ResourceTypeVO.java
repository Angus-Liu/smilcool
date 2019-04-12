package com.smilcool.server.core.pojo.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ResourceTypeVO {
    private Integer id;

    private Integer parentId;

    private String name;

    private String url;

    private String description;

    private Integer seq;

    private Integer state;

    private String remark;

    private Date createTime;

    private Date updateTime;

    private Boolean deleted;

    private List<ResourceTypeVO> children;
}