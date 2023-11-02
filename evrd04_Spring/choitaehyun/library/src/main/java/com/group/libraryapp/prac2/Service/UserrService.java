package com.group.libraryapp.prac2.Service;

import org.springframework.stereotype.Service;

@Service
public class UserrService {

    private final UserrRepository userRepository;

    public UserServiceV2(UserrRepository userRepository) {
        this.userRepository = userRepository;
    }


}
