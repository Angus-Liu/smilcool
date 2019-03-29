package com.smilcool.server.core.pojo.vo;

import lombok.Data;

import java.util.Date;

@Data
public class RolePermissionVO {
    private Integer id;

    private Integer roleId;

    private Integer permissionId;

    private Integer state;

    private Date createTime;

    private Date updateTime;

    private Boolean isDel;
}