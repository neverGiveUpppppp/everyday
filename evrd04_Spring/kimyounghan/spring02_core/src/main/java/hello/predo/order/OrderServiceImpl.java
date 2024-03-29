package hello.predo.order;

import hello.predo.discount.DiscountPolicy;
import hello.predo.discount.FixDiscountPolicy;
import hello.predo.discount.RateDiscountPolicy;
import hello.predo.member.Member;
import hello.predo.member.MemberRepository;
import hello.predo.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component // 추가 : section 6-1 컴포넌트스캔과 의존관계 자동 주입 시작하기
public class OrderServiceImpl implements OrderService{

    // OCP DIP 위반
//    private MemberRepository memberRepository = new MemoryMemberRepository();
//    private DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private DiscountPolicy discountPolicy = new RateDiscountPolicy();

    // OCP DIP 위반 해결(DI컨테이너 적용)
    private MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;

    @Autowired // 추가 : section 6-1 컴포넌트스캔과 의존관계 자동 주입 시작하기
    public OrderServiceImpl (MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    // section5-5 검증용 테스트코드 for MemberRepository 조회
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }

    /*************************** business logic *******************************/

    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName,itemPrice, discountPrice);
    }

}
