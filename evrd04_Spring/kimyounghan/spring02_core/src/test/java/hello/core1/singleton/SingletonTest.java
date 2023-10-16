package hello.core1.singleton;

import hello.core2.member.MemberService;
import hello.core2.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;


/*
<DI컨테이너 문제점>
요청이 올 때마다 새로운 객체 생성
    → 메모리낭비 high, 시스템부하↑

해결방안 : 싱글톤 패턴
    객체 1개로 공유


싱글톤패턴을 적용하면, 고객의 요청이 올때마다 객체를 생성하는 것이 아니라,
이미 만들어진 객체를 공유해서 효율적으로 사용


싱글톤패턴 문제점
    1.코드가 길어짐
    2.DIP위반 : 의존관계상 구체 클래스 의존
    3.구체클래스 의존 때문에 OCP 위반 가능성 high
    4.테스트하기 difficult
        싱글톤은 지정해서 가져온다
        인스턴스를 미리 박아두기에 설정이 끝나버림
        → 유연하게 테스트하기 difficult
    5.내부 속성 변경 및 초기화 difficult
    6.private생성자 때문에 자식클래스 생성 difficult
        결론적으로, 유연성이 떨어짐

스프링의 기본 빈등록 방식은 싱글톤
DI컨테이너처럼, 요청할 때 마다 새로운객체를 생성해서 반환하는 기능도 제공

 */
public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너 테스트") // 순수 자바 DI 컨테이너 사용 비교 테스트
    void pureContainer() {
        AppConfig appConfig = new AppConfig();

        //1. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        //2. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        // 두 객체의 참조값이 다른 지 확인하기
        System.out.println("memberService1 = " + memberService1); // memberService1 = hello.core.member.MemberServiceImpl@62e136d3
        System.out.println("memberService2 = " + memberService2); // memberService2 = hello.core.member.MemberServiceImpl@c8e4bb0

        // memberService1 != memberService2
        assertThat(memberService1).isNotSameAs(memberService2); // 두 객체가 같은 지 비교하는데 같지 않은게 맞는거니까 isSameAs() 대신 isNotSameAs()를 써서 테스트 초록불 들어오게 설계
    /*
    출력결과
    memberService1 = hello.core.member.MemberServiceImpl@62e136d3
    memberService2 = hello.core.member.MemberServiceImpl@c8e4bb0
            두 객체의 인스턴스가 다름
     */
    }


    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용") // 싱글톤 패턴을 사용하는 테스트
    public void singletonServiceTest() {

        // 싱글톤 패턴을 구현한 클래스의 생성자를 private으로 설정해 외부에서 new키워드로 새 객체의 생성을 막음
//        new SingletonService(); // 컴파일 오류 발생 : 'SingletonService()' has private access in 'hello.core.singleton.SingletonService'

        // 1.조회: 호출할 때 마다 같은 객체를 반환
        SingletonService singletonService1 = SingletonService.getInstance();

        // 2.조회: 호출할 때 마다 같은 객체를 반환
        SingletonService singletonService2 = SingletonService.getInstance();

        // 참조값이 같은 것을 확인
        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        // singletonService1 == singletonService2
        assertThat(singletonService1).isSameAs(singletonService2);

        singletonService1.logic();
    /*
    출력결과
    singletonService1 = hello.core.singleton.SingletonService@2b4a2ec7
    singletonService2 = hello.core.singleton.SingletonService@2b4a2ec7
    싱글톤 객체 로직 호출
            두 객체의 인스턴스가 같음
     */
    }


    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        // 1.조회: 호출할 때 마다 같은 객체를 반환
        MemberService memberService1 = ac.getBean("memberService",MemberService.class);

        // 2.조회: 호출할 때 마다 같은 객체를 반환
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        // 참조값이 같은 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // memberService1 == memberService2
        assertThat(memberService1).isSameAs(memberService2);
    /*
    출력결과
    memberService1 = hello.core.member.MemberServiceImpl@2421cc4
    memberService2 = hello.core.member.MemberServiceImpl@2421cc4
        둘의 객체 같음 -> 싱글톤 잘 작동

    스프링 쓰면 스프링 컨테이너를 사용하기에 new로 생성할 필요x
     */
    }

}
