package com.smilcool.server.common.util;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Bean 操作相关
 *
 * @author Angus
 * @date 2019/3/21
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
    public static <T> T copyProp(Object source, Class<T> targetType) {
        try {
            T target = targetType.newInstance();
            BeanUtils.copyProperties(source, target);
            return target;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 拷贝对象数组
     *
     * @param sourceList 待拷贝数组
     * @param targetType 目标类型
     * @param <T>        目标类型
     * @return 目标类型数组
     */
    public static <T> List<T> copyProp(List<?> sourceList, Class<T> targetType) {
        List<T> targetList = new ArrayList<>();
        sourceList.forEach(source -> {
            try {
                T target = targetType.newInstance();
                BeanUtils.copyProperties(source, target);
                targetList.add(target);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return targetList;
    }
}
