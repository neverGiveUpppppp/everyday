package com.prac.zPrac;


interface AllAnimal{
    public abstract String TypeofFood();

}

class Animal{
    String name;
    void setName(String name){
        this.name = name;
    }
}


class Tiger extends Animal implements AllAnimal{

    @Override
    public String TypeofFood() {
        return "meat(pig)";
    }
}

class Lion extends Animal implements AllAnimal{

    @Override
    public String TypeofFood() {
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
        System.out.println("feed to : " + allAnimal.TypeofFood()); // 동물에 따라 음식을 다르게 줘야. 동물 분류 로직을 어디에? 상속 때문에 자연 분류됨. 매개변수에서
    }
}


public class Interface6_JumpToJava {
    public static void main(String[] args) {
        ZooKeeper zooKeeper = new ZooKeeper();
        Tiger tiger = new Tiger();
        Lion lion = new Lion();

        zooKeeper.feed(tiger);
        zooKeeper.feed(lion);
    }

}
