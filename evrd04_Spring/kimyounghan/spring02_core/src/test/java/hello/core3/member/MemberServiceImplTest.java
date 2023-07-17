package hello.core3.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceImplTest {

    MemberService memberService = new MemberServiceImpl();

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