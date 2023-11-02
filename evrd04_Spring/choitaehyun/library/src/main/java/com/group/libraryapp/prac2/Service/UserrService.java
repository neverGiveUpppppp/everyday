package com.group.libraryapp.prac2.Service;

import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.prac2.Repository.UserrRepository;
import com.group.libraryapp.prac2.domain.Userr;
import com.group.libraryapp.prac2.dto.request.UserCreateReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserrService {

    private final UserrRepository userRepository;

    @Autowired
    public UserrService(UserrRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Transactional
    public void userCreate(UserCreateReq req) {
        userRepository.save(new Userr(req.getName(), req.getAge()));
    }

    @Transactional
    public List<Userr> getUsers() {
        return userRepository.findAll().stream()
                .map(users -> new UserResponse(users.getId(),users.getName(),users.getAge()))
                .collect(Collectors.toList());
    }


}
