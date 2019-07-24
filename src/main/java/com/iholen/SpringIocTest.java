package com.iholen;

import com.iholen.ioc.SimpleIoc;
import com.iholen.ioc.bean.Car;
import com.iholen.ioc.bean.Wheel;

/**
 * @author huliang
 * @date 2019-07-24 11:11
 */
public class SpringIocTest {
    public static void main(String[] args) {
        String configPath = SimpleIoc.class.getClassLoader().getResource("beans.xml").getFile();
        SimpleIoc simpleIoc = new SimpleIoc(configPath);
        Wheel wheel = (Wheel)simpleIoc.getBean("wheel");
        System.out.println(wheel.toString());

        Car car = (Car)simpleIoc.getBean("car");
        System.out.println(car.toString());

    }
}
