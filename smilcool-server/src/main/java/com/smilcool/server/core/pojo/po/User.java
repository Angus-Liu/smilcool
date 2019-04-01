package com.smilcool.server.core.pojo.po;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer id;

    private String username;

    private String password;

    private String nickname;

    private String avatar;

    private String sex;

    private Date birthday;

    private String sign;

    private String intro;

    private String grade;

    private String college;

    private String major;

    private String phone;

    private String email;

    private Integer state;

    private String remark;

    private Date createTime;

    private Date updateTime;

    private Boolean deleted;
}