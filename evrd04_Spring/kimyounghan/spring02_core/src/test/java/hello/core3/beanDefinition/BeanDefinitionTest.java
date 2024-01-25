package hello.core3.beanDefinition;

import hello.core3.AppConfig;
import org.apache.catalina.core.ApplicationContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanDefinitionTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 설정 메타정보 확인")
    void findApplicationBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) // ROLE_APPLICATION :  일반적으로 사용자가 정의한 빈
                System.out.println("beanDefinitionName = " + beanDefinitionName + " || beanDefinition = " + beanDefinition);
        }
    }
    // BeanDefinition으로 추상화해서 사용하는 것 정도만 이해하면 됨



}
