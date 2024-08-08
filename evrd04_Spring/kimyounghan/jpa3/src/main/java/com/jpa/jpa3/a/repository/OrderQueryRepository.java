package com.jpa.jpa3.a.repository;

import com.jpa.jpa3.a.dto.OrderFlatDTO;
import com.jpa.jpa3.a.dto.OrderItemQueryDTO;
import com.jpa.jpa3.a.dto.OrderQueryDTO;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class OrderQueryRepository {

    private final EntityManager em;

    /**
     * 주문 조회 V4: JPA에서 DTO 직접 조회
     *   컬렉션은 별도로 조회
     *   Query: 루트 1번, 컬렉션 N 번
     *   단건 조회에서 많이 사용하는 방식
     */
    public List<OrderQueryDTO> findOrderQueryDtos(){
        List<OrderQueryDTO> result = findOrders();

        return result.forEach( o -> {
            List <OrderItemQueryDTO> orderItems = findOrderItems(o.getOrderId());
            o.setOrderItems(orderItems);
        });
    }
    // V4 - 1:N 관계(컬렉션)를 제외한 나머지를 한번에 조회
    private List<OrderQueryDTO> findOrders(){
        return em.createQuery(
                "select new com.jpa.jpa3.dto.OrderQueryDto(o.id, m.name, o.orderDate, o.status, d.address)" +
                        "from Order o" +
                        "join o.member m" +
                        "join o.delivery d", OrderQueryDTO.class)
                .getResultList();
    }
    // V4 - 1:N 관계인 orderItems 조회
    private List<OrderItemQueryDTO> findOrderItems(String orderId){
        return em.createQuery(
                "select new com.jpa.jpa3.dto.OrderItemQueryDTO((oi.order.id, i.name,oi.orderPrice, oi.count)" +
                        " from OrderItem oi" +
                        " join oi.item i" +
                        " where oi.order.id = : orderId", OrderItemQueryDTO.class)
                .setParameter("orderId", orderId)
                .getResultList();
    }

    /**
     * 주문 조회 V5: JPA에서 DTO 직접 조회 - 컬렉션 조회 최적화
     */
    public List<OrderQueryDTO> findAllByDTO_optimization() {
        List<OrderQueryDTO> result = findOrders();

        Map<Long, List<OrderItemQueryDTO>> orderItemMap = findOrderItemMap(toOrderIds(result));

        result.forEach(o -> o.setOrderItems(orderItemMap.get(o.getOrderId())));
        return result;
    }
    private List<Long> toOrderIds(List<OrderQueryDTO> result) {
        return result.stream()
                .map(o -> o.getOrderId())
                .collect(Collectors.toList());
    }
    private Map<Long, List<OrderItemQueryDTO>> findOrderItemMap(List<Long> orderIds) {
        List<OrderItemQueryDTO> orderItems = em.createQuery(
                        "select new com.jpa.jpa3.dto.OrderItemQueryDTO(oi.order.id, i.name,oi.orderPrice, oi.count)" +
                                " from OrderItem oi" +
                                " join oi.item i" +
                                " where oi.order.id in :orderIds", OrderItemQueryDTO.class)
                .setParameter("orderIds", orderIds)
                .getResultList();
        return orderItems.stream()
                .collect(Collectors.groupingBy(OrderItemQueryDTO::getOrderId));
    }


    public List<OrderFlatDTO> findAllByDto_flat() {
        return em.createQuery(
                    "select new com.jpa.jpa3.dto.OrderFlatDTO(o.id, m.name, o.orderDate,o.orderStatus, d.address, i.name, oi.orderPrice, oi.count)" +
                            " from Order o" +
                            " join o.member m" +
                            " join o.delivery d" +
                            " join o.orderItems oi" +
                            " join oi.item i", OrderFlatDTO.class)
            .getResultList();
    }
}
