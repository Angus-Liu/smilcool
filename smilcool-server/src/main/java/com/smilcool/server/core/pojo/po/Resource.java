package com.smilcool.server.core.pojo.po;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Resource {
    private Integer id;

    private Integer userId;

    private String resourceDicType;

    private String resourceDicItem;

    private Integer zanCount;

    private Integer pvCount;

    private Integer commentCount;

    private Integer state;

    private String remark;

    private Date createTime;

    private Date updateTime;

    private Boolean deleted;

    public Resource(Integer userId, String resourceDicType, String resourceDicItem) {
        this.userId = userId;
        this.resourceDicType = resourceDicType;
        this.resourceDicItem = resourceDicItem;
    }
}