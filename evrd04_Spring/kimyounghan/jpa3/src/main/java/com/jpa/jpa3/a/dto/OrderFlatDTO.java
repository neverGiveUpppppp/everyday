package com.jpa.jpa3.a.dto;

import com.jpa.jpa3.a.domain.Address;
import com.jpa.jpa3.a.domain.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderFlatDTO {

    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private Address address;
    private OrderStatus orderStatus;
    private String itemName;         // 상품명
    private int orderPrice;          // 주문 가격
    private int count;               // 주문 수량

    public OrderFlatDTO(Long orderId, String name, LocalDateTime orderDate, OrderStatus orderStatus, Address address, String itemName, int orderPrice, int count) {
        this.orderId = orderId;
        this.name = name;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.address = address;
        this.itemName = itemName;
        this.orderPrice = orderPrice;
        this.count = count;
    }

}
