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
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	
	
	
	/** 게시판 목록 조회 + 페이지네이션
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
			mv.addObject("pi",pageInfo);
			mv.setViewName("boardListView");
		}else {
			throw new BoardException("게시글 전체 조회에 실패");
		}
		return mv;
	}
	@RequestMapping("blist.bo") // menubar.jsp의 게시판 버튼의 url주소
	public ModelAndView boardList2(@RequestParam(value="page", required=false) Integer page, ModelAndView mv	) {
		
		int currentPage = 1;
		if(page != null) {
			currentPage = page;
		}
		int listCount = bService.getListCount();
		PageInfo pageInfo = Pagination.getPageInfo(currentPage, listCount);
		ArrayList<BoardVO> boardList = bService.getBoardList(pageInfo);
		if(boardList != null) {
			mv.addObject("pi",pageInfo);
			mv.addObject("list", boardList);
			mv.setViewName("boardListView");
		}else {
			throw new BoardException("게시글 전체 조회 실패");
		}
		return mv;
	}
	// ModelAndView -> Model
	@RequestMapping("blist.bo") // menubar.jsp의 게시판 버튼의 url주소
	public String boardList3(@RequestParam(value="page", required=false) Integer page, Model model) {
		int currentPage = 1;
		if(page != null) {
			currentPage = page;
		}
		int listCount = bService.getListCount();
		PageInfo pageInfo = Pagination.getPageInfo(currentPage, listCount);
		ArrayList<BoardVO> boardList = bService.getBoardList(pageInfo);
		
		if(boardList != null) {
			model.addAttribute("pi",pageInfo);
			model.addAttribute("list",boardList);
			return "redirect:boardListView";
		}else {
			throw new BoardException("게시글 전체조회 실패");
		}
	}
	@RequestMapping(value="blist.bo", method=RequestMethod.GET)
	public ModelAndView boardList4(@RequestParam(value="page", required=false) Integer page, ModelAndView mv) {
		int currentPage = 1;
		if(page != null) {
			currentPage = page;
		}
		int listCount = bService.getListCount();
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount);
		ArrayList<BoardVO> boardList = bService.getBoardList(pi);
		
		if(boardList != null) {
			mv.addObject("list",boardList);
			mv.addObject("pi",pi);
			mv.setViewName("boardListView");
		}else {
			throw new BoardException("게시글 전체 조회에 실패했습니다");
		}
		return mv;
		
	}
	@RequestMapping(value="blist.bo", method=RequestMethod.POST)
	public String boardList5(@RequestParam(value="page", required=false) Integer page, Model model) {
		int currentPage = 1;
		if(page != null) {
			currentPage = page;
		}
		int listCount = bService.getListCount();
		PageInfo pageInfo = Pagination.getPageInfo(currentPage, listCount);
		ArrayList<BoardVO> aList = bService.getBoardList(pageInfo);
		if(aList != null) {
			model.addAttribute("list",aList);
			model.addAttribute("pi",pageInfo);
			return "redirect:boardListView";
		}else {
			throw new BoardException("게시글 전체 조회에 실패했습니다");
		}
	}
	@RequestMapping(value="blist.bo", method=RequestMethod.POST)
	public String boardList6(@RequestParam(value="page", required=false) Integer page, Model model) {
		int currentPage = 1;
		if(page != null) {
			currentPage = page;
		}
		int listCount = bService.getListCount();
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount);
		ArrayList<BoardVO> list = bService.getBoardList(pi);
		if(list != null) {
			model.addAttribute("list",list);
			model.addAttribute("pi",pi);
			return "redirect:boardListView"; 
		}else {
			throw new BoardException("게시글 전체 조회에 실패했습니다");
		}
	}
	/** 연습 텍스트 : 게시판 목록 조회 + 페이지네이션 **/
	// 받아올 파라미터 & 사용할 객체 체크 : 뷰에서 받아오는 name속성값 체크 
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
	@RequestMapping("binsertView.bo")
	public String boardInsertForm2() {
		return "boardInsertForm";
	}
	@RequestMapping("binsertView.bo")
	public String boardInsertForm3() {
		return "boardInsertForm";
	}
	@RequestMapping("binsertView.bo")
	public String boardInsertForm4() {
		return "boardInsertForm";
	}
	@RequestMapping("binsertView.bo")
	public String boardInsertForm5() {
		return "boardInsertForm";
	}
	@RequestMapping("binsertView.bo")
	public String boardInsertForm6() {
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
		} else {
			throw new BoardException("게시글 등록에 실패하였습니다.");
		}
	}
	@RequestMapping("binsert.bo")
	public String insertBoard2(@ModelAttribute BoardVO boardVo, 
							   @RequestParam("uploadFile") MultipartFile uploadFile, HttpServletRequest request) {
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
	@RequestMapping("binsert.bo")
	public String insertBoard3(BoardVO boardVo, @RequestParam(value="uploadFile") MultipartFile uploadFile
												, HttpServletRequest request) {
		if(uploadFile != null && !uploadFile.isEmpty()) {
			String renameFileName = saveFile(uploadFile,request);
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
	@RequestMapping("binsert.bo")
	public String insertBoard4(@ModelAttribute BoardVO boardVo, 
							   @RequestParam(value="uploadFile") MultipartFile uploadFile, HttpServletRequest request) {
		if(uploadFile != null && !uploadFile.isEmpty()) {
			String renameFileName = saveFile3(uploadFile, request);
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
	@RequestMapping("binsert.bo")
	public String insertBoard5(BoardVO boardVo, @RequestParam("uploadFile") MultipartFile uploadFile, HttpServletRequest request) {
		if(uploadFile != null && !uploadFile.isEmpty()) {
			String renameFileName = saveFile5(uploadFile, request);
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
	@RequestMapping(value="binsert.bo",method=RequestMethod.POST)
	public String insertBoard6(@ModelAttribute BoardVO boardVo, @RequestParam("uploadFile") MultipartFile uploadFile,
																HttpServletRequest request) {
		if(uploadFile != null && !uploadFile.isEmpty()) {
			String renameFileName = saveFile6(uploadFile,request);
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
	@RequestMapping("binsert.bo")
	public String insertBoard7(@RequestParam("uploadFile") MultipartFile uploadFile, HttpServletRequest request, 
								@ModelAttribute BoardVO boardVo) {
		if(uploadFile != null && !uploadFile.isEmpty()) {
			String renameFileName = saveFile(uploadFile, request);
			boardVo.setOriginalFileName(uploadFile.getOriginalFilename());
			boardVo.setRenameFileName(renameFileName);
		}
		int result = bService.insertBoard(boardVo);
		
		if(result > 0) {
			return "redirect:blist.bo";
		}else {
			throw new BoardException("게시물 등록 실패");
		}
	}
	/** 연습 텍스트 : 게시판 등록 **/
	// 받아올 파라미터 & 사용할 객체 체크 : 뷰에서 받아오는 name속성값 체크 
	// 유저가 업로드한 파일이 없는 경우 대비
	// 유저가 업로드한 파일, vo에 저장하기
	// 		리네임파일 가져오기
	// 		오리지널파일 저장
	// 		리네임파일 저장
	// db 처리 및 리턴 blist.bo
	
	public String saveFile(MultipartFile multipartFile, HttpServletRequest request) {
		
		// 프로젝트파일의 저장소 위치 : webapp - resource - buploadFiles
		String root = request.getSession().getServletContext().getRealPath("resources"); //  webapp폴더 아래 resources폴더를 의미함
		
		String savePath = root + "\\buploadFiles";
		
		File folder = new File(savePath);
		if(!folder.exists()) {
			folder.mkdirs();
		}
		// 저장할 파일명을 변경해야함 -> 리네임 규약(ex:카톡파일명) 만들어야하나 수업에서는 패스
		// 파일명 랜덤값 만들어서 겹치지 않게 해야하나 이번 수업 때는 생략. 필요하면 jspServlet쪽에 찾아보기를 
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		
		String originFileName = multipartFile.getOriginalFilename();
		String renameFileName = sdf.format(new Date(System.currentTimeMillis())) + originFileName.substring(originFileName.lastIndexOf("."));
		
		String renamePath = folder + "\\" + renameFileName;
		
		try {
			// https://dev-gorany.tistory.com/123 : 멀티파일 관련 참조자료
			// 단일파일이 아닌 복수파일 업로드 내용포함
			multipartFile.transferTo(new File(renamePath));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return renameFileName;
	}
	public String saveFile2(MultipartFile multipartFile, HttpServletRequest request) {
		String root = request.getSession().getServletContext().getRealPath("resources");
		String savePath = root + "\\buploadFiles";
		
		File folder = new File(savePath);
		if(!folder.exists()) {
			folder.mkdirs();
		}
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
	public String saveFile3(MultipartFile multipartFile, HttpServletRequest request) {
		
		String root = request.getSession().getServletContext().getRealPath("resources");
		String savePath = root + "\\buploadFiles";
		
		File folder = new File(savePath);
		if(!folder.exists()) {
			folder.mkdirs();
		}
		
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
	public String saveFile5(MultipartFile uploadFile, HttpServletRequest request) {
		
		String root = request.getSession().getServletContext().getRealPath("resources");
		String savePath = root + "\\buploadFiles";
		
		File folder = new File(savePath);
		if(!folder.exists()) {
			folder.mkdirs();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String originFileName = uploadFile.getOriginalFilename();
		String renameFileName = sdf.format(new Date(System.currentTimeMillis())) + originFileName.substring(originFileName.lastIndexOf("."));
		String renamePath = folder + "\\" + renameFileName;
		try {
			uploadFile.transferTo(new File(renamePath));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return renameFileName;
	}
	public String saveFile6(MultipartFile uploadFile, HttpServletRequest request) {
		
		
		String root = request.getSession().getServletContext().getRealPath("resources");
		String savePath = root + "\\buploadFiles";
		
		File folder = new File(savePath);
		if(!folder.exists()) {
			folder.mkdirs();
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		
		String originFileName = uploadFile.getOriginalFilename();
		String renameFileName = sdf.format(new Date(System.currentTimeMillis())) + originFileName.substring(originFileName.lastIndexOf("."));
		
		String renamePath = folder + "\\" + renameFileName;
		
		try {
			uploadFile.transferTo(new File(renamePath));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return renameFileName;
	}
	public String saveFile7(MultipartFile uploadFile, HttpServletRequest request) {
		
		String root = request.getSession().getServletContext().getRealPath("resources");
		String savePath = root + "\\buploadFiles";
		
		File file = new File(savePath);
		if(!file.exists()) {
			file.mkdirs();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		
		String originFileName = uploadFile.getOriginalFilename();
		String renameFileName = sdf.format(new Date(System.currentTimeMillis())) + originFileName.substring(originFileName.lastIndexOf("."));
		
		String renamePath = file + "\\" + renameFileName;
		try {
			uploadFile.transferTo(new File(renamePath));
		}catch(IllegalStateException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return renameFileName;
	}
	/** 연습 텍스트 : saveFile **/
	// 받아올 파라미터 & 사용할 객체 체크
	// root경로 세팅
	// 파일 지정경로 세팅 = root + 파일저장소 위치
	// 파일 객체 생성 및 경로 지정 for renameFile 저장
	// 파일이 없을 경우 대비
	//		디렉토리 생성
	// 날짜데이터포맷 객체선언 및 형식지정
	// 오리지널 파일명 겟
	// 리네임 파일명 겟 : 파일명 규칙, 현재시간+오리지널파일명
	// 리네임파일 파일명 및 경로 지정 : 파일경로 + 리네임파일명
	// 리네임파일, 톰캣 서버에 파일 생성 : 지정 저장경로에 전송 & 저장
	// return renamefile
	
	
	
	
	
	/** 게시판 상세
	 * @param bId
	 * @param page
	 * @param mv
	 * @return
	 */
	@RequestMapping("bdetail.bo")
	public ModelAndView boardDetail (@RequestParam("bId") int bId, @RequestParam("page") int page, ModelAndView mv) {
		
		BoardVO board = bService.selectBoard(bId);
		
		if(board != null) {
			mv.addObject("board",board).addObject("page",page).setViewName("boardDetailView");
		} else {
			throw new BoardException ("게시글 상세보기에 실패하였습니다.");
		}
		return mv;
	}
	@RequestMapping("bdetail.bo")
	public ModelAndView boardDetail2(@RequestParam("bId") int bId, @RequestParam("page") int page, ModelAndView mv) {
		BoardVO boardVo = bService.selectBoard2(bId);
		
		if(boardVo != null) {
			mv.addObject("board", boardVo).addObject("page",page).setViewName("boardDetailView");
		} else {
			throw new BoardException ("게시글 상세보기에 실패하였습니다.");
		}
		return mv;
	}
	@RequestMapping("bdetail.bo")
	public ModelAndView boardDetail3(@RequestParam("bId") int bId, @RequestParam("page") int page, ModelAndView mv) {
		BoardVO boardVo = bService.selectBoard3(bId);
		
		if(boardVo != null) {
			mv.addObject("board",boardVo).addObject("page",page).setViewName("boardDetailView");
		} else {
			throw new BoardException ("게시글 상세보기에 실패하였습니다.");
		}
		return mv;
	}
	
	@RequestMapping("bdetail.bo")
	public String boardDetail4(@RequestParam("page") int page, @RequestParam("bId") int bId, Model model) {
		BoardVO boardVo = bService.selectBoard4(bId);
		
		if(boardVo != null) {
			model.addAttribute("board",boardVo).addAttribute("page",page);
			return "redirect:boardDetailView";
		} else {
			throw new BoardException ("게시글 상세보기에 실패하였습니다.");
		}
	}
	/** 연습 텍스트 : 게시판 상세  **/
	// 받아올 파라미터 & 사용할 객체 체크
	// db에서 상세페이지 데이터 받아오기
	// 뷰에 보낼 데이터 저장 및 리턴
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
}
