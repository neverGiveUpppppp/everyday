package com.prac.zPrac2;


import java.util.function.*;

@FunctionalInterface
interface RunSomething{
    void doIt();
//        void doIt2(); // @FunctionalInterface로 인한 컴파일 에러 발생.
    // 함수형 인터페이스는 추상메소드 1개만 가능. 다른 static, default는 괜찮음

    static void name(){
        System.out.println("static method");
    }
    default void name2(){
        System.out.println("default method");
    }
}

@FunctionalInterface
interface RunSomething2{
    int doit(int num);

}

class ex1_1{
    public static void main(String[] args) {
        RunSomething runSomething = () -> System.out.println("Hello"    ); // 함수 내부 한 줄, {} 생략가능
        RunSomething runSomething1 = () -> {
            System.out.println("함수 내부 두 줄이상"); // 함수 내부 두 줄이상, {} 필수
            System.out.println("{} 필수");
        };
    }
}


/*

람다는 함수형 인터페이스를 인라인형태로 구현한 오브젝트
자바는 객체지향 언어이기에 이를 변수에 할당하고 메소드의 파라미터로 전달하고 리턴타입으로 리턴할 수도 있음
-> 자바에서 함수를 first class object로 사용할 수 있다는 의미
    대입연산자(=) 기준 우측의 코드들을 변수로 할당하고 이를 다시 메소드의 파라미터로 전달하거나 리턴타입으로 리턴할 수도 있음

고차함수란?
함수가 함수를 파라미터로 받거나 리턴하는 함수

순수함수란?
순수함수가 아닌 상태의 경우
    1)상태값을 가지고 있다, 상태값에 의존한다의 경우
    2)외부에 있는 값을 변경할려는 경우

 */
class functional_Prgrming{
    public static void main(String[] args) {
        RunSomething2 runSomething2 = (number) -> {
            return number + 10;
        };
        System.out.println(runSomething2.doit(10));
        System.out.println(runSomething2.doit(10)); // 10을 넣으면20이 일정하게 나와야 함수형 프로그래밍
        System.out.println(runSomething2.doit(20)); // 이렇게 값이 변동 가능하면 함수형 프로그래밍이라 보기 어렵
        System.out.println(runSomething2.doit(20));
        System.out.println(runSomething2.doit(20));

    }
}

class PureFunction{
    public static void main(String[] args) {
        // 1)상태값을 가지고 있다, 상태값에 의존한다의 경우

        int baseNum = 10; // 함수 밖
        RunSomething2 runSomething4 = new RunSomething2() {
            int baseNumber = 10; // 함수 밖. 이런 경우 순수한 함수라고 볼 수 없고 "상태값을 가지고 있다, 상태값에 의존한다"라고 이야기함

            @Override
            public int doit(int num) {
                return num + baseNumber;    // return num + baseNumber;에서 baseNumber을 사용하는게
                // 상태값을 가지고있다, 상태값에 의존한다고 라고 말함
            }
        };

        // 2)외부에 있는 값을 변경할려는 경우
        int baseNumber2_1 = 10;
        RunSomething2 runSomething5 = new RunSomething2(){
            int baseNumber2_2 = 20;
            @Override
            public int doit(int num) {
//                    baseNumber2_1++;  // 위의  int baseNumber2_1 = 10;에 값을 변경할려함. 자바에서는 문법자체로 막혀있음
                baseNumber2_2++;    // 위의  int baseNumber2_2 = 20;에 값을 변경할려함. 컴파일 에러는 안뜨나 순수함수는 아님
                return num + baseNumber2_2;
            };


        };

    }
}


/********************************************** B.Java Functional Interface *********************************************************************/

// 1.Function<T, R>
//     T가 입력값 R이 리턴값
//     R apply(T t)
// 방법1
class Func implements Function<Integer, Integer> {
    @Override
    public Integer apply(Integer integer){
        return integer + 10;
    }
}
class Func_embodiment{    // 구현체 : embodiment
    public static void main(String[] args) {
        Func func = new Func();
        func.apply(5);
        System.out.println(func.apply(5)); // 15

    }
}

