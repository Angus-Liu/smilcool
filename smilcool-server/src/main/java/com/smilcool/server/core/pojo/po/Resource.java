package com.smilcool.server.core.pojo.po;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Resource {
    private Integer id;

    private Integer userId;

    private Integer resourceTypeId;

    private Integer zan;

    private Integer pv;

    private Integer state;

    private String remark;

    private Date createTime;

    private Date updateTime;

    private Boolean deleted;

    public Resource(Integer userId, Integer resourceTypeId) {
        this.userId = userId;
        this.resourceTypeId = resourceTypeId;
    }
}