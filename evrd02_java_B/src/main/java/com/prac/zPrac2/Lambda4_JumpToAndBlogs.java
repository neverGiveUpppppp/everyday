package com.prac.zPrac2;

class LambdaEx4_1{

    interface Compare1{
        int compareTo(int a, int b);
    }
    public static void exec(Compare1 compare1){
        int a = 15;
        int b = 25;
        int value = compare1.compareTo(a, b);
        System.out.println(value);
    }
    public static void main(String[] args) {
        exec((i, j) -> {
            return i + j;
        });
    }
}

class LambdaEx4_2{
    interface Mynumber{
        int getMax(int a, int b);
    }

    public static void main(String[] args) {
        Mynumber max = (a,b) -> (a >= b) ? a : b; // // (a,b) -> 에서 데이터형 int를 선언안해도 되는 이유는 interface getMax()에서 했기 때문
        System.out.println(max.getMax(10,20));
    }

}

class LambdaEx4_3{
    public static void main(String[] args) {
        Runnable runnable = () -> {
          for(int i = 0; i < 7; i++)
              System.out.println(i);
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }
}

/******************** JumpToJava *****************************/

// 기존 사용법
interface Summary{
    int sum(int a, int b);
}
class LambdaEx4_4{
    class Using implements Summary{
        public int sum(int a, int b){
            return a + b;
        }
    }
    public void main(String[] args) {
        Using u = new Using();
        int result = u.sum(1,2);
        System.out.println(result);
    }
}

// 람다표현식
class LambdaEx4_5{
    interface Summary2{
        int sum(int a, int b);
    }
    class Using2 implements Summary2{
        public int sum(int a, int b){
            return a + b;
        }
    }

    public void main(String[] args) {
        Using2 u = new Using2();
        int result = u.sum(2,3);
        System.out.println(result);
    }

}

// 인터페이스 주의사항 : 추상 메소드 1개만
//  이를 위한 @FunctionalInterface
class LambdaEx4_6{
    @FunctionalInterface
    interface Sum{
        int sum(int a, int b);
//        int mul(int a, int b); 추상메소드가 2개여서 @FunctionalInterface에서 컴파일에러 발생시킴

        // static이나 default 메소드는 제한x
        static void multiply(){
            System.out.println("static method");
        }
        static void printTest(){
            System.out.println("static method no limit");
        }
        default void printTest_default(){
            System.out.println("default method no limit too");
        }
    }

}


// 3.1.람다 축약
class LambdaEx4_7{
    interface Summary3{
        int sum(int a, int b);
    }
    public static void main(String[] args) {
        Summary3 s1 = (int a, int b) -> a + b;
        Summary3 s2 = (a, b) -> a + b;
        s1.sum(0,1);
        s2.sum(1,2);
        int result = s2.sum(1,2);
        System.out.println(result);
    }
}

// 3.2.람다 축약 - 메소드 참조
/*
 메서드 참조 문법
      클래스이름::메소드이름
      참조변수명::메소드명
 파라미터 중복이 없다면 메서드 참조 표현식 사용불가

 두 수를 더하여 결과를 리턴하는 함수 (a, b) -> a+b 는
 Integer.sum(int a, int b)와 동일하기 때문에 다음과 같이 더 축약이 가능
      ex) Integer.sum
*/

class LambdaEx4_8{
    interface Summary4{
        int sum(int a, int b);
    }
    public static void main(String[] args) {
        Summary4 s1 = Integer::sum;
        int result = s1.sum(1,2);
        System.out.println(result);
    }

}

class LambdaEx4_9{

}

class LambdaEx4_10{

}

class LambdaEx4_11{

}

public class Lambda4_JumpToAndBlogs {
}
