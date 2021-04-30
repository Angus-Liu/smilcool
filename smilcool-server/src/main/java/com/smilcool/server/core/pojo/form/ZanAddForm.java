package com.smilcool.server.core.pojo.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ZanAddForm {
    @NotNull(message = "资源ID不能为空")
    private Integer resourceId;
}
