package hello.predo.discount;

import hello.predo.member.Grade;
import hello.predo.member.Member;

public class FixDiscountPolicy implements DiscountPolicy{

    private int discountPrice = 1000;

    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP)
            return discountPrice;
        else
            return 0;
    }
}
