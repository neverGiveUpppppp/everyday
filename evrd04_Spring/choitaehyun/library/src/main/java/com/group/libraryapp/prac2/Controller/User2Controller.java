package com.group.libraryapp.prac2.Controller;

import com.group.libraryapp.prac2.Service.UserrService;
import com.group.libraryapp.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserrController {
    private final UserrService userService;

    @Autowired
    public UserrController(UserrService userService){
        this.userService = userService;
    }


}
