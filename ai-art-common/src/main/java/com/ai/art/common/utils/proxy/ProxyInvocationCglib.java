package com.ai.art.common.utils.proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/**
 * cglib动态代理
 * @author wangBo
 */
public class ProxyInvocationCglib implements MethodInterceptor {

    /**
     * 被代理的对象
     */
    private Object target;

    /**
     * 定义获取代理对象方法
     * @param objectTarget 被代理对象
     * @return 结果
     */
    public Object getCglibProxy(Object objectTarget) {
        // 为目标对象target赋值
        this.target = objectTarget;

        Enhancer enhancer = new Enhancer();
        // 设置父类,因为Cglib是针对指定的类生成一个子类，所以需要指定父类
        enhancer.setSuperclass(objectTarget.getClass());
        // 设置回调
        enhancer.setCallback(this);
        // 创建并返回代理对象
        return enhancer.create();
    }

    /**
     * 继承该类后重写该方法，写被代理方法执行前后的业务
     * @param o 请求
     * @param method 请求
     * @param objects 请求
     * @param methodProxy 请求
     * @return 结果
     * @throws InvocationTargetException 异常
     * @throws IllegalAccessException 异常
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects,
                            MethodProxy methodProxy) throws InvocationTargetException,
                                                     IllegalAccessException {
        // 重写方法后，预留方法执行前的业务

        // 方法执行，参数：target 目标对象 参数数组
        Object invoke = method.invoke(target, objects);

        // 重写方法后，预留方法执行后的业务

        return invoke;
    }
}