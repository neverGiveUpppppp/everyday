package com.jpa.jpa3.b.dto.response;

import lombok.Data;

@Data
public class CreateMember2Response {
    private Long id;

    public CreateMember2Response(Long id) {
        this.id = id;
    }
}
