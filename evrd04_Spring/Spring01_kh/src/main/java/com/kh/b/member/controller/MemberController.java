package com.kh.b.member.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public void insertMember(@RequestParam(value="id", required=true) String id,
							 @RequestParam(value="name", required=true) String name,
							 @RequestParam(value="pwd", required=true) String pwd,
							 @RequestParam(value="pwd2", required=true) String pwd2,
							 @RequestParam(value="nickName", required=true) String nickName	) {
	}
	
	
	
	
	
	
	
	
	
	
	
}
