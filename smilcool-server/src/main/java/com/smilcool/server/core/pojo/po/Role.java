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
public class Role {
    private Integer id;

    private String name;

    private String description;

    private Boolean initial;

    private Integer state;

    private Boolean fixed;

    private String remark;

    private Date createTime;

    private Date updateTime;

    private Boolean deleted;
}