package com.smilcool.server.core.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author Angus
 * @date 2019/5/3
 */
@Data
public class MomentVO {
    private Integer id;

    private Integer userId;

    private Integer resourceId;

    private Integer resourceTypeId;

    private String content;

    private String images;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
