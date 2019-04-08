package com.smilcool.server.core.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.smilcool.server.common.util.serializer.CommonStateSerializer;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author Angus
 */
@Data
public class PermissionVO {
    private Integer id;

    private Integer parentId;

    private String name;

    private String description;

    private String url;

    private Integer type;

    private Integer seq;

    @JsonSerialize(using = CommonStateSerializer.class)
    private Integer state;

    private String remark;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private Boolean deleted;

    private List<PermissionVO> children;
}