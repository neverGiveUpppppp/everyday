package com.prac.zPrac1;



interface Brand{
    String brand();
}

class Smartphone2{
    String device;
    void setDevice(String device){
        this.device = device;
    }
    void checkPrint(){
        System.out.println(this.device);
    }
    void app(){
        System.out.println("application : " + this.device);
    }
}

class Apple extends Smartphone2 implements Brand{
    void app(){
        System.out.println("apple 앱스토어 : " + this.device);
    }

    public String brand(){
        return "apple";
    }
}

class Samsung extends Smartphone2 implements Brand{
    public String brand(){
        return "samsung";
    }
}

class Manufacturer{
//    void produce(Apple apple){
//        System.out.println("아이폰 생산");
//    }
//    void produce(Samsung samsung){
//        System.out.println("갤럭시 생산");
//    }
    void produce(Brand brand){
        System.out.println( brand.brand() + " 생산");
    }
}

public class Interface3 {
    public static void main(String[] args) {
        Apple apple = new Apple();
        apple.setDevice("iPhone XR");
        apple.checkPrint();
        apple.app();

        System.out.println("==============================");

        Samsung samsung = new Samsung();
        samsung.setDevice("Galaxy");
        samsung.checkPrint();
        samsung.app();

        System.out.println("==============================");

        Manufacturer manufacturer = new Manufacturer();
        manufacturer.produce(apple);    // Apple class의 interface Brand가 추상화이기 때문에 apple을 받을 수 있는 것. implements했잖아
        manufacturer.produce(samsung);

    }
}
