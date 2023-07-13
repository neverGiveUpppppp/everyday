package hello.core2.member;

import java.util.HashMap;

public class MemoryMemberRepository implements MemberRepository {

    private static HashMap<Long, Member> store = new HashMap<>(); // 실무 ConcurrentHashMap

    @Override

    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
