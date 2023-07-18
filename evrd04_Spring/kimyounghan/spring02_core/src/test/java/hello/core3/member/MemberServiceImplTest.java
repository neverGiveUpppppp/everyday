package hello.core3.member;

import hello.core3.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceImplTest {
    // DIP,OCP 위반
//    MemberService memberService = new MemberServiceImpl();

    // DIP,OCP 위반 해결
    MemberService memberService;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join() {
        // given
        Member member = new Member(1L, "memberA", Grade.VIP);

        // when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        // then
        Assertions.assertThat("memberAA").isEqualTo("memberAA");
    }

    @Test
    void findMember() {
        // given
        Member member = new Member(1L, "memberA", Grade.VIP);

        // when
        Member findMember = memberService.findMember(member.getId());

        // then
        Assertions.assertThat(member).isEqualTo(findMember);

    }
}