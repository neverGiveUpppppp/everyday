package com.prac.zPrac2;



//// 1.기존 코드 to 람다코드 변환
//// 기존 코드
//interface Calc1{
//    int sum(int a, int b);
//}
//class MyCalc1 implements Calc1{
//    public int sum(int a, int b){
//        return a+b;
//    }
//}
//class Trandition{
//    public static void main(String[] args) {
//        MyCalc1 myCalc2 = new MyCalc1();
//        int result = myCalc2.sum(3,4);
//        System.out.println(result); // 7
//    }
//}
//
//// 람다코드
//interface Calc2{
//    int sum(int a, int b);
//}
//public class Lambda1 {
//    public static void main(String[] args) {
//        Calc2 mc = (int a, int b) ->  a + b;
//        int result = mc.sum(3,5);
//        System.out.println(result);  // 8
//    }
//}


//// 2.람다 사용 주의사항
//// 람다는 인터페이스에 메소드가 2개이상이면 사용불가
//// 이런 규칙 때문에 @FunctionalInterface를 사용하여 2개이상 생성되지 않게 강제할 수 있다
//@FunctionalInterface // @FunctionalInterface를 붙이면 2개 이상의 메서드를 가진 인터페이스 생성불가
//interface Calc3{
//    int sum(int a, int b);
////    int mul(int a, int b); // @FunctionalInterface에 빨간줄 에러
////                              multiple non-overriding abstract methods found in interface
//
//}
//class Lambda1_1{
//    public static void main(String[] args) {
//        Calc3 c = (a,b) -> a + b;
//        int result = c.sum(1,3);  // 4
//        System.out.println(result);
//    }
//}


import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

// 3.람다 축약
// 1)입력값 데이터타입 생략가능
@FunctionalInterface // 인터페이스 메소드2개이상 방지
interface Calc1_4{
    int sum(int a, int b);
}
class Lambda1_2{
    public static void main(String[] args) {
        Calc1_4 c = (a, b) -> a + b;      // (int a, int b)를 (a, b)로 축약. 어차피 interface에서 데이터형이 선언되어있기에 가능한 것
        int result = c.sum(1,5);
        System.out.println(result);
    }
}


// 2)메서드 참조
// 메서드 참조 문법
//      클래스이름::메소드이름
// 파라미터 중복이 없다면 메서드 참조 표현식 사용불가
interface Calc1_5{
    int sum(int a, int b);
}
class Lambda1_3{
    public static void main(String[] args) {
        Calc1_5 c1 = (a, b) -> a + b;
        Calc1_5 c2 = c1::sum;            //      c1 :: sum
        int result1 = c2.sum(1,5); // 참조변수명::메소드명

        Calc1_5 c = Integer::sum;        // Integer :: sum
        int result2 = c.sum(1,6);  //  클래스명 :: 메소드명
        System.out.println(result2);
    }
}





// 4.람다 함수 인터페이스
// 자바가 제공하는 람다함수를 위한 인터페이스들

// 1)BiFunction
class Lambda1_4{
    public static void main(String[] args) {
        // 람다쓸려고 메소드 하나인 인터페이스 생성해서 쓰는건 낭비인데
        // 이렇게 자바에서 제공하는 람다용 내장인터페이스를 차라리 자주 쓰게 될 듯
        BiFunction<Integer,Integer,Integer> bi = (a,b) -> a + b;
        int result = bi.apply(1,5); // BiFunction에서 사용 가능한 더하기 메소드가 정해져있음
//        bi.andThen() // BiFunction 전용 메소드는 apply()와 andThen()뿐 나머지 사용가능한 메소드는 최상위클래스 Object 메소드들임
    }
}

// 2)BinaryOperator
class Lambda1_5{
    public static void main(String[] args) {
        BinaryOperator<Integer> bo = (a, b) -> a + b;
        int result = bo.apply(1,2); // 얘도 BiFunction과 마찬가지로 전용 메소드는 apply()와 andThen()뿐
    }
}




