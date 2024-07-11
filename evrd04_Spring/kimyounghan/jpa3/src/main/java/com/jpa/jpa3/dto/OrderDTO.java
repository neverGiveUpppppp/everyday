package com.jpa.jpa3.dto;

import com.jpa.jpa3.domain.Address;
import com.jpa.jpa3.domain.Order;
import com.jpa.jpa3.domain.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Data
public class OrderDTO {

    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;
    private List<OrderItemDTO> orderItems;

    public OrderDTO(Order order) {
        orderId = order.getId();
        name = order.getMember().getName();
        orderDate = order.getOrderDate();
        orderStatus = order.getOrderStatus();
        address = order.getDelivery().getAddress();
        orderItems = order.getOrderItems().stream()
                .map(orderItem -> new OrderItemDTO(orderItem))
                .collect(toList());
    }


}
