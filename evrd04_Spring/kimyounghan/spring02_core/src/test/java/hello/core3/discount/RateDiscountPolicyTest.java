package hello.core3.discount;

import hello.core3.member.Grade;
import hello.core3.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    DiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("vip_o")
    public void RateDiscountPolicyTest(){

        // given
        long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);

        // when
        int discount = discountPolicy.discount(member, 20000);

        // then
        Assertions.assertThat(discount).isEqualTo(1000);

    }

    @Test
    @DisplayName("vip_x")
    public void RateDiscountPolicyTest2(){
        /// given
        long memberId = 1L;
        Member member = new Member(memberId, "memberB", Grade.BASIC);

        // when
        int discount = discountPolicy.discount(member, 20000);

        // then
        Assertions.assertThat(1000).isEqualTo(discount);

    }

}