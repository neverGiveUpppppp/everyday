package com.kh.a.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MemberController {

	
	
	@RequestMapping(value="login.me", method=RequestMethod.POST)
	public void login(HttpServletRequest request) {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		System.out.println("id1" + id);
		System.out.println("pwd1" + pwd);
	}
	
	
}
