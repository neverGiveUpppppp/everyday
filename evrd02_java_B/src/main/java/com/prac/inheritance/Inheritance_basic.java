package com.prac.inheritance;

class Parent {
    void show() {
        System.out.println("parent");
    }
}
class Child extends Parent {
    void show() {
        System.out.println("child");
    }
}
public class Inheritance_basic {
    public static void main(String[] args) {
        Parent pa = (Parent) new Child();  // (Parent) : 내부 코드에는 이렇게 부모형으로 강제 형변환 되어있음
        pa.show(); // 부모와 자식의 show() 오버로딩 없이 형태가 같기 때문에(매개변수 종류,타입,순서와 void도 같고 등)
        // 자료형이 Parent이지만 인스턴스 자체는 Child이므로 show()는 Child쪽으로 찾아감.
    }
}