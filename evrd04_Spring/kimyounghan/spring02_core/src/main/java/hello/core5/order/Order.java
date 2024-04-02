package hello.core5.order;

import lombok.Data;

@Data
public class Order {
    private Long memberId;
    private String itemName;
    private int itemPrice;
    private int discountPrice;

    public int calculatePrice(){
        return itemPrice - discountPrice; // 원가 - 할인액 = 최종금액
    }

}
