package com.smilcool.server.common.util.validation;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

/**
 * 自定义
 */
@Slf4j
public class EnumValidator implements ConstraintValidator<EnumValidation, Object> {

    private EnumValidation validation;

    @Override
    public void initialize(EnumValidation validation) {
        this.validation = validation;
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try {
            Field field = validation.value().getDeclaredField(validation.field());
            field.setAccessible(true);
            for (Enum<?> e : validation.value().getEnumConstants()) {
                if (value.equals(field.get(e))) {
                    return true;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