// 람다 + 내장 함수형인터페이스 Function
class Func_Lambda{
    public static void main(String[] args) {
        Function<Integer,Integer> function = (i) -> i + 10;
//        Function<Integer,Integer> function1 = (int i) + 10; // int i에 타입쓰면 에러 발생. 이미 Function<Integer,Integer>에서 타입선언했기 때문
        function.apply(1);
        System.out.println(function.apply(1));
    }
}


//  default method : andThen(), compose()

class defaultMethod{
    public static void main(String[] args) {
        Function<Integer,Integer> plus10 = (i) -> i + 10;
        Function<Integer,Integer> multiply2 = (i) -> i * 2;

        // 함수 조합하기1 : compose()
        // variable.compose() : compose() 소괄호 뒤부터 먼저 수행하고 이후에 앞에 variable을 수행함
        //      고차함수(Higher-Order Function) 성격을 가짐(함수가 함수를 매개변수로 사용하거나 함수를 리턴하는 것)
        plus10.compose(multiply2);
        System.out.println(plus10.compose(multiply2).apply(2)); // 14  = 10 + (2 * 2)

        Function<Integer, Integer> compose = plus10.compose(multiply2);
        compose.apply(5);
        System.out.println(compose.apply(2)); // 14  = 10 + (2 * 2)


        // 함수 조합하기2 : andThen()
        // compose()와 반대순서로 계산. 뒤에다가 붙이는 것.
        plus10.andThen(multiply2).apply(2);
        System.out.println(plus10.andThen(multiply2).apply(2)); // 24   = (2+10) * 2
    }
}


// 2.BiFunction<T, U, R>
//     두 개의 값(T,U)를 받아서 R타입을 리턴하는 함수 인터페이스
//        R apply(T t, U u)
class BiFunc{
    public static void main(String[] args) {
        BiFunction<Integer,Integer,Integer> biFunction = (i,j) -> i + j;
        BiFunction<Integer,Integer,String> biFunction2 = (i,j) -> String.valueOf(i + j);
        BiFunction<Integer,Integer,String> biFunction3 = (i,j) -> String.valueOf(i + j) + " String return";

        biFunction.apply(1,1);
        biFunction2.apply(1,2);
        biFunction3.apply(1,2);
        System.out.println(biFunction.apply(1,1)); // 2
        System.out.println(biFunction2.apply(1,2)); // 3                <- String 타입
        System.out.println(biFunction3.apply(1,2)); // 3 String return  <- String 타입
    }
}



//3.Consumer<T>
//    ● T 타입을 받아서 아무값도 리턴하지 않는(void) 함수 인터페이스
//        리턴이 없기 때문에 void
//        ○ void Accept(T t)
//    ● 함수 조합용 메소드
//        ○ andThen
class Consumer_{
    public static void main(String[] args) {
        Consumer<String> consumer = (str) -> System.out.println(str);
        consumer.accept("hello");   // hello
        System.out.println(consumer); // com.prac.zPrac2.Consumer_$$Lambda$14/0x0000000800c01200@41629346
    }
}


//4.Supplier<T>
//    ● T 타입의 값을 제공하는 함수 인터페이스
//        값을 하나 받아오는데 받아올 값의 타입을 지정함
//        ○ T get()
class Supplier_{
    public static void main(String[] args) {
        Supplier<String> supplier1 = () -> "서플라이어"; // ()선언부가 비어있어야함
        Supplier<Integer> supplier2 = () -> 10;

        System.out.println(supplier1);          // $$Lambda$14/0x0000000800c01200@41629346
        System.out.println(supplier2);          // $$Lambda$15/0x0000000800c01418@404b9385

        // Supplier의 값을 얻을려면 .get() 해야함
        supplier1.get();
        supplier2.get();
        System.out.println(supplier1.get());    // 서플라이어
        System.out.println(supplier2.get());    // 10
    }
}


