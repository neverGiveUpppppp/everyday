package com.prac.zPrac1;



interface Vehiclable{
    public abstract void go();
    public abstract void stop();
    public abstract int setSpeed(int speed);
}

class Bus implements Vehiclable{
    public void go(){
        System.out.println("Bus : start to go");
    }
    public void stop(){
        System.out.println("Bus : stop");
    }
    public int setSpeed(int speed){
        return speed;
    }

}

class Driver implements Vehiclable{
    public void manipulate_go(Vehiclable vehiclable){
        vehiclable.go();
    }
    public void manipulate_stop(Vehiclable vehiclable){
        vehiclable.stop();
    }
    public void setSpeed(Vehiclable vehiclable, int speed){
        vehiclable.setSpeed(speed);
        System.out.printf("속도를 %dkm로 변경합니다.",speed);
    }

    @Override
    public void go() {

    }

    @Override
    public void stop() {

    }

    @Override
    public int setSpeed(int speed) {
        return 0;
    }
}

public class Interface8_Bus {
    public static void main(String[] args) {
        Driver driver = new Driver();
        Bus bus = new Bus();

        driver.manipulate_go(bus);
        driver.manipulate_stop(bus);
        driver.setSpeed(bus,50);


    }
}
