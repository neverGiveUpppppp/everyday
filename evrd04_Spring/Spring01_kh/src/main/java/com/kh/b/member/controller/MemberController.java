package com.kh.b.member.controller;

import java.util.List;

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
	
	
	
	
	/**	로그인
	 * @param memberVo
	 * @param model
	 * @return
	 */
	@RequestMapping(value="login.me", method=RequestMethod.POST)
	public String login(MemberVO memberVo, Model model) {
		
		System.out.println(bcrypt.encode(memberVo.getPwd()));		
		
		MemberVO loginMember = mService.login(memberVo);
		
		boolean match = bcrypt.matches(memberVo.getPwd(), loginMember.getPwd());
		
		if(match) {
			model.addAttribute("loginUser", loginMember);
			logger.info(loginMember.getId());
			return "redirect:home.do";
		}else {
			throw new MemberException("로그인에 실패하였습니다");
		}
	}
	
	
	
	
	/** 로그아웃
	 * @param status
	 * @return
	 */
	@RequestMapping("logout.me")
	public String logout(SessionStatus status){
		status.setComplete();
		return "redirect:home.do";
	}
	
	
	
	
	/** 회원가입 로그
	 * @return
	 */
	@RequestMapping("enrollView.me")
	public String enrollView() {
		if(logger.isDebugEnabled()) {
			logger.debug("회원등록페이지");
		}
		return "memberJoin";
	}
	
	
	
	
	@RequestMapping("minsert.me")
	public void insertMember1(@RequestParam(value="id", required=true) String id,
							 @RequestParam(value="name", required=true) String name,
							 @RequestParam(value="pwd", required=true) String pwd,
							 @RequestParam(value="pwd2", required=true) String pwd2,
							 @RequestParam(value="nickName", required=true) String nickName	) {
	}
	
	
//	@RequestMapping("minsert.me")
	@RequestMapping(value="minsert.me", method=RequestMethod.POST)
	public String insertMember2(@ModelAttribute MemberVO memberVo,
								@RequestParam("post") String post,
								@RequestParam("address1") String address1,
								@RequestParam("address2") String address2) {
		memberVo.setAddress(post + "/" + address1 + "/" + address2);
		
		
		// 비번 암호화 작업
//		System.out.println(memberVo.getPwd()); // 암호화 전
		String encPwd = bcrypt.encode(memberVo.getPwd()); // encode() : 암호화가 된 비번을 반환하는 메소드
		memberVo.setPwd(encPwd);
//		System.out.println(memberVo.getPwd()); // 암호화 후
		
		
		int result = mService.insertMember(memberVo);
		if(result > 0) {
			return "redirect:home.do";
		}else {
			throw new MemberException("회원 가입 실패");
		}
	}
		
// 비밀번호가 그대로 개발자에게 노출되기 때문에 문제됨
// 스프링이 쓰는 보안이 뛰어난 방식 : BCrypt 암호화 방식
// 		원본 데이터 --> salting, salt값 --> 암호화 데이터
//						(random)
//	원본 데이터에서 솔팅을 진행하면 얘는 암호화가 된 데이터가 발생함(다이제스트라고 부름)
//	sha512도 마찬가지고 중간에 솔팅값이 들어가면서 원본 데이터가 암호화가 되는건데 솔팅값이 계쏙 동일하게 되면 a라는 데이터가 들어가면 게속 똑같은 암호화된 데이터가 들어가게 되면서 일정 패턴이 생기는 것
//	비크립트는 솔팅값을 랜덤하게 생성
//	그러다보니 똑같이 비밀번호를 집어넣어도 암호화된 데이터가 다 다르게 나옴
//	jsp때는 항상 암호화가 같아서 db와 값을 비교만 했으면 됬으나 스프링에서는 다름

	@RequestMapping(value="minsert.me",method=RequestMethod.POST)		
	public String insertMember3(@ModelAttribute MemberVO memVo,
								@RequestParam("post") String post,
								@RequestParam("address1") String address1,
								@RequestParam("address2") String address2) {
		memVo.setAddress(post + "/" + address1 + "/" + address2);
		String encPwd = bcrypt.encode(memVo.getPwd());
		memVo.setPwd(encPwd);
		
		int result = mService.insertMember(memVo);
		if(result > 0) {
			return "redirect:home.do";
		}else {
			throw new MemberException("회원가입실패");
		}
	}
	
	/**	회원가입
	 * @param memberVo
	 * @param post
	 * @param address1
	 * @param address2
	 * @return
	 */
	@RequestMapping(value="minsert.me",method=RequestMethod.POST)
	public String insertMember(@ModelAttribute MemberVO memberVo,
								@RequestParam("post") String post,
								@RequestParam("address1") String address1,
								@RequestParam("address2") String address2) {
		memberVo.setAddress(post + "/" + address1 + " / " + address2);
		String encPwd = bcrypt.encode(memberVo.getPwd());
		memberVo.setPwd(encPwd);
		int result = mService.insertMember(memberVo);
		
		if(result > 0) {
			return "redirect:home.do";
		}else {
			throw new MemberException("회원 가입 실패");
		}
	}
	
	
	@RequestMapping("myinfo.me") // menubar.jsp
	public String myInfo() {
		return "mypage";
	}
	@RequestMapping("mupdateView.me")
	public String updateView() {
		return "memberUpdateForm";
	}
	@RequestMapping("mupdate.me")
	public String updateMember(@ModelAttribute MemberVO memberVo,
								@RequestParam("post") String post,
								@RequestParam("address1") String address1,
								@RequestParam("address2") String address2,
								Model model) {
		memberVo.setAddress(post+"/"+address1+"/"+address2);
		
		int result = mService.updateMember(memberVo);
		MemberVO loginUser = mService.login(memberVo);
		if(result > 0 ) {
			model.addAttribute("loginUser",loginUser);
			return "redirect:myinfo.me";
		}else {
			throw new MemberException("회원정보 수정에 실패하였습니다");
		}
	}
	
	
	
	
	
	
}
