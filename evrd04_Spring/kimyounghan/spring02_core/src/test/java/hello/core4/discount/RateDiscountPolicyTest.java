package hello.core4.discount;

import hello.core4.member.Grade;
import hello.core4.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RateDiscountPolicyTest {

    RateDiscountPolicy ratediscount = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다")
    void vip_o() {
        Member member = new Member(1L, "memberVIP", Grade.VIP);

        int discount = ratediscount.discount(member, 1000);

        assertThat(discount).isEqualTo(100 );
    }

    @Test
    @DisplayName("VIP가 아니면 할인 미적용")
    void vip_x(){
        Member member = new Member(2L, "memberBASIC", Grade.BASIC);

        int discount = ratediscount.discount(member, 1000);

        assertThat(discount).isEqualTo( 0);

    }
}
