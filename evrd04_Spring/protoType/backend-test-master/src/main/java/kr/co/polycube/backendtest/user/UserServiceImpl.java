package kr.co.polycube.backendtest.user;


import kr.co.polycube.backendtest.user.dto.CreateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Transactional
    public Long createUser(Users user) {
        validateDuplicationUser(user);
        return user.getId();
    }

    @Transactional(readOnly = true)
    public Users findOne(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @Transactional
    public void updateUser(Long id, String name) {
        Users existingUser = findOne(id);
        existingUser.setName(name);
    }

    private void validateDuplicationUser(Users user) {
        List<Users> findMembers = userRepository.findByName(user.getName());
        if(!findMembers.isEmpty())
            throw new IllegalStateException("이미 존재하는 회원입니다.");
    }

}
