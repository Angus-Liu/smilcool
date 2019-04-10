package com.smilcool.server.core.pojo.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RuleMap {
    private Integer id;

    private String url;

    private String description;

    private Integer type;

    private Boolean authc;

    private Boolean useRoles;

    private String roles;

    private Boolean usePerms;

    private String perms;

    private Boolean useRest;

    private String rest;

    private Integer seq;

    private Integer state;

    private String remark;

    private Date createTime;

    private Date updateTime;

    private Boolean deleted;
}