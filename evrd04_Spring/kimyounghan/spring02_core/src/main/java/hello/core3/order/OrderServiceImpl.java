package hello.core3.order;

import hello.core3.discount.DiscountPolicy;
import hello.core3.discount.FixDiscountPolicy;
import hello.core3.discount.RateDiscountPolicy;
import hello.core3.member.Grade;
import hello.core3.member.Member;
import hello.core3.member.MemberRepository;
import hello.core3.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {
    // DIP,OCP 위반
//    MemberRepository memberRespository = new MemoryMemberRepository();
//    DiscountPolicy discountPolicy = new FixDiscountPolicy();

    // DIP,OCP 위반 해결 : DI컨테이너
    MemberRepository memberRespository;
    DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRespository, DiscountPolicy discountPolicy){
        this.memberRespository = memberRespository;
        this.discountPolicy = discountPolicy;

    }


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice){
        Member member = memberRespository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

}
