package hello.core4.order;

import hello.core4.member.Member;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
