package hello.core4.member;


import org.springframework.stereotype.Repository;

import java.lang.annotation.Retention;
import java.util.HashMap;
import java.util.Map;

@Repository
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findByid(Long memberId) {
        return store.get(memberId);
    }
}
