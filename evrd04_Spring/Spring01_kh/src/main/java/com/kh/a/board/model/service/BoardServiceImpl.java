package com.kh.a.board.model.service;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.a.board.model.dao.BoardDAO;
import com.kh.a.board.model.vo.BoardVO;
import com.kh.a.board.model.vo.PageInfo;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	public BoardDAO bDAO;
	
	@Autowired
	public SqlSessionTemplate sqlSession;

	
	@Override
	public int getListCount() {
		return bDAO.getListCount(sqlSession);
	}
	
	
	@Override
	public ArrayList<BoardVO> getBoardList(PageInfo pi){
		return bDAO.getBoardList(sqlSession, pi);
	}


	@Override
	public int insertBoard(BoardVO b) {
		return bDAO.insertBoard(sqlSession, b);
	}
	
	public BoardVO selectBoard(int bId) {
		int result = bDAO.addReadCount(sqlSession, bId);
		
		BoardVO boardVo = null;
		if(result > 0) {
			boardVo = bDAO.selectBoard(sqlSession, bId);
		}
		return boardVo;
	}
	public BoardVO selectBoard2(int bId) {
		int result = bDAO.addReadCount2(sqlSession, bId);
		
		BoardVO boardVo = null;
		if(result > 0) {
			boardVo = bDAO.selectBoard2(sqlSession, bId);
		}
		return boardVo;
	}
	public BoardVO selectBoard3(int bId) {
		int result = bDAO.addReadCount3(sqlSession, bId);
		
		BoardVO boardVo = null;
		if(result > 0) {
			boardVo = bDAO.selectBoard3(sqlSession, bId);
		}
		return boardVo;
	}
	public BoardVO selectBoard4(int bId) {
		int result = bDAO.addReadCount4(sqlSession, bId);
		BoardVO boardVo = null;
		if(result > 0) {
			boardVo = bDAO.selectBoard4(sqlSession,bId);
		}
		return boardVo;
	}
	/** 연습 텍스트 : 게시판 상세  **/
	// 게시글 읽기하니 조회수+1 해야함
	// vo객체 선언
	// 해당 게시판 db에서 가져오기
	// 리턴
	
	
	
	
	
}
