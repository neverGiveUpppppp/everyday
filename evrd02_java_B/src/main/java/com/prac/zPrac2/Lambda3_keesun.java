package com.prac.zPrac2;



/*

A.Functional Interface and Lambda



 */


import java.util.function.*;

interface Lambda3_1_1{
    void doIt();
    default void printAge(){
        System.out.println("default method");
    }
    static void printAge2(){
        System.out.println("static method");
    }
}
interface Lambda3_1_2{
    int doIt(int number);
}



// 익명클래스 & 람다
class Lambda3_1_3{
    public static void main(String[] args) {

        // 익명 내부 클래스(anonymous inner class)
        // 자바8 이전에는 인터페이스를 쓸려면 구현체를 만들어야하는데 아래처럼 익명내부클래스를 만들어서 사용함
        Lambda3_1_1 anonymousClass1 = new Lambda3_1_1(){
            @Override
            public void doIt(){
                System.out.println("Anonymous inner class");
            }
        };
        Lambda3_1_2 anonymousClass2 = new Lambda3_1_2(){
            @Override
            public int doIt(int number){
                System.out.println("Anonymous inner class");
                return number;
            }
        };

        // 익명클래스 to 람다 변환
        Lambda3_1_1 ano_to_lam1 = () -> System.out.println("람다 변환");
        Lambda3_1_2 ano_to_lam2 = (int number) -> {
            return number + 10;
        };
        Lambda3_1_2 ano_to_lam3 = (int a) -> a;
        Lambda3_1_2 ano_to_lam4 = (a) -> a;
        Lambda3_1_2 ano_to_lam5 = (a) -> {
            return a;
        };
    }
}



/*

람다는 함수형 인터페이스를 인라인형태로 구현한 오브젝트
자바는 객체지향 언어라 이를 변수에 할당하고 메소드의 파라미터로 전달하고 리턴타입으로 리턴할 수도 있음
-> 자바에서 함수를 first class object로 사용할 수 있다는 의미


고차함수란?
함수가 함수를 파라미터로 받거나 리턴하는 함수


 */
class Lambda3_1_4{
    public static void main(String[] args) {
        Lambda3_1_2 lambda = (number) -> {
            return number + 10;
        };
        System.out.println(lambda.doIt(1));
        System.out.println(lambda.doIt(1)); // 1 넣어서 11이 일정하게 나와야 함수형 프로그래밍

        System.out.println(lambda.doIt(2)); // 이렇게 값이 바뀔 수 있으면 함수형 프로그래밍x
    }
}



class Lambda3_1_5{
    public static void main(String[] args) {

        // 1) 상태값을 가지고 있다, 상태값에 의존한다의 경우
        int baseNum = 10; // 함수 밖
        Lambda3_1_2 lambda = new Lambda3_1_2(){
            int baseNum = 10; // 함수 밖. 이런 경우 순수한 함수라고 볼 수 없고 "상태값을 가지고 있다, 상태값에 의존한다"라고 이야기함
            @Override
            public int doIt(int number){
                return number + baseNum;
            }
        };

        // 2) 외부에 있는 값을 변경할려는 경우
        int baseNum2 = 10;
        Lambda3_1_2 lambda2 = new Lambda3_1_2() {
            int baseNum22 = 20;
            @Override
            public int doIt(int number){
//                baseNum2 ++;    // 함수 밖 전역변수격인 baseNum2의 값을 변경할려는 코드. 자바 문법 자체에 금지
                baseNum22 ++;   // 함수 안 지역변수 baseNum22의 값을 변경할려는 코드. 문법상 가능은 하나 순수함수는 아니게됨
                return number + baseNum22;
            }
        };
    }
}



/*******************************************************************************************************/

/*

B. java Functional Interface


1.Function<T, R>
    입력값ㅇ 1개 리턴값ㅇ
2.BiFunction<T, U, R>
    입력값ㅇ 2개 리턴값ㅇ
3.Consumer<T>
    입력값ㅇ 1개 리턴값x
    accept(), andThen()
4.Supplier<T>
    입력값ㅇ 1개
    get()
5.Predicate<T>
    입력값ㅇ 1개 리턴값ㅇ boolean
    and(), or(), nagate()
6.UnaryOperator<T>
    Function의 <>이 동일할 때 사용가능
7.BinaryOperator<T>
    BiFunction의 <>이 동일할 때 사용가능
8.BiConsumer
    Consumer 상속. 두개의 값을 받는데 값 리턴 없는 것

 */



