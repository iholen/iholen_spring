package com.iholen.aop;

import com.iholen.aop.advice.Advice;
import java.lang.reflect.Proxy;

/**
 * @author huliang
 * @date 2019-07-24 15:34
 */
public class SimpleAop {

    public static Object getProxy(Object bean, Advice advice) {
        return Proxy.newProxyInstance(SimpleAop.class.getClassLoader(), bean.getClass().getInterfaces(), advice);
    }

}
