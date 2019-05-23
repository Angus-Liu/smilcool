package com.smilcool.server.core.pojo.po;

import lombok.Data;

import java.util.Date;

@Data
public class SecondHand {
    private Integer id;

    private Integer userId;

    private Integer resourceId;

    private String secondHandCategory;

    private String title;

    private String name;

    private Double price;

    private String address;

    private String description;

    private String remark;

    private Date createTime;

    private Date updateTime;

    private Boolean deleted;

    private String images;
}