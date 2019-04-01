package com.smilcool.server.core.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.smilcool.server.common.util.serializer.CommonStateSerializer;
import lombok.Data;

import java.util.Date;

@Data
public class RolePermissionVO {
    private Integer id;

    private Integer roleId;

    private Integer permissionId;

    @JsonSerialize(using = CommonStateSerializer.class)
    private Integer state;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private Boolean deleted;
}