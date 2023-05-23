package com.prac.zPrac2;


import java.util.function.BiFunction;
import java.util.function.Function;

// 1.기존방식과 람다 방식 차이
// 1)기존
interface LambdaEx2_1_1{
    int sum(int a, int b);
}
class LambdaEx2_1_2 implements LambdaEx2_1_1{
    public int sum(int a, int b){
        return a + b;
    }
}
class LambdaEx2_1_3{
    public static void main(String[] args) {
        LambdaEx2_1_2 lambda = new LambdaEx2_1_2();
        lambda.sum(1,2);
        System.out.println(lambda.sum(1,2)); // 3
    }
}

// 2)람다
interface LambdaEx2_2_1{
    int sum(int a, int b);
}
class LambdaEx2_2_2{
    public static void main(String[] args) {
        LambdaEx2_2_1 lambda = (int a, int b) -> { return a + b; }; // 람다 표현식
        int lambda_return = lambda.sum(1,3);
        System.out.println(lambda_return); // 4
    }
}


// 2.@FunctionalInterface & 함수형 인터페이스 : 추상메소드 1개 only
@FunctionalInterface
interface LambdaEx2_2_3{
    int sum(int a, int b);
//    int subtraction(int a, int b); // 함수형 인터페이스는 추상메소드가 1개여야만 하는데 @FunctionalInterface이 강제함
    // @FunctionalInterface을 사용하므로써 컴파일 단계에서 해당 에러를 잡아낼 수 있게해준다
}



// 3.람다 축약
interface LambdaEx3_1_1{
    int sum(int a, int b);
}
class LambdaEx3_1_2{
    public static void main(String[] args) {
        LambdaEx3_1_1 lambda = (int a, int b) -> { return a + b; }; // 람다 표현식
        LambdaEx3_1_1 lambda1 = (a, b) -> { return a + b; };    // 람다 표현식 생략1
        LambdaEx3_1_1 lambda2 = (a, b) -> a + b;                // 람다 표현식 생략2
        LambdaEx3_1_1 lambda3 = Integer::sum;                   // 람다 표현식 생략3 : 메소드 참조

        int lambda_return = lambda.sum(1,3);
        System.out.println(lambda_return); // 4
    }
}


// 4.내장 함수 인터페이스

public class Lambda2_JumpToJava {
    public static void main(String[] args) {
        BiFunction<Integer,Integer,Integer> bi = ( a,  b) -> a + b;  //
        BiFunction<Integer,Integer,Integer> bi2 = Integer::sum;      // 람다 메서드참조
//        Function<Integer,Integer> function = (int a) -> a;
        Function<Integer,Integer> function2 = (a) -> a;
        // Function<Integer,Integer>에서 Integer를 선언해뒀으므로 int a, int b처럼 데이터타입 선언하면 컴파일 에러 발생하는 것

        int result = 0;
        result = bi.apply(2,4);
        result = bi2.apply(3,4);
        System.out.println(result);
    }
}
