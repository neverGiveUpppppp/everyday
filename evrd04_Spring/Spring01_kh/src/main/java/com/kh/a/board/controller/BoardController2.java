package com.kh.a.board.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.kh.a.board.exception.BoardException;
import com.kh.a.board.model.service.BoardService;
import com.kh.a.board.model.vo.BoardVO;
import com.kh.a.board.model.vo.PageInfo;
import com.kh.a.board.model.vo.Reply;
import com.kh.a.common.Pagination;





@Controller
public class BoardController2 {

	@Autowired
	public BoardService bService;
	
	
	
	
	
	/** 게시판 목록 조회
	 * @param page
	 * @param mv
	 * @return
	 */
	@RequestMapping("blist.bo")
	public ModelAndView boardList(@RequestParam(value="page", required=false) Integer page, ModelAndView mv) {
		int currentPage = 1;
		if(page != null) {
			currentPage = page;
		}
		
		int listCount = bService.getListCount();
		PageInfo pageInfo = Pagination.getPageInfo(currentPage, listCount);
		ArrayList<BoardVO> list = bService.getBoardList(pageInfo);
		
		if(list != null) {
			mv.addObject("list",list);
			mv.setViewName("boardListView");
		}else {
			throw new BoardException("게시글 전체 조회에 실패");
		}
		return mv;
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
		} else {
			throw new BoardException("게시글 등록에 실패하였습니다.");
		}
	}
	/** 연습 텍스트**/
	// 유저가 업로드한 파일이 없는 경우 대비
	// 
	
	public String saveFile(MultipartFile multipartFile, HttpServletRequest request) {
		
		// 프로젝트파일의 저장소 위치 : webapp - resource - buploadFiles
		String root = request.getSession().getServletContext().getRealPath("resource"); //  webapp폴더 아래 resources폴더를 의미함
		
		String savePath = root + "\\buploadFiles";
		
		File folder = new File(savePath);
		if(!folder.exists()) {
			folder.mkdir();
		}
		// 저장할 파일명을 변경해야함 -> 리네임 규약(ex:카톡파일명) 만들어야하나 수업에서는 패스
		// 파일명 랜덤값 만들어서 겹치지 않게 해야하나 이번 수업 때는 생략. 필요하면 jspServlet쪽에 찾아보기를 
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		
		String originFileName = multipartFile.getOriginalFilename();
		String renameFileName = sdf.format(new Date(System.currentTimeMillis())) + originFileName.substring(originFileName.lastIndexOf("."));
		
		String renamePath = folder + "\\" + renameFileName;
		
		try {
			multipartFile.transferTo(new File(renamePath));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return renameFileName;
	}
	/** 연습 텍스트**/
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