//1.Function<T, R>
//    입력값ㅇ 1개 리턴값ㅇ
class Lambda3_2_1 implements Function<Integer,Integer> {
    @Override
    public Integer apply(Integer integer){
        return integer + 10;
    }
}
class Lambda3_2_1a{
    public static void main(String[] args) {
        // 기존 방식
        Lambda3_2_1 lambda1 = new Lambda3_2_1();
        System.out.println(lambda1.apply(1)); // 11
    }
}
class Lambda3_2_1b{
    public static void main(String[] args) {
        // 람다 방식
        Function<Integer,Integer> lambda = (num) -> num + 10;
//        Function<Integer,Integer> lambda = (int num) -> num + 10; 람다 변수선언부 int num에서 데이터타입 int 쓰면 컴파일 에러발생. 앞에서 제네릭에 <Integer>로 선언했기 때문
        System.out.println(lambda.apply(1)); // 11
    }
}

// default method(기본 제공) : andThen(), compose()
class Lambda3_2_1c{
    public static void main(String[] args) {
        Function<Integer,Integer> plus10 = (n) -> n + 10;
        Function<Integer,Integer> multiply2 = (n) -> n * 2;
        System.out.println("compose() 방법1 : " + plus10.compose(multiply2).apply(1)); // 12
        System.out.println("compose() 방법1 : " + plus10.compose(multiply2)); // .apply() 적용안하면 객체값 반환 : java.util.function.Function$$Lambda$16/0x0000000800c46490@448139f0


        // 1)compose()
        //      고차함수 성격(함수가 함수를 매개변수로 사용하거나 리턴하는 것)
        Function<Integer,Integer> compose = plus10.compose(multiply2);
        System.out.println("compose() 방법2 : " + compose.apply(1)); // 12
        System.out.println("compose() 방법2 : " + compose);  // .apply() 적용안하면 객체값 반환 : java.util.function.Function$$Lambda$16/0x0000000800c46490@7cca494b

        // 2)andThen()
        // compose()와 반대순서 계산
        plus10.andThen(multiply2);
        System.out.println(plus10.andThen(multiply2).apply(1)); // 22 = (1+10) * 2


    }
}



//2.BiFunction<T, U, R>
//    입력값ㅇ 2개 리턴값ㅇ
class Lambda3_2_2{
    public static void main(String[] args) {
        BiFunction<Integer,Integer,Integer> bi = (i, j) -> i + j;
        Function<Integer,Integer> func = (i) -> i + 1;
        bi.apply(1,2);
        func.apply(1);
        System.out.println(bi.apply(1,1)); // 3
        System.out.println(func.apply(1));    // 2
        // compose() & andThen() 추가
        //  Function은 사용가능 BiFunction은 compose() 사용불가
        
    }

}


//3.Consumer<T>
//    입력값ㅇ 1개 리턴값x
//    accept(), andThen()
class Lambda3_2_3{
    public static void main(String[] args) {
        // Consumer : 입력값 1개 & 리턴x
        Consumer<Integer> consumer = (i) -> System.out.println(i); // 10 12 차례로 찍힘
        consumer.accept(10);
        consumer.accept(12);
    }


}


//4.Supplier<T>
//    입력값ㅇ 1개
//    get()
class Lambda3_2_4{
    public static void main(String[] args) {
        Supplier<String> supplier = () -> "서플라이어"; // ()선언부가 비어있어야함
        Supplier<Integer> get10 = () -> 10;
        System.out.println();
    }
}


//5.Predicate<T>
//    입력값ㅇ 1개 리턴값ㅇ boolean
//    and(), or(), nagate()
class Lambda3_2_5{
    public static void main(String[] args) {
        Predicate<String> isValid = (s) -> s.startsWith("validation");
        isValid.test("a");
        isValid.or(isValid);

        System.out.println(isValid.toString()); // String.valueOf(), .toString()도 안먹히는데 객체로만 나옴
        System.out.println(String.valueOf(isValid)); // String.valueOf(), .toString()도 안먹히는데 객체로만 나옴
        System.out.println(isValid.test("a")); // false
        System.out.println(isValid.test("valid")); // false
        System.out.println(isValid.test("validation")); // true
        System.out.println(isValid.or(isValid)); // java.util.function.Predicate$$Lambda$17/0x0000000800c4a268@6d03e736

    }
}


