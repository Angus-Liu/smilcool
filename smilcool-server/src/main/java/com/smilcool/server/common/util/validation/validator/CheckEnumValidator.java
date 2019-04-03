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
     * 可枚举值
     */
    private CheckEnum.EnumType enumType;

    @Override
    public void initialize(CheckEnum checkEnum) {
        // 获取可枚举值
        this.enumType = checkEnum.value();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        // 空值应由 @NotNull 注解校验
        if (value == null) {
            return true;
        }
        // 可枚举的值为空或值不在可枚举值中报错
        return enumType.enums != null && enumType.enums.contains(value);
    }
}
