package com.prac.zPrac;


// interface RemoteControl 예제 -> MVC 적용해보기

interface Remote_Control{
    static final int MAX_VOLUME = 10;
    static final int MIN_VOLUME = 0;
//    static boolean POWER = false; // 인터페이스 내에서는 자동으로 final이 추가됨. 따라서 TV 클래스에 생성해야함

    public abstract boolean turnOn();
    public abstract boolean turnOff();
    public abstract int setVolume(int volume);


}

class TV implements Remote_Control{
    TvView tvView = new TvView();   // 메세지 출력할 view 클래스 인스턴스생성
    private int volume = 0;
    static boolean POWER = false;


    @Override
    public boolean turnOn() {
        tvView.turnOn();
        return POWER = true;
    }
    @Override
    public boolean turnOff() {
        tvView.turnOff();
        return POWER = false;
    }
    @Override
    public int setVolume(int volume) {
        if(POWER == true) {
            if (volume > MAX_VOLUME) {
                this.volume = MAX_VOLUME;
                tvView.maxVolume();
                tvView.currentVolume(volume);
            } else if (volume < MIN_VOLUME) {
                this.volume = MIN_VOLUME;
                tvView.minVolume();
                tvView.currentVolume(volume);
            } else {
                this.volume = volume;
                tvView.currentVolume(volume);
            }
        }else{
            tvView.power();
        }
        return volume;
    }


}

class TvView{
    public void power(){
        System.out.println("TV 전원을 켜야합니다");
    }
    public void turnOn(){
        System.out.println("TV를 켭니다");
    }
    public void turnOff() {
        System.out.println("TV를 끕니다");
    }

    public void currentVolume(int volume){
        System.out.println("현재 볼륨은 "+volume+"입니다");
    }
    public void maxVolume(){
        System.out.println("최대볼륨은 10입니다. 10으로 설정됩니다");
    }
    public void minVolume(){
        System.out.println("최소볼륨은 0입니다. 0으로 설정됩니다");
    }
}

class TvVO{

}

public class Interface6_RemoteControl_MVC {
    public static void main(String[] args) {
        TV tv = new TV();
        tv.turnOn();
        tv.setVolume(5);
        tv.setVolume(10);
        tv.turnOff();
        tv.setVolume(10); // 전원이 꺼져있으므로 볼륨을 변경해도 작동x

    }
}
