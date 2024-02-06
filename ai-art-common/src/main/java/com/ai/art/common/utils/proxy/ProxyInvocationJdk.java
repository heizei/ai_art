package com.ai.art.common.utils.proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.Proxy;

/**
 * jdk动态代理
 * @author wangBo
 */
public class ProxyInvocationJdk implements InvocationHandler {

    /**
     * 被代理的对象
     */
    private Object target;

    /**
     * 获取代理对象的方法
     * @param targetObject 需要代理的对象
     * @return 目标
     */
    public Object getJdkProxy(Object targetObject) {
        // 目标对象赋值
        this.target = targetObject;
        // JDK动态代理只能针对实现了接口的类进行代理
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
            target.getClass().getInterfaces(), this);
    }

    /**
     * 继承该类后重写该方法，写被代理方法执行前后的业务
     * @param o 请求
     * @param method 请求
     * @param objects 请求
     * @return 结果
     * @throws InvocationTargetException 异常
     * @throws IllegalAccessException 异常
     */
    @Override
    public Object invoke(Object o, Method method,
                         Object[] objects) throws InvocationTargetException,
                                           IllegalAccessException {

        // 重写方法后，预留方法执行前的业务

        Object invoke = method.invoke(target, objects);

        // 重写方法后，预留方法执行后的业务

        return invoke;
    }
}