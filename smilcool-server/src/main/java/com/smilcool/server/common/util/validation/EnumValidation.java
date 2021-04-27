package com.smilcool.server.common.util.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 自定义校验注解，用于校验字段对应值属于枚举类型值的情况
 *
 * @see <a href="https://javaee.github.io/tutorial/bean-validation-advanced001.html">Creating Custom Constraints</a>
 */
@Documented
@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = {EnumValidator.class})
public @interface EnumValidation {

    Class<? extends Enum<?>> value();

    String field() default "name";

    String message() default "invalid enum field";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
