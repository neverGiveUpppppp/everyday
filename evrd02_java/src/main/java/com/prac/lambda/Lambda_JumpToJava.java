package com.prac.lambda;



import java.util.function.BiFunction;
import java.util.function.Function;


// 점프투 자바 : 함수형 프로그래밍
// https://wikidocs.net/157858


/*
목차
    1.예제 : 기존 코드 -> 람다 코드 변환
    2.인터페이스 사용 시, 주의사항
        함수형 인터페이스는 추상 클래스와는 달리 단 하나의 추상 메소드만 가질 수 있음
        인터페이스의 메서드가 2개 이상이면 람다함수 사용불가x
        ∴ 람다 함수로 사용할 인터페이스는 @FunctionalInterface을 사용
          @FunctionalInterface을 사용하면 2개 이상의 메서드를 가진 인터페이스를 작성하는 것이 불가능
    3.람다 축약
        1)입력값 데이터타입 생략가능
           before : (int a, int b) -> a + b
           after  : (a, b) -> a + b
        2)메서드 참조 ::
           before : (a, b) -> a + b
           after  : Integer::sum
                (a, b) -> a+b 는 Integer.sum(int a, int b)와 동일하기 때문
                파라미터 중복이 없다면 메서드 참조 표현식 사용불가
                람다표현식에서 불필요한 매개변수를 제거하는 것
    4.람다 함수 인터페이스
    자바가 제공하는 람다함수를 위한 인터페이스들
        1)BiFunction
            자바가 제공하는 BiFunction 인터페이스를 사용하면 Calculator(사용자정의) 인터페이스를 대체 가능
            BiFunction<Integer, Integer, Integer> : BiFunction<입력값1, 입력값2, 출력값>
            *위처럼 제네릭이 다 같은 데이터타입 형태라면, BinaryOperator 사용가능
        2)BinaryOperator<Integer>
    이외에도 자바가 람다함수를 위해 제공하는 인터페이스는 많다
        람다쓸려고 메소드 하나인 인터페이스 생성해서 쓰는건 낭비인데
        이렇게 자바에서 제공하는 람다용 내장인터페이스를 차라리 자주 쓰게 될 듯
 */

// 기존 클래스
interface Calculator1 {
    int sum(int a, int b);
}

class MyCalculator1 implements Calculator1 {
    public int sum(int a, int b) {
        return a+b;
    }
}

class Sample {
    public static void main(String[] args) {
        MyCalculator1 mc = new MyCalculator1();
        int result = mc.sum(3, 4);
        System.out.println(result);  // 7 출력
    }
}


// 1.기존 클래스를 람다로 전환


// 람다 표현식(Lambda Expression)
interface Calculator2 {
    int sum(int a, int b);
//    int mul(int a, int b);  // mul 메서드를 추가하면 컴파일 에러가 발생한다.
}

public class Lambda_JumpToJava {
    public static void main(String[] args) {
        Calculator2 mc = (int a, int b) -> a + b;  // 람다 적용한 부분 : (int a, int b) -> a +b
        int result = mc.sum(3, 4);           //  int a, int b는 Calculator 인터페이스의 sum 함수의 입력항목에 해당하고 -> 뒤의 a+b 가 리턴값에 해당
        System.out.println(result);
        // 람다함수를 사용하면, MyCalculator와 같은 구현체 클래스 없이도 인터페이스Calculator의 객체를 생성 가능
    }
}



/* ------------------------------------------------------------------------------ */



// 2.인터페이스 사용 시, 주의사항
// 함수형 인터페이스는 추상 클래스와는 달리 단 하나의 추상 메소드만 가질 수 있음
// 람다는 인터페이스에 메소드가 2개이상이면 사용불가
// 이런 규칙 때문에 @FunctionalInterface를 사용하여 2개이상 생성되지 않게 강제할 수 있다
@FunctionalInterface
interface Calculator {
    int sum(int a, int b);
//    int mul(int a, int b);  // @FunctionalInterface는 두 번째 메서드를 허용x
}


