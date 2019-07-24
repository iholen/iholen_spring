package com.iholen.aop.advice;

import com.iholen.aop.invocation.MethodInvocation;
import java.lang.reflect.Method;

/**
 * @author huliang
 * @date 2019-07-24 15:32
 */
public class BeforeAdvice implements Advice {

    private Object bean;
    private MethodInvocation methodInvocation;

    public BeforeAdvice(Object bean, MethodInvocation methodInvocation) {
        this.bean = bean;
        this.methodInvocation = methodInvocation;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        methodInvocation.invoke();

        return method.invoke(bean, args);
    }
}
