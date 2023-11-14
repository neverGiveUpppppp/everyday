package com.group.libraryapp.prac3.service;

import com.group.libraryapp.prac3.domain.user.User;
import com.group.libraryapp.prac3.dto.user.request.UserCreateRequest;
import com.group.libraryapp.prac3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(UserCreateRequest request) {
        User user = userRepository.save(new User(request.getName(), request.getAge()));
    }

}
