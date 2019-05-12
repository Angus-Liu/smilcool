package com.smilcool.server.core.pojo.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Angus
 * @date 2019/5/12
 */
@Data
public class FileAddForm {
    @NotNull(message = "发布用户ID不能为空")
    private Integer userId;

    @NotNull(message = "资源类型ID不能为空")
    private Integer resourceTypeId;

    @NotBlank(message = "标题不能为空")
    private String title;

    @NotBlank(message = "文件名不能为空")
    private String name;

    @NotBlank(message = "文件大小不能为空")
    private String size;

    @NotBlank(message = "文件链接不能为空")
    private String url;

    private String description;
}
