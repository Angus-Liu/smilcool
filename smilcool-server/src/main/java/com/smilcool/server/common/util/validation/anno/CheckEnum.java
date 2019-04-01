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

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
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
        EMPTY(new int[]{}),
        COMMON_STATE(CommonState.enums()),
        USER_STATE(UserState.enums()),
        PERMISSION_TYPE(PermissionType.enums());

        public int[] enums;

        EnumType(int[] enums) {
            this.enums = enums;
        }
    }

    // 枚举类型
    EnumType value() default EnumType.EMPTY;

    String message() default "枚举字段非法";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
