package hello.predo.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service
public class MemberServiceImpl implements MemberService{

    // OCP DIP 위반
//    private final MemberRepository memberRepository = new MemoryMemberRepository();

    // OCP DIP 위반 해결(DI컨테이너 적용)
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    public void join(Member member) {
        memberRepository.save(member);
    }

    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
