package hello.core4.discount;

import hello.core4.member.Member;

public interface DiscountPolicy {

    int discount(Member member, int price);

}
