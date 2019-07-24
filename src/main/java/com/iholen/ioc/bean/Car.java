package com.iholen.ioc.bean;

/**
 * @author huliang
 * @date 2019-07-24 11:15
 */
public class Car {


    private String name;
    private Wheel wheel;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Wheel getWheel() {
        return wheel;
    }

    public void setWheel(Wheel wheel) {
        this.wheel = wheel;
    }

    @Override
    public String toString() {
        return "Car{" +
            "name='" + name + '\'' +
            ", wheel=" + wheel +
            '}';
    }
}
