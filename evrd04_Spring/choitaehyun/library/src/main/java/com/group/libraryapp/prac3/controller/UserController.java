package com.group.libraryapp.prac3.controller;

import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.prac3.domain.user.User;
import com.group.libraryapp.prac3.dto.user.request.UserCreateRequest;
import com.group.libraryapp.prac3.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {


    public final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public void createUser(@RequestBody UserCreateRequest request) {
        userService.createUser(request);
    }

    @GetMapping("/user")
    public List<UserResponse> getUsers() {
        userService.getUsers();
    }


}
