package com.jpa.jpa3.a.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class OrderItemQueryDTO {

    @JsonIgnore
    private Long orderId;
    private String itemName;  // 상품명
    private int orderPrice;   // 주문 가격
    private int count;        // 주문 수량

    public OrderItemQueryDTO(Long orderId, String itemName, int orderPrice, int count) {
        this.orderId = orderId;
        this.itemName = itemName;
        this.orderPrice = orderPrice;
        this.count = count;
    }

}
