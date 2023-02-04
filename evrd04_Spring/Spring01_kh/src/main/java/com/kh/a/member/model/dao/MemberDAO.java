package com.kh.a.member.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.a.member.model.vo.MemberVO;


@Repository("mDAO")
public class MemberDAO {

	public MemberVO login(SqlSessionTemplate sqlSession, MemberVO m) {
		return sqlSession.selectOne("memberMapper.login",m);
	}

	public int insertMember(SqlSessionTemplate sqlSession, MemberVO m) {
		return sqlSession.insert("memberMapper.insertMember",m);
	}
	
	
	public int updateMember(SqlSessionTemplate sqlSession, MemberVO memberVo) {
		return sqlSession.update("memberMapper.updateMember",memberVo);
	}
	

}
