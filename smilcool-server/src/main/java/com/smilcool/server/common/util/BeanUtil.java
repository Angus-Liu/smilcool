package com.smilcool.server.common.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.collections.CollectionUtils;
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
        if (source != null) {
            try {
                T target = targetType.newInstance();
                BeanUtils.copyProperties(source, target);
                return target;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return null;
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
        if (CollectionUtils.isNotEmpty(sourceList)) {
            sourceList.forEach(source -> {
                try {
                    T target = targetType.newInstance();
                    BeanUtils.copyProperties(source, target);
                    targetList.add(target);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        }
        return targetList;
    }

    /**
     * 拷贝分页对象
     *
     * @param sourcePage 待拷贝分页
     * @param targetType 目标类型
     * @param <T>        目标类型
     * @return 目标类型分页对象
     */
    public static <T> Page<T> copyProp(Page<?> sourcePage, Class<T> targetType) {
        Page<T> targetPage = new Page<>();
        BeanUtils.copyProperties(sourcePage, targetPage);
        List<T> targetList = copyProp(sourcePage.getRecords(), targetType);
        targetPage.setRecords(targetList);
        return targetPage;
    }
}
