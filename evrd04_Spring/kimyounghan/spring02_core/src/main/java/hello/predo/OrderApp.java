package hello.predo;

import hello.predo.member.*;
import hello.predo.order.Order;
import hello.predo.order.OrderService;
import hello.predo.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {

        // OCP DIP 위반
//        MemberService memberService = new MemberServiceImpl();
//        OrderService orderService = new OrderServiceImpl();

        // OCP DIP 위반 해결(DI컨테이너 적용)
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        OrderService orderService = appConfig.orderService();

        // section3-9 스프링으로 전환하기 : 스프링 컨테이너 적용
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        OrderService orderService = ac.getBean("orderService", OrderService.class);

/************************************** 실행 및 테스트 로직 ****************************************/

        long memberId = 1L;
        Member member = new Member(memberId, "userA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(member.getId(), "itemA", 10000);
        System.out.println("order = " + order);
        System.out.println("order.calculatePrice() = " + order.calculatePrice());

    }
}
