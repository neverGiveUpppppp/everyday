package hello.core3.singletone3;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatefulServiceTest {

    @Test
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService stateService1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService stateService2 = ac.getBean("statefulService", StatefulService.class);

        stateService1.order("userA", 10000);
        stateService2.order("userB", 20000);

        int price = stateService1.getPrice();
        System.out.println("price : " + price);

        Assertions.assertThat(stateService1.getPrice()).isEqualTo(20000);

    }

    static class TestConfig{
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }
}
