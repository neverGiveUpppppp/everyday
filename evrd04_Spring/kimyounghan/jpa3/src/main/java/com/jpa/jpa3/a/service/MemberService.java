package com.jpa.jpa3.a.service;

import com.jpa.jpa3.a.domain.Member;
import com.jpa.jpa3.a.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long join(Member member) {
        validateDuplicationMember(member);
        return member.getId();
    }

    private void validateDuplicationMember(Member member) {
        memberRepository.findByName(member.getName());
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    @Transactional
    public void update(Long id, String name) {
        Member member = memberRepository.findOne(id);
        member.setName(name);
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }


}
