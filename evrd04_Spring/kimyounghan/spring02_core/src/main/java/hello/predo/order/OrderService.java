package hello.predo.order;

import hello.predo.discount.DiscountPolicy;
import hello.predo.member.Member;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
