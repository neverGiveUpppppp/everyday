package com.group.libraryapp.prac4.service;

import com.group.libraryapp.prac4.dto.User2CreateRequest;
import com.group.libraryapp.prac4.dto.User2Response;

import java.util.List;

public interface User2Service {
    void userCreate(User2CreateRequest request);

    List<User2Response> userGetAll();

}
