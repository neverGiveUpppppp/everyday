
package com.example.service;

import org.springframework.stereotype.Service;

import com.example.controller.form.MemberSaveForm;
import com.example.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	
	private final MemberMapper memberMapper;
	
	
	/**
	 * 회원 계정이 존재하는지 boolean 결과 리턴
	 * @param account
	 * @return
	 */
	public boolean isUseAccount(String account) {
		return memberMapper.selectMemberAccountCount(account) > 0;
	}

	public void insertMember(MemberSaveForm form) {
		memberMapper.insertMember(form);
	}
	
}
