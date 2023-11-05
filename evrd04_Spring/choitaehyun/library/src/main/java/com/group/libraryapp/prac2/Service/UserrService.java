package com.group.libraryapp.prac2.Service;

import com.group.libraryapp.dto.user.request.UserUpdateRequest;
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

    @Transactional(readOnly = true)
    public List<UserResponse> getUsers() {
        return userRepository.findAll().stream()
                .map(users -> new UserResponse(users.getId(),users.getName(),users.getAge()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateUser(UserUpdateRequest request) {
        Userr user = userRepository.findById(request.getId()) // .findById() : select * from user whwere id = ?; 이 쿼리가 자동으로 나가게됨. 반환값은 Optional<User>
                .orElseThrow(IllegalArgumentException::new);// 해당 id 유저가 없다면 예외 발생(유효성 체크)
        user.updateName(request.getName()); // update sql를 바로 호출하는게 아닌 User객체를 최신화해주고 save()로 Update 진행
    }

    @Transactional
    public void deleteUser(String name) {
        Userr user = userRepository.findByName(name)
                .orElseThrow(IllegalArgumentException::new);
        if(user == null)
            throw new IllegalArgumentException();
    }
}
