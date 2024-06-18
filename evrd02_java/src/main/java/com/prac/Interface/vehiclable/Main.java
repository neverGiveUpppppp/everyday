package com.prac.Interface.vehiclable;

public class Main {
    public static void main(String[] args) {
        Driver driver = new Driver();
        Bus bus = new Bus();
        Taxi taxi = new Taxi();

        bus.setSpeed(-80);
        bus.setSpeed(50);
        driver.drive(bus);  // Bus클래스의 bus객체를 참조변수로 넘김
        driver.stop(bus);

        System.out.println();

        // 직접 객체생성해서 넘기는 방법
        driver.drive(new Taxi());   // 택시가 시속0km로 달립니다
        driver.drive(new Bus());    // 버스가 시속0km로 운행합니다
//        driver.drive(new AIcar()); // 상속되지 않은 클래스는 넘길 수 없음

        System.out.println();

        Vehiclable vehiclable = driver.newCarMethod(new Taxi());
        vehiclable.run();   // 택시가 시속0km로 달립니다
        vehiclable.stop();  // 택시가 멈춥니다

        System.out.println();

        vehiclable = driver.newCarMethod(new Bus());
        vehiclable.run();   // 버스가 시속0km로 운행합니다
        vehiclable.stop();  // 버스가 멈춥니다
        // 같은 vehiclable 변수이지만 newCarMethod에 새로운 new 객체를 넣어주므로써 내부값이 교체됨

    }


}


