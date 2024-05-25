package kr.co.polycube.backendtest.user.dto;

import kr.co.polycube.backendtest.user.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class UpdateUserResponse {
    private Long id;
    private String name;

    public UpdateUserResponse(Users updatedUser) {
        this.id = updatedUser.getId();
        this.name = updatedUser.getName();
    }
}
