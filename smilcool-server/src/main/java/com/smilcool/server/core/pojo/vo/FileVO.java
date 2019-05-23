package com.smilcool.server.core.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author Angus
 * @date 2019/5/18
 */
@Data
public class FileVO {
    private Integer id;

    private Integer userId;

    private Integer resourceId;

    private String fileCategory;

    private String title;

    private String description;

    private String name;

    private String size;

    private String url;

    private Integer downloadCount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 发布用户信息
     */
    private UserVO user;

    /**
     * 关联资源信息
     */
    private ResourceVO resource;
}