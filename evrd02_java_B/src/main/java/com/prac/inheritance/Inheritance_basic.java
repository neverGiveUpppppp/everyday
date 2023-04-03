package com.prac.inheritance;

class Parent {
    void show() { System.out.println("parent"); }
}
class Child extends Parent {
    void show() { System.out.println("child"); }
}
public class Inheritance_basic {
    public static void main(String[] args) {
        Parent pa = new Child();
        pa.show();
    }
}