package hello.core5.discount;


import hello.core5.member.Grade;
import hello.core5.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("mainDiscountPolicy")
public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPerCent = 10; // %는 경계값 테스트 등 많은 테스트를 필요로 함

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return price * discountPerCent / 100;
        }else{
            return 0;
        }
    }

}
