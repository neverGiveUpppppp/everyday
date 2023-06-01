package com.prac.inheritance;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;


abstract class Vehicle {
    String name;
    abstract public String getName(String val);
    public String getName() {
        return "Vehicle name : " + name;
    }
}
class Car extends Vehicle {
    private String name;
    public Car(String val) {
        this.name = super.name = val;
    }
    @Override
    public String getName(String val) {
        return "Car name : " + val;
    }
    public String getName(byte[] val) {
        return "Car name : " + val;
    }

    public void setName(String newName){
        this.name = newName;
    }
}


public class Inheritance_VehicleCar {
    public static void main(String[] args) {
        Vehicle obj = new Car("Spark");
        System.out.println(obj.getName()); // obj.getName()이 부모클래스만 찍히는 이유
                                           // 부모클래스의 기능을 사용할려고 Car클래스를 데이터타입 Vehicle로 형변환를 시켜 사용한 것
        // abstract class : 클래스의 규칙, 목적 등을 강제함
        //    ex)해당 타입, 값이 오도록 강제하는 기능
        // generic과 비슷

    }
}
