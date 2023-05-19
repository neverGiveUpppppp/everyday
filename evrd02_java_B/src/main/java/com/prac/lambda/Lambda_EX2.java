package com.prac.lambda;


// 2. 두개의 숫자중 큰 수 찾기
// https://coding-factory.tistory.com/265
public class Lambda_EX2 {
    @FunctionalInterface // 함수형 인터페이스 체크 어노테이션
    public interface MyNumber{
        int getMax(int num1, int num2);
    }
    public static void main(String[] args){
        MyNumber max = (x,y) -> (x >= y) ? x : y;
        System.out.println(max.getMax(10,20)); // 10,20이 윗줄 x,y에 다시 들어감.
/*
     람다식 자체가 함수형 인터페이스형에 관련된 것이니 굳이 인터페이스를 implements 같은 걸로 잇지 않아도
     12라인 MyNumer max 변수에서 9라인 getMax()를 max.getMax()로 불러온 것이 반증
     13라인 max.getMax(10,20)을 통해 inferface MyNumber의 getMax(int num1, int num2)로 각각 10,20이 들어감
     max.getMax()를 통해 값이 들어갔고 max에서 .getMax()를 불렀으니 getMax() 다음 max.가 동작해야하기에
     MyNumber max = (x,y) -> (x >= y) ? x : y;가 동작하는 것
     20인 y가 더 크게 max에 20이 들어가고, 마지막으로 println에 max인 20이 들어가 출력됨

     13라인 max.getMax(10,20)로 interface MyNumber의 getMax에 10,20을 넣어주고
     12라인 (x >= y) ? x : y에서 interface의 getMax()에서 10,20을 받아 삼항연산자를 실행함
 */
    }
}
