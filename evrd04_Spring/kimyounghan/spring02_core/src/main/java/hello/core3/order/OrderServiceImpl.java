package hello.core3.order;

import hello.core3.discount.DiscountPolicy;
import hello.core3.discount.FixDiscountPolicy;
import hello.core3.member.Grade;
import hello.core3.member.Member;
import hello.core3.member.MemberRepository;
import hello.core3.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    MemberRepository memberRespository = new MemoryMemberRepository();
    DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    private Order createOrder(Long memberId, String itemName, int itemPrice){

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

}
