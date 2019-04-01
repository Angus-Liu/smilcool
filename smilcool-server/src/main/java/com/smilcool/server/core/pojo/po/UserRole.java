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
public class UserRole {
    private Integer id;

    private Integer userId;

    private Integer roleId;

    private Integer state;

    private Date createTime;

    private Date updateTime;

    private Boolean deleted;

    public UserRole(Integer userId) {
        this.userId = userId;
    }

    public UserRole(Integer userId, Integer roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }
}