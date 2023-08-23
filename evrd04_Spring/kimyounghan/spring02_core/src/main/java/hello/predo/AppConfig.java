package hello.predo;

import hello.predo.discount.DiscountPolicy;
import hello.predo.discount.RateDiscountPolicy;
import hello.predo.member.MemberRepository;
import hello.predo.member.MemberService;
import hello.predo.member.MemberServiceImpl;
import hello.predo.member.MemoryMemberRepository;
import hello.predo.order.OrderService;
import hello.predo.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    // AppConfig 리팩터링 전
//    public MemberService memberService(){
//        return new MemberServiceImpl(new MemoryMemberRepository());
//    }
//    public OrderService orderService(){
//        return new OrderServiceImpl(new MemoryMemberRepository(), new RateDiscountPolicy());
//    }

    // AppConfig 리팩터링 후
//    public MemberService memberService() {
//        return new MemberServiceImpl(memberRepository());
//    }
//    public OrderService orderService(){
//        return new OrderServiceImpl(memberRepository(), discountPolicy());
//    }
//
//    public MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
//    }
//    public DiscountPolicy discountPolicy(){
//        return new RateDiscountPolicy();
//    }

    // section3-9 스프링으로 전환하기 : @Configuration 및 @Bean으로 빈등록하기
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
    @Bean
    public DiscountPolicy discountPolicy(){
        return new RateDiscountPolicy();
    }
    
}
