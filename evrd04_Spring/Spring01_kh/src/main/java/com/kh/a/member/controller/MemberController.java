package com.kh.a.member.controller;


import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.kh.a.member.exception.MemberException;
import com.kh.a.member.model.service.MemberService;
import com.kh.a.member.model.vo.MemberVO;


@SessionAttributes("loginUser")
@Controller
public class MemberController {
	
	@Autowired
	private MemberService mService;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	private Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	
	/** 로그인
	 * @param m
	 * @param model
	 * @return
	 */
	@RequestMapping(value="login.me",method=RequestMethod.POST)
	public String login(MemberVO m, Model model) {

		System.out.println(bcrypt.encode(m.getPwd()));
		
		MemberVO loginMember = mService.login(m);
		
		boolean match = bcrypt.matches(m.getPwd(), loginMember.getPwd());
		
		if(match) {
			model.addAttribute("loginUser",loginMember);
			logger.info(loginMember.getId());
			return "redirect:home.do";
		}else {
			throw new MemberException("로그인 실패하였습니다.");
		}
	}
	/** 연습 텍스트**/
	// 로그인 할려는 유저 정보 받아오기
	// 로그인할려는 유저와 db에 해당 유저와의 정보 비교
	// 맞다면, 데이터를 뷰로 보내기
	// 해당 유저id정보 로그 남기기
	// 어떤 뷰로 갈지 지정
	
	
	
	/** 로그아웃
	 * @param status
	 * @return
	 */
	@RequestMapping(value="logout.me")
	public String logout(SessionStatus status) {
		status.setComplete();
		return "redirect:home.do";
	}
	/** 연습 텍스트**/
	// 세션 종료 선언
	// 어떤 뷰로 갈지 지정
	
	
	
	
	/** 회원등록 페이지 전환
	 * @return
	 */
	@RequestMapping("enrollView.me")
	public String enrollView() {
		if(logger.isDebugEnabled()) {
			logger.debug("회원등록페이지");
		}
		return "memberJoin";
	}
	/** 연습 텍스트**/
	// 로그 남기기
	// 회원등록 버튼 눌렀을 때 해당 페이지로 이동하도록
	
	
	
	/** 회원 등록
	 * @param m
	 * @param post
	 * @param address1
	 * @param address2
	 * @return
	 */
	@RequestMapping(value = "minsert.me", method=RequestMethod.POST)
	public String insertMember(@ModelAttribute MemberVO m,
								@RequestParam("post") String post,
								@RequestParam("address1") String address1,
								@RequestParam("address2") String address2){
		
		m.setAddress(post+"/"+address1+"/"+address2);
		String encPwd = bcrypt.encode(m.getPwd());
		m.setPwd(encPwd); // bcrpyt로 암호화하여 다시 vo에 비번값으로 넣어줌
		
		int result = mService.insertMember(m); // encode한 비번을 그대로 db에 저장시킴
		if(result > 0) {
			return "redirect:home.do";
		}else {
			throw new MemberException("회원가입 실패");
		}
	}
	/** 연습 텍스트**/
	// 회원가입하는 유저에 대한 객체 정보 받기
	// 상세주소 포함해서 주소 다시 저장
	// 유저 정보 중 비번을 암호화하는 작업
	// DB처리
	
	
	
	
	
	/** 내정보보기
	 * @return
	 */
	@RequestMapping("myinfo.me")
	public String myInfo() {
		return "mypage";
	}
	/** 연습 텍스트**/
	// 내정보보기 눌렀을 때 해당 페이지로 넘겨주기
	
	
	
	/**회원 수정폼
	 * @return
	 */
	@RequestMapping("mupdateView.me")
	public String updateView() {
		return "memberUpdateForm";
	}
	/** 연습 텍스트**/
	// 회원정보 수정 눌렀을 때 해당 페이지로 넘겨주기
	
	
	
