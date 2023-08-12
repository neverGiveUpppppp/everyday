package hello.core3.member;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {

    // DIP, OCP 위반
//    private final MemberRepository memberRepository= new MemoryMemberRepository();

    // DIP, OCP 위반 해결
    private MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }



    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

}
