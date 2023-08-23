package hello.predo.order;

import hello.predo.discount.DiscountPolicy;
import hello.predo.discount.FixDiscountPolicy;
import hello.predo.discount.RateDiscountPolicy;
import hello.predo.member.Member;
import hello.predo.member.MemberRepository;
import hello.predo.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    // OCP DIP 위반
//    private MemberRepository memberRepository = new MemoryMemberRepository();
//    private DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private DiscountPolicy discountPolicy = new RateDiscountPolicy();

    // OCP DIP 위반 해결(DI컨테이너 적용)
    private MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;

    public OrderServiceImpl (MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }




    /*************************** business logic *******************************/

    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName,itemPrice, discountPrice);
    }

}
