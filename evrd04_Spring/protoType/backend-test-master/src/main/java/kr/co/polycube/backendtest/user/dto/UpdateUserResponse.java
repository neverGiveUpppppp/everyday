package kr.co.polycube.backendtest.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class UpdateUserResponse {
    private Long id;
    private String name;
}
