package hello.predo.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.assertThat;


// spring2 section9-4 프로토타입 스코프 - 싱글톤 빈과 함께사용시 Provider로 문제 해s결
public class PrototypeProviderTest {

    @Test
    void providerTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);
    }

    static class ClientBean {
        // section9-4 : 1)스프링컨테이너에 요청
//        @Autowired
//        private ApplicationContext ac;
//
//        public int logic() {
//            PrototypeBean prototypeBean = ac.getBean(PrototypeBean.class); // Dependency Lookup (DL) 의존관계조회(탐색) : 직접 필요한 의존관계를 찾는 것
//
//            prototypeBean.addCount();
//            int count = prototypeBean.getCount();
//            return count;
//        }

        // section9-4 : 2)ObjectFactory, ObjectProvider
//        @Autowired
//        private ObjectProvider<PrototypeBean> prototypeBeanProvider;
//
//        public int logic(){
//            PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
//            prototypeBean.addCount();
//            int count = prototypeBean.getCount();
//            return count;
//        }

        // section9-4 : 3) JSR-330 Provider
        @Autowired
        private Provider<PrototypeBean> provider;

        public int logic(){
            PrototypeBean prototypeBean = provider.get();
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }

    }

    @Scope("prototype")
    static class PrototypeBean{
        private int count = 0;

        public void addCount(){
            count++;
        }
        public int getCount(){
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);
        }
        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
