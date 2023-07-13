package hello.core2.discount;

import hello.core2.member.Grade;
import hello.core2.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인")
    void vip_o(){

        // given
        Member member = new Member(1L, "memberVip", Grade.VIP);

        // when
        int discount = discountPolicy.discount(member, 10000);

        // then
//        Assertions.assertThat(discount).isEqualTo(1000);
        assertThat(discount).isEqualTo(1000);

    }

    @Test
    @DisplayName("VIP 아니면 할인적용x")
    void vip_x(){
        // given
        Member member = new Member(2L, "memberBasic", Grade.BASIC);

        // when
        int discount = discountPolicy.discount(member, 10000);

        // then
//        assertThat(discount).isEqualTo(1000);
        assertThat(discount).isEqualTo(0);
    }


}
