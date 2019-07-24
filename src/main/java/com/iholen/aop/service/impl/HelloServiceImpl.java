package com.iholen.aop.service.impl;

import com.iholen.aop.service.HelloService;

/**
 * @author huliang
 * @date 2019-07-24 15:38
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public void sayHello() {
        System.out.println("Aop Test");
    }
}
