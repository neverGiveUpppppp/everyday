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
        validateDuplicationUser(user);
        Users existingUser = findOne(user.getId());  // findOne() 재사용
        existingUser.setName(user.getName());
        return existingUser;  // 영속성 컨텍스트 변경감지(dirty check) : save() 생략
    }

    private void validateDuplicationUser(Users user) {
        if(user.getName() == null || user.getName().trim().equals(""))
            throw new IllegalArgumentException("이름은 필수값입니다.");

        if(user.getName().length() > 20)
            throw new IllegalStateException("이름은 최대 20글자까지 가능합니다.");

        Optional<Users> findMembers = userRepository.findByName(user.getName());
        if(findMembers.isPresent())
            throw new IllegalStateException("이미 존재하는 회원입니다.");
    }

}
