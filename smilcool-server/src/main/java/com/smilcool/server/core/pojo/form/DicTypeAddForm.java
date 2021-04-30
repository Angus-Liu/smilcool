package com.smilcool.server.core.pojo.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DicTypeAddForm {
    @NotBlank(message = "字典类型名不能为空")
    private String name;

    @NotBlank(message = "字典类型码不能为空")
    private String code;

    private Integer seq;

    private Integer state;

    private String remark;
}
