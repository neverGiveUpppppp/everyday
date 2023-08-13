package hello.core3.scan.filter;

import hello.core3.scan.MyExcludeComponent;
import hello.core3.scan.MyIncludeComponent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.context.annotation.ComponentScan.*;

public class ComponentFilterAppConfigTest {

    @Test
    void filterScan(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);

        BeanA beanA = ac.getBean("beanA", BeanA.class);
        assertThat(beanA).isNotNull();

        Assertions.assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("beanB", BeanB.class));

    }


    @Configuration
    @ComponentScan(
        includeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
        excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class))
    @ComponentScan(
        includeFilters = {@Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class)},
        excludeFilters = {@Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class),
                          @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = BeanA.class)
        }
    )
    static class ComponentFilterAppConfig{

    }

}
