package com.prac.Interface;



// 인터페이스를 이용한 다중 상속
// 인터페이스를 이용한 다중 상속 : 클래스 다중상속의 문제점인  메소드 호출 모호성 방지

interface Animalll{
    public abstract void cry();
}

interface Catt extends Animalll{
    public abstract void cry();
}
interface Dogg extends Animalll{
    public abstract void cry();
}

class MyPet implements Catt,Dogg{   // 인터페이스 다중상속
    public void cry(){
        System.out.println("멍멍 냥냥!");
    }
}


public class Interface_TCPschool2 {
    public static void main(String[] args) {
        MyPet myPet = new MyPet();
        myPet.cry();
    }
}
