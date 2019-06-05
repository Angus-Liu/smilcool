package com.smilcool.server.core.pojo.po;

import lombok.Data;

import java.util.Date;

@Data
public class SysParam {
    private Integer id;

    private String name;

    private String value;

    private Integer seq;

    private Boolean fixed;

    private Integer state;

    private String remark;

    private Date createTime;

    private Date updateTime;

    private Boolean deleted;
}