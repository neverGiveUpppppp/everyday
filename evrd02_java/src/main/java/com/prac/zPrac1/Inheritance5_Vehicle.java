package com.prac.zPrac1;

abstract class Vehicle{
    String name;
    abstract public String getName(String val);
    public String getName(){
        return "Vehicle name : " + name;
    }
}

class Car extends Vehicle{

    String name = null;

    public Car(String car){
        this.name = super.name = car;
    }

    @Override
    public String getName(String val) {
        return "Car name : " + val;
    }

    public void setName(String newName){
        this.name = newName;
    }
}



public class Inheritance5_Vehicle {
    public static void main(String[] args) {
        Vehicle obj = new Car("spark");
        System.out.println(obj.getName());           // Vehicle name : spark
        System.out.println(obj.getName("test")); // Car name : test
        // 자식 클래스인 Car의 값이 아닌 부모 Vehicle의 클래스가 찍힌 이유?
        // 자식 클래스에서 부모 클래스의 메소드를 오버라이딩하지 않으면, 부모 클래스의 메소드가 그대로 사용됨
        // getName의 매개변수가 다르기 때문에 오버라이딩이 안된 것
        //  getName()과 getName(String str)은 다른데, Car class에 getName(String str)만 있기 때문에
        //  getName()을 찾아 부모클래스로 간 것. getName("test")를 찍으면 Car에서 잘 나오는 것을 볼 수 있다
    }
}