package com.smilcool.server.core.pojo.po;

import lombok.Data;

import java.util.Date;

@Data
public class DicItem {
    private Integer id;

    private String dicTypeCode;

    private String name;

    private String code;

    private Integer seq;

    private Boolean fixed;

    private Integer state;

    private String remark;

    private Date createTime;

    private Date updateTime;

    private Boolean deleted;
}