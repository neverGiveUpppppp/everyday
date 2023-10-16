package hello.core1.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatelessServiceTest {

    @Test
    void statelessServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatelessService stateless1 = ac.getBean(StatelessService.class);
        StatelessService stateless2 = ac.getBean(StatelessService.class);

        //ThreadA: 사용자A 10000원 주문
        int userA_price = stateless1.order("userA", 10000);
        //ThreadB: 사용자B 20000원 주문
        int userB_price = stateless2.order("userB", 20000);

        //ThreadA: 사용자A 주문 금액 조회
        System.out.println("price = " + userA_price); // price = 10000

        Assertions.assertThat(userA_price).isEqualTo(10000);
    }

    static class TestConfig{
        @Bean
        public StatelessService statelessService(){
            return new StatelessService();
        }
    }


}
