package hello.core3;

import hello.core3.discount.RateDiscountPolicy;
import hello.core3.member.MemberRepository;
import hello.core3.member.MemberService;
import hello.core3.member.MemberServiceImpl;
import hello.core3.member.MemoryMemberRepository;
import hello.core3.order.OrderService;
import hello.core3.order.OrderServiceImpl;

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }
    public OrderService orderService(){
        return new OrderServiceImpl(new MemoryMemberRepository(), new RateDiscountPolicy());
    }


//    public MemberRepository memberService(MemberRepository memberRepository){
//        return new MemoryMemberRepository();
//    }

//    public OrderService orderService(OrderService orderService) {
//        return new OrderServiceImpl();
//    }

}
