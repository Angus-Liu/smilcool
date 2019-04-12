package com.smilcool.server.core.pojo.form;

import com.smilcool.server.common.util.validation.anno.CheckEnum;
import com.smilcool.server.common.util.validation.anno.CheckEnum.EnumType;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RuleMapAddForm {
    @NotBlank(message = "请求地址不能为空")
    private String url;

    @NotBlank(message = "描述不能为空")
    private String description;

    @CheckEnum(value = EnumType.PERMISSION_TYPE, message = "类型取值为[0-菜单，1-按钮，2-其他]")
    private Integer type;

    private Boolean authc;

    private Boolean useRoles;

    private String roles;

    private Boolean usePerms;

    private String perms;

    private Boolean useRest;

    private String rest;

    private Integer seq;

    private Integer state;

    private String remark;
}