package com.jpa.jpa3.a.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReadAllMemberResponse<T> { // member도메인에 화면단용 response 객체

    private T data;

}
