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
	
	
	
	
	
	
	
	
}
