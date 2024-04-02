package hello.core5.member;

public interface MemberRepository {

    void save(Member member);

    Member findById(Long memberId);

}
