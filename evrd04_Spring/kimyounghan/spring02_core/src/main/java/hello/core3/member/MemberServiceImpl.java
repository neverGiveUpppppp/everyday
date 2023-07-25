package hello.core3.member;


public class MemberServiceImpl implements MemberService {

    // DIP, OCP 위반
//    private final MemberRepository memberRepository= new MemoryMemberRepository();

    // DIP, OCP 위반 해결
    private MemberRepository memberRepository;

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

}