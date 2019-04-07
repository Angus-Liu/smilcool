package com.smilcool.server.core.pojo.form;

import com.smilcool.server.common.util.validation.anno.CheckEnum;
import com.smilcool.server.common.util.validation.anno.CheckEnum.EnumType;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Angus
 * @date 2019/4/1
 */
@Data
public class RoleUpdateForm {
    /**
     * 角色名
     */
    @NotBlank(message = "角色名不能为空")
    String name;

    /**
     * 角色描述
     */
    @NotBlank(message = "角色描述不能为空")
    String description;

    @CheckEnum(value = EnumType.COMMON_STATE, message = "状态取值为[0-停用，1-启用]")
    Integer state;

    /**
     * 备注
     */
    String remark;
}
