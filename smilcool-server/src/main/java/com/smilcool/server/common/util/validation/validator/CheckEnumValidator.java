package com.smilcool.server.common.util.validation.validator;

import com.smilcool.server.common.enums.CommonState;
import com.smilcool.server.common.enums.PermissionType;
import com.smilcool.server.common.enums.UserState;
import com.smilcool.server.common.util.validation.anno.CheckEnum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;
import java.util.Set;

/**
 * @author Angus
 * @date 2019/4/1
 */
public class CheckEnumValidator implements ConstraintValidator<CheckEnum, Integer> {

    /**
     * 可枚举值集合
     */
    private Set<?> enums;

    @Override
    public void initialize(CheckEnum checkEnum) {
        // 获取枚举类型
        CheckEnum.EnumType value = checkEnum.value();
        // 获取枚举类型值对应数组
        this.enums = value.enums;
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        // 空值应由 @NotNull 注解校验
        if (value == null) {
            return true;
        }
        // 可枚举的值为空直接报错
        if (enums == null) {
            return false;
        }
        return enums.contains(value);
    }
}
