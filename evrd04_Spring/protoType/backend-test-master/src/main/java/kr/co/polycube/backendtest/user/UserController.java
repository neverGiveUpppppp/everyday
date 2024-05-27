package kr.co.polycube.backendtest.user;


import jakarta.validation.Valid;
import kr.co.polycube.backendtest.user.dto.CreateUserRequest;
import kr.co.polycube.backendtest.user.dto.CreateUserResponse;
import kr.co.polycube.backendtest.user.dto.UpdateUserRequest;
import kr.co.polycube.backendtest.user.dto.UpdateUserResponse;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public CreateUserResponse join(@Valid @RequestBody CreateUserRequest request) {
        // dto를 엔티티로 변환
        Users user = new Users();
        user.setName(request.getName());

        Long id = userService.createUser(user);
        return new CreateUserResponse(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUser(@PathVariable("id") Long id) {
        Users user = userService.findOne(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public UpdateUserResponse modifyUser(@Valid @RequestBody UpdateUserRequest request) {
        // dto를 엔티티로 변환
        Users user = new Users();
        user.setId(request.getId());
        user.setName(request.getName());

        Users updatedUser = userService.updateUser(user);
        return new UpdateUserResponse(updatedUser);
    }

}