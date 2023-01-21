package com.kh.a.member.controller;

<<<<<<< HEAD
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
=======
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

>>>>>>> 7bf68bdaecc81d379184651d61317c6beb83a5bf
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
	
	
	
	
	
	
	

	
	
	@RequestMapping(value="login.me", method=RequestMethod.POST)
	public void login(HttpServletRequest request) {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		System.out.println("id1" + id);
		System.out.println("pwd1" + pwd);
	}
	
	
}
