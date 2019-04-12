package com.smilcool.server.core.pojo.form;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Angus
 * @date 2019/4/12
 */
@Data
@AllArgsConstructor
public class ResourceAddForm {
    @NotNull(message = "发布用户ID不能为空")
    private Integer userId;

    @NotNull(message = "资源类型ID不能为空")
    private Integer resourceTypeId;
}
