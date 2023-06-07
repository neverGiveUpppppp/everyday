package com.prac.zPrac2;

public class Lambda5_whiteship {


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




}
