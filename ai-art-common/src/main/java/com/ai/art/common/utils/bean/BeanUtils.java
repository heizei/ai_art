package com.ai.art.common.utils.bean;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Bean 工具类
 */
public class BeanUtils extends org.springframework.beans.BeanUtils {
    private static final Logger  log                    = LoggerFactory.getLogger(BeanUtils.class);

    /** Bean方法名中属性名开始的下标 */
    private static final int     BEAN_METHOD_PROP_INDEX = 3;

    /** * 匹配getter方法的正则表达式 */
    private static final Pattern GET_PATTERN            = Pattern
        .compile("get(\\p{javaUpperCase}\\w*)");

    /** * 匹配setter方法的正则表达式 */
    private static final Pattern SET_PATTERN            = Pattern
        .compile("set(\\p{javaUpperCase}\\w*)");

    /**
     * Bean属性复制工具方法。
     * 
     * @param dest 目标对象
     * @param src 源对象
     */
    public static void copyBeanProp(Object dest, Object src) {
        try {
            copyProperties(src, dest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取对象的setter方法。
     * 
     * @param obj 对象
     * @return 对象的setter方法列表
     */
    public static List<Method> getSetterMethods(Object obj) {
        // setter方法列表
        List<Method> setterMethods = new ArrayList<Method>();

        // 获取所有方法
        Method[] methods = obj.getClass().getMethods();

        // 查找setter方法

        for (Method method : methods) {
            Matcher m = SET_PATTERN.matcher(method.getName());
            if (m.matches() && (method.getParameterTypes().length == 1)) {
                setterMethods.add(method);
            }
        }
        // 返回setter方法列表
        return setterMethods;
    }

    /**
     * 获取对象的getter方法。
     * 
     * @param obj 对象
     * @return 对象的getter方法列表
     */

    public static List<Method> getGetterMethods(Object obj) {
        // getter方法列表
        List<Method> getterMethods = new ArrayList<Method>();
        // 获取所有方法
        Method[] methods = obj.getClass().getMethods();
        // 查找getter方法
        for (Method method : methods) {
            Matcher m = GET_PATTERN.matcher(method.getName());
            if (m.matches() && (method.getParameterTypes().length == 0)) {
                getterMethods.add(method);
            }
        }
        // 返回getter方法列表
        return getterMethods;
    }

    /**
     * 检查Bean方法名中的属性名是否相等。<br>
     * 如getName()和setName()属性名一样，getName()和setAge()属性名不一样。
     * 
     * @param m1 方法名1
     * @param m2 方法名2
     * @return 属性名一样返回true，否则返回false
     */

    public static boolean isMethodPropEquals(String m1, String m2) {
        return m1.substring(BEAN_METHOD_PROP_INDEX).equals(m2.substring(BEAN_METHOD_PROP_INDEX));
    }

    /**
     * 将Object类型的数据转化成Map<String,Object>
     *
     * @param obj
     * @return map
     * @throws Exception
     */
    public static Map<String, Object> convertToMap(Object obj) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Class<?> clazz = obj.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Object fieldValue = field.get(obj);
                if (fieldValue != null){
                    map.put(field.getName(), fieldValue);
                }
            }
        } catch (IllegalAccessException e) {
            log.info("将Object类型的数据转化成Map错误" + e.getMessage());
            return map;
        }
        return map;
    }

    /**
     *  将Map<String,Object>转化为Object对象
     * @param map map
     * @param clazz 类
     * @param <T> 泛型isUpperCase
     * @return 对象
     */
    public static <T> T convertMapToObj(Map<String, Object> map, Class<T> clazz) {
        try {
            // 创建一个新对象
            T obj = clazz.newInstance();
            // 获取类的所有方法
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                if (method.getName().startsWith("set") && method.getParameterCount() == 1) {
                    String fieldName = method.getName().substring(3, 4).toLowerCase()
                                       + method.getName().substring(4);
                    // 从map中获取对应的值
                    Object value = map.get(fieldName);
                    if (value != null) {
                        // 使用反射调用setter方法设置属性值
                        method.invoke(obj, value);
                    }
                }
            }
            return obj;
        } catch (Exception e) {
            log.info("将Map<String,Object>转化为Object对象错误" + e.getMessage());
            return null;
        }
    }

}
