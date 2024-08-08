package com.jpa.jpa3.b.api;


import com.jpa.jpa3.b.domain.Member2;
import com.jpa.jpa3.b.dto.response.CreateMember2Response;
import com.jpa.jpa3.b.service.Member2Service;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class Member2Controller {

    private final Member2Service memberService;

    @PostMapping("/api/v/members")
    public CreateMember2Response saveMember1(@RequestBody @Valid Member2 member) {
        Long id = memberService.join(member);
        return new CreateMember2Response(id);
    }
}
