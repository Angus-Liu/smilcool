package com.smilcool.server.core.pojo.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SecondHandAddForm {
    @NotNull(message = "二手类别不能为空")
    private String secondHandCategory;

    @NotBlank(message = "标题不能为空")
    private String title;

    @NotBlank(message = "物品名称不能为空")
    private String name;

    private Double price;

    private String address;

    private String image;

    private String description;
}