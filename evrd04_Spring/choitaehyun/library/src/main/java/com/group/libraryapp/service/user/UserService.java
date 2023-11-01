package com.group.libraryapp.service.user;

import com.group.libraryapp.domain.book.user.User;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveUser(UserCreateRequest request) {
        User user = userRepository.save(new User(request.getName(), request.getAge()));
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getUsers(){
        return userRepository.findAll().stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateUser(UserUpdateRequest request) {
        // 수정할려는 유저가 있는 지 확인 필요
        User user = userRepository.findById(request.getId())
                .orElseThrow(IllegalArgumentException::new);
        user.updateName(request.getName());
        userRepository.save(user); // 영속성 컨텍스트 변경감지dirty check로 생략가능
    }

    @Transactional
    public void deleteUser(String name){
        User user = userRepository.findByName(name)
                .orElseThrow(IllegalArgumentException::new);
        if (user == null) {     // By 앞에 들어갈 수 있는 구절 4가지 : find(), Optional<>, exists(), count()
            throw new IllegalArgumentException();
        }
//            userRepository.save(name); // 영속성 컨텍스트 변경감지 생략
    }

}
