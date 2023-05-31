package com.prac.inheritance;

// 상속 관계에서 어떻게 코드가 움직이지는지 디버깅 찍어봐야하는 코드
class SuperObject{
    public void print(){
        draw();
    }
    public void draw(){
        draw(); // subclass draw()로 내려감
        System.out.println("프린트 : Super Object");
    }
}

class SubObject extends SuperObject{
    public void print(){
        super.draw();
    }
    public void draw(){
        System.out.println("프린트 : Sub Object");
    }
}

public class Inheritance_moveTurn2{
    public static void main(String[]args){

        SuperObject superObj = new SubObject();
        superObj.print(); // 부모(Super)와 자식(Sub) 똑같이 매개변수 없는 print()가 있지만,
        // 부모(super) 타입일 뿐 인스턴스 자체는 자식(sub)이기 때문에 자식의 print()로 찾아간다
//        System.out.println(1%2); // 1
//        System.out.println(2%4); // 2
//        System.out.println(3%4); // 3
//
//        System.out.println(6%3); // 0
//        System.out.println(3%6); // 3
    }
}