package com.kh.a.member.model.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.a.member.model.dao.MemberDAO;
import com.kh.a.member.model.vo.MemberVO;

@Service("mService")
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDAO mDAO;
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	
	@Override
	public MemberVO login(MemberVO m) {
		return mDAO.login(sqlSession, m);
	}
	@Override
	public MemberVO login2(MemberVO memberVo) {
		return mDAO.login2(sqlSession, memberVo);
	}


	@Override
	public int insertMember(MemberVO m) {
		return mDAO.insertMember(sqlSession, m);
	}

	
	@Override
	public int updateMember(MemberVO memberVo) {
		return mDAO.updateMember(sqlSession, memberVo);
	}
	
}
