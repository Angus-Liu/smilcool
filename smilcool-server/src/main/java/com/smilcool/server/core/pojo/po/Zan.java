package com.smilcool.server.core.pojo.po;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Zan {
    private Integer id;

    private Integer userId;

    private Integer resourceId;

    private Integer state;

    private String remark;

    private Date createTime;

    private Date updateTime;

    private Boolean deleted;

    public Zan(Integer userId, Integer resourceId) {
        this.userId = userId;
        this.resourceId = resourceId;
    }
}