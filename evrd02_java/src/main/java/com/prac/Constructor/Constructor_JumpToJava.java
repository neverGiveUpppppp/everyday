package com.prac.Constructor;


class Animal{
    String name;

    void setName(String name){
        this.name = name;
    }
}

class Dog extends Animal {
    void sleep(){
        System.out.println(this.name + " zzz");
    }
}

class HouseDog extends Dog {
    HouseDog(String name){    // 생성자 오버로딩(Overloading)
        this.setName(name);
    }
    HouseDog(int type){       // 생성자 오버로딩(Overloading)
        if(type == 1){
            this.setName("yorkshire");
        }else if(type == 2){
            this.setName("bulldog");
        }
    }


    void sleep(){
        System.out.println(this.name + " zzz in house");
    }
    void sleep(int hour){
        System.out.println(this.name + " zzz in house for " + hour + "hours");
    }

}


public class Constructor_JumpToJava {
    public static void main(String[] args) {
//        HouseDog dog = new HouseDog();
//        System.out.println(dog.name); // null : 현재 넣은 값이 없음
/*
Q.name이라는 객체변수에 값을 무조건 설정해야만 객체가 생성될 수 있도록 강제할 수 있는 방법은 없을까?
A.생성자(Constructor)
생성자는 객체가 생성될 때 호출된다. 즉, 생성자는 다음과 같이 new 키워드가 사용될 때 호출된다.

Class HouseDog에 코드 추가
    HouseDog(String name){
        this.setName(name);
    }


 클래스에 생성자가 하나도 없다면, 컴파일러가 자동으로 디폴트 생성자를 추가한다.
 하지만 사용자가 작성한 생성자가 하나라도 구현되어 있다면 컴파일러는 디폴트 생성자를 추가하지 않는다

Class Dog에 기본생성자(default constructor) 추가
Dog() {
    }

 */
        HouseDog dog = new HouseDog("poppy");
        System.out.println(dog.name); // Happy : 생성자로 인해 값이 들어가면서 Happy출력
        HouseDog happy = new HouseDog("Happy"); // 생성자 오버로딩(Overloading)
        HouseDog yorkshire = new HouseDog(1);    // 생성자 오버로딩(Overloading)
        System.out.println(happy.name);
        System.out.println(yorkshire.name);

    }
}
