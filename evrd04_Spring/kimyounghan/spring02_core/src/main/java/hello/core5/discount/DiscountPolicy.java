package hello.core5.discount;

import hello.core5.member.Member;

public interface DiscountPolicy {

    int discount(Member member, int price);

}
