package com.prac.Interface.vehiclable;

public class Taxi implements Vehiclable{

    private int speed;

    @Override
    public void run(){
        System.out.println("택시가 시속"+this.speed+"km로 달립니다");
    }
    @Override
    public void stop(){
        System.out.println("택시가 멈춥니다");
    }
    @Override
    public void setSpeed(int speed){
        if(speed < 0 ){
            System.out.println("속도는 음수 일 수 없습니다");
            return ;
        }
        this.speed = speed;
    }
}
