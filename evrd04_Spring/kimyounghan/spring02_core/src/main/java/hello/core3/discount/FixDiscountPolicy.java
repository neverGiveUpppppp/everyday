package hello.core3.discount;

import hello.core3.member.Grade;
import hello.core3.member.Member;

public class FixDiscountPolicy implements DiscountPolicy {

    private final int discount = 1000;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP)
            return price * discount / 100;
        else
            return 0;
    }

}
