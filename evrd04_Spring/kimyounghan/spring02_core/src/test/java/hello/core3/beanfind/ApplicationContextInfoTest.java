package hello.core3.beanfind;

import hello.core3.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name=" + beanDefinitionName + " || object=" + bean);
/* 모든 빈 출력내용
name=org.springframework.context.annotation.internalConfigurationAnnotationProcessor || object=org.springframework.context.annotation.ConfigurationClassPostProcessor@479ceda0
name=org.springframework.context.annotation.internalAutowiredAnnotationProcessor || object=org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor@6d07a63d
name=org.springframework.context.annotation.internalCommonAnnotationProcessor || object=org.springframework.context.annotation.CommonAnnotationBeanPostProcessor@571c5681
name=org.springframework.context.event.internalEventListenerProcessor || object=org.springframework.context.event.EventListenerMethodProcessor@488d1cd7
name=org.springframework.context.event.internalEventListenerFactory || object=org.springframework.context.event.DefaultEventListenerFactory@68dc098b
name=appConfig || object=hello.core3.AppConfig$$SpringCGLIB$$0@38ba6ce3
 */
        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            // Role ROLE_APPLICATION : 직접 등록한 어플리케이션 빈
            // Role ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name="+beanDefinitionName+" // object="+bean); // name=appConfig // object=hello.core3.AppConfig$$SpringCGLIB$$0@479ceda0
            }
        }
    }

}
