package hello.predo.member;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

//@Repository
@Component // 추가 : section 6-1 컴포넌트스캔과 의존관계 자동 주입 시작하기
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(),member); // Member 필드에서 만든 Long id로 Map의 key값에 넣고 Member 데이터 전체를 value값으로 넣는 것
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }

}
