package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// section5 @Configuration과 싱글톤 : 싱글톤이 잘 작동하는 지 검증용 테스트코드
public class ConfigurationSingletonTest {

    @Test
    void configurationTest(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class); // 원래는 구체타입(구체화클래스)으로 꺼내는게 좋은 것은 아니나 검증용으로 간단하게 쓸거라 구체타입으로 조회
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        System.out.println("memberService -> memberRepository1 = " + memberRepository1);
        System.out.println("orderService  -> memberRepository2 = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);
    /*
    출력결과
    memberService -> memberRepository1 = hello.core.member.MemoryMemberRepository@7a220c9a
    orderService  -> memberRepository2 = hello.core.member.MemoryMemberRepository@7a220c9a
    memberRepository = hello.core.member.MemoryMemberRepository@7a220c9a
        사용한 객체가 모두 같음 : 싱글톤 유지 잘되고 있음
     */
        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        Assertions.assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }



    @Test
    void configurationDeep(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        AppConfig bean = ac.getBean(AppConfig.class);
        System.out.println("bean = " + bean.getClass());
    /*
    출력결과
    bean = class hello.core.AppConfig$$SpringCGLIB$$0
        AppConfig 뒤에 SpringCGLIB 더 붙었다.
     */


    }


}
