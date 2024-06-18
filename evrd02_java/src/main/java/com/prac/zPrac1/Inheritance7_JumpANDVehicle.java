package com.prac.zPrac1;




class Primates{  // 영장류 : Primates
    String name;
    String birth;

    void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
}
class Simian extends Primates{  // 유인원 : Simian
    public Simian(String birth){
        this.birth = birth;
    }
    void eat(){
        System.out.println("eat food");
    }
    void say(){
        System.out.println("my name is " + this.name);
    }
    void sleep(){
        System.out.println("fall asleep");
    }

}


class Chimpanzee extends Simian{
    public Chimpanzee(String birth) {
        super(birth);
    }

    void eat(String meat){
        System.out.println("eat food as " + meat);
    }
    void sleep(int hour){
        System.out.println("fall asleep in " + hour);
    }

}

class Human extends Simian{
    public Human(String birth) {
        super(birth);
    }

    void eat(String food){
        System.out.println("eat " + food);
    }

    void sleep(String house){
        System.out.println("fall asleep in where "+ house);
    }
    void hunt(){
        System.out.println("hunt for prey");
    }
}




public class Inheritance7_JumpANDVehicle {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();


        Chimpanzee chimpanzee = new Chimpanzee("침팬치");
        chimpanzee.setName("chimny");
        System.out.println("my name is " + chimpanzee.name);
        chimpanzee.eat();
        chimpanzee.sleep(5); // fall asleep in 5
        chimpanzee.say();
        System.out.println("===============");

        Human human = new Human("인간");
        human.setName("Mark");
        System.out.println("my name is " + human.name);
        human.eat();             // eat food : Simian class의 eat() 사용. eat() 매개변수가 없기 때문에 일로 찾아간 듯
        human.eat("갈비");  // eat 갈비 : Human class의 eat() 사용. eat(String food)
        human.sleep();           // fall asleep : Simian class의 sleep() 사용. 매개변수 없는 거 찾아감
        human.sleep("빌라"); // fall asleep in where 빌라
        human.say();
        human.hunt();

        sb.append("===============\n");
        chimpanzee.getName();
        sb.append(chimpanzee.getName() + "\n");
        sb.append("===============\n");

        Simian simian = new Simian("유인원");
        String birth = simian.birth;

        sb.append(birth + "\n");
        sb.append(simian.birth + "\n");
//        simian.birth("a");
//        chimpanzee.birth();
//        chimpanzee.birth("a");
        sb.append(chimpanzee.birth + "\n");
        sb.append(human.birth + "\n");
        sb.append(chimpanzee.birth + "\n");
        sb.append(human.birth + "\n");

        System.out.println(sb.toString());

    }
}
