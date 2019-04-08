package com.smilcool.server.core.pojo.form;

import com.smilcool.server.common.util.validation.anno.CheckEnum;
import com.smilcool.server.common.util.validation.anno.CheckEnum.EnumType;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Angus
 * @date 2019/3/29
 */
@Data
public class PermissionAddForm {
    private Integer parentId;

    @NotBlank(message = "权限名不能为空")
    private String name;

    @NotBlank(message = "权限描述不能为空")
    private String description;

    @NotBlank(message = "请求地址不能为空")
    private String url;

    @CheckEnum(value = EnumType.PERMISSION_TYPE, message = "类型取值为[0-菜单，1-按钮，2-其他]")
    private Integer type;

    private Integer seq;

    private String remark;
}
