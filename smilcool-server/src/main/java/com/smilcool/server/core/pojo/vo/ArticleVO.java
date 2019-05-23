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

    private Integer resourceId;

    private String articleCategory;

    private String title;

    private String brief;

    private String cover;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    private String tags;

    private String markdownContent;

    private String htmlContent;

    /**
     * 发布用户信息
     */
    private UserVO user;

    /**
     * 关联资源信息
     */
    private ResourceVO resource;
}
