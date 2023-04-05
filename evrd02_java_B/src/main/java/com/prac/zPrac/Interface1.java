package com.prac.zPrac;




interface MainFood{
    String cooking();

    // 자바8 버전 이후부터는 interface에 디폴트 메서드(default method) 사용가능
    default void printFood(){

        System.out.printf("my food is %s%n",cooking());
    }

    // 자바8 버전 이후부터는 인터페이스에 스태틱 메서드(static method) 사용 가능
    int LEG_COUNT = 4; // 인터페이스 상수
    // 풀센턴스 : public static final int LEG_COUNT = 4;
    // public static final 생략 가능

    static int speed(){
        return LEG_COUNT * 30; // 다른 곳에서 MainFood.speed()로 사용 가능
    }

}

class Food{
    String mFood;
    void setMFood(String mFood){

        this.mFood = mFood;
    }
}

class Steak extends Food implements MainFood{
    public String cooking(){
        return "스테이크";
    }
    public void printFood(){

        System.out.printf("override : my food is %s%n",cooking());
    }
}
class Resorto extends Food implements MainFood{
    public String cooking(){

        return "리조또";
    }
}



class Cheif{
//    void cook(Steak steak){
//        System.out.println("Cooking : steak");
//    }
//    void cook(Resorto resorto){
//        System.out.println("Cooking : resorto");
//    }
    void cook(MainFood mainFood){

        System.out.println("Cooking : mainFood - " + mainFood.cooking());
    }
}

public class Interface1 {
    public static void main(String[] args) {
        Cheif cheif = new Cheif();
        Steak steak = new Steak();
        Resorto resorto = new Resorto();
        cheif.cook(steak);      // Cooking : steak
        cheif.cook(resorto);    // Cooking : resorto
        steak.printFood();      // override : my food is 스테이크
        resorto.printFood();    // my food is 리조또
        // 이 방식으로 기능 수행은 가능하나 신메뉴를 앞으로 수천개씩 추가해야한다면?
        // 유지보수가 너무 힘들 것임. 이때 interface 필요
        // (일반적으로 interface파일은 단독 파일을 만드나 여기서는 편의상 한 곳에)

    }
}
