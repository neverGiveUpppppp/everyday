package com.prac.lambda;



// 3. Runnable 인스턴스 생성
// https://coding-factory.tistory.com/265
public class Lambda_EX3 {
    public static void main(String[] args){
        Runnable runnable = () -> {
            for(int i = 0; i < 20; i++){
                System.out.println(i);
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

/*
디버깅 순서
1.09라인 Runnable runnable
2.15라인 Thread thread = new Thread(runnable) : runnable을 Thread에 넣었으니 쓰레드로 runnable을 돌리라는 의미. Runnable인터페이스는 run()을 강제하며 쓰레드객체를 해당 클래스가 상속하지 않고도 구현되기 위해 Runnable 인터페이스가 만들어짐(다중상속방지)
3.16라인 thread.start() : Thread 시작. runnable의 익명함수가 작동하게됨. 익명 함수 안에는 for문이 있으므로 for문 동작
4.27라인 main메소드 마침 중괄호 ( } ) : 메인메소드 끝남
    - 여기까지 콘솔에는 0만 찍힘
5.29라인 Lambda_EX3 클래스의 마침 중괄호( } ) :  
    - 나머지 1부터 19까지 다 찍힘
 */

    }
}
