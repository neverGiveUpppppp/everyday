package com.prac.zPrac1;


interface AllAnimal{
    public abstract String typeofFood();

    default void printFood(){
        System.out.printf("my food is %s\n", typeofFood());
    }

    static String favorite(){
        return "좋아 하는 것 : ";
    }

    String FAVORITES_ANIMAL = "FOOD";

}

class Animal{
    String name;
    void setName(String name){
        this.name = name;
    }
}


class Tiger extends Animal implements AllAnimal{

    @Override
    public String typeofFood() {
        return "meat(pig)";
    }

    @Override
    public void printFood() {
        AllAnimal.super.printFood();
    }
}

class Lion extends Animal implements AllAnimal{

    @Override
    public String typeofFood() {
        return "meat(cow)";
    }

}


class ZooKeeper{
//    메소드의 매개변수 때문에 아래 메소드들을 주석 처리 안하면 우선순위가 아래녀석들로 먼저가서  void feed(AllAnimal allAnimal){이 실행x
//    void feed(Tiger tiger){
//        System.out.println("feed : tiger");
//        System.out.println("feed : " + tiger); // feed : com.prac.zPrac.Tiger@1b6d3586
//    }
//    void feed(Lion lion){
//        System.out.println("feed : lion");
//        System.out.println("feed : " + lion); // feed : com.prac.zPrac.Lion@4554617c
//    }

    void feed(AllAnimal allAnimal){
//        System.out.println("feed : " + 음식); // 동물에 따라 음식을 다르게 줘야. 동물 분류 로직을 어디에?
//        System.out.println("feed to : " + name + allAnimal.TypeofFood()); // 동물에 따라 음식을 다르게 줘야. 동물 분류 로직을 어디에? 상속 때문에 자연 분류됨. 매개변수에서
//        System.out.println("feed to : " + getClass().getName() + allAnimal.TypeofFood()); // 동물에 따라 음식을 다르게 줘야. 동물 분류 로직을 어디에? 상속 때문에 자연 분류됨. 매개변수에서
        System.out.println("feed to : " + allAnimal.typeofFood()); // 동물에 따라 음식을 다르게 줘야. 동물 분류 로직을 어디에? 상속 때문에 자연 분류됨. 매개변수에서
    }
}


public class Interface6_JumpToJava {
    public static void main(String[] args) {
        ZooKeeper zooKeeper = new ZooKeeper();
        Tiger tiger = new Tiger();
        Lion lion = new Lion();

        zooKeeper.feed(tiger);
        zooKeeper.feed(lion);
//        zooKeeper.feed(lion.printFood()); //  interface default method 사용법
//        zooKeeper.feed(tiger(tiger).printFood()); //  interface default method 사용법
        tiger.printFood();// interface default method 사용법 : default메소드는 하위클래스에서 메소드 선언안해도 인터페이스 디폴트 메소드를 사용가능
        lion.printFood();
        
        AllAnimal.favorite();
        System.out.println(AllAnimal.favorite());       // static method 사용법(일반클래스 스태티처럼 사용가능)
        System.out.println(AllAnimal.FAVORITES_ANIMAL); // 인터페이스 상수 사용법

    }

}
