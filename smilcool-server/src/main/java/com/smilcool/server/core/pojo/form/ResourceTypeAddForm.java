package com.smilcool.server.core.pojo.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Angus
 * @date 2019/4/12
 */
@Data
public class ResourceTypeAddForm {
    private Integer parentId;

    @NotBlank(message = "资源类型名不能为空")
    private String name;

    @NotBlank(message = "资源类型标签不能为空")
    private String tag;

    @NotBlank(message = "资源类型描述不能为空")
    private String description;

    private Integer seq;

    private Integer state;

    private String remark;
}
