package com.jpa.jpa3.a.service;

import com.jpa.jpa3.a.repository.MemberRepository;
import com.jpa.jpa3.a.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;


}
