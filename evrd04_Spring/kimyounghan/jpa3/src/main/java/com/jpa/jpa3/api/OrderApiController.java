package com.jpa.jpa3.api;


import com.jpa.jpa3.domain.Order;
import com.jpa.jpa3.domain.OrderItem;
import com.jpa.jpa3.repository.OrderRepository;
import com.jpa.jpa3.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderService orderService;
    private final OrderRepository orderRepository;

    /**
     * 주문 조회 V1: 엔티티 직접 노출
     */
    @GetMapping("/api/v1/orders")
    public List<Order> ordersV1(){
        List<Order> all = orderRepository.findAllByCriteria(new OrderSearch());
        for(Order order : all){
            order.getMember().getName();
            order.getDelivery().getAddress();  // Lazy 강제 초 기화
            List<OrderItem> orderItems = order.getOrderItems();
            orderItems.stream().forEach(o -> o.getItem().getName());
        }
        return all;
    }

}
