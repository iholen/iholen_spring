package com.iholen;

import com.iholen.aop.SimpleAop;
import com.iholen.aop.advice.Advice;
import com.iholen.aop.advice.BeforeAdvice;
import com.iholen.aop.invocation.MethodInvocation;
import com.iholen.aop.service.HelloService;
import com.iholen.aop.service.impl.HelloServiceImpl;

/**
 * @author huliang
 * @date 2019-07-24 11:11
 */
public class SpringAopTest {

    public static void main(String[] args) {
        // 定义 beforeAdvice 实际执行的代码
        MethodInvocation logger = () -> System.out.println("before invoke");
        // 定义 helloService
        HelloService helloService = new HelloServiceImpl();
        // 将logger加入到helloService
        Advice beforeAdvice = new BeforeAdvice(helloService, logger);
        // 获取 helloService 代理对象
        HelloService helloServiceProxy = (HelloService)SimpleAop.getProxy(helloService, beforeAdvice);
        helloServiceProxy.sayHello();
    }

}
