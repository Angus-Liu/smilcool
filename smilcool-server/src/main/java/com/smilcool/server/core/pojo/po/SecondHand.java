package com.smilcool.server.core.pojo.po;

import lombok.Data;

import java.util.Date;

@Data
public class SecondHand {
    private Integer id;

    private Integer resourceId;

    private String title;

    private String name;

    private Double price;

    private String address;

    private String image;

    private String description;

    private String remark;

    private Date createTime;

    private Date updateTime;

    private Boolean deleted;
}