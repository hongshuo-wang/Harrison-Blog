package com.harrison.utils;

import com.harrison.domain.entity.Article;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Harrison
 * @date: 2023/2/14 16:29
 * @Description: TODO
 */
public class BeanCopyUtils {
    private BeanCopyUtils(){}

    /**
     * 单个对象拷贝
     * @param source 原始对象
     * @param clazz 拷贝后的对象字节码
     * @param <V>
     */
    public static <V> V copyBean(Object source, Class<V> clazz) {
        // 创建目标对象
        V target = null;
        try {
            target = clazz.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(source, target);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        // 返回拷贝后的对象
        return target;
    }

    /**
     * 拷贝列表对象
     * @param sourceList 原始列表
     * @param clazz   要拷贝的类型
     * @return  返回clazz类型的列表
     * @param <V>
     */
    public static <V> List<V> copyBeanList(List<?> sourceList, Class<V> clazz) {
        return sourceList.stream()
                .map(source -> copyBean(source, clazz))
                .collect(Collectors.toList());
    }

}
