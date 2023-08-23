package hello.predo.member;

import hello.predo.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



class MemberServiceTest {

    // OCP DIP 위반
//    MemberService memberService = new MemberServiceImpl();

    // OCP DIP 위반 해결(DI컨테이너 적용)
    MemberService memberService;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join() {
        // given
        Member member = new Member(1L, "userA", Grade.VIP);

        // when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        // then
        Assertions.assertThat(member).isEqualTo(findMember);

    }

}