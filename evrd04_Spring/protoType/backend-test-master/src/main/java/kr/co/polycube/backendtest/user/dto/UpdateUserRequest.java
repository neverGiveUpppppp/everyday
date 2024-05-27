package kr.co.polycube.backendtest.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateUserRequest {

    @NotBlank(message = "id값은 필수입니다.")
    private Long id;

    @NotBlank(message = "이름은 필수값입니다.")
    @Size(max = 20, message = "이름은 20글자까지 가능합니다.")
    private String name;
}
