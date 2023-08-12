package hello.core3.order;

import hello.core3.discount.DiscountPolicy;
import hello.core3.member.Member;
import hello.core3.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {
    // DIP,OCP 위반
//    MemberRepository memberRespository = new MemoryMemberRepository();
//    DiscountPolicy discountPolicy = new FixDiscountPolicy();

    // DIP,OCP 위반 해결 : DI컨테이너
//    MemberRepository memberRepository;
//    DiscountPolicy discountPolicy;

<<<<<<< HEAD
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
=======
    // section7-1 의존관계 자동 주입
//    private final MemberRepository memberRepository;
//    private final DiscountPolicy discountPolicy;
>>>>>>> 141b81d18eab1dbc045c3190e4ee52f093c49757

    // 1) 생성자 주입  // 생성자 1개 autowired 생략가능
//    private final MemberRepository memberRepository;
//    private final DiscountPolicy discountPolicy;
//
//    public OrderServiceImpl(MemberRepository memberRespository, DiscountPolicy discountPolicy){
//        this.memberRepository = memberRespository;
//        this.discountPolicy = discountPolicy;
//    }

    // 2)수정자 주입
//    private MemberRepository memberRepository;
//    private DiscountPolicy discountPolicy;
//
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository){
//        this.memberRepository = memberRepository;
//    }
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }

    // 3) 필드 주입
//    @Autowired private MemberRepository memberRepository;
//    @Autowired private DiscountPolicy discountPolicy;
//
//    @Autowired
//    private MemberRepository memberRepository;
//    @Autowired
//    private DiscountPolicy discountPolicy;

    // 4) 일반메소드 주입
//    private MemberRepository memberRepository;
//    private DiscountPolicy discountPolicy;
//
//    @Autowired
//    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }
    
    

/********************* business logic *******************************/

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice){
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }



    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

}
