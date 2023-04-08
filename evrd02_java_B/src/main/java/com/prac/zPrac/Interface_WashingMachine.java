package com.prac.zPrac;



interface WashingMachine{
    public abstract void startButton();
    public abstract void stopButton();
    public abstract void pauseButton();
    public abstract int washingType(int washingType);
    public abstract void dehydrate();
}
interface PlusFunction{
    public abstract void dry();
    public abstract void antibacterial();

}

class LGWashingMachine implements WashingMachine, PlusFunction{
    public void startButton(){
        System.out.println("세탁을 시작합니다");
    }
    public void stopButton(){
        System.out.println("세탁 중지합니다");
    }
    public void pauseButton(){
        System.out.println("세탁을 잠시 멈춥니다");
    }
    public int washingType(int washingType){
        switch (washingType){
            case 1:
                System.out.println("울 세탁"); break;
            case 2:
                System.out.println("실크 세탁"); break;
            case 3:
                System.out.println("이불 세탁"); break;
            case 4:
                System.out.println("일반 세탁"); break;
            default:
                System.out.println("이상 발생. 점검 요망."); break;
        }
        return washingType;
    }

    public void dehydrate(){
        System.out.println("탈수합니다");
    }

    @Override
    public void dry() {
        System.out.println("건조합니다");
    }

    public void antibacterial(){
        System.out.println("항균을 시작합니다");
    }

}



public class Interface_WashingMachine {
    public static void main(String[] args) {
        LGWashingMachine lg = new LGWashingMachine();
        lg.startButton();
        lg.pauseButton();
        lg.stopButton();
        lg.washingType(1);
//        System.out.println(lg.washingType(1));
        lg.startButton();
        lg.antibacterial();
        lg.dehydrate();
        lg.dry();

    }
}
