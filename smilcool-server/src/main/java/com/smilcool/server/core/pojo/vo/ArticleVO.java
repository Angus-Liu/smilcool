package com.smilcool.server.core.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author Angus
 * @date 2019/4/29
 */
@Data
public class ArticleVO {
    private Integer id;

    private Integer userId;

    // TODO 发布用户信息

    private Integer resourceId;

    private Integer resourceTypeId;

    private String title;

    private String brief;

    private String label;

    private String cover;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private String markdownContent;

    private String htmlContent;
}
