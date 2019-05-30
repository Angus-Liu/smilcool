package com.smilcool.server.core.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author Angus
 * @date 2019/5/13
 */
@Data
public class DicItemVO {
    private Integer id;

    private String dicTypeCode;

    private String name;

    private String code;

    private Integer seq;

    private Boolean fixed;

    private Integer state;

    private String remark;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private Boolean deleted;
}
