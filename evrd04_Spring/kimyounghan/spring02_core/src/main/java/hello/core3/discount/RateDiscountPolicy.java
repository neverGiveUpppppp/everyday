package hello.core3.discount;

import hello.core3.annotation.MainDiscountPolicy;
import hello.core3.member.Grade;
import hello.core3.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Primary    // section7-6 @Autowired 필드 명, @Qualifier, @Primary
//@Qualifier("mainDiscountPolicy") // section7-6 @Autowired 필드 명, @Qualifier, @Primary
@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy{

    private int discountRate = 10;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP)
            return price * discountRate / 100;
        else
            return 0;
    }
}
