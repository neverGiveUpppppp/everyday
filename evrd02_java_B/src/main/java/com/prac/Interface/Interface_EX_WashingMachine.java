package com.prac.Interface;


// 출처 : https://ddolcat.tistory.com/502


interface WashingMachine{
    public abstract void startButton();
    public abstract void pauseButton();
    public abstract void stopButton();
    public abstract int changeSpeed(int speed);
}
interface DryCourse{
    public abstract void dry();
}

class LGWashingMachine implements WashingMachine, DryCourse{
    @Override
    public void startButton(){
        System.out.println("세탁기가 빨래를 시작합니다");
    }

    @Override
    public void pauseButton() {
        System.out.println("세탁기가 빨래를 잠시 멈춥니다");
    }
    @Override
    public void stopButton(){
        System.out.println("세탁기가 빨래를 멈춥니다");
    }
    @Override
    public int changeSpeed(int speed){
        int rtnSpeed = 0;
        switch(speed){
            case 1:
                rtnSpeed = 20;
                break;
            case 2:
                rtnSpeed = 50;
                break;
            case 3:
                rtnSpeed = 100;
                break;
        }
        return rtnSpeed;
    }

    @Override
    public void dry(){
        System.out.println("세탁기가 빨래를 건조합니다");
    }
    
}

public class Interface_EX_WashingMachine {
    public static void main(String[] args) {
        LGWashingMachine lgwm = new LGWashingMachine();
        lgwm.startButton();
        lgwm.changeSpeed(1);
        System.out.println("세탁기의 속도는 " + lgwm.changeSpeed(3));
        lgwm.pauseButton();
        lgwm.stopButton();
        lgwm.dry();

    }
}
