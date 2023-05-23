package com.prac.zPrac2;

// 1.기존 클래스를 람다로 전환
//// 기존 방식
//interface Calc2_1{
//    int sum(int a, int b);
//}
//class MyCalc2_1 implements Calc2_1{
//    public int sum(int a, int b){
//        return a + b;
//    }
//}
//public class Lambda2 {
//    public static void main(String[] args) {
//        MyCalc2_1 c = new MyCalc2_1();
//        int num = c.sum(1,2);
//        System.out.println(num);
//    }
//}
//interface Calc2_11{
//    int sum(int a, int b);
//}
//class MyCalc2_11{
//    public int sum(int a, int b){
//        return a + b;
//    }
//}
//class MyCalc2_111{
//    public static void main(String[] args) {
//       MyCalc2_11 c = new MyCalc2_11();
//       int num = c.sum(2,4);
//        System.out.println(num);
//    }
//}

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

// 람다표현식으로 전환
interface Calc2_22{
    int sum(int a, int b);
}
class MyCalc2_22{
    public static void main(String[] args) {
//        // 익명내부클래스(Anonymous inner class)
//        Calc2_22 c = new Calc2_22() {
//            @Override
//            public int sum(int a, int b) {
//                return 0;
//            }
//        }
        Calc2_22 c = (a, b) -> a + b;
        int num = c.sum(1,6);
        System.out.println(num);
    }
}
interface Calc2_2{
    int sum(int a, int b);
}

class MyCalc2_2{
    public static void main(String[] args) {
        Calc2_2 c = (int a, int b) -> a + b;
        int num = c.sum(1,4);
        System.out.println(num);
    }
}

// 2. 함수형 인터페이스 사용 시, 주의사항
@FunctionalInterface // 함수형 인터페이스가 될 수 있게 추상메소드를 1개만 강제하는 어노테이션
// 2개 이상되면 밑줄 에러 뜬다
interface Calc2_23{
    int sum(int a, int b);
//    int mul(int a, int b); // 람다용 인터페이스는 추상메소드가 1개여야만함. 다른 디폴트,스태틱 같은 메소드는 상관x only abstract method
}



// 3.람다 축약
interface Calc2_3{
    int sum(int a, int b);
}
class Calc2_4{
    public static void main(String[] args) {
        Calc2_3 c = (a, b) -> {
            return a + b;
        };
        int number = c.sum(3,1);
        System.out.println(number);
    }
}
class Calc2_5{
    public static void main(String[] args) {
        Calc2_3 c = Integer::sum;
        int num = c.sum(3,2);
        System.out.println(num);
    }
}


// 4.람다 함수 인터페이스
class Calc2_6{
    public static void main(String[] args) {
        BiFunction<Integer, Integer, Integer> bi = ( a,  b) -> a + b;
        int add = bi.apply(1,3);
        System.out.println(add);
    }
}
class Calc2_7{
    public static void main(String[] args) {
        BiFunction<String, String, String> bi = (a,b) ->{
            a = "a"; // 변수a로 str_a값이 들어오나 a에 다시 "a"가 들어와서 덮힘
            b = "b";
            return a+b;
        };
        String str = bi.apply("str_a ","str_b "); // apply로 붙인 str_a값이 위의 람다함수 안에 a="a"에 묻힘
        System.out.println(str);
    }
}
class Calc2_8{
    public static void main(String[] args) {
        BinaryOperator<Integer> bo = (a,b) -> {
            return a + b;
        };
        int num = bo.apply(2,3);
        System.out.println(num);
    }
}
class Calc2_9{
    public static void main(String[] args){
        BinaryOperator<String> bo = (String a, String b) -> {
            return a + b;
        };
        String words = bo.apply("aaa", "bbb");
        System.out.println(words);
    }
}

interface Calc2_10_1{
    int exec(int a, int b);
}
class Calc2_10_2{
    public static void main(String[] args){
        exec((a,b) -> {
            return a + b;
        });
    }
    public static void exec(Calc2_10_1 c){
        int q = 10;
        int w = 20;
        int values = c.exec(q, w);
        System.out.println(values);
    }

}



