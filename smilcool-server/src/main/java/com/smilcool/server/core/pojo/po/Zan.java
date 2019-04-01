package com.smilcool.server.core.pojo.po;

import lombok.Data;

import java.util.Date;

@Data
public class Zan {
    private Integer id;

    private Integer userId;

    private Integer resourceId;

    private Integer state;

    private String remark;

    private Date createTime;

    private Date updateTime;

    private Boolean deleted;
}