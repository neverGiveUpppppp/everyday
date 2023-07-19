package hello.core3;

import hello.core3.member.Grade;
import hello.core3.member.Member;
import hello.core3.member.MemberService;
import hello.core3.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {

        // DIP,OCP 위반
//        MemberService memberService = new MemberServiceImpl();

        // DIP,OCP 위반 해결 : DI컨테이너
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        // Java to spring 전환
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);


        Member member = new Member(1L,"memberA", Grade.VIP);
        memberService.join(member);

        // 테스트
        Member findMember = memberService.findMember(1L);
        System.out.println(member.getName());
        System.out.println(findMember.getName());
    }
}
