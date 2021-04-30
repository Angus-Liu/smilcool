package com.smilcool.server.core.pojo.po;

import lombok.Data;

import java.util.Date;

@Data
public class DicType {
    private Integer id;

    private String name;

    private String code;

    private Integer seq;

    private Integer state;

    private String remark;

    private Date createTime;

    private Date updateTime;

    private Boolean deleted;
}