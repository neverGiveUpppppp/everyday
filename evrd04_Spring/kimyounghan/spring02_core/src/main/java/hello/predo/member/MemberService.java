package hello.predo.member;

public interface MemberService {
    public abstract void join(Member member);

    public abstract Member findMember(Long memberId);

}
