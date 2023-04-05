package com.prac.inheritance;


import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

class Animal{
    String name;

    void setName(String name){
        this.name = name;
    }
}

class Dog extends Animal{
    void sleep(){
        System.out.println(this.name + " zzz");
    }
}

class HouseDog extends Dog{
    void sleep(){
        System.out.println(this.name + " zzz in house");
    }
    void sleep(int hour){
        System.out.println(this.name + " zzz in house for " + hour + "hours");
    }

}


public class Inheritance_JumpToJava {
    public static void main(String[] args) {
        Dog dog =  new Dog();
        dog.setName("poppy");
        System.out.println(dog.name); // poppy
        // Dog 클래스에 코드는 없지만 상속 때문에 부모 값들이 이미 자식인 Dog class에 있음
        dog.sleep(); // Dog class의 sleep() 안에 println()에서 출력 : poppy zzz

        HouseDog houseDog = new HouseDog();
        houseDog.setName("happy");
        houseDog.sleep(); // happy zz
        // 오버라이딩(재정의) 후 : happy zzz in house
        houseDog.sleep(5); // happy zzz in house for 5hours


    }
}
