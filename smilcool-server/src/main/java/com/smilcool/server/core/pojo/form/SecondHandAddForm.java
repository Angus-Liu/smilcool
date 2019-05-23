package com.smilcool.server.core.pojo.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Angus
 * @date 2019/4/10
 */
@Data
public class SecondHandAddForm {
    @NotBlank(message = "二手交易类别不能为空")
    private String secondHandCategory;

    @NotBlank(message = "标题不能为空")
    private String title;

    @NotBlank(message = "物品名称不能为空")
    private String name;

    @NotNull(message = "物品价格不能为空")
    private Double price;

    @NotBlank(message = "交易地址不能为空")
    private String address;

    @NotBlank(message = "物品图片不能为空")
    private String images;

    @NotBlank(message = "物品描述不能为空")
    private String description;
}