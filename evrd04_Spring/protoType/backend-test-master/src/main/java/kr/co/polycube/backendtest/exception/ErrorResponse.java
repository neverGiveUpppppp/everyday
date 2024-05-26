package kr.co.polycube.backendtest.exception;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private String error;
    private String message;


}
