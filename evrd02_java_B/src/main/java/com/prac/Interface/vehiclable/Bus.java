package com.prac.Interface.vehiclable;

public class Bus implements Vehiclable{
    private int speed;

    @Override
    public void run(){
        System.out.println("버스가 시속"+this.speed+"km로 운행합니다");
    }
    @Override
    public void stop(){
        System.out.println("버스가 멈춥니다");
    }
    @Override
    public void setSpeed(int speed){
        if(speed < 0){
            System.out.println("속도는 음수일 수 없습니다");
            return ;
        }
        this.speed = speed;
    }
}
