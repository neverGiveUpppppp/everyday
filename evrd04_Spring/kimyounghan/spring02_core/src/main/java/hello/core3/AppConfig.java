package hello.core3;

import hello.core3.discount.DiscountPolicy;
import hello.core3.discount.FixDiscountPolicy;
import hello.core3.discount.RateDiscountPolicy;
import hello.core3.member.MemberRepository;
import hello.core3.member.MemberService;
import hello.core3.member.MemberServiceImpl;
import hello.core3.member.MemoryMemberRepository;
import hello.core3.order.OrderService;
import hello.core3.order.OrderServiceImpl;
import org.springframework.context.annotation.Configuration;

@Configuration // DI컨테이너 to 스프링 컨테이너
public class AppConfig {
    // 리팩토링 전
//    public MemberService memberService() {
//        return new MemberServiceImpl(new MemoryMemberRepository());
//    }
//    public OrderService orderService(){
//        return new OrderServiceImpl(new MemoryMemberRepository(), new RateDiscountPolicy());
//    }
    // 리팩토링 후
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }
    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }


    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
    public DiscountPolicy discountPolicy(){
        return new RateDiscountPolicy();
//        return new FixDiscountPolicy();
    }

}
