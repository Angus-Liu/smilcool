package com.smilcool.server.common.util.validation;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

/**
 * 自定义校验器，用于校验字段对应值属于枚举类型值的情况
 *
 * @see <a href="https://javaee.github.io/tutorial/bean-validation-advanced001.html">Creating Custom Constraints</a>
 */
@Slf4j
public class EnumValidator implements ConstraintValidator<EnumValidation, Object> {
    private Class<? extends Enum<?>> enumClass;
    private Field validatedField;

    @SneakyThrows
    @Override
    public void initialize(EnumValidation validation) {
        enumClass = validation.value();
        String fieldName = validation.field();
        if (fieldName.equals("name") || fieldName.equals("ordinal")) {
            validatedField = Enum.class.getDeclaredField(fieldName);
        } else {
            validatedField = enumClass.getDeclaredField(fieldName);
        }
        validatedField.setAccessible(true);
    }

    @SneakyThrows
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        for (Enum<?> e : enumClass.getEnumConstants()) {
            if (value.equals(validatedField.get(e))) return true;
        }
        return false;
    }
}
