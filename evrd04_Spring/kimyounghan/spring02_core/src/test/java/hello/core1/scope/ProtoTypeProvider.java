package hello.core1.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Provider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.assertThat;

public class ProtoTypeProvider {

    @Test
    void providerTest() {

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClienBean.class, ProtoTypeBean.class);

        ClienBean bean1 = ac.getBean(ClienBean.class);
        int logic1 = bean1.logic();

        ClienBean bean2 = ac.getBean(ClienBean.class);
        int logic2 = bean2.logic();

        assertThat(logic2).isEqualTo(logic1);
    }



    static class ClienBean{
        @Autowired
        private Provider<ProtoTypeBean> provider;

        public int logic(){
            ProtoTypeBean protoTypeBean = provider.get();
            protoTypeBean.addCount();
            int count = protoTypeBean.getCount();
            return count;
        }

    }

    @Scope("prototype")
    static class ProtoTypeBean{
        private int count = 0;

        public void addCount(){
            count++;
        }
        public int getCount(){
            return count;
        }

        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init " + this);
        }
        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean.destroy");
        }
    }

}
