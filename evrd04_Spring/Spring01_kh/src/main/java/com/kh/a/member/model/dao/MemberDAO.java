package com.kh.a.member.model.dao;

import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.a.member.model.vo.MemberVO;


@Repository("mDAO")
public class MemberDAO {

	public MemberVO login(SqlSessionTemplate sqlSession, MemberVO m) {
		return sqlSession.selectOne("memberMapper.login",m);
	}
	public MemberVO login2(SqlSessionTemplate sqlSession, MemberVO memberVo) {
		return sqlSession.selectOne("memberMapper.login",memberVo);
	}

	public int insertMember(SqlSessionTemplate sqlSession, MemberVO m) {
		return sqlSession.insert("memberMapper.insertMember",m);
	}
	
	
	public int updateMember(SqlSessionTemplate sqlSession, MemberVO memberVo) {
		return sqlSession.update("memberMapper.updateMember",memberVo);
	}
	
	public int updatePwd(SqlSessionTemplate sqlSession,HashMap<String, String> map) {
		return sqlSession.update("memberMapper.updatePwd",map);
	}
	
	public int deleteMember(SqlSessionTemplate sqlSession, String id) {
		return sqlSession.update("memberMapper.deleteMember",id);
	}
	
	public int checkIdDup(SqlSessionTemplate sqlSession, String id) {
		return sqlSession.selectOne("memberMapper.checkIdDup",id);
	}
	
	

}
