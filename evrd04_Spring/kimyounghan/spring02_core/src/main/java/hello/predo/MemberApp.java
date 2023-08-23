package hello.predo;

import hello.predo.member.*;
import hello.predo.order.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {

        // OCP DIP 위반
//        MemberService memberService = new MemberServiceImpl(new MemoryMemberRepository());
//        MemberService memberService = new MemberServiceImpl();

        // OCP DIP 위반 해결(DI컨테이너 적용)
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        // section3-9 스프링으로 전환하기 : 스프링 컨테이너 적용
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = ac.getBean("memberService", MemberService.class);

/************************************** 실행 및 테스트 로직 ****************************************/
        Member member = new Member(1L, "userA", Grade.VIP);
        memberService.join(member);

        Member findByMember = memberService.findMember(member.getId());
        System.out.println("new member = " + member);
        System.out.println("findByMember = " + findByMember);
        // app의 로직 테스트는 위와 같은 방식보다 jUnit을 권장
    }
}
