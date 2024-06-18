package com.prac.temp;

class A {
    int a;
    int b;
    int c;
}
public class Test_Reference {
    static void func1(A m) {
        m.a *= 10;
    }

    static void func2(A m) {
        m.a += m.b;
    }

    public static void main(String args[]) {
        A m = new A();
        m.a = 100;
        m.c = 100;
        func1(m);
        m.b = m.a;
        func2(m);
        System.out.printf("%d%n", m.a);
// 객체 변수나 배열의 이름은 객체 변수나 배열의 시작 주소를 가리키므로, 인수로 전달하는 경우
// 메소드에서 변경된 값이 main( )의 객체 변수나 배열에도 적용
//     -> Reference Type 연관

    }
}