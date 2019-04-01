package com.smilcool.server.core.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.smilcool.server.common.util.serializer.CommonStateSerializer;
import lombok.Data;

import java.util.Date;

/**
 * @author Angus
 * @date 2019/3/29
 */
@Data
public class UserRoleVO {
    private Integer id;

    private Integer userId;

    private Integer roleId;

    @JsonSerialize(using = CommonStateSerializer.class)
    private Integer state;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private Boolean deleted;
}
