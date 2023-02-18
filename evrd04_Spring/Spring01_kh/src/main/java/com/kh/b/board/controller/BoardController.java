package com.kh.b.board.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.a.board.exception.BoardException;
import com.kh.a.board.model.service.BoardService;
import com.kh.a.board.model.vo.BoardVO;
import com.kh.a.board.model.vo.PageInfo;
import com.kh.b.common.Pagination;


@Controller
public class BoardController {

	
	@Autowired
	public BoardService bService;
	
	
	
	
	/** 게시판 목록 조회 + 페이지네이션
	 * @param page
	 * @param mv
	 * @return
	 */
	@RequestMapping("blist.bo") // menubar.jsp의 게시판 버튼의 url주소
	public ModelAndView boardList(@RequestParam(value="page") Integer page, ModelAndView mv) {
		int currentPage = 1;
		if(page != null) {
			currentPage = page;
		}
		int listCount = bService.getListCount();
		
		PageInfo pageInfo = Pagination.getPageInfo(currentPage, listCount);
		
		ArrayList<BoardVO> list = bService.getBoardList(pageInfo);
		
		if(list != null) {
			mv.addObject("pi",pageInfo);
			mv.addObject("list", list);
			mv.setViewName("boardListView");
		}else {
			throw new BoardException("게시글 전체 조회 실패");
		}
		return mv;
	}
	/** 연습 텍스트 : 게시판 목록 조회 + 페이지네이션 **/
	// 현재 페이지 선언할당
	// 페이지가 널or0이 아닐 경우 현재페이지와 페이지 바인딩
	// 전체페이지수 db처리
	// 페이징vo에 필드값 넣기
	// 게시판 목록 불러오기
	// 뷰에 데이터 넣어주고 뷰 지정 & 리턴 boardListView
	
	
	/** 글쓰기 폼 이동
	 * @return
	 */
	@RequestMapping("binsertView.bo")
	public String boardInsertForm() {
		return "boardInsertForm";
	}
	
	/** 게시판 등록
	 * @param boardVo
	 * @param uploadFile
	 * @param request
	 * @return
	 */
	@RequestMapping("binsert.bo")
	public String insertBoard(@ModelAttribute BoardVO boardVo, 
							  @RequestParam("uploadFile") MultipartFile uploadFile, 
							  HttpServletRequest request) {
		if(uploadFile != null && !uploadFile.isEmpty()) {
			String renameFileName = saveFile(uploadFile, request);
			boardVo.setOriginalFileName(uploadFile.getOriginalFilename());
			boardVo.setRenameFileName(renameFileName);
		}
		int result = bService.insertBoard(boardVo);
		if(result > 0) {
			return "redirect:blist.bo";
		}else {
			throw new BoardException("게시판 등록 실패");
		}
	}
	/** 연습 텍스트 : 게시판 등록 **/
	// 유저가 업로드한 파일이 없는 경우 대비
	// 리네임파일 가져오기
	// 오리지널파일 저장
	// 리네임파일 저장
	// db 처리 및 리턴 blist.bo
	
	public String saveFile(MultipartFile file, HttpServletRequest request) {
		
		String root = request.getSession().getServletContext().getRealPath("resources"); // application영역으로 가는 코드. 어플영역은 웹앱(웹컨텐트) 아래를 말함
		String savePath = root + "\\buploadFiles";
		
		File folder = new File(savePath);
		if(!folder.exists()) {
			folder.mkdirs(); // 저장할 폴더 생성
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String originFileName = file.getOriginalFilename();
		String renameFileName = sdf.format(new Date(System.currentTimeMillis())) 
								+ originFileName.substring(originFileName.lastIndexOf("."));
		
		
		String renamePath = folder + "\\" + renameFileName;
		
		try {
			file.transferTo(new File(renamePath)); 
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return renameFileName;
	}
	/** 연습 텍스트 : saveFile **/
	// root경로 세팅
	// 파일 경로 세팅 = root + 파일저장소 위치
	// 파일 생성 및 경로 지정
	// 파일이 없을 경우 대비
	// 날짜데이터 포맷파싱 객체선언
	// 오리지널 파일명 겟
	// 리네임 파일명 겟 : 파일명 규칙, 현재시간+오리지널파일명
	// 리네임파일 경로 지정 : 파일경로 + 리네임파일명
	// 받은 파일을 지정 저장경로에 전송
	
	
	
	
	
	
	
	
	
}
