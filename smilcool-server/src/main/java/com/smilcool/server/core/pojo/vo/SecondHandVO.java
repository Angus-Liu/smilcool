package com.smilcool.server.core.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class SecondHandVO {
    private Integer id;

    private Integer userId;

    private Integer resourceId;

    private Integer resourceTypeId;

    private String title;

    private String name;

    private Double price;

    private String address;

    private String image;

    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}