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
        superObj.print();
//        System.out.println(1%2); // 1
//        System.out.println(2%4); // 2
//        System.out.println(3%4); // 3
//
//        System.out.println(6%3); // 0
//        System.out.println(3%6); // 3
    }
}