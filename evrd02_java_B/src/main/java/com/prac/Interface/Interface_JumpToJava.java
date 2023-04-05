package com.prac.Interface;


interface Predator{
    String getFood();

    // 자바8 버전 이후부터는 interface에 디폴트 메서드(default method) 사용가능
    default void printFood(){

        System.out.printf("my food is %s%n",getFood());
    }

    // 자바8 버전 이후부터는 인터페이스에 스태틱 메서드(static method) 사용 가능
    int LEG_COUNT = 4; // 인터페이스 상수

    static int speed(){
        return LEG_COUNT * 30;
    }

}

class Animal{
    String name;

    void setName(String name){
        this.name = name;
    }
}

class Tiger extends Animal implements Predator{

    @Override
    public String getFood() {  // 인터페이스의 메서드는 항상 public으로 구현
        return "apple";
    }
}
class Lion extends Animal implements Predator{
    public String getFood(){
        return "banana";
    }

}
class ZooKeeper{
    // 인터페이스 추가 전
//    void feed(Tiger tiger){
//        System.out.println("feed apple");
//    }
//    void feed(Lion lion){
//        System.out.println("feed banana");
//    }

    // 인터페이스 추가 후
    void feed(Predator predator){
        System.out.println("feed " + predator.getFood());
    }

}

public class Interface_JumpToJava {
    public static void main(String[] args) {
        ZooKeeper zooKeeper = new ZooKeeper();
        Tiger tiger = new Tiger();
        Lion lion = new Lion();
        zooKeeper.feed(tiger); // feed apple
        zooKeeper.feed(lion);  // feed banana


/*
인터페이스 필요성
동물원에 호랑이,사자 외에 악어, 표범 등이 계속 추가된다면?
ZooKeeper는 육식동물이 추가될 때마다 같은 feed 메서드를 추가해야하는 번거로움 및 유지보수 어려운 점
-> 해결책 : 인터페이스

코드추가
interface Predator{
    String getFood();
cf.인터페이스의 메서드는 메서드의 이름과 입출력에 대한 정의만 있고 그 내용은 없다.
    그 이유는 인터페이스는 규칙이기 때문
    getFood라는 메서드는 인터페이스를 implements한 클래스들이 구현해야만 하는 것
}
각각 Tiger, Lion에 implements Predator

Class ZooKeeperd에 코드 추가
    void feed(Predator predator){
        System.out.println("feed predator");
    }
-> 이제 어떤 육식동물이 추가되더라도 ZooKeeper는 feed()를 추가할 필요가 없이
Predator 인터페이스를 구현한 클래스를 작성하기만 하면 되는 것

<인터페이스 필요성>
보통 중요 클래스를 작성하는 입장이라면(여기서는 ZooKeeper)
클래스의 구현체와 상관없이 인터페이스를 기준으로 중요 클래스를 작성해야만 한다.
구현체(Tiger, Lion, Crocodile,...)는 늘어날수 있지만 인터페이스(Predator)는 하나이기 때문


<인터페이스의 핵심과 개념>
핵심 : 메서드의 갯수가 줄어들었다는 점이 아니라,
ZooKeeper클래스가 동물들의 종류에 의존적인 클래스에서 동물들의 종류와 상관없는 독립적인 클래스가 되었다는 점


 */

    }
}

