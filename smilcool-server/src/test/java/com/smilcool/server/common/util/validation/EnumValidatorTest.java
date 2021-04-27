package com.smilcool.server.common.util.validation;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Set;
import java.util.stream.Collectors;

public class EnumValidatorTest {

    @AllArgsConstructor
    enum SexEnum {
        MAN(0),
        WOMEN(1);

        private final int value;
    }

    @AllArgsConstructor
    static class User {
        @NotBlank
        @EnumValidation(value = SexEnum.class)
        private final String name;

        @Min(0) @Max(120)
        private final int age;

        @EnumValidation(value = SexEnum.class, field = "value")
        private final int sex;
    }

    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();


    @Test
    public void testEnumValidate() {
        User u1 = new User("", -1, -1);
        User u2 = new User("NotBlank", -1, -1);
        User u3 = new User("NotBlank", 200, 1);
        User u4 = new User("MAN", 10, 0);

        Lists.newArrayList(u1, u2, u3, u4).forEach(
                u -> {
                    Set<ConstraintViolation<User>> constraintViolations = validator.validate(u);
                    if (constraintViolations.isEmpty()) {
                        System.out.println("validate success.");
                    } else {
                        String validationMsg = constraintViolations.stream()
                                .map(ConstraintViolation::getMessage)
                                .collect(Collectors.joining(", "));
                        System.out.println("validate fail: " + validationMsg);
                    }
                    System.out.println("---- split ----");
                }
        );
    }
}