//5.Predicate<T>
//    ● T 타입을 받아서 boolean을 리턴하는 함수 인터페이스
//        어떤 값을 하나 받아서 true false를 리턴해줌
//        ○ startsWith()
//        ○ boolean test(T t) : t 파라미터값을 넣어서 똑같은 게 있으면 true 아니면 false
//    ● 함수 조합용 메소드
//        ○ And
//        ○ Or
//        ○ Negate
class Predicate_{
    public static void main(String[] args) {
        Predicate<String> predicate1 = (s) -> s.startsWith("My name");
        Predicate<Integer> predicate2 = (i) -> i % 2 == 0;  // boolean으로 리턴할 수 있게 ==


        predicate1.test("My name");
        predicate2.test(2);
        System.out.println(predicate1.test("My "));         // false
        System.out.println(predicate1.test("My nam"));      // true          <- 똑같은 문자만 찾음
        System.out.println(predicate1.test("My name"));     // true         <- 같은 문자가 있어서 true 반환
        System.out.println(predicate2.test(1));             // false
        System.out.println(predicate2.test(2));             // true         <- 위에 람다식에서 나머지가 0이어서 true 반환

        System.out.println("======================");
        // 함수 조합 가능 :
        //      or() + test()
        //      and() + test()
        // true나 false에 대해서 not을 붙인다거나 and, or 조합 가능
        predicate1.or(predicate1);
        predicate1.and(predicate1);
        predicate1.or(predicate1).test("My name");
        predicate1.and(predicate1).test("My name");
        System.out.println(predicate1.or(predicate1));  // Predicate$$Lambda$16/0x0000000800c466a8@6d311334
        System.out.println(predicate1.and(predicate1)); // Predicate$$Lambda$18/0x0000000800c46d20@3d075dc0
        System.out.println(predicate1.or(predicate1).test("My name"));  // true
        System.out.println(predicate1.and(predicate1).test("My name")); // true
    }
}


//6.UnaryOperator<T>
//    ● Function<T, R>의 특수한 형태로,입력값 하나를 받아서 동일한 타입을 리턴하는 함수인터페이스
//        Function을 상속 받았기에 Function에서 제공하는 default method인 compose(), andThen() 사용가능
//    ● T,R 두 타입이 같을 경우 사용 가능
class UnaryOperator_{
    public static void main(String[]args){
        UnaryOperator<Integer> unaryOperator1 = (i) -> i + 10;
        UnaryOperator<String> unaryOperator2 = (s) -> "My name is " + (s);

        unaryOperator1.apply(1);
        unaryOperator2.apply("Robert");
        System.out.println(unaryOperator1.apply(1));          // 11
        System.out.println(unaryOperator2.apply("Robert"));   // My name is Robert

        // compose() : 소괄호 뒤부터 먼저 수행하고 이후에 앞에 variable을 수행함
        // andThen() : 앞 변수와 apply() 값 먼저 계산하고 andThen()의 파라미터를 계산함
        unaryOperator1.compose(unaryOperator1).apply(5);
        unaryOperator1.andThen(unaryOperator1).apply(5);
        System.out.println(unaryOperator1.compose(unaryOperator1).apply(5)); // 25 = 10+(5+10)
        System.out.println(unaryOperator1.andThen(unaryOperator1).apply(6)); // 26 = 10+(6+10)
    }
}





/********************************************** C.Lambda Expression *********************************************************************/


/*
인자 리스트
    ● 인자가 없을 때: ()
    ● 인자가 한개일 때: (one) 또는  one
    ● 인자가 여러개 일  때: (one, two)
    ● 인자의 타입은 생략 가능, 컴파일러가 추론(infer)하지만 명시할 수도 있다. (Integer one,Integer two)
바디
    ● 화살표 오른쪽에 함수 본문을 정의함
    ● 여러 줄인 경우에 { }를 사용해서 묶는다.
    ● 한 줄인 경우에 생략 가능, return도 생략 가능.
 */

