package hello.predo.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Service
@Component // 추가 : section 6-1 컴포넌트스캔과 의존관계 자동 주입 시작하기
public class MemberServiceImpl implements MemberService{

    // OCP DIP 위반
//    private final MemberRepository memberRepository = new MemoryMemberRepository();

    // OCP DIP 위반 해결(DI컨테이너 적용)
    private final MemberRepository memberRepository;

    @Autowired // 추가 : section 6-1 컴포넌트스캔과 의존관계 자동 주입 시작하기
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    public void join(Member member) {
        memberRepository.save(member);
    }

    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // section5-5 검증용 테스트코드 for MemberRepository
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

}
