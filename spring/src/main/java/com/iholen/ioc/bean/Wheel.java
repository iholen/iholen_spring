package com.iholen.ioc.bean;

/**
 * @author huliang
 * @date 2019-07-24 11:15
 */
public class Wheel {


    private String brand;
    private String specification;

    public String getBrand() {
        return brand;
    }

    public String getSpecification() {
        return specification;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    @Override
    public String toString() {
        return "Wheel{" +
            "brand='" + brand + '\'' +
            ", specification='" + specification + '\'' +
            '}';
    }
}
