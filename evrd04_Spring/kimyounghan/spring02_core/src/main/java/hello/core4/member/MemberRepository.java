package hello.core4.member;

public interface MemberRepository {

    void save(Member member);

    Member findByid(Long memberId);


}

