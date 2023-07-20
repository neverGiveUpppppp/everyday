package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService2 {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    /**
     * 회원 가입
     */
    public Long join(Member member) {
        validateDuplicateMember(member);
        repository.save(member);
        return member.getId();
    }

    public void validateDuplicateMember(Member member) {
        repository.findByName(member.getName()).ifPresent(
            m -> {
                throw new IllegalStateException("이미 존재하는 회원");
            });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return repository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return repository.findById(memberId);
    }

}
