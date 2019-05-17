package com.smilcool.server.core.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author Angus
 * @date 2019/5/17
 */
@Data
public class LostFoundVO {
    private Integer id;

    private Integer userId;

    private Integer resourceId;

    private String lostFoundCategory;

    private String title;

    private String description;

    private String itemName;

    private Date time;

    private String address;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String images;
}
