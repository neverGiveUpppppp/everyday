package hello.predo.discount;

import hello.predo.member.Grade;
import hello.predo.member.Member;

public class RateDiscountPolicy implements DiscountPolicy {
    private int rateDiscountPrice = 10;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP)
            return price * rateDiscountPrice / 100;
        else
            return 0;
    }
}
