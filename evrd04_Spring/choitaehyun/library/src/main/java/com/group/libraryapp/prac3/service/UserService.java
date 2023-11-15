package com.group.libraryapp.prac3.service;

import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.prac3.domain.user.User;
import com.group.libraryapp.prac3.dto.user.request.UserCreateRequest;
import com.group.libraryapp.prac3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void createUser(UserCreateRequest request) {
        User user = userRepository.save(new User(request.getName(), request.getAge()));
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getUsers(){
        return userRepository.findAll().stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());

    }


}
