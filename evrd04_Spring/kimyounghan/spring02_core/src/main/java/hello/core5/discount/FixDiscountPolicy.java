package hello.core5.discount;

import hello.core5.member.Grade;
import hello.core5.member.Member;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class FixDiscountPolicy implements DiscountPolicy{
    private int discountFixAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){ // enum은 ==(주소값 비교) 사용 // equals()는 값 비교
            return discountFixAmount;
        }else{
            return 0;
        }
    }

}
