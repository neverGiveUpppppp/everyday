package hello.core5.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented // @Qualifier에 들어가서 어노테이션 복사해옴
@Qualifier("mainDiscountPolicy") // @Qualifier의 컴파일 체크X 단점 보완하기 위해 @Qualifier("mainDiscountPolicy")를 @MainDiscountPolicy 안에 넣어버림
public @interface MainDiscountPolicy {

}
