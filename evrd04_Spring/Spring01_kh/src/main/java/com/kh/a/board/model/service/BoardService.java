package com.kh.a.board.model.service;

import java.util.ArrayList;

import com.kh.a.board.model.vo.BoardVO;
import com.kh.a.board.model.vo.PageInfo;

public interface BoardService {

	int getListCount();
	ArrayList<BoardVO> getBoardList(PageInfo pi);
	int insertBoard(BoardVO b);
	BoardVO selectBoard(int bId);
	

}
