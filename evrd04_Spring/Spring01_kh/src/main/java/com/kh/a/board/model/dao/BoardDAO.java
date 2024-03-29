package com.kh.a.board.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.a.board.model.vo.BoardVO;
import com.kh.a.board.model.vo.PageInfo;
import com.kh.vo.Reply;

@Repository
public class BoardDAO {

	
	public int getListCount(SqlSessionTemplate sqlSession) {
		return sqlSession.selectOne("boardMapper.getListCount");
	}
	
	
	public ArrayList<BoardVO> getBoardList(SqlSessionTemplate sqlSession, PageInfo pi) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit(); // 게시판 몇개를 건너 뛸지 계산하는게 핵심
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());  
		return (ArrayList)sqlSession.selectList("boardMapper.getBoardList", null, rowBounds);
	}
	// 시작행(startRow) 끝행(endRow) 만든이유
	// 몇번째부터 몇번째행까지 가져올지 정하기 위해서 startRow, endRow 만듬
	// ex) 2페이지 11-20 가져오기
//	int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;  
//	int endRow = startRow + pi.getBoardLimit() - 1;
	/* 1일 때 1 2일때 11 3일 때 21 나와야함. cpx10 1일 때 10 2일때는 20 3일 때는 30의 스타트로우가 발생할 것임. 
	   cp자체에다가 -1먼저 해주게 되면 1이 아니라 0 2가 아니라 1 3이 아니라 2가 들어가게 됨. 곱하기 10을 하는 것도
	   0 10 20이 들어가게됨 여기에 마지막에 +1만 하면 1 11이 나옴 */


	public int insertBoard(SqlSessionTemplate sqlSession, BoardVO b) {
		return sqlSession.insert("boardMapper.insertBoard",b);
	}
	
	
	
	public int addReadCount(SqlSessionTemplate sqlSession, int bId) {
		return sqlSession.update("boardMapper.addReadCount",bId);
	}
	public BoardVO selectBoard(SqlSessionTemplate sqlSession, int bId) {
		return sqlSession.selectOne("boardMapper.selectBoard", bId);
	}
	public int addReadCount2(SqlSessionTemplate sqlSession, int bId) {
		return sqlSession.update("boardMapper.addReadCount",bId);
	}
	public BoardVO selectBoard2(SqlSessionTemplate sqlSession, int bId) {
		return sqlSession.selectOne("boardMapper.selectBoard",bId);
	}
	public int addReadCount3(SqlSessionTemplate sqlSession, int bId) {
		return sqlSession.update("boardMapper.addReadCount",bId);
	}
	public BoardVO selectBoard3(SqlSessionTemplate sqlSession, int bId) {
		return sqlSession.selectOne("boardMapper.selectBoard",bId);
	}
	public int addReadCount4(SqlSessionTemplate sqlSession, int bId) {
		return sqlSession.update("boardMapper.addReadCount",bId);
	}
	public BoardVO selectBoard4(SqlSessionTemplate sqlSession, int bId) {
		return sqlSession.selectOne("boardMapper.selectBoard",bId);
	}
	public int addReadCount5(SqlSessionTemplate sqlSession, int boardId) {
		return sqlSession.update("boardMapper.addReadCount",boardId);
	}
	public BoardVO selectBoard5(SqlSessionTemplate sqlSession, int boardId) {
		return sqlSession.selectOne("boardMapper.selectBoard",boardId);
	}
	
	public int addReadCount6(SqlSessionTemplate sqlSession, int bId) {
		return sqlSession.update("boardMapper.addReadCount",bId);
	}
	public BoardVO selectBoard6(SqlSessionTemplate sqlSession, int bId) {
		return sqlSession.selectOne("boardMapper.selectBoard",bId);
	}
	public int addReadCount7(SqlSessionTemplate sqlSession, int boardId) {
		return sqlSession.update("boardMapper.selectBoard",boardId);
	}
	public BoardVO selectBoard7(SqlSessionTemplate sqlSession, int boardId) {
		return sqlSession.selectOne("boardMapper.selectBoard",boardId);
	}
	public int addReadCount9(SqlSessionTemplate sqlSession, int bId) {
		return sqlSession.update("boardMapper.addReadCount",bId);
	}
	public BoardVO selectBoard9(SqlSessionTemplate sqlSession, int bId) {
		return sqlSession.selectOne("boardMapper.selectBoard",bId);
	}

	
	public int updateBoard(SqlSessionTemplate sqlSession, BoardVO boardVo) {
		return sqlSession.update("boardMapper.updateBoard",boardVo);
	}


	public int deleteBoard(SqlSessionTemplate sqlSession, int bId) {
		return sqlSession.update("boardMapper.deleteBoard",bId);
	}


	public int insertReply(SqlSessionTemplate sqlSession, Reply replyVo) {
		return sqlSession.insert("boardMapper.insertReply",replyVo);
	}


	// 댓글 가져오기 : selectList
	public ArrayList<Reply> selectReplyList(SqlSessionTemplate sqlSession, int bId) {
		return (ArrayList)sqlSession.selectList("boardMapper.selectReplyList", bId);
	}
	public ArrayList<Reply> selectReplyList2(SqlSessionTemplate sqlSession, int bId){
		return (ArrayList)sqlSession.selectList("boardMapper.selectReplyList2",bId);
	}
	public ArrayList<Reply> selectReplyList3(SqlSessionTemplate sqlSession, int bId){
		return (ArrayList)sqlSession.selectList("boardMapper.selectReplyList3",bId);
	}
	
	
	
	public ArrayList<BoardVO> topList(SqlSessionTemplate sqlSession){
		return (ArrayList)sqlSession.selectList("boardMapper.topList");
	}
	public ArrayList<BoardVO> topList1(SqlSessionTemplate sqlSession){
		return (ArrayList)sqlSession.selectList("boardMapper.topList1");
	}
	
	
	

}
