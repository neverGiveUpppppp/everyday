package com.group.libraryapp.prac4.controller;

import com.group.libraryapp.prac4.domain.User2;
import com.group.libraryapp.prac4.dto.User2CreateRequest;
import com.group.libraryapp.prac4.dto.User2Response;
import com.group.libraryapp.prac4.dto.User2UpdateReq;
import com.group.libraryapp.prac4.service.User2ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class User2Controller {


    private final User2ServiceImpl user2Service;

    @Autowired
    public User2Controller(User2ServiceImpl user2Service) {
        this.user2Service = user2Service;
    }

    @PostMapping("/user")
    public void userCreate(User2CreateRequest request) {
        user2Service.userCreate(request);
    }

    @GetMapping("/user")
    public List<User2Response> userGetAll() {
        return user2Service.userGetAll();
    }

    @PutMapping("/user")
    public void userUpdate(@RequestBody User2UpdateReq request) {
        user2Service.userUpdate(request);
    }


}
