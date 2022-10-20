package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.domain.Board;
import com.example.service.BoardService;

import lombok.RequiredArgsConstructor;

/**
 * 게시물 컨트롤러
 */
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
	
	final Logger logger = LoggerFactory.getLogger(getClass());

	private final BoardService boardService;

	/**
	 * 게시물 목록 화면
	 * @param model
	 * @return
	 */
	@GetMapping
	public String list(Model model) {
		logger.debug("boardList...");
		// 게시물 목록 조회 후 model에 boardList key로 저장
		model.addAttribute("boardList",boardService.selectBoardList());
		// jsp를 호출
		return "/board/list";
	}
	
	/**
	 * 게시물 상세 화면
	 * @param model
	 * @return
	 */
	
	@GetMapping("/{boardSeq}")
//	public String form(Model model, @PathVariable(name = "boardSeq") int boardSeq) { // 스프링에서 requried를 true로 기본값으로 준다고함
	public String form(Model model, @PathVariable int boardSeq) { // 스프링에서 requried를 true로 기본값으로 준다고함
		// 게시물 조회
		Board board = boardService.selectBoard(boardSeq);
		Assert.notNull(board, "게시글 정보 없습니다");
		// detail.html에서 board를 사용하기 위해 model에 넣는다
		model.addAttribute("board", board);
		return "/board/detail";
	}
	
	/**
	 * 게시물 등록 화면
	 * @param model
	 * @return
	 */

	@GetMapping("/form")
	public String form(Model model) {
		// jsp를 호출
		return "/board/form";
	}
	
	
	/**
	 * 게시물 수정 화면
	 * @param model
	 * @param boardSeq
	 * @return 
	 */
	
	@GetMapping("/edit/{boardSeq}")
	public String edit(Model model, @PathVariable(required = true)int boardSeq){ // 여기 required도 디폴트가 true라 생략가능
		logger.debug("edit");
		
		// 데이터 조회
		Board board = boardService.selectBoard(boardSeq);
		Assert.notNull(board, "게시글 정보가 없습니다.");
		model.addAttribute("board", board);
		//jsp 호출
		return "/board/form";
		
	}
	
	
	/**
	 * 등록 / 업데이트 처리
	 * @param board
	 * @return
	 */
	
	
	/**
	 * 게시물 등록/저장 요철 처리
	 * @param model
	 * @return
	 */
	
	@PostMapping("/save")
	public String save(Board board) {
//		Board board = new Board();
//		board.setBoardType(boardType);
//		board.setTitle(title);
//		board.setContents(contents);
//		try {
//			// 게시물 등록 처리
//			boardDao.insertBoard(board);
//			nextUrl = "/board/list";
//			message = "게시물 등록이 성공하였습니다.";
//			save = true;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		위 코드 간소화		
//		boardDao.insertBoard(board);
//		return "board/form";
		
		
		
		// 유효성 체크
		Assert.hasLength(board.getUserName(),"회원 이름을 입력해주세요.");
		Assert.hasLength(board.getTitle(), "제목을 입력해주세요.");
		Assert.hasLength(board.getBoardType(), "종류를 입력해주세요.");
		Assert.hasLength(board.getContents(), "내용을 입력해주세요.");
		Board selectBoard = null;
		
		// 등록이 아닌 수정화면에서 요청인 경우
		if(board.getBoardSeq() > 0) {
			// 기존에 등록된 데이터인지 조회
			selectBoard = boardService.selectBoard(board.getBoardSeq());
		}
		// 수정인 경우 업데이트
		if(selectBoard != null) {
			boardService.updateBoard(board);
		}else {
			//게시물 등록 처리
			boardService.insertBoard(board);
		}
		
		// 목록 화면으로 이동(URL 리다이렉트)
		return "redirect:/board";
	}
	
	
	@PostMapping("/delete")
//	public HttpEntity<Boolean> delete(@RequestParam(required = true) int boardSeq){ // 여기 required도 디폴트가 true라 생략가능
	public HttpEntity<Boolean> delete(@RequestParam int boardSeq){ // 여기 required도 디폴트가 true라 생략가능
		logger.debug("delete");
		
		// 데이터 조회
		Board board = boardService.selectBoard(boardSeq);
		// board가 null일 경우 에러 메세지 출력
		Assert.notNull(board, "게시글 정보가 없습니다.");
		//삭제 처리
		boardService.deleteBoard(boardSeq);
		return new ResponseEntity<Boolean>(HttpStatus.OK);
		
	}
	
	
	/**
	 * Exception 발생에 대한 예외처리
	 * controller advice에서 예외메세지 출력 전 컨트롤러에서 발생시켜보기(컨트롤러 안에있는게 우선순위)
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception e) {
		logger.error("BoardController handleException",e);
		ModelAndView view = new ModelAndView("error/error.html");
		view.addObject("exception",e);
		return view;
		
	}
	
	
}
