package com.kh.a.board.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
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

import com.kh.a.board.exception.BoardException;
import com.kh.a.board.model.service.BoardService;
import com.kh.a.board.model.vo.BoardVO;
import com.kh.a.board.model.vo.PageInfo;
import com.kh.a.common.Pagination;
import com.kh.vo.Member;
import com.kh.vo.Reply;





@Controller
public class BoardController2 {

	@Autowired
	public BoardService bService;
	
	
	/**  
	 * 게시판 목록 조회 + 페이지네이션
	 * 
	 * 글쓰기 폼 이동
	 * 게시판 등록
	 * 게시판 등록 & saveFile
	 * 
	 * 게시판 상세
	 * 
	 * 게시판 수정폼 이동
	 * 게시판 수정 + 파일 & deleteFile
	 * 
	 * 게시판 삭제  + 파일
	 *
	 * 댓글 쓰기
	 * 댓글 목록읽기
	 * Top-N 분석
	 * 
	 * **/
	
	
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
	@RequestMapping(value="blist.bo", method=RequestMethod.GET)
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
	@RequestMapping(value="blist.bo", method=RequestMethod.GET)
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
	@RequestMapping(value="blist.bo", method=RequestMethod.GET)
	public String boardList7(@RequestParam(value="page") Integer page, 
							 @ModelAttribute BoardVO boVo, Model model) {
		int currentPage = 1;
		if(page != null) {
			currentPage = page;
		}
		int listCount = bService.getListCount();
		PageInfo pageVo	= Pagination.getPageInfo(currentPage, listCount);
		ArrayList<BoardVO> aList = bService.getBoardList(pageVo);
		if(aList != null) {
			model.addAttribute("list",aList).addAttribute("pi",pageVo);
			return "redirect:boardListView";
		}else {
			throw new BoardException("게시글 목록 조회 실패");
		}
	}
	@RequestMapping(value="blist.bo", method=RequestMethod.GET)
	public String boardList8(BoardVO bodVo, @RequestParam("page") Integer page, Model model) {
		int currentPage = 1;
		if(page != null) {
			currentPage = page;
		}
		int listCount = bService.getListCount();
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount);
		ArrayList<BoardVO> boardList = bService.getBoardList(pi);
		if(boardList != null) {
			model.addAttribute("list",boardList).addAttribute("pi",pi);
			return "redirect:boardListView";
		}else {
			throw new BoardException("게시글 목록 조회 실패");
		}
	}
	@RequestMapping(value="blist.bo", method=RequestMethod.GET)
	public String boardList9(BoardVO boardVo, @RequestParam("page") Integer page, Model model) {
		int currentPage = 1;
		if(page != null) {
			currentPage = page;
		}
		int listCount = bService.getListCount();
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount);
		ArrayList<BoardVO> boardList = bService.getBoardList(pi);
		if(boardList != null) {
			model.addAttribute("list",boardList);
			return "redirect:boardListView";
		}else {
			throw new BoardException("게시글 목록 조회 실패");
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
	@RequestMapping("binsertView.bo")
	public String boardInsertForm7() {
		return "boardInsertForm";
	}
	@RequestMapping("binsertView.bo")
	public String boardInsertForm8() {
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
	@RequestMapping(value="binsert.bo",method=RequestMethod.POST)
	public String insertBoard8(BoardVO bodVo, HttpServletRequest request,
				@RequestParam(value="uploadFile", required=false) MultipartFile uploadFile) {
		if(uploadFile != null && !uploadFile.isEmpty()) {
			String renameFileName = saveFile7(uploadFile,request);
			bodVo.setOriginalFileName(uploadFile.getOriginalFilename());
			bodVo.setRenameFileName(renameFileName);
		}
		int result = bService.insertBoard(bodVo);
		if(result > 0) {
			return "redirect:blist.bo";
		}else {
			throw new BoardException("게시글 등록 실패");
		}
	}
	@RequestMapping(value="binsert.bo", method=RequestMethod.POST)
	public String insertBoard9(@ModelAttribute BoardVO boardVo, @RequestParam("uploadFile") MultipartFile uploadFile,HttpServletRequest request) { 
		if(uploadFile != null && !uploadFile.isEmpty()) {
			String renameFileName = saveFile9(uploadFile, request);
			boardVo.setOriginalFileName(uploadFile.getOriginalFilename());
			boardVo.setRenameFileName(renameFileName);
		}
		int result = bService.insertBoard(boardVo);
		if(result > 0) {
			return "redirect:blist.bo";
		}else{
			throw new BoardException("게시글 등록 실패");
		}
	}
	@RequestMapping("binsert.bo")
	public ModelAndView insertBoard10(@ModelAttribute BoardVO bodVo, ModelAndView mv,
										@RequestParam("uploadFile") MultipartFile uploadFile, HttpServletRequest request) {
		if(uploadFile != null && !uploadFile.isEmpty()) {
			String renameFileName = saveFile10(uploadFile, request);
			bodVo.setOriginalFileName(uploadFile.getOriginalFilename());
			bodVo.setRenameFileName(renameFileName);
		}
		int result = bService.insertBoard(bodVo);
		if(result > 0) {
			mv.setViewName("blist.bo");
		}else {
			throw new BoardException("게시글 등록 실패");
		}
		return mv;
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

	public String saveFile8(HttpServletRequest request, MultipartFile file) {
		String root = request.getSession().getServletContext().getRealPath("resources");
		String savePath = root + "\\buploadFiles";
		
		File folder = new File(savePath);
		if(!folder.exists()) {
			folder.mkdirs();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		
		String originFileName = file.getOriginalFilename();
		String renameFileName = sdf.format(new Date(System.currentTimeMillis())) + originFileName.substring(originFileName.lastIndexOf("."));
		
		String renameFilePath = file + "\\" + renameFileName;
		try {
			file.transferTo(new File(renameFilePath));
		}catch(IllegalStateException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return renameFileName;
	}
	public String saveFile9(MultipartFile file, HttpServletRequest request) {
		String root = request.getSession().getServletContext().getRealPath("resources");
		String savePath9 = root + "\\buploadFiles";
		File f = new File(savePath9);
		if(!f.exists()) {
			f.mkdirs();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String originFileName = file.getOriginalFilename();
		String renameFileName = sdf.format(new Date(System.currentTimeMillis())) + originFileName.substring(originFileName.lastIndexOf("."));
		
		String renameFilePath = f + "\\" + renameFileName;
		
		try {
			file.transferTo(new File(renameFilePath));
		}catch(IllegalStateException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return renameFileName;
	}
	public String saveFile10(MultipartFile file, HttpServletRequest request) {
		String root = request.getSession().getServletContext().getRealPath("resources");
		String savePath = root + "\\buploadFiles";
		
		File folder = new File(savePath);
		if(!folder.exists()) {
			folder.mkdirs();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String originFileName = file.getOriginalFilename();
		String renameFileName = sdf.format(new Date(System.currentTimeMillis())) + originFileName.substring(originFileName.lastIndexOf("."));
		
		String renameFilePath = folder + "\\" + renameFileName;
		
		try {
			file.transferTo(new File(renameFilePath));
		}catch(IllegalStateException e	) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return renameFileName; // 이 메소드의 하는 역할? 파일 생성 및 리네임파일변수의 리네임짓기
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
	@RequestMapping("bdetail.bo")
	public ModelAndView boardDetail5(@RequestParam("bId") int boardId, @RequestParam(value="page",required=false)
									ModelAndView mv) {
		BoardVO bodVo = bService.selectBoard2(boardId);
		
		if(bodVo != null) {
			mv.addObject("list",bodVo);
		} else {
			throw new BoardException ("게시글 상세보기에 실패하였습니다.");
		}
		return mv;
	}
	@RequestMapping("bdetail.bo")
	public String boardDetail6(@RequestParam("bId") int bId, @RequestParam("page") int page, Model model) {
							
		BoardVO bVo = bService.selectBoard6(bId);
		
		if(bVo != null) {
			model.addAttribute("board",bVo).addAttribute("page",page);
			return "redirect:blist.bo";
		}else {
			throw new BoardException("게시글 상세 조회 실패");
		}
	}
	/** 연습 텍스트 : boardDetail **/
	
	@RequestMapping("bdetail.bo")
	public String boardDetail7(@RequestParam(value="page",required=false) int page, @RequestParam("bId") int boardId, Model model){
		BoardVO boardVo = bService.selectBoard5(boardId);
		if(boardVo != null) {
			model.addAttribute("page",page);
			model.addAttribute("bId",boardId);
			return "redirect:boardDetailView";
		}else {
			throw new BoardException("게시글 상세보기 실패");
		}
	}
	@RequestMapping("bdetail.bo")
	public String boardDetail8(@RequestParam("bId") int boardId, @RequestParam(value="page", required=false) int page, Model model) {
		BoardVO board = bService.selectBoard7(boardId);
		if(board != null) {
			model.addAttribute("page",page).addAttribute("bId",boardId);
			return "redirect:boardDetailView";
		}else {
			throw new BoardException("게시글 상세보기 실패");
		}
	}
	@RequestMapping("bdetail.bo")
	public String boardDetail9(@RequestParam("bId") int bId, @RequestParam(value="page", required=false) int page, Model model) {
		BoardVO board = bService.selectBoard9(bId);
		if(board != null) {
			model.addAttribute("bId",bId);
			model.addAttribute("page",page);
			return "redirect:boardDetailView";
		}else {
			throw new BoardException("게시글 상세조회 실패");
		}
	}
	/** 연습 텍스트 : 게시판 상세  **/
	// 받아올 파라미터 & 사용할 객체 체크
	// 비니지스 로직 실행 : 게시판상세읽기
	
	
	
	
	
	
	/** 게시판 수정폼 이동
	 * @param b
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping("bupView.bo")
	public String boardUpdateForm(@ModelAttribute BoardVO b, @RequestParam("page") int page, Model model) {
		model.addAttribute("board", b).addAttribute("page", page);
		return "boardUpdateForm";
	}
	@RequestMapping("bupView.bo")
	public String boardUpdateForm2(BoardVO b, @RequestParam("page") int page, Model model) {
		model.addAttribute("board",b).addAttribute("page", page);
		return "boardUpdateForm";
	}
	@RequestMapping("bupView.bo")
	public String boardUpdateForm3(@ModelAttribute BoardVO bVo, @RequestParam("page") int page, Model model) {
		model.addAttribute("board",bVo).addAttribute("page",page);
		return "boardUpdateForm";
	}
	@RequestMapping("bupView.bo")
	public String boardUpdateForm4(@ModelAttribute BoardVO boardVo, @RequestParam("page") int page, Model model) {
		model.addAttribute("board", boardVo);
		model.addAttribute("page",page);
		return"boardUpdateForm";
	}
	
	
	/** 게시판 수정 + 파일
	 * @param boardVo
	 * @param page
	 * @param reloadFile
	 * @param model
	 * @return
	 */
	@RequestMapping("bupdate.bo")
	public String updateBoard(@ModelAttribute BoardVO boardVo, @RequestParam("page") int page,
								@RequestParam("reloadFile") MultipartFile reloadFile, HttpServletRequest request, Model model) {
		if(reloadFile != null && !reloadFile.isEmpty()){
			if(boardVo.getRenameFileName() != null ) {
				deleteFile(boardVo.getRenameFileName(),request);
			}
			String renameFileName = saveFile(reloadFile, request); 
			boardVo.setOriginalFileName(reloadFile.getOriginalFilename());
			boardVo.setRenameFileName(renameFileName);
		}
		int result = bService.updateBoard(boardVo);
		if(result > 0) {
			model.addAttribute("bId",boardVo.getBoardId());
			model.addAttribute("page",page);
			return "redirect:bdetail.bo";
			//return "redirect:bdetail.bo?bId=" + b.getBoardId() + "&page=" + page;
			// 리다이렉트인데 데이터보존됨
		} else {
			throw new BoardException("게시글 수정에 실패하였습니다.");
		}
	}
	@RequestMapping("bupdate.bo")
	public String updateBoard2(@ModelAttribute BoardVO boardVo, @RequestParam("reloadFile") MultipartFile reloadFile, HttpServletRequest request,
								Model model, @RequestParam("page") int page) {
		if(reloadFile != null && !reloadFile.isEmpty()){
			if(boardVo.getRenameFileName() != null ) {
				deleteFile(boardVo.getRenameFileName(),request);
			}
			String renameFileName = saveFile(reloadFile, request); 
			boardVo.setOriginalFileName(reloadFile.getOriginalFilename());
			boardVo.setRenameFileName(renameFileName);
		}
		int result = bService.updateBoard(boardVo);
		if(result > 0) {
			model.addAttribute("bId",boardVo.getBoardId());
			model.addAttribute("page",page);
			return "redirect:bdetail.bo";
			//return "redirect:bdetail.bo?bId=" + b.getBoardId() + "&page=" + page;
			// 리다이렉트인데 데이터보존됨
		} else {
			throw new BoardException("게시글 수정에 실패하였습니다.");
		}
	}
	@RequestMapping("bupdate.bo")
	public String updateBoard3(@ModelAttribute BoardVO bodVo, @RequestParam("reloadFile") MultipartFile reloadFile, HttpServletRequest request,
								@RequestParam("page") int page, Model model) {	
		if(reloadFile != null && !reloadFile.isEmpty()) {
			if(bodVo.getRenameFileName() != null) {
				deleteFile3(bodVo.getRenameFileName(), request);
			}
			String reloadFileName = saveFile(reloadFile, request);
			bodVo.setOriginalFileName(bodVo.getOriginalFileName());
			bodVo.setRenameFileName(reloadFileName);
		}
		int result = bService.updateBoard(bodVo);
		if(result > 0) {
			model.addAttribute("page",page);
			model.addAttribute("bId",bodVo.getBoardId());
			return "redirect:bdetail.bo";
		} else {
			throw new BoardException("게시글 수정에 실패하였습니다.");
		}
	}
	@RequestMapping(value="bupdate.bo")
	public String updateBoard4(BoardVO bodVo, @RequestParam("reloadFile") MultipartFile reloadFile, HttpServletRequest request, 
								@RequestParam("page") int page, Model model) {
		if(reloadFile != null && !reloadFile.isEmpty()) {
			if(bodVo.getRenameFileName() != null) {
				deleteFile4(bodVo.getRenameFileName(), request);
			}
			String renameFileName = saveFile(reloadFile, request); 
			bodVo.setOriginalFileName(reloadFile.getOriginalFilename());
			bodVo.setRenameFileName(renameFileName);
		}
		int result = bService.updateBoard(bodVo);
		if(result > 0) {
			model.addAttribute("bId",bodVo.getBoardId()).addAttribute("page",page);
			return "redirect:bdetail.bo";
		}else {
			throw new BoardException("게시글 수정 실패");
		}
	}
	@RequestMapping(value="bupdate.bo",method=RequestMethod.POST)
	public String updateBoard5(@ModelAttribute BoardVO boardVo, @RequestParam("page") int page, Model model,
								@RequestParam("reloadFile") MultipartFile reloadFile, HttpServletRequest request) {
		if(reloadFile != null && !reloadFile.isEmpty()) {
			if(boardVo.getRenameFileName() != null) {
				deleteFile5(boardVo.getRenameFileName(), request);
			}
			String renameFileName = saveFile(reloadFile, request);
			boardVo.setOriginalFileName(reloadFile.getOriginalFilename());
			boardVo.setRenameFileName(renameFileName);
		}
		int result = bService.updateBoard(boardVo);
		if(result > 0) {
			model.addAttribute("bId",boardVo.getBoardId());
			model.addAttribute("page",page);
			return "redirect:bdetail.bo";
		}else {
			throw new BoardException("게시글 수정 실패");
		}
	}
	@RequestMapping(value="bupdate.bo",method=RequestMethod.POST)
	public String updateBoard6(BoardVO boardVo, @RequestParam("page") int page, Model model,
								@RequestParam("reloadFile") MultipartFile reloadFile, HttpServletRequest request) {
		
		if(reloadFile != null && !reloadFile.isEmpty()) {
			if(boardVo.getRenameFileName() != null && !boardVo.getRenameFileName().isEmpty()) {
				deleteFile6(boardVo.getRenameFileName(), request);
			}
			String renameFileName = saveFile(reloadFile, request);
			boardVo.setOriginalFileName(reloadFile.getOriginalFilename());
			boardVo.setRenameFileName(renameFileName);
		}
		int result = bService.updateBoard(boardVo);
		if(result > 0) {
			model.addAttribute("bId",boardVo.getBoardId());
			model.addAttribute("page",page);
			return "redirect:bdetail.bo";
		}else {
			throw new BoardException("게시글 수정 실패");
		}
	}
	@RequestMapping("bupdate.bo")
	public String updateBoard7(@ModelAttribute BoardVO boardVo, @RequestParam("page") int page,
								@RequestParam("reloadFile") MultipartFile reloadFile, HttpServletRequest request, Model model) {
		if(reloadFile != null && !reloadFile.isEmpty()) {
			if(boardVo.getRenameFileName() != null && !boardVo.getRenameFileName().isEmpty()) {
				deleteFile7(boardVo.getRenameFileName(), request);
			}
			String renameFileName = saveFile(reloadFile, request);
			boardVo.setOriginalFileName(reloadFile.getOriginalFilename());
			boardVo.setRenameFileName(renameFileName);
		}
		int result = bService.updateBoard(boardVo);
		if(result > 0) {
			model.addAttribute("board", boardVo.getBoardId()).addAttribute("page",page);
			return "redirect:bdetail.bo";
			//return "redirect:bdetail.bo?bId=" + b.getBoardId() + "&page=" + page;
			// 리다이렉트인데 데이터보존됨
		}else {
			throw new BoardException("게시글 수정 실패");
		}
	}
	@RequestMapping("bupdate.bo")
	public String updateBoard8(@ModelAttribute BoardVO boardVo, @RequestParam("page") int page,
								@RequestParam("reloadFile") MultipartFile reloadFile, HttpServletRequest request, Model model) {
		if(reloadFile != null && !reloadFile.isEmpty()) {
			if(boardVo.getRenameFileName() != null) {
				deleteFile(boardVo.getRenameFileName(),request);
			}
			String renameFileName = saveFile(reloadFile, request);
			boardVo.setOriginalFileName(reloadFile.getOriginalFilename());
			boardVo.setRenameFileName(renameFileName);
		}
		int result = bService.updateBoard(boardVo);
		if(result > 0) {
			model.addAttribute("page", page);
			model.addAttribute("bId", boardVo.getBoardId());
			return "redirect:bdetail.bo?bId="+boardVo.getBoardId()+"&page="+page;
//			return "redirect:bdetail.bo";
		}else {
			throw new BoardException("게시글 수정 실패");
		}
	}

	/** 연습 텍스트 : 게시판 수정 + 파일  **/
	// 받아올 파라미터 & 사용할 객체 체크
	// 새업로드파일 여부 체크
	// 기존에 파일이 남아있다면, 기존파일 삭제
	// 새파일 저장
	// 새파일의 원본,리네임명 vo저장
	// DB처리 및 성공 시, 뷰에 데이터값,뷰 세팅
	
	public void deleteFile(String fileName, HttpServletRequest request) {
		String root = request.getSession().getServletContext().getRealPath("resources"); // Q.이 resources 경로가 다른 플젝도 같을까?
		String savePath = root + "\\buploadFiles";
		
		File f = new File(savePath + "\\" + fileName);
		if(f.exists()) { // 파일이 존재하는지 확인하고 삭제. 없는 파일삭제하라면 에러발생하기 때문
			f.delete();
		}
	}
	public void deleteFile2(String fileName, HttpServletRequest request) {
		String root = request.getSession().getServletContext().getRealPath("resources"); // Q.이 resources 경로가 다른 플젝도 같을까?
		String savePath = root + "\\buploadFiles";
		File f = new File(savePath + "\\" + fileName);
		if(f.exists()) {
			f.delete();
		}
	}
	public void deleteFile3(String fileName, HttpServletRequest request) {
		String root = request.getSession().getServletContext().getRealPath("resources");
		String savePath = root + "\\buploadFiles";
		File f = new File(savePath+"\\"+fileName);
		if(f.exists()) {
			f.delete();
		}
	}
	public void deleteFile4(String fileName, HttpServletRequest request) {
		String root = request.getSession().getServletContext().getRealPath("resources");
		String savePath = root + "buploadFiles";
		File f = new File(savePath + "\\" + fileName);
		if(f.exists()) {
			f.delete();
		}
	}
	public void deleteFile5(String fileName, HttpServletRequest request) {
		String root = request.getSession().getServletContext().getRealPath("resources");
		String savePath = root + "buploadFiles";
		File f = new File(savePath + "\\" + fileName);
		if(f.exists()) {
			f.delete();
		}
	}
	public void deleteFile6(String fileName,HttpServletRequest request) {
		String root = request.getSession().getServletContext().getRealPath("resources");
		String savePath = root + "\\uploadFiles";
		File f = new File(savePath+"\\"+fileName);
		if(f.exists()) {
			f.delete();
		}
	}
	public void deleteFile7(String fileName,HttpServletRequest request) {
		String root = request.getSession().getServletContext().getRealPath("resources");
		String savePath = root + "\\uploadFiles";
		
		File f = new File(savePath+"\\"+fileName);
		if(f.exists()) {
			f.delete();
		}
	}
	public void deleteFile8(String leftFile, HttpServletRequest request) {
		String root = request.getSession().getServletContext().getRealPath("resources");
		String savePath = root + "\\buploadFiles";
		File f = new File(savePath+"\\"+leftFile);
		if(!f.exists()) {
			f.delete();
		}
	}
	/** 연습 텍스트 : 게시판 수정 + 파일 & deleteFile  **/
	// 받아올 파라미터 & 사용할 객체 체크
	// 루트 및 파일저장 경로 설정
	// 파일객체 생성 및 경로지정
	// 파일이 있다면, 삭제
	
	
	

	@RequestMapping("bdelete.bo")
	public String deleteBoard(@RequestParam("bId") int bId, @RequestParam("renameFileName") String renameFileName, HttpServletRequest request) {
		// 뷰에서 @RequestParam으로 renameFileName 보내는 곳 : boardDetailView.jsp 87-89 Line < c:url>,< c:param>을 통해 보냄
		if(!renameFileName.equals("")) { // renameFileName이 비어있지 않다면
			deleteFile(renameFileName, request); // renameFileName을 넘겨준다, 어디서 삭제할 것인가:request
		}
		int result = bService.deleteBoard(bId);
		
		if(result > 0) {
			return "redirect:blist.bo";
		}else {
			throw new BoardException("게시판 삭제 실패");
		}
	}
	@RequestMapping("bdelete.bo")
	public String deleteBoard2(@RequestParam("bId") int bId, @RequestParam("renameFileName") String renameFileName, HttpServletRequest request) {
		if(!renameFileName.equals("")) {
			deleteFile2(renameFileName, request);
		}
		int result = bService.deleteBoard(bId);
		if(result > 0) {
			return "redirect:blist.bo";
		}else {
			throw new BoardException("게시판 삭제 실패");
		}
	}
	@RequestMapping("bdelete.bo")
	public String deleteBoard3(@RequestParam("bId") int bId, @RequestParam("renameFileName") String renameFileName, HttpServletRequest request) {
		if(!renameFileName.equals("")) { // renameFileName이 비어있지 않다면
			deleteFile3(renameFileName, request);
		}
		int result = bService.deleteBoard(bId);
		if(result > 0) {
			return "redirect:blist.bo";
		}else {
			throw new BoardException("게시판 삭제 실패");
		}
	}
	@RequestMapping("bdelete.bo")
	public String deleteBoard4(@RequestParam("bId") int bId, @RequestParam("renameFileName") String renameFileName, HttpServletRequest request) {
		if(!renameFileName.equals("")) { 
			deleteFile4(renameFileName, request);
		}
		int result = bService.deleteBoard(bId);
		if(result > 0) {
			return "redirect:blist.bo";
		}else {
			throw new BoardException("게시판 삭제 실패");
		}
	}
	@RequestMapping("bdelete.bo")
	public String deleteBoard5(@RequestParam("bId") int bId,
							@RequestParam("renameFileName") String renameFileName, HttpServletRequest request ) {
		if(!renameFileName.equals("")) { // renameFileName이 비어있지 않다면
			deleteFile(renameFileName, request); // renameFileName을 넘겨준다, 어디서 삭제할 것인가:request
		}
		int result = bService.deleteBoard(bId);
		if(result > 0) {
			return "redirect:blist.bo";
		}else {
			throw new BoardException("게시판 삭제 실패");
		}
	}
	@RequestMapping("bdelete.bo")
	public String deleteBoard6(@RequestParam("bId") int bId, @RequestParam("renameFileName") String renameFileName, HttpServletRequest request) {
		if(!renameFileName.equals("")) {
			deleteFile(renameFileName,request);
		}
		int result = bService.deleteBoard(bId);
		if(result>0) {
			return "redirect:blist.bo";
		}else {
			throw new BoardException("게시판 삭제 실패");
		}
	}
	/** 연습 텍스트 : 게시판 삭제 + 파일   **/
	// 받아올 파라미터 & 사용할 객체 체크
	// 삭제할 게시판에 파일이 있는지 체크 : 게시판만 삭제하고 파일을 남길 수는 없으니
	// 남아 있다면 파일삭제
	// 삭제 실행
	// blist.bo로 이동
	
	
	
	
	@RequestMapping("addReply.bo")
	@ResponseBody
	public String addReply(@ModelAttribute Reply replyVo, HttpSession session) {
		String id = ((Member)session.getAttribute("loginUser")).getId(); // session영역에서 로그인 중인 유저의 id정보를 얻어서 vo Member타입으로 형변환
		replyVo.setReplyWriter(id);
		
		int result = bService.insertReply(replyVo);
		if(result > 0) {
			return "success";
		}else {
			throw new BoardException("댓글 등록에 실패하였습니다.");
		}
	}
	@RequestMapping("addReply.bo")
	@ResponseBody
	public String addReply2(Reply replyVo, HttpSession session) {
		String id = ((Member)session.getAttribute("loginuser")).getId();
		replyVo.setReplyWriter(id);
		int result = bService.insertReply(replyVo);
		if(result > 0) {
			return "success";
		}else {
			throw new BoardException("댓글 등록 실패");
		}
	}
	@RequestMapping("addReply.bo")
	@ResponseBody
	public String addReply3(Reply replyVo, HttpSession session) {
		String id = ((Member)session.getAttribute("loginUser")).getId();
		replyVo.setReplyWriter(id);
		int result = bService.insertReply(replyVo);
		if(result > 0) {
			return "success";
		}else {
			throw new BoardException("댓글 등록 실패");
		}
	}
	@RequestMapping("addReply.bo")
	@ResponseBody
	public String addReply4(@ModelAttribute Reply replyVo, HttpSession	session) {
		String writer = ((Member)session.getAttribute("loginUser")).getId();
		replyVo.setReplyWriter(writer);
		int result = bService.insertReply(replyVo);
		if(result > 0) {
			return "success";
		}else {
			throw new BoardException("댓글 등록 실패");
		}
	}
	@RequestMapping("addReply.bo")
	@ResponseBody
	public String addReply5(@ModelAttribute Reply replyVo, HttpSession session) {
		String replyWriter = ((Member)session.getAttribute("loginUser")).getId();
		replyVo.setReplyWriter(replyWriter);
		int result = bService.insertReply(replyVo);
		if(result > 0) {
			return "success";
		}else {
			throw new BoardException("댓글 등록 실패");
		}
	}
	@RequestMapping("addReply.bo")
	@ResponseBody
	public String addReply6(@ModelAttribute Reply replyVo, HttpSession session) {
		String writerId = ((Member)session.getAttribute("loginUser")).getId();
		replyVo.setReplyWriter(writerId);
		int result = bService.insertReply(replyVo);
		if(result > 0) {
			return "success";
		}else {
			throw new BoardException("댓글 등록 실패");
		}
	}
	@RequestMapping("addReply.bo")
	@ResponseBody
	public String addReply7(@ModelAttribute Reply replyVo, HttpSession session) {
		String writerId = ((Member)session.getAttribute("loginUser")).getId();
//		String writerId2 = ((Member)session.getAttribute("loginUser")).getId();
		replyVo.setReplyWriter(writerId);
		int result = bService.insertReply(replyVo);
		if(result > 0) {
			return "success";
		}else {
			throw new BoardException("댓글 등록 실패");
		}
		
	}
	/** 연습 텍스트 : 댓글 쓰기 **/
	// 받아올 파라미터 & 사용할 객체 체크
	// 댓글쓴이 변수설정 및 로그인정보 가져오기 : 누가 썼는지 알아야하기 때문에 모델어트리뷰트나 HttpSession을 통해서 가져올 수 있음
	// 댓글쓴이 정보를 vo에 저장
	// 댓글 정보 DB 저장
	
	
	
	
	@RequestMapping(value="rList.bo", produces="application/json; charset=UTF-8")
	@ResponseBody
	public String getReplyList(@RequestParam("bId") int bId) {
		ArrayList<Reply> list = bService.selectReplyList(bId);

		JSONArray jArr = new JSONArray();
		for(Reply r : list) {
			JSONObject job = new JSONObject();
			job.put("replyId", r.getReplyId());
			job.put("replyContent", r.getReplyContent());
			job.put("replyWriter", r.getReplyWriter());
			job.put("nickName", r.getNickName());
			job.put("replyCreateDate", r.getReplyCreateDate());
			job.put("replyModifyDate", r.getReplyModifyDate());
			job.put("replyStatus", r.getReplyStatus());
			
			jArr.put(job);
		}
		// @ResponseBody 필요
		// 이유 : 리턴이 스트링인데 뷰리졸버가 뷰파일로 바꾸기 때문
		// 뷰리졸버에게 뷰에대한 경로를 넘기지 않고 데이터로 담아서 보낼 수 있게 인지시켜야함
		return jArr.toString();
		// 연습텍스트 다른 파일에 넣어주기
	}
	@RequestMapping(value="rList.bo", produces="application/json; charset=UTF-8")
	@ResponseBody
	public String getReplyList2(@RequestParam("bId") int bId) {
		
		ArrayList<Reply> replyList = bService.selectReplyList(bId);
		
		JSONArray jsonArr = new JSONArray();
		
		for(Reply reply : replyList) {
			JSONObject jsonObj	= new JSONObject();
			jsonObj.put("replyId", reply.getReplyId());
			jsonObj.put("replyContent", reply.getReplyContent());
			jsonObj.put("replyWriter", reply.getReplyWriter());
			jsonObj.put("nickName", reply.getNickName());
			jsonObj.put("replyCreateDate", reply.getReplyCreateDate());
			jsonObj.put("replyModifyDate", reply.getReplyModifyDate());
			jsonObj.put("replyStatus", reply.getReplyStatus());
			
			jsonArr.put(jsonObj);
		}
		return jsonArr.toString();
	}
	@RequestMapping(value="rList.bo", produces="application/json, charset=UTF=8")
	@ResponseBody
	public String getReplyList3(@RequestParam("bId") int bId ) {
		ArrayList<Reply> rplyList = bService.selectReplyList2(bId);
		
		JSONArray jsonArray = new JSONArray();
		
		for(Reply reply : rplyList) {
			JSONObject jsonObj	= new JSONObject();
			jsonObj.put("replyId", reply.getReplyId());
			jsonObj.put("replyContent", reply.getReplyContent());
			jsonObj.put("replyWriter", reply.getReplyWriter());
			jsonObj.put("nickName", reply.getNickName());
			jsonObj.put("replyCreateDate", reply.getReplyCreateDate());
			jsonObj.put("replyModifyDate", reply.getReplyModifyDate());
			jsonObj.put("replyStatus", reply.getReplyStatus());
			
			jsonArray.put(jsonObj);
		}
		return jsonArray.toString();
	}
	@RequestMapping(value="rList.bo", produces="application/json, charset=UTF-8")
	@ResponseBody
	public String getReply4(@RequestParam("bId") int bId) {
		ArrayList<Reply> replyList = bService.selectReplyList3(bId);
		
		JSONObject jsonObj = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		for(Reply reply : replyList) {
			jsonObj.put("replyId", reply.getReplyId());
			jsonObj.put("replyContent", reply.getReplyContent());
			jsonObj.put("replyWriter", reply.getReplyWriter());
			jsonObj.put("nickName", reply.getNickName());
			jsonObj.put("replyCreateDate", reply.getReplyCreateDate());
			jsonObj.put("replyModifyDate", reply.getReplyModifyDate());
			jsonObj.put("replyStatus", reply.getReplyStatus());
			
			jsonArray.put(jsonObj);
		}
		return jsonArray.toString();
	}
	
	/** 연습 텍스트 : 댓글 목록읽기 **/
	// 받아올 파라미터 & 사용할 객체 체크 : 해당 게시판에 달린 댓글정보를 구분하기 위한 정보 받아오기
	// DB에서 댓글 정보 받아오기
	// 댓글들 데이터 받아올 객체 생성 : 줄줄이 받아야와야함
	// 댓글이 여러개 일 수 있으니 반복문으로 받아오기 
	// DB에서 받아온 객체들을 생성한 json객체에 넣어주기
	// 객체에 저장한 정보 리턴
	
	
	@RequestMapping(value="topList", produces="application/json charset=UTF-8")
	@ResponseBody
	public String topList1() {
		ArrayList<BoardVO> listTopN = bService.topList1();
		
		JSONArray jsonArr = new JSONArray();
		for(BoardVO boardVo : listTopN) {  // listTopN에는 게시판 한 개 한 개가 담겨있음
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("boardId", boardVo.getBoardId());
			jsonObj.put("boardTitle", boardVo.getBoardTitle());
			jsonObj.put("boardContent", boardVo.getBoardContent());
			jsonObj.put("nickName", boardVo.getNickName());
			jsonObj.put("boardModifyDate", boardVo.getBoardModifyDate());
			jsonObj.put("boardCount", boardVo.getBoardCount());
			jsonObj.put("originalFileName", boardVo.getOriginalFileName());
			
			jsonArr.put(jsonObj);
		}
		return jsonArr.toString();
	}
	/** 연습 텍스트 : Top-N 분석  **/
	// 받아올 파라미터 & 사용할 객체 체크
	// Top N목록 정보를 받을 객체 선언 및 생성
	// 해당 정보를 담아서 보낼 JSON객체 생성
	// DB에서 받아온 게시판 정보 각각을 담을 수 있게 loop로 json객체 담기
	// 뷰단으로 json 객체 전송
	
	
	
}
