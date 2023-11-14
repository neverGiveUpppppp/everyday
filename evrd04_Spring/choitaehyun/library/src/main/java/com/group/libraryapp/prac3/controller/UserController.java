package com.group.libraryapp.prac3.controller;

import com.group.libraryapp.prac3.dto.user.request.UserCreateRequest;
import com.group.libraryapp.prac3.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    public final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("user")
    public void createUser(@RequestBody UserCreateRequest request){
        userService.createUser(request);
    }

}
