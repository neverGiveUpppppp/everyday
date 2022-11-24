package com.ex.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.ex.service.BoardService;

public class BoardController {
	

	final Logger logger = LogManager.getLogger(getClass());
	
	private BoardService boardService;
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception{
		logger.info("boardList!");
		ModelAndView mv = new ModelAndView();
		try {
			request.setAttribute("boardList",boardService.selectBoardList());
		} catch(SQLException e) {
			logger.error("boardListError",e);
		}
		mv.setViewName("/board/list");
		return mv;
	}
	
	public BoardService getBoardService() {
		return boardService;
	}
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}
}
