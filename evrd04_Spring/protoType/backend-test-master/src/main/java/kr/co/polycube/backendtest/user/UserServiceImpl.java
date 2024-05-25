package kr.co.polycube.backendtest.user;


import kr.co.polycube.backendtest.user.dto.CreateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Transactional
    public Long createUser(Users user) {
        validateDuplicationUser(user);
        Users savedUser = userRepository.save(user);
        return savedUser.getId();
    }

    @Transactional(readOnly = true)
    public Users findOne(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지않는 사용자입니다."));
    }

    @Transactional
    public Users updateUser(Users user) {
        Users existingUser = findOne(user.getId());  // findOne() 재사용
        existingUser.setName(user.getName());
        return existingUser;  // 영속성 컨텍스트 변경감지(dirty check) : save() 생략
    }

    private void validateDuplicationUser(Users user) {
        Optional<Users> findMembers = userRepository.findById(user.getId());
        if(findMembers.isPresent())
            throw new IllegalStateException("이미 존재하는 회원입니다.");
    }

}
