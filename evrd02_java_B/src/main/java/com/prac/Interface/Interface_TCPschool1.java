package com.prac.Interface;




/*

접근제어자 interface 인터페이스이름 {
    public static final 타입 상수이름 = 값;
    ...
    public abstract 메소드이름(매개변수목록);
    ...
}

 */



// 인터페이스를 사용한 다중 상속의 예제
interface Animall {
    public abstract void cry();
}
interface Pet{
    public abstract void play();
}

class Cat implements Animall,Pet{
    public void cry(){
        System.out.println("냐옹 냐옹!");
    }
    public void play(){
        System.out.println("쥐 잡기 놀이하자~");
    }
}

class Dog implements Animall,Pet{
    public void cry(){
        System.out.println("멍멍 멍멍!");
    }
    public void play(){
        System.out.println("산책 가자~");
    }
}


public class Interface_TCPschool1 {
    public static void main(String[] args) {
        Cat cat = new Cat();
        Dog dog = new Dog();

        cat.cry();
        dog.cry();
        cat.play();
        dog.play();

    }
}
