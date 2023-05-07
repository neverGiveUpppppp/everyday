package com.prac.zPrac;

// 인터페이스 다중상속 + MVC패턴 적용

interface CPU{
    public abstract void compute();
    public abstract void setClock(double cpuClock);
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
    public boolean monitorPower = false;
    public int fanSpeed = 0;
    public double cpuClock = 0;
    public int memory = 0;

    public void isPower(boolean power){
        this.power = power;
    }
    public void setFanSpeed(int fanSpeed){
        this.fanSpeed = fanSpeed;
    }
    public void isMonitorPower(boolean monitorPower){
        this.monitorPower = monitorPower;
    }
    public void setCpuClock(double cpuClock){
        this.cpuClock = cpuClock;
    }
    public void setMemory(int memory){
        this.memory = memory;
    }
}

class Computer extends Function implements CPU,Memory,SDD{
    Function fc = new Function();
    View view = new View();


    public void powerButton(boolean powerComputer){
        if(powerComputer == true) {
//            isPower(true);
            fc.isPower(true);
            fc.setCpuClock(1.0);  // 컴퓨터가 켜지면 cpu clock 자동으로 돌아가게 설정
            fc.setMemory(1);
            view.powerButton("Power On");
        }else{
            fc.isPower(false);
            fc.setCpuClock(0);  // 컴퓨터가 꺼지면 전부 off
            fc.setMemory(0);
            view.powerButton("Power Off");
        }
    }
    public void monitorPower(boolean powerMonitor){
        if(powerMonitor == true) {
            fc.isMonitorPower(true);
            view.monitorPower("Monitor Power On");
        }else{
            fc.isMonitorPower(false);
            view.monitorPower("Monitor Power Off");
        }
    }

    @Override
    public void compute(){
        view.generalMsg("CPU : 연산합니다");
    }
    @Override
    public void setClock(double cpuClock){
        if(fc.power){ // power on일 경우
            if(this.cpuClock > cpuClock){
                this.cpuClock = cpuClock;
                view.setClock("CPU 클럭을 낮춥니다 : %.2fGhz\n",cpuClock);
            }else if(this.cpuClock < cpuClock){
                this.cpuClock = cpuClock;
                view.setClock("CPU 클럭을 높입니다 : %.2fGhz\n",cpuClock);
            }else{
                view.setClock("현 CPU클럭을 유지합니다. : %fGhz",cpuClock);
            }
        }else{      // power off일 경우
            this.cpuClock = cpuClock;
        }
    }
    @Override
    public void setMemory(int memory) {
        if (fc.power) {
            this.memory = memory;
            view.setMemory("Memory : 메모리가 %d로 설정됩니다\n", memory);
        } else {
            this.memory = memory;
        }
    }

    @Override
    public void cache() {
        view.generalMsg("Memory : 메모리 캐쉬 작동");
    }

    @Override
    public void store(){
        view.generalMsg("SDD : 데이터를 저장합니다");
    }
}

class View{
    public void generalMsg(String generalMsg){
        System.out.println(generalMsg);
    }

    public void powerButton(String power) {
        System.out.println(power);
    }
    public void setClock(String setClockMsg, double cpuClock) {
        System.out.printf(setClockMsg,cpuClock);
    }
    public void setClock(String str){
        System.out.println(str);
    }

    public void monitorPower(String monitorPower) {
        System.out.println(monitorPower);
    }

    public void setMemory(String setMemoryMsg, int memory){
        System.out.printf(setMemoryMsg, memory);
    }

}

public class Interface7_Computer_basedWashingMachin {
    public static void main(String[] args) {
        Computer computer = new Computer();
        computer.powerButton(true);
        computer.monitorPower(true);
        computer.cache();
        computer.compute();
        computer.setClock(4.5);
        computer.setClock(1.5);
        computer.setMemory(8);
        computer.store();

    }
}




