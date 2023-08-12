package hello.core4;

import hello.core4.discount.DiscountPolicy;
import hello.core4.member.*;
import hello.core4.order.Order;
import hello.core4.order.OrderService;
import hello.core4.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {

        //DI 컨테이너 적용
//        AppConfig appConfig = new AppConfig();
//        MemberRepository memberRepository = appConfig.memberRepository();
//        OrderService orderService = appConfig.orderService();

        // 스프링 컨테이너 적용
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        OrderService orderService = ac.getBean("orderService", OrderService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);
        Order order = orderService.createOrder(member.getId(), "itemA", 10000);
        System.out.println("order = " + order);
        System.out.println("order.calcalatePrice = " + order.calculatePrice());

    }
}
