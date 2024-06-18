package com.prac.inheritance;

class A{
    int a;

    A(int a){ // A class의 생성자

        this.a = a;
    }
    void display(){

        System.out.println("a = "+a);
    }
}

class B extends A{
    B(int a){
        super(a);
        super.display();
    }
}

public class Inheritance_moveTurn1 {
    public static void main(String[] args) {

        B obj = new B(10);
    }
}
