package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.controller.form.MemberSaveForm;
import com.example.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
	
	final Logger logger = LoggerFactory.getLogger(getClass());
	
	private final MemberService memberService;
	
	/**
	 * 정보 입력 화면
	 * @param model
	 * @return
	 */
	@GetMapping("/form")
	public String form(Model model) {
		return "/member/form";
	}

	
	/**
	 * 가입처리
	 * @param form
	 * @return
	 */
	@PostMapping("/join")
	@ResponseBody
	public String join(@Validated MemberSaveForm form) {
		// 계정 중복체크
		boolean isUseAccount = memberService.isUseAccount(form.getAccount());
		logger.info("isUserAccount : {}", isUseAccount);
		Assert.state(!isUseAccount, "이미 사용 중인 계정");
		// 회원가입 처리
		memberService.insertMember(form);
		// 가입 완료 화면
		return "/member/join-complete";
	}
	
	
	
}



