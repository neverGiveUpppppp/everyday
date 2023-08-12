package hello.core2.member;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

//@Service
public class MemberServiceImpl implements MemberService{
    // DIP 위반 해결
//    private MemberRepository memberRepository = new MemoryMemberRepository();

    // DIP 위반 해결
    private MemberRepository memberRepository;


    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberid) {
        return memberRepository.findById(memberid);
    }

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

}