//6.UnaryOperator<T>
//    Function의 <>이 동일할 때 사용가능
class Lambda3_2_6{
    public static void main(String[] args) {
        UnaryOperator<String> unary = (str) -> str + "Operator";
        UnaryOperator<Integer> plus10 = (i) -> i + 10;
        UnaryOperator<Integer> multiply3 = (i) -> i * 3;
        unary.apply("Unary");
        System.out.println(unary.apply("Unary"));
        plus10.apply(10);
        System.out.println(plus10.apply(10)); // 20

        // compose() : 함수조합
        plus10.compose(multiply3).apply(1);
        System.out.println(plus10.compose(multiply3).apply(1)); // 13 = 10+(1x3)
        // andThen() : 함수조합
        plus10.andThen(multiply3).apply(1);
        System.out.println(plus10.andThen(multiply3).apply(1)); // 33 = (1+10)x3
    }

}


//7.BinaryOperator<T>
//    BiFunction의 <>이 동일할 때 사용가능
class Lambda3_2_7{
    public static void main(String[] args) {
        BinaryOperator<Integer> biOper = (param1, param2) -> {
            return param1 - param2;
        };
        BinaryOperator<Integer> biFunc = (param1,param2) -> {
            return param1 + param2;
        };
        Function<Integer,Integer> function = (i) -> i % 5;

        biOper.apply(10,5);
//        biOper.andThen(biFunc.apply(10,10)).apply(10,5);
        biOper.andThen(function); //BinaryOperator의 andThen() 안에는 Function을 받음


    }

}


//8.BiConsumer
//    Consumer 상속. 두개의 값을 받는데 값 리턴 없는 것


/*******************************************************************************************************/

/*

C.람다 표현식

람다
    ● (인자리스트) -> {바디}

인자 리스트
    ● 인자가 없을 때: ()
    ● 인자가 한개일 때: (one) 또는  one
    ● 인자가 여러개 일  때: (one, two)
    ● 인자의 타입은 생략 가능, 컴파일러가 추론(infer)하지만 명시할 수도 있다. (Integer one,Integer two)
바디
    ● 화살표 오른쪽에 함수 본문을 정의한다.
    ● 여러 줄인 경우에 { }를 사용해서 묶는다.
    ● 한 줄인 경우에 생략 가능, return도 생략 가능.

변수 캡처(Variable Capture)
    ● 로컬 변수 캡처
        ○ final이거나 effective final인 경우에만 참조할 수  다.
        ○ 그렇지 않을 경우 concurrency 문제가  길 수 있어서 컴파일가 방지한다.
    ● effective final
        ○ 이것도  역시  자바  8부터  지원하는  기능으로  “사실상" final인  변수.
        ○ final 키워드  사용하지  않은  변수를  익명  클래스  구현체  또는  람다에서  참조할  수 있다.

    ● 익명 클래스 구현체와 달리 ‘쉐도윙’하지 않는다.
        ○ 익명 클래스는 새로 스콥을 만들지만, 람다는 람다를 감싸고 있는 스콥과 같다.


 */


class Lambda4_1_1{
    public static void main(String[] args) {
        // interface Supplier : get()
        Supplier<Integer> noParam1 = () -> 10;
        Supplier<Integer> noParam2 = () -> {
            return 10;
        };
        noParam1.get();
        System.out.println(noParam1.get()); // 10
        BinaryOperator<Integer> biOper = (Integer a, Integer b) -> a + b;
        biOper.apply(10,20);
        System.out.println(biOper.apply(10,20)); // 30

    }
}



class Lambda4_1_2{
    public static void main(String[] args) {
        Lambda4_1_2 lambda = new Lambda4_1_2();
        lambda.run();
    }

    private void run(){
        int num = 10;
        IntConsumer intConsumer = (i) -> {
            System.out.println(i + num); // 25 =10+25
        };
        intConsumer.accept(15);
    }


}



class Lambda4_1_3{
    public static void main(String[] args) {

    }
}



class Lambda4_1_4{
    public static void main(String[] args) {

    }
}



class Lambda4_1_5{
    public static void main(String[] args) {

    }
}



class Lambda4_1_6{
    public static void main(String[] args) {

    }
}



class Lambda4_1_7{
    public static void main(String[] args) {

    }
}





/*******************************************************************************************************/



/*******************************************************************************************************/




public class Lambda3_keesun {

}