// 인자 리스트
class Lambda_Argument_List{
    public static void main(String[] args) {
        // ● 인자가 없을 때: ()
        Supplier<Integer> supplier1 = () -> 10;
        Supplier<Integer> supplier2 = () -> {
            return 10;
        };

        // ● 인자가 한개일 때: (one) 또는  one
        Function<Integer,Integer> function1 = (i) -> i + 5;
        Function<Integer,Integer> function2 = (i) -> {
            return i + 5;
        };

        // ● 인자가 여러개 일  때: (one, two)
        BiFunction<Integer,Integer,Integer> biFunction1 = (a, b) -> a + b;
        BiFunction<Integer,Integer,Integer> biFunction2 = (a, b) -> {
            return a + b;
        };

        // ● 인자의 타입은 생략 가능, 컴파일러가 추론(infer)하지만 명시할 수도 있다. (Integer one,Integer two)
        UnaryOperator<Integer> unaryOperator1 = (i) -> i % 4;           // 변수 i의 타입 생략가능(타입추론)
        UnaryOperator<Integer> unaryOperator2 = (Integer i) -> i % 4;   // 타입 생략x
    }
}
// 바디
class Lambda_Body{
    public static void main(String[] args) {

        // ● 화살표 오른쪽에 함수 본문을 정의함
        BinaryOperator<Integer> binaryOperator = (a,b) -> a + b; // 함수 본문 정의 : a + b

        // ● 여러 줄인 경우에 { }를 사용해서 묶는다.
        BiFunction<String,String,String> biFunction = (s1, s2) -> {
            s1 = "My name is " + s1;
            s2 = "My phone is " + s2;
            return s1 + "\n" + s2;
        };
        biFunction.apply("Robert","iPhone");
        System.out.println(biFunction.apply("Robert","iPhone")); // My name is Robert
                                                                       // My nphone is iPhone

        // ● 한 줄인 경우에 생략 가능, return도 생략 가능.
        Function<String,String> function1 = (s) -> "Hi " + s; // {} 및 return 생략함
        Function<String,String> function2 = (String s) -> {   // 생략 안 된 원문
            return "Hi " + s;
        };
    }
}




/*
변수 캡처(Variable Capture)
    ● 로컬 변수 캡처
        ○ final이거나 effective final인 경우에만 참조가능
        ○ 그렇지 않을 경우, concurrency동시성 문제가 생길 수 있어 컴파일이 방지함
    ● effective final
        ○ 이것도 역시 자바 8부터 지원하는 기능으로 “사실상" final인 변수
        ○ final 키워드 사용하지 않은 변수를 익명 클래스 구현체 또는 람다에서 참조가능

    ● 익명 클래스 구현체와 달리 ‘쉐도윙’하지 않는다.
        ○ 익명 클래스는 새로 scope을 만들지만, 람다는 람다를 감싸고 있는 scope이 같다.
 */
class Variable_Capture{
    // ● 로컬 변수 캡처
    public static void main(String[] args) {
        Variable_Capture variableCapture = new Variable_Capture();
        variableCapture.run();
        System.out.println("==========");
        variableCapture.run2();


    }
    public void run(){
        int baseNumber = 10;    // baseNumber라는 로컬변수가 캡처가 됨
        IntConsumer printInt = (int i) -> {
            System.out.println(i + baseNumber); // 람다에서 local variable인 baseNumber를 참조하고 있음
        };
        printInt.accept(10); // 출력 : 20
    }
/*
변수 캡처 기능은 익명클래스나 내부클래스에서도 쓰이던 기능
또한 자바8이전에는 반드시 final 키워드와 붙어있어야만 했음
    내부 클래스 : 메소드 내부에 정의되는 로컬 클래스
 */


// scope 범위 차이 : 로컬클래스 vs 익명클래스 vs 람다
//      참조하는 local variable이 달라진다
    public void run2(){
        final int baseNumber = 10;

        // 1.로컬 클래스(local class)
        class LocalClass{
            void printBaseNum(){
                int baseNumber = 12;
                System.out.println(baseNumber); // 12 // baseNumber 찍어보면 local variable인 baseNumber=12;로 감
                                                // 바로 위 int baseNumber = 12;를 참조(분리된 scope)
            }
        }

        // 2.익명 클래스(anonymous class)
        Consumer<Integer> consumer = new Consumer<>(){
            @Override
            public void accept(Integer i){
                int baseNumber = 11;
                System.out.println(i + baseNumber); // 21 // 바로 위 int baseNumber = 11;를 참조(분리된 scope)
            }
        };

        // 3.람다(Lamda)
        IntConsumer printInt = (i) -> {
            System.out.println(i + baseNumber); // 20 // run2() 바로 아래 final int baseNumber = 10;의 baseNumber를 참조함
                                                // 람다를 포함하는 메소드(run2())와 scope 공유
        };

        new LocalClass().printBaseNum(); // 1.로컬 클래스 호출 : 12
        consumer.accept(10);          // 2.익명 클래스 호출 : 21
        printInt.accept(10);       // 3.람다 호출 : 20
        

    }

}


/***************************************** D.Method Reference ****************************************/



public class Lambda5_whiteship {

}







