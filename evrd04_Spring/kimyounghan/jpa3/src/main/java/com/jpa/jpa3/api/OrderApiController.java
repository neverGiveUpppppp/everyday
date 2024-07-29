package com.jpa.jpa3.api;


import com.jpa.jpa3.domain.Order;
import com.jpa.jpa3.domain.OrderItem;
import com.jpa.jpa3.domain.OrderSearch;
import com.jpa.jpa3.dto.OrderDTO;
import com.jpa.jpa3.dto.OrderFlatDTO;
import com.jpa.jpa3.dto.OrderItemQueryDTO;
import com.jpa.jpa3.dto.OrderQueryDTO;
import com.jpa.jpa3.repository.OrderQueryRepository;
import com.jpa.jpa3.repository.OrderRepository;
import com.jpa.jpa3.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.*;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderService orderService;
    private final OrderRepository orderRepository;

    /**
     * 주문 조회 V1: 엔티티 직접 노출
     */
    @GetMapping("/api/v1/orders")
    public List<Order> ordersV1() {
        List<Order> all = orderRepository.findAllByCriteria(new OrderSearch());
        for (Order order : all) {
            order.getMember().getName();
            order.getDelivery().getAddress();  // Lazy 강제 초 기화
            List<OrderItem> orderItems = order.getOrderItems();
            orderItems.stream().forEach(o -> o.getItem().getName());
        }
        return all;
    }

    /**
     * 주문 조회 V2: 엔티티를 DTO로 변환
     * - fetch join 사용 X
     * - 트랜잭션 안에서 지연 로딩 필요
     */
    @GetMapping("/api/v2/orders")
    public List<OrderDTO> ordersV2() {
        List<Order> orders = orderRepository.findAllByCriteria(new OrderSearch());
        List<OrderDTO> result = orders.stream()
                .map(o -> new OrderDTO(o))
                .collect(toList());
        return result;
    }

    /**
     * 주문 조회 V3.1: 엔티티를 DTO로 변환 - 페치 조인 최적화 (fetch join 사용 O)
     * - 페이징 시에는 N부분을 포기해야함(대신에 batch fetch size? 옵션 주면 N -> 1 쿼리로 변경 가능)
     */
    public List<OrderDTO> ordersV3() {
        List<Order> orders = orderRepository.findAllWithItem();
        List<OrderDTO> result = orders.stream()
                .map(o -> new OrderDTO(o))
                .collect(toList());
        return result;
    }


    /**
     * 주문 조회 V3.2: 엔티티를 DTO로 변환 - 페치 조인 최적화 - 페이징과 한계 돌파
     * 페이징 불가 한계 돌파
     */
    @GetMapping("/api/v3.1/orders")
    public List<OrderDTO> ordersV3_page(@RequestParam(value = "offset", defaultValue = "0") int offset,
                                        @RequestParam(value = "limit", defaultValue = "100") int limit) {
        List<Order> orders = orderRepository.findAllWithMemberDelivery(offset, limit);
        List<OrderDTO> result = orders.stream()
                .map(o -> new OrderDTO(o))
                .collect(toList());
        return result;
        // yml에 옵션 추가 - default_batch_fetch_size: 1000
    }


    /**
     * 주문 조회 V4: JPA에서 DTO 직접 조회
     * - 컬렉션 N 조회 (1 + N Query)
     * - 페이징 가능
     */
    private final OrderQueryRepository orderQueryRepository;

    @GetMapping("/api/v4/orders")
    public List<OrderQueryDTO> ordersV4() {
        return orderQueryRepository.findOrderQueryDtos();
    }

    /**
     * 주문 조회 V5: JPA에서 DTO 직접 조회 - 컬렉션 조회 최적화
     * - 컬렉션 1 조회 최적화 버전 (1 + 1 Query)
     * - 페이징 가능
     */
    @GetMapping("/api/v5/orders")
    public List<OrderQueryDTO> ordersV5(){
        return orderQueryRepository.findAllByDTO_optimization();
    }


    /**
     * 주문 조회 V6: JPA에서 DTO로 직접 조회, 플랫 데이터 최적화
     *  - 플랫 데이터(1Query) (1 Query)
     *  - 페이징 불가능
     */
    @GetMapping("/api/v6/orders")
    public List<OrderQueryDTO> ordersV6() {
        List<OrderFlatDTO> flats = orderQueryRepository.findAllByDto_flat();

        return flats.stream()
                .collect(groupingBy(o -> new OrderQueryDTO(o.getOrderId(),o.getName(),
                                o.getOrderDate(), o.getOrderStatus(), o.getAddress()),
                        mapping(o -> new OrderItemQueryDTO(o.getOrderId(),
                                o.getItemName(), o.getOrderPrice(), o.getCount()), toList())
                )).entrySet().stream()
                .map(e -> new OrderQueryDTO(e.getKey().getOrderId(),
                        e.getKey().getName(), e.getKey().getOrderDate(), e.getKey().getOrderStatus(),
                        e.getKey().getAddress()))
                .collect(toList());
    }


}
