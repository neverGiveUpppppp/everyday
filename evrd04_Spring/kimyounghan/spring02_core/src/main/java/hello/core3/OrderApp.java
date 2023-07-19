package hello.core3;

import hello.core3.discount.DiscountPolicy;
import hello.core3.discount.FixDiscountPolicy;
import hello.core3.member.Grade;
import hello.core3.member.Member;
import hello.core3.member.MemberService;
import hello.core3.member.MemberServiceImpl;
import hello.core3.order.Order;
import hello.core3.order.OrderService;
import hello.core3.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {
        // DIP,OCP 위반
//        MemberService memberService = new MemberServiceImpl();
//        OrderService orderService = new OrderServiceImpl();

        // DIP,OCP 위반 해결
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        OrderService orderService = appConfig.orderService();

        // Java to spring 전환
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);



        long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(member.getId(), "itemA", 10000);

        System.out.println(order);

    }
}
