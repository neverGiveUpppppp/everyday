package com.kh.a.member.model.service;

import com.kh.a.member.model.vo.MemberVO;

public interface MemberService {

	MemberVO login(MemberVO m);
	MemberVO login2(MemberVO memberVo);

	int insertMember(MemberVO m);

	int updateMember(MemberVO memberVo);




}
