package com.group.libraryapp.prac4.service;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.prac4.domain.User2;
import com.group.libraryapp.prac4.dto.User2CreateRequest;
import com.group.libraryapp.prac4.dto.User2Response;
import com.group.libraryapp.prac4.repository.User2Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
public class User2ServiceImpl implements User2Service {

    private final User2Repository user2Repository;

    public User2ServiceImpl(User2Repository user2Repository) {
        this.user2Repository = user2Repository;
    }

    @Transactional
    public void userCreate(User2CreateRequest request) {
        user2Repository.save(new User2(request.getName(),request.getAge()));

    }

    @Transactional
    public List<User2Response> userGetAll() {
        return user2Repository.findAll().stream()
                .map(User2Response::new)
                .collect(Collectors.toList());
    }

    @Transactional // @Transactional : 트랜잭션 원자성(atomic) 적용 어노테이션 // 해당 메소드가 시작될 때 start transaction;을 해주고 에러 없으면 commit 있으면 rollback함
    public void saveUser(UserCreateRequest request) {
//        User u = userRepository.save(new User(request.getName(), request.getAge()));// jpa는 해당 메소드에 객체를 넣어주면 자동으로 쿼리 생성해서 보냄
        Consumer<User> users = (user) -> System.out.println(user);
        Consumer<User> userss = (user) -> User::new;
//        userRepository.save(Consumer<User> users = (user) -> user::new);
//        throw new IllegalArgumentException(); // (Unchecked Exception) 인위적으로 예외 발생시켜 트랜잭션 원자성 확인
//        throw new IOException(); // checked Exception은 Unchecked와 달리 트랜잭션 안에서 rollback 안됨
    }

}
