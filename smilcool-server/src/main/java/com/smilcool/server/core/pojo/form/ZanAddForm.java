package com.smilcool.server.core.pojo.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Angus
 * @date 2019/5/22
 */
@Data
public class ZanAddForm {
    @NotNull(message = "资源ID不能为空")
    private Integer resourceId;
}
