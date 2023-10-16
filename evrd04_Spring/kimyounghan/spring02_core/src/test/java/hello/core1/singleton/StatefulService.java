package hello.core1.singleton;


/*

<싱글톤 객체, 무상태(stateless) 설계 필수>
여러 클라이언트가 하나의 같은 객체 인스턴스를 공유하기 때문에 싱글톤 객체는 상태를 유지(stateful)하게 설계X
즉, 싱글톤 객체 무상태(stateless)로 설계 필수

<싱글톤 객체, 무상태(stateless) 조건>
1.특정 클라이언트에 의존적인 필드가 있으면X
2.특정 클라이언트가 값을 변경할 수 있는 필드가 있으면X
3.가급적 읽기만 가능해야함
4.필드 대신에 자바에서 공유 되지않는, 지역변수, 파라미터, ThreadLocal 등을 사용해야함

 */
public class StatefulService {
    // 상태(stateful) 설계
    private int price; // 상태를 유지하는 필드

    public void order(String name, int price) {
        System.out.println("name = " + name + " || price = " + price);
        this.price = price;  // 여기가 문제! 여러 쓰레드에서 이 부분을 통해 클라이언트가 필드에 값을 넣어주고 가져가면서 값이 덮어씌워지기 때문에 큰 장애 발생.
        // Thread A에서 1000을 넣었는데 Thread B에서 2000을 넣으면 각각 처리 되는게 아닌 1000이던게 2000으로 덮어씌워지면서 문제 발생. StatefulServiceTest 참조
    }
    // 무상태 설계와 비교해서 볼 것!! StatelessService 참조
    public int getPrice() {
        return price;
    }

// 무상태(stateless) 설계
/*
<무상태(stateless) 설계>
public class StatelessService {

    public int order(String name, int price){
        return price;
    }
}
public class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ...

        //ThreadA: 사용자A 10000원 주문
        int userA_price = statefulService1.order("userA", 10000);
        //ThreadB: 사용자B 20000원 주문
        int userB_price = statefulService2.order("userB", 20000);

        //ThreadA: 사용자A 주문 금액 조회
        System.out.println("price = " + userA_price); // price = 10000

        ...
    }
}
 */
// 상태(statefull)과 무상태(stateless) 설계 비교
/*
<상태와 무상태 비교>
public class StatelessService {

//    private int price; // 상태를 유지하는 필드

    public int order(String name, int price){
//        this.price = price;
        return price;
    }

//    public int getPrice() {
//        return price;
//    }
}
public class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ...

        //ThreadA: 사용자A 10000원 주문
//        statefulService1.order("userA", 10000);
        int userA_price = statefulService1.order("userA", 10000);
        //ThreadB: 사용자B 20000원 주문
//        statefulService2.order("userB",20000);
        int userB_price = statefulService2.order("userB",20000);

        //ThreadA: 사용자A 주문 금액 조회
//        int price = statefulService1.getPrice();
        System.out.println("price = " + userA_price); // price = 10000

        ...
    }
}
 */


}