	/** 회원정보 수정
	 * @param member
	 * @param post
	 * @param address1
	 * @param address2
	 * @param model
	 * @return
	 */
	@RequestMapping("mupdate.me")
	public String updateMember(@ModelAttribute MemberVO member,
								@RequestParam(value="post") String post,
								@RequestParam(value="address1") String address1,
								@RequestParam(value="address2") String address2,
								Model model) {
		// 상세주소들 유저정보에 넣기
		member.setAddress(post+address1+address2);
		
		// 저장한 값 DB로
		int result = mService.updateMember(member);
		
		// 화면에 보여줄 정보 최신화를 위한 select문
		MemberVO loginUser = mService.login(member);
		
		if(result > 0) {
			model.addAttribute("loginUser",loginUser); // 가져온 유저정보(select문) 뷰에 넣어주기
			return "redirect:myinfo.me";
		}else {
			throw new MemberException("회원정보 수정 실패");
		}
		
	}
	/** 연습 텍스트**/
	// 회원정보 수정
	// 상세주소들(adres1,2) 포함해서 주소정보 다시 유저정보(MemberVO)에 넣기
	// 유저정보 업데이트 실행 : vo값 -> DB로 저장
	// 업뎃한 정보를 클라이언트한테 보여주기 위해 최신정보를 select 해오기 위한 select문
	// 최신정보인 select문을 뷰로 보내주기
	
	
	
	
	
	/** 비번 수정폼
	 * @return
	 */
	@RequestMapping("mpwdUpdateView.me")
	public String pwdUpdateView() {
		return "memberPwdUpdateForm";
	}
	/** 연습 텍스트**/
	// 비번수정 요청이 왔을 때 해당 페이지로 넘겨주기
	
	
	
	/** 비번 수정
	 * @param oldPwd
	 * @param newPwd
	 * @param model
	 * @return
	 */
	@RequestMapping("mPwdUpdate.me")
	public String updatePwd(@RequestParam(value="pwd") String pwd,
							@RequestParam(value="newPwd1") String newPwd,
							Model model) {
		
		MemberVO memberVo = (MemberVO)model.getAttribute("loginUser");
		
		int result = 0;
		String encode = null;
		if(bcrypt.matches(pwd, newPwd)) {
			HashMap<String, String> map = new HashMap<>();
			map.put("id",memberVo.getId());
			encode = bcrypt.encode(memberVo.getPwd());
			map.put("newPwd", encode);
			result = mService.updatePwd(map);
		}
		
		if(result > 0) {
			memberVo.setPwd(encode);
			model.addAttribute("loginUser",memberVo);
			return "redirect:myinfo.me	";
		}else {
			throw new MemberException("비밀번호 변경 실패");
		}
	}
	/** 연습 텍스트**/
	// 로그인 정보 가져오기
	// 전 비번과 새 비번 일치하는 지 비교
	// 바뀐 비번 저장하기
	// 바뀐 비번, DB에 업데이트 저장
	// 새 비번 정보를 뷰에 보내기
	
	
	
	
	/** 회원 탈퇴
	 * @param model
	 * @return
	 */
	@RequestMapping("mdelete.me")
	public String deleteMember(Model model) {
		String id = ((MemberVO)model.getAttribute("loginUser")).getId();
		int result = mService.deleteMember(id);
		if(result>0) {
			return "redirect:logout.me";
		}else {
			throw new MemberException("회원 탈퇴 실패");
		}
		
	}
	/** 연습 텍스트**/
	// 세션에서 id값 가져와서 저장 : 어느 id를 삭제할 지 알아야하니 id 필요
	// DB 처리
	// 리턴
	
	
	
	@RequestMapping("dupId.me")
	public void duplicateId(@RequestParam("id") String id, HttpServletResponse response) {
		
		int result = mService.checkIdDup(id);
		try {
			response.getWriter().print(result); // 뷰에 중복확인결과 전송 : 하나만 보낼꺼니 그냥 printWrtier 써보겠다
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	/** 연습 텍스트**/
	// id 중복체크
	// 뷰에 중복확인결과 전송 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
