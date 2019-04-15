package com.smilcool.server.core.pojo.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SecondHandAddForm {
    @NotNull(message = "发布用户ID不能为空")
    private Integer userId;

    @NotNull(message = "资源类型ID不能为空")
    private Integer resourceTypeId;

    @NotBlank(message = "标题不能为空")
    private String title;

    @NotBlank(message = "物品名称不能为空")
    private String name;

    private Double price;

    private String address;

    private String image;

    private String description;
}