package hello.core3.order;

import hello.core3.member.Member;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);

}