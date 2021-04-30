package com.smilcool.server.common.util;

import lombok.NonNull;
import lombok.SneakyThrows;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Bean 操作相关
 */
public class BeanUtil {

    /**
     * 拷贝单个对象
     *
     * @param source     待拷贝对象
     * @param targetType 目标类型
     * @param <T>        目标类型
     * @return 目标类型对象
     */
    @SneakyThrows
    public static <T> T copyProp(@NonNull Object source, Class<T> targetType) {
        T target = targetType.newInstance();
        BeanUtils.copyProperties(source, target);
        return target;
    }

    /**
     * 拷贝对象数组
     *
     * @param sourceList 待拷贝数组
     * @param targetType 目标类型
     * @param <T>        目标类型
     * @return 目标类型数组
     */
    public static <T> List<T> copyProp(@NonNull List<?> sourceList, Class<T> targetType) {
        List<T> targetList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(sourceList)) {
            sourceList.forEach(source -> {
                T target = copyProp(source, targetType);
                targetList.add(target);
            });
        }
        return targetList;
    }
}
