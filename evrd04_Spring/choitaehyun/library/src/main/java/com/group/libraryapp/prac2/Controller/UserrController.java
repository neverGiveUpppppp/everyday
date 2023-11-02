package com.group.libraryapp.prac2.Controller;

import com.group.libraryapp.prac2.Service.UserrService;
import com.group.libraryapp.prac2.domain.Userr;
import com.group.libraryapp.prac2.dto.request.UserCreateReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserrController {
    private final UserrService userService;

    @Autowired
    public UserrController(UserrService userService){
        this.userService = userService;
    }

    @PostMapping("/user")
    public void userCreate(@RequestBody UserCreateReq req){
        userService.userCreate(req);
    }

    @GetMapping("/user")
    public List<Userr> getUsers() {
        return userService.getUsers();
    }

}
