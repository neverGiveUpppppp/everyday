package com.jpa.jpa3.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateMemberRequest {
    private Long id;
    private String name;
}
