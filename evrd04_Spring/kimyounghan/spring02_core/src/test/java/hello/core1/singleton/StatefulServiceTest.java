package hello.core1.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {

        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);
// 원래는 멀티쓰레드로 구성해야하나 예제가 복잡해지기에 가정만함

        //ThreadA: 사용자A 10000원 주문
        statefulService1.order("userA", 10000);
        //ThreadB: 사용자B 20000원 주문
        statefulService2.order("userB",20000);

        //ThreadA: 사용자A 주문 금액 조회
        int price = statefulService1.getPrice();
        //ThreadA: 사용자A는 10000원을 기대했지만, 기대와 다르게 20000원 출력
        System.out.println("price = " + price); // price = 20000
/*
price = 20000인 이유
    StatefulService.order() 보면 StatefulService의 필드인 int price에 값을 넣어준다.
    즉, statefulService1,2가 같은 필드값을 공유하기 때문에 값 10000이 주입된 후 20000이 들어가 덮어씌워진 것
 */
        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig{
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }

    }

}
