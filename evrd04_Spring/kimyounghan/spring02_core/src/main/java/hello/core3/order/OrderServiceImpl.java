package hello.core3.order;

import hello.core3.discount.DiscountPolicy;
import hello.core3.member.Member;
import hello.core3.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {
    // DIP,OCP 위반
//    MemberRepository memberRespository = new MemoryMemberRepository();
//    DiscountPolicy discountPolicy = new FixDiscountPolicy();

    // DIP,OCP 위반 해결 : DI컨테이너
    MemberRepository memberRepository;
    DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;

    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice){
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }



    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

}
