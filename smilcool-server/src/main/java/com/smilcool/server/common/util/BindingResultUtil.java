package com.smilcool.server.common.util;

import com.smilcool.server.common.exception.SmilcoolException;
import org.springframework.validation.BindingResult;

import java.util.Optional;

/**
 * @author Angus
 * @date 2019/3/20
 */
public class BindingResultUtil {
    /**
     * 字段校验 - 将第一条错误信息抛出
     *
     * @param bindingResult validation 字段校验结果
     */
    public static void validate(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Optional.ofNullable(bindingResult.getFieldError())
                    .ifPresent(fieldError -> {
                        throw new SmilcoolException(fieldError.getDefaultMessage());
                    });
            throw new SmilcoolException("字段校验错误");
        }
    }
}
