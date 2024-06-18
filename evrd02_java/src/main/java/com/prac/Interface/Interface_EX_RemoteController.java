package com.prac.Interface;


// 출처 및 원본 : https://kjh95.tistory.com/137

import java.rmi.Remote;

/*
1.인터페이스의 다형성
2.인터페이스 매개변수의 다형성
 객체를 직접 매개변수로 생성하여 넘기는 방법

default 메서드
정적(static) 메서드

 */


//  1.인터페이스의 다형성
interface RemoteControl{

    static final int MAX_VOLUMN = 10; // static final 생략가능. 컴파일 시, 자동추가해줌
    static final int MIN_VOLUMN = 0;
//    static boolean POWER = false; // 인터페이스 내에서는 자동으로 final이 추가됨. 따라서 TV 클래스에 생성해야함


    public abstract void turnOn();    // abstract 생략가능. 컴파일 시, 자동추가해줌
    public abstract void turnOff();
    public abstract void setVolume(int volume);

}


class Audio implements RemoteControl{
    private int volume;
    @Override
    public void turnOn(){
        System.out.println("audio : turn on");
    }
    @Override
    public void turnOff(){
        System.out.println("audio : turn off");
    }
    @Override
    public void setVolume(int volume){
        if(volume > RemoteControl.MAX_VOLUMN){
            System.out.println("최대볼륨은 10입니다");
            this.volume = RemoteControl.MAX_VOLUMN;

        }else if(volume < RemoteControl.MIN_VOLUMN){
            System.out.println("최소볼륨은 0입니다");
            this.volume = RemoteControl.MIN_VOLUMN;
        }else{
            this.volume = volume;
        }
        System.out.println("현재 볼륨 : "+volume);
    }
}
class Television implements RemoteControl{
    private int volume;
    static boolean POWER = false;

    @Override
    public void turnOn(){
        System.out.println("TV : turn on");
    }
    @Override
    public void turnOff(){
        System.out.println("TV : turn off");
    }
    @Override
    public void setVolume(int volume){
        if(volume > RemoteControl.MAX_VOLUMN){
            System.out.println("최대볼륨은 10입니다");
            this.volume = RemoteControl.MAX_VOLUMN;
        }else if(volume < RemoteControl.MIN_VOLUMN){
            System.out.println("최소볼륨은 0입니다");
            this.volume = RemoteControl.MIN_VOLUMN;
        }else{
            this.volume = volume;
        }
        System.out.println("현재 볼륨 : "+this.volume);
    }
    public void printMessage(){

    }
}



public class Interface_EX_RemoteController {
    public static void main(String[] args) {
        RemoteControl rControl = new Television();
        rControl.turnOn();
        rControl.setVolume(15);
        rControl.setVolume(-100);
        rControl.setVolume(8);
        rControl.turnOff();
        System.out.println();

        rControl = new Audio(); // RemoteControl rControl에 선언한 변수명에 new객체를 새로해서 이어감
        rControl.turnOn();
        rControl.setVolume(10);
        rControl.setVolume(-1);
        rControl.turnOff();

        // 나중에 이 구조를 MVC패턴으로 리팩토링해보자
    }
}
