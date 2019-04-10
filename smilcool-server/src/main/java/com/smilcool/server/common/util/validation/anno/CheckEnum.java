package com.smilcool.server.common.util.validation.anno;

import com.smilcool.server.common.enums.CommonState;
import com.smilcool.server.common.enums.PermissionType;
import com.smilcool.server.common.enums.UserState;
import com.smilcool.server.common.util.validation.validator.CheckEnumValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.HashSet;
import java.util.Set;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Angus
 * @date 2019/4/1
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {CheckEnumValidator.class})
public @interface CheckEnum {

    /**
     * 添加枚举的原因在于，注解的属性不支持变量，原本的想法是让 CheckEnum 的 value 为 int[] 型，
     * 但在使用时 @CheckEnum(value = CommonState.enums(),...) 会报错，故而设置一个枚举作为中专站
     */
    enum EnumType {
        EMPTY(new HashSet<>(), "枚举字段非法"),
        COMMON_STATE(CommonState.enums(), "状态取值为[0-停用，1-启用]"),
        USER_STATE(UserState.enums(), "状态取值为[0-停用，1-启用，2-未激活]"),
        PERMISSION_TYPE(PermissionType.enums(), "类型取值为[0-菜单，1-按钮，2-其他]");

        /**
         * 可枚举值集合
         */
        public Set<?> enums;

        public String msg;

        EnumType(Set<?> enums, String msg) {
            this.enums = enums;
            this.msg = msg;
        }
    }

    // 枚举类型
    EnumType value() default EnumType.EMPTY;

    String message() default "枚举字段非法";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
