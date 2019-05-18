package com.smilcool.server.core.pojo.vo;

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

    private Date createTime;
}
