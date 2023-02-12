package com.kh.a.member.model.service;

import java.util.HashMap;

import com.kh.a.member.model.vo.MemberVO;

public interface MemberService {

	MemberVO login(MemberVO m);
	MemberVO login2(MemberVO memberVo);
	int insertMember(MemberVO m);
	int updateMember(MemberVO memberVo);
	int updatePwd(HashMap<String, String> map);
	int deleteMember(String id);
	int checkIdDup(String id);



}