/* ------------------------------------------------------------------------------ */



// 3.람다 축약
// 1) (int a, int b) -> a + b는 아래처럼 축약가능
//    (a, b) -> a + b
//      인터페이스에 이미 입출력에 대한 타입이 정의되어 있으므로 int는 생략 가능
interface Calculator3 {
    int sum(int a, int b);  // 인터페이스에 이미 입출력에 대한 타입이 선언됨 : sum(int a, int b)
}
class Sample3 {
    public static void main(String[] args) {
        Calculator3 mc = (a, b) -> a + b; // 따라서 입력값에서의 a,b의 int는 생략가능
        int result = mc.sum(3, 4);
        System.out.println(result);
    }
}

// 2) 메서드 참조
/*
 메서드 참조 문법
      클래스이름::메소드이름
      참조변수명::메소드명
 파라미터 중복이 없다면 메서드 참조 표현식 사용불가

 두 수를 더하여 결과를 리턴하는 함수 (a, b) -> a+b 는
 Integer.sum(int a, int b)와 동일하기 때문에 다음과 같이 더 축약이 가능
      ex) Integer.sum
*/

@FunctionalInterface
interface Calculator4 {
    int sum(int a, int b);
}
class Sample4 {
    public static void main(String[] args) {
        Calculator4 mc = Integer::sum;    // Integer :: sum
        int result = mc.sum(3, 4);  //  클래스명 :: 메소드명
        System.out.println(result);
    }
}




/* ------------------------------------------------------------------------------ */




// 4.람다 함수 인터페이스
// 자바가 제공하는 람다함수를 위한 인터페이스들
// 인터페이스를 생성하는 대신 함수형 프로그래밍을 위해 제공되는 인터페이스들을 사용하여 위 코드를 좀 더 축약해보자
/*
    자바가 제공하는 BiFunction 인터페이스를 사용하면 Calculator(사용자정의) 인터페이스를 대체 가능
    BiFunction<Integer, Integer, Integer> : BiFunction<입력값1, 입력값2, 출력값>
        *위처럼 제네릭이 다 같은 데이터타입 형태라면, BinaryOperator 사용가능
    BinaryOperator<Integer>
    이외에도 자바가 람다함수를 위해 제공하는 인터페이스는 많다
 */


//@FunctionalInterface    ← BiFunction로 대체
//interface Calculator{
//    int sum(int a, int b);
//}
class Sample5 {
    public static void main(String[] args) {
        BiFunction<Integer, Integer, Integer> mc = (a, b) -> a + b;     // BiFunction<입력값1, 입력값2, 출력값>
//      BinaryOperator<Integer> mc = (a, b) -> a + b;   // 전부 동일한 타입이라면 BinaryOperator<Integer> 대체가능
        int result = mc.apply(3, 4);  // sum이 아닌 apply 메서드를 사용해야 한다.
        System.out.println(result);  // 7 출력


        BiFunction<Integer,Integer,Integer> bi2 = Integer::sum;      // 람다 메서드참조
//        Function<Integer,Integer> function = (int a) -> a;
        Function<Integer,Integer> function2 = (a) -> a;
        // Function<Integer,Integer>에서 Integer를 선언해뒀으므로 int a, int b처럼 데이터타입 선언하면 컴파일 에러 발생하는 것

    }
}

class Sample5_2{
    public static void main(String[] args) {
        BiFunction<String, String, String> bi = (a,b) ->{
        a = "a"; // 변수a로 str_a값이 들어와서 a="str_a"가 되지만, a에 다시 "a"가 들어와서 덮힘
        b = "b";
        return a+b;
        };
        String str = bi.apply("str_a ","str_b "); // apply로 붙인 str_a값이 위의 람다함수 안에 a="a"에 묻힘
        System.out.println(str); // ab
    }
}











