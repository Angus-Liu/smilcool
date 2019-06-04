package com.smilcool.server.core.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.smilcool.server.common.util.serializer.CommonStateSerializer;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class RoleVO {
    private Integer id;

    private String name;

    private String description;

    private Boolean initial;

    private Integer state;

    private Boolean fixed;

    private String remark;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 权限信息
     */
    private List<Integer> permissionIdList;
}