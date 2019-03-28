package com.smilcool.server.core.pojo.po;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Role {
    private Integer id;

    private String name;

    private String description;

    private Integer state;

    private String remark;

    private Date createTime;

    private Date updateTime;

    private Boolean isDel;
}