package com.group.libraryapp.prac2.Controller;

import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.prac2.Service.UserrService;
import com.group.libraryapp.prac2.domain.Userr;
import com.group.libraryapp.prac2.dto.request.UserCreateReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<UserResponse> getUsers() {
        return userService.getUsers();
    }

    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdateRequest request) {
        userService.updateUser(request);
    }

    @DeleteMapping
    public void deleteUser(@RequestParam String name) {
        userService.deleteUser(name);
    }

}
