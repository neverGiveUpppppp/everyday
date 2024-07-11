package com.jpa.jpa3.dto;

import com.jpa.jpa3.domain.OrderItem;
import lombok.Data;

@Data
public class OrderItemDTO {

    private String itemName;
    private int orderPrice;
    private int count;

    public OrderItemDTO(OrderItem orderItem) {
        itemName = orderItem.getItem().getName();
        orderPrice = orderItem.getOrderPrice();
        count = orderItem.getCount();
    }

}
