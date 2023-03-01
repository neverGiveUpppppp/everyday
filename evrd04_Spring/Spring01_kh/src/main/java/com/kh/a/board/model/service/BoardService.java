package com.kh.a.board.model.service;

import java.util.ArrayList;

import com.kh.a.board.model.vo.BoardVO;
import com.kh.a.board.model.vo.PageInfo;

public interface BoardService {

	int getListCount();
	ArrayList<BoardVO> getBoardList(PageInfo pi);
	int insertBoard(BoardVO b);
	BoardVO selectBoard(int bId);
	BoardVO selectBoard2(int bId);
	BoardVO selectBoard3(int bId);
	BoardVO selectBoard4(int bId);
	BoardVO selectBoard5(int boardId);
	BoardVO selectBoard6(int bId);
	int updateBoard(BoardVO boardVo);
	






}
