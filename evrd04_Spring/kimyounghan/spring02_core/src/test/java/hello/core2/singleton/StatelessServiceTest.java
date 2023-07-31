package hello.core2.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatelessServiceTest {

    @Test
    void statelessServiceSinglenton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatelessService statelessService1 = ac.getBean("statelessService", StatelessService.class);
        StatelessService statelessService2 = ac.getBean("statelessService", StatelessService.class);

        int userA = statelessService1.order("userA", 10000);
        int userB = statelessService2.order("userA", 20000);

        System.out.println("userA = " + userA);
        System.out.println("userB = " + userB);

        Assertions.assertThat(userA).isEqualTo(10000);

    }

    static class TestConfig{
        @Bean
        public StatelessService statelessService(){
            return new StatelessService();
        }
    }

}