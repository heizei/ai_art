package com.ai.art.common.utils.collection;

import java.util.*;
import java.util.function.Function;

/**
 * ListUtils:list工具类
 * @author wangBo
 */
public class ListUtils {

    /**
     * 集合转map：key只对应第一个获取到的value
     * @param data 集合
     * @param function 获取key的函数式接口
     * @return 结果
     * @param <T> 输入类型
     * @param <R> 输出类型
     */
    public static <T, R> Map<R, T> toMap(Collection<T> data, Function<T, R> function) {
        HashMap<R, T> hashMap = new HashMap<>();
        // 集合判空
        if (data == null || data.isEmpty()) {
            return hashMap;
        }
        // 集合迭代器
        Iterator<T> iterator = data.iterator();
        while (iterator.hasNext()) {
            // 获取对象
            T item = iterator.next();
            // 返回map的key
            R key = function.apply(item);
            // 如果已经有key则不更新返回旧值
            hashMap.putIfAbsent(key, item);
        }
        return hashMap;
    }

    /**
     * 集合转map：key对应多个value
     * @param data 集合
     * @param function 获取key的函数式接口
     * @return 结果
     * @param <T> 输入类型
     * @param <R> 输出类型
     */
    public static <T, R> Map<R, Collection<T>> toMapWithList(Collection<T> data,
                                                             Function<T, R> function) {
        HashMap<R, Collection<T>> hashMap = new HashMap<>();
        // 集合判空
        if (data == null || data.isEmpty()) {
            return hashMap;
        }
        // 集合迭代器
        Iterator<T> iterator = data.iterator();
        while (iterator.hasNext()) {
            // 获取对象
            T item = iterator.next();
            // 返回map的key
            R key = function.apply(item);

            if (hashMap.get(key) != null) {
                // key已经存在
                hashMap.get(key).add(item);
            } else {
                // key不存在
                Collection<T> collection = new ArrayList<>();
                collection.add(item);
                hashMap.put(key, collection);
            }
        }
        return hashMap;
    }

}