package com.kh.a.member.controller;


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

import com.kh.Spring.member.model.vo.Member;
import com.kh.a.member.exception.MemberException;
import com.kh.a.member.model.service.MemberService;
import com.kh.a.member.model.vo.MemberVO;



import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


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
	
	
	/** 로그아웃
	 * @param status
	 * @return
	 */
	@RequestMapping(value="logout.me")
	public String logout(SessionStatus status) {
		status.setComplete();
		return "redirect:home.do";
	}
	
	
	
	@RequestMapping("enrollView.me")
	public String enrollView() {
		if(logger.isDebugEnabled()) {
			logger.debug("회원등록페이지");
		}
		return "memberJoin";
	}
	
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
	@RequestMapping("enrollView.me")
	public String insertMember2(@ModelAttribute MemberVO mem,
								@RequestParam(value="post") String post,
								@RequestParam("address1") String address1,
								@RequestParam("address2") String address2) {
		mem.setAddress(post+address1+address2);
		String encPwd = bcrypt.encode(mem.getPwd());
		mem.setPwd(encPwd);
		int result = mService.insertMember(mem);
		if(result > 0) {
			return "redirect:home.do";
		}else {
			throw new MemberException("회원가입에 실패하였습니다");
		}
	}
	@RequestMapping("enrollView.me")
	public String insertMember3(@ModelAttribute MemberVO member,
								@RequestParam(value="post") String post,
								@RequestParam(value="address1") String address1,
								@RequestParam(value="address2") String address2) {
		member.setAddress(post+"/"+address1+"/"+address2);
		// 암호화
		String encryptPwd = bcrypt.encode(member.getPwd());
		member.setPwd(encryptPwd);
		int result = mService.insertMember(member);
		if(result > 0) {
			return "redirect:home.do";
		}else {
			throw new MemberException("회원 가입 실패");
		}
	}
	
	@RequestMapping("enrollView.me")
	public String insertMember4(@RequestParam(value="id", required=true) String id,
								@RequestParam(value="pwd", required=true) String pwd,
								@RequestParam(value="pwd2", required=true) String pwd2,
								@RequestParam(value="name", defaultValue="홍길동") String name,
								@RequestParam(value="gender", required=true) char gender,
								@RequestParam(value="age",required=true) int age,
								@RequestParam(value="nickname") String nickname,
								@RequestParam(value="phone", defaultValue="번호없음",required=true) String phone){
		return "a";
	}
	
	
	
	
	
	/** 내정보보기 & 회원정보 수정
	 * @param member
	 * @param post
	 * @param address1
	 * @param address2
	 * @param model
	 * @return
	 */
	@RequestMapping("myinfo.me")
	public String myInfo() {
		return "mypage";
	}
	@RequestMapping("mupdateView.me")
	public String updateView() {
		return "memberUpdateForm";
	}
	
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
	
	
	// 회원정보 수정
	@RequestMapping("mupdate.me")
	public String updateMember2(@ModelAttribute MemberVO member,
								@RequestParam(value="post") String post,
								@RequestParam(value="address1") String address1,
								@RequestParam(value="address2") String address2,
								Model model) {
		
		// 상세주소들(adres1,2) 포함해서 주소정보 다시 유저정보(MemberVO)에 넣기
		member.setAddress(post+address1+address2);
		// 유저정보 업데이트 실행 : vo값 -> DB로 저장
		int result = mService.updateMember(member);
		// 업뎃한 정보를 클라이언트한테 보여주기 위해 최신정보를 select 해오기 위한 select문
		MemberVO newInfo = mService.login(member);
		
		if(result > 0) {
			// 최신정보인 select문을 뷰로 보내주기
			model.addAttribute(newInfo);
			return "redirect:myinfo.me";
		}else {
			throw new MemberException("회원정보 수정 실패");
		}
	}
	
	
	
	
	
	
	
	@RequestMapping("mpwdUpdateView.me")
	public String pwdUpdateView() {
		return "memberPwdUpdateForm";
	}
	@RequestMapping("mPwdUpdate.me")
	public String updatePwd(@RequestParam(value="pwd") String pwd,
							@RequestParam(value="newPwd1") String newPwd,
							Model model) {
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
