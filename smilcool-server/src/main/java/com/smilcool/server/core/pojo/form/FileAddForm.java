package com.smilcool.server.core.pojo.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Angus
 * @date 2019/5/12
 */
@Data
public class FileAddForm {
    @NotBlank(message = "文件类型不能为空")
    private String fileCategory;

    @NotBlank(message = "标题不能为空")
    private String title;

    @NotBlank(message = "描述不能为空")
    private String description;

    @NotBlank(message = "文件名不能为空")
    private String name;

    @NotBlank(message = "文件大小不能为空")
    private String size;

    @NotBlank(message = "文件链接不能为空")
    private String url;
}
