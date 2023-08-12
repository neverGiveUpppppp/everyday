package hello.core4.discount;

import hello.core4.discount.DiscountPolicy;
import hello.core4.member.Grade;
import hello.core4.member.Member;
import org.springframework.stereotype.Component;

@Component
public class RateDiscountPolicy implements DiscountPolicy {

    private int discountRate = 10;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP)
            return price * discountRate / 100;
        else
            return 0;
    }
}
