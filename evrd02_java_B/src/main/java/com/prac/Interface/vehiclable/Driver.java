package com.prac.Interface.vehiclable;


// 독립 클래스(인터페이스 매개변수의 다형성
public class Driver {

    // interface Vehiclable를 implements한 Taxi,Bus Class만 올 수 있음
    public void drive(Vehiclable vehiclable){
        vehiclable.run();
    }
    public void stop(Vehiclable vehiclable){ // Vehiclable 인터페이스를 객체로 받지만 다형성으로 그 아래 자식인 Taxi,Bus클래스도 올 수 있음
        vehiclable.stop();
    }

    // 인터페이스 Vehiclable가 리턴타입, 매개변수(다형성) 타입임
    // 즉, 리턴타입도 구현클래스가 와야하고, 매개변수도 구현클래스가 와야함
    public Vehiclable newCarMethod(Vehiclable vehiclable){
        if(vehiclable instanceof Taxi){         // instanceof 연산자 : 해당 객체가 맞는지 확인. boolean 반환
            Taxi taxi = (Taxi) vehiclable;
            return taxi;
        }else if(vehiclable instanceof Bus){
            Bus bus = (Bus)vehiclable;      // 다운 캐스팅
            return bus;
        }
        return null;
    }

}

/*
    <instanceof 연산자>

    부모, 자식 클래스에서 instanceof 연산자가 뭐를 true로 반환하는지 확인해보기

class Parent{}
class Child extends Parent{}

public class InstanceofTest {
    public static void main(String[] args){

        Parent parent = new Parent();
        Child child = new Child();

        System.out.println( parent instanceof Parent );  // true
        System.out.println( child instanceof Parent );   // true
        System.out.println( parent instanceof Child );   // false
        System.out.println( child instanceof Child );   // true
    }


    <instanceof 사용을 지양해야되는 이유>

1.캡슐화가 깨진다
2.OCP(SOLID)에 위배 : 객체 확장이 어려워짐
3.SRP(SOLID)에 위배 : 한 클래스에 여러 책임이 생기므로 위배됨
4.성능저하 : 다형성 적용이 더 빠름

깔끔한 코드 + 리팩토링 용이
다형성을 이용한 구현을 하면 if문이 많이 필요 없다.
각 타입에 필요한 구현을 각 객체에 하면 되기 때문
instanceof를 이용해 구현을 했다가 empty가 piece를 상속하지 않는 것과 같이 상속 구조가 바뀌어버리면 관련 코드는 모두 수정이 필요하다.
하지만 다형성을 적용해 구현을 하면 구조가 바뀌더라도 그 바뀐 타입의 메서드만 수정해주면 되므로 쉽고 간단한 리팩토링이 가능

대체방법 : 다형성 사용하기

참고자료
https://tecoble.techcourse.co.kr/post/2021-04-26-instanceof/

}


 */