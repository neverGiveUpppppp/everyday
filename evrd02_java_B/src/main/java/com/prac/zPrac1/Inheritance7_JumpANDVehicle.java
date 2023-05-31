package com.prac.zPrac1;




class Primates{  // 영장류 : Primates
    String name;

    void setName(String name){
        this.name = name;
    }
}
class Simian extends Primates{  // 유인원 : Simian
    void eat(){
        System.out.println("eat food");
    }
    void sleep(){
        System.out.println("fall asleep");
    }

}


class Chimpanzee extends Simian{
    void eat(String meat){
        System.out.println("eat food as " + meat);
    }
    void sleep(int hour){
        System.out.println("fall asleep in " + hour);
    }
    void say(){
        System.out.println("my name is " + this.name);
    }

}

class Human extends Simian{
    void eat(String food){
        System.out.println("eat " + food);
    }

    void sleep(String house){
        System.out.println("fall asleep in where "+ house);
    }
    void say(){
        System.out.println("my name is " + this.name);
    }
    void hunt(){
        System.out.println("hunt for prey");
    }
}




public class Inheritance7_JumpANDVehicle {
    public static void main(String[] args) {
        Chimpanzee chimpanzee = new Chimpanzee();


        Human human = new Human();

    }
}
