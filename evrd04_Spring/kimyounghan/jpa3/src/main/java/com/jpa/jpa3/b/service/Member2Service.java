package com.jpa.jpa3.b.service;

import com.jpa.jpa3.b.domain.Member2;
import com.jpa.jpa3.b.repository.Member2Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class Member2Service {

    private final Member2Repository memberRepository;

    public Long join(Member2 member) {
        validateDuplicationMember(member); // 중복 회원 검증
        return member.getId();
    }

    private void validateDuplicationMember(Member2 member) {
        List<Member2> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty())
            throw new IllegalStateException("이미 존재하는 회원입니다.");
    }

}
