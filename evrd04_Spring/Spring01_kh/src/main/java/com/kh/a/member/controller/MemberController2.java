package com.kh.a.member.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import com.kh.a.member.exception.MemberException;
import com.kh.a.member.model.service.MemberService;
import com.kh.a.member.model.vo.MemberVO;

@Controller
public class MemberController2 {

	@Autowired
	public MemberService mService;
	
	@Autowired
	public BCryptPasswordEncoder bcrypt;
	
	private Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	
	/**	로그인
	 * @param memberVo
	 * @param model
	 * @return
	 */
	@RequestMapping(value="login.me")
	public String login(@ModelAttribute MemberVO memberVo, Model model) {
		
		MemberVO loginMember = mService.login(memberVo);
		boolean matches = bcrypt.matches(memberVo.getPwd(), loginMember.getPwd());
		
		if(matches) {
			model.addAttribute("loginUser",loginMember);
			logger.info(loginMember.getId());
			return "redirect:home.do";
		}else {
			throw new MemberException("로그인 실패");
		}
	}
	@RequestMapping(value="login.me")
	public String login2(MemberVO memberVo, Model model) {
		MemberVO loginMatch = mService.login(memberVo);
		boolean matches = bcrypt.matches(memberVo.getPwd(), loginMatch.getPwd());
		if(matches) {
			model.addAttribute("loginUser",loginMatch);
			logger.info(loginMatch.getId());
			return "redirect:home.do";
		}else {
			throw new MemberException("로그인 실패");
		}
	}
	
	
	
	
	/** 로그아웃
	 * @param status
	 * @return
	 */
	@RequestMapping("logout.me")
	public String logout(SessionStatus status) {
		status.setComplete();
		return "redirect:home.do";
	}
	@RequestMapping("logout.me")
	public String logout2(SessionStatus status) {
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
	@RequestMapping("enrollView.me")
	public String enrollView2() {
		if(logger.isDebugEnabled()) {
			logger.debug("회원가입페이지");
		}
		return "memberJoin";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
