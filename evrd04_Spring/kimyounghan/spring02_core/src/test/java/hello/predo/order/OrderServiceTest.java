package hello.predo.order;

import hello.predo.AppConfig;
import hello.predo.member.Grade;
import hello.predo.member.Member;
import hello.predo.member.MemberService;
import hello.predo.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    // OCP DIP 위반
//    MemberService memberService = new MemberServiceImpl();
//    OrderService orderService = new OrderServiceImpl();

    // OCP DIP 위반 해결(DI컨테이너 적용)
    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void creatOrder(){
        long memberId = 1L;
        Member member = new Member(memberId, "userA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
        Assertions.assertThat(order.calculatePrice()).isEqualTo(9000);
    }

}