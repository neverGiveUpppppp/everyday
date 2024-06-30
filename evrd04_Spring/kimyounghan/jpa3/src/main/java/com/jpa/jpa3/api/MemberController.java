package com.jpa.jpa3.api;


import com.jpa.jpa3.domain.Member;
import com.jpa.jpa3.dto.response.CreateMemberResponse;
import com.jpa.jpa3.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

/**
 * 등록 V1: 요청 값으로 Member 엔티티를 직접 받는다.
 */
    @PostMapping("/api/v1/members")
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member){
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }



}
