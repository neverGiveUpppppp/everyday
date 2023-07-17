package hello.core3.discount;

import hello.core3.member.Member;

public interface DiscountPolicy {
    int discount(Member member, int price);
}
