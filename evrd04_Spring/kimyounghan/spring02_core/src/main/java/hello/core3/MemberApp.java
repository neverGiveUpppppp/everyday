package hello.core3;

import hello.core3.member.Grade;
import hello.core3.member.Member;
import hello.core3.member.MemberService;
import hello.core3.member.MemberServiceImpl;

public class MemberApp {
    public static void main(String[] args) {

        MemberService memberService = new MemberServiceImpl();

        Member member = new Member(1L,"memberA", Grade.VIP);
        memberService.join(member);

        // 테스트
        Member findMember = memberService.findMember(1L);
        System.out.println(member.getName());
        System.out.println(findMember.getName());
    }
}
