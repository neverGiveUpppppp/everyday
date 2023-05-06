package com.prac.zPrac;

// 인터페이스 다중상속

interface CPU{
    public abstract void compute();

}
interface Memory{
    public abstract void setMemory(int memory);
    public abstract void cache();

}
interface SDD{
    public abstract void store();
}

class Function{
    public boolean power = false;
    public int fanSpeed = 0;
    public boolean monitorPower = false;

    public void isPower(boolean power){
        this.power = power;
    }
    public void setFanSpeed(int fanSpeed){
        this.fanSpeed = fanSpeed;
    }
    public void isMonitorPower(boolean monitorPower){
        this.monitorPower = monitorPower;
    }

}

class Computer extends Function implements CPU,Memory,SDD{

    private int memory = 0;
    
    public void powerButton(boolean powerComputer){
        if(power == true) {
//            isPower(true);
            super.isPower(true);
            System.out.println("Power On");
        }else{
            super.isPower(false);
            System.out.println("Power Off");
        }
    }
    public void monitorPower(boolean powerMonitor){
        if(powerMonitor == true) {
            super.isMonitorPower(true);
            System.out.println("Monitor Power On");
        }else{
            super.isMonitorPower(false);
            System.out.println("Monitor Power Off");
        }
    }

    @Override
    public void compute(){
        System.out.println("CPU : 연산합니다");
    }
    @Override
    public void setMemory(int memory){
        this.memory = memory;
        System.out.printf("Memory : 메모리가 %d로 설정됩니다\n", memory);
    }

    @Override
    public void cache() {
        System.out.println("Memory : 메모리 캐쉬 작동");
    }

    @Override
    public void store(){
        System.out.println("SDD : 데이터를 저장합니다");
    }


}


public class Interface7_Computer_basedWashingMachin {
    public static void main(String[] args) {
        Computer computer = new Computer();
        computer.powerButton(true);
        computer.monitorPower(true);
        computer.compute();
        computer.setMemory(8);
        computer.cache();
        computer.store();

    }
}




