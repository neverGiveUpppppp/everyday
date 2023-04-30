package com.kh.a.board.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonIOException;
import com.kh.a.board.exception.BoardException;
import com.kh.a.board.model.service.BoardService;
import com.kh.a.board.model.vo.BoardVO;
import com.kh.a.board.model.vo.PageInfo;
import com.kh.vo.Reply;
import com.kh.a.common.Pagination;
import com.kh.vo.Member;





@Controller
public class BoardController {

	
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
	// ModelAndView -> Model
	@RequestMapping("blist.bo") // menubar.jsp의 게시판 버튼의 url주소
	public String boardList2(@RequestParam(value="page") Integer page, Model model) {
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
	// 받은 파일을 지정 저장경로에 전송 & 저장
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
	/** 연습 텍스트 : 게시판 상세  **/
	// 받아올 파라미터 & 사용할 객체 체크
	// db에서 상세페이지 데이터 받아오기
	// 뷰에 보낼 데이터 저장 및 리턴
	
	
	
	
	
	/** 게시판 수정 + 파일
	 * @param boardVo
	 * @param page
	 * @param reloadFile
	 * @param model
	 * @return
	 */
	@RequestMapping("bupdate.bo")
	public String updateBoard(BoardVO boardVo, @RequestParam("page") int page, Model model,
							@RequestParam("reloadFile") MultipartFile reloadFile, HttpServletRequest request) {
		if(reloadFile != null && !reloadFile.isEmpty()) {
			if(boardVo.getOriginalFileName() != null) {
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
		} else {
			throw new BoardException("게시글 수정에 실패하였습니다.");
		}
	}
	/** 연습 텍스트 : 게시판 수정 + 파일  **/
	// 받아올 파라미터 & 사용할 객체 체크
	// 새업로드파일 여부 체크
	// 기존에 파일이 남아있다면, 기존파일 삭제
	// 새파일 저장
	// 새파일의 원본,리네임명 vo저장
	// DB처리 및 성공 시, 뷰에 데이터값,뷰 세팅
	
	// 위의 deleteFile() 메소드
		public void deleteFile(String fileName, HttpServletRequest request) {
		String root = request.getSession().getServletContext().getRealPath("resources"); // Q.이 resources 경로가 다른 플젝도 같을까?
		String savePath = root + "\\buploadFiles";
		
		File f = new File(savePath + "\\" + fileName);
		if(f.exists()) { // 파일이 존재하는지 확인하고 삭제. 없는 파일삭제하라면 에러발생하기 때문
			f.delete();
		}
	}
	/** 연습 텍스트 : 게시판 수정 + 파일  **/
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
	/** 연습 텍스트 : 댓글 쓰기 **/
	// 받아올 파라미터 & 사용할 객체 체크
	// 댓글쓴이 변수설정 및 로그인정보 가져오기 : 누가 썼는지 알아야하기 때문에 모델어트리뷰트나 HttpSession을 통해서 가져올 수 있음
	// 댓글쓴이 정보를 vo에 저장
	// 댓글 정보 DB 저장
	
	@RequestMapping(value="rList.bo", produces="application/json, charset=UTF-8")
	@ResponseBody
	public String getReplyList(@RequestParam("bId") int bId) {
		ArrayList<Reply> replyList = bService.selectReplyList(bId);
		
		JSONArray jsonArr = new JSONArray();
		
		for(Reply reply : replyList) {
			JSONObject jsonObj = new JSONObject(); // jsonObj에 put으로 저장된 게 하나의 댓글 정보가 담긴 객체 하나
			jsonObj.put("replyId", reply.getReplyId());
			jsonObj.put("replyContent", reply.getReplyContent());
			jsonObj.put("replyWriter", reply.getReplyWriter());
			jsonObj.put("nickName", reply.getNickName());
			jsonObj.put("replyCreateDate", reply.getReplyCreateDate());
			jsonObj.put("replyModifyDate", reply.getReplyModifyDate());
			jsonObj.put("replyStatus", reply.getReplyStatus());
				
			jsonArr.put(jsonObj); // 배열 한칸마다 댓글 하나의 정보가 담긴 json Object 객체가 들어가게되는 구조
		}
		
		return jsonArr.toString();
	}
	/** 연습 텍스트 : 댓글 목록읽기 **/
	// 받아올 파라미터 & 사용할 객체 체크 : 해당 게시판에 달린 댓글정보를 구분하기 위한 정보 받아오기
	// DB에서 댓글 정보 받아오기
	// 댓글들 데이터 받아올 객체 생성 : 줄줄이 받아야와야함
	// 댓글이 여러개 일 수 있으니 반복문으로 받아오기 
	// DB에서 받아온 객체들을 생성한 json객체에 넣어주기
	// 객체에 저장한 정보 리턴
	
	/** 연습 텍스트 : Top-N 분석  **/
	// 받아올 파라미터 & 사용할 객체 체크
	// 루트 및 파일저장 경로 설정
	// 파일객체 생성 및 경로지정
	// 파일이 있다면, 삭제
	
	
}
