package com.kh.a;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.a.member.model.vo.MemberVO;


@Controller
public class PracticeController2 {

	@Autowired
	BCryptPasswordEncoder bcrypt;
	
	/*****************************파라미터 전송 받기 *****************************/
	// 1. HttpServletRequest 방식 : JSP/Servlet 방식
	// 2. @RequestParam방식 : 파라메터1:1매핑
	// 3. @RequestParam 생략방식
	// 4. @ModelAttribute 방식 : 객체매핑
	// 5. @ModelAttribute 생략방식
	
	
	/** 1. HttpServletRequest 방식
	 * @param request
	 */
	@RequestMapping("UserMenu.domnCode")
	public void UserMenu1(HttpServletRequest request) {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
	}
	
	
	
	
	/** 2.@RequestParam 방식
	 * @param id
	 * @param pwd
	 * 1)value 속성 생략	
	 * 2)value + defaultValue
	 * 3)value + defaultValue + required 
	 */
	@RequestMapping(value="UserMenu.domnCode",method=RequestMethod.POST)
	public void domnCode1(@RequestParam(value="id") String id, @RequestParam(value="pw") String pw) {
		String encPwd = bcrypt.encode(pw);
	}
	@RequestMapping(value="bbsConfigVo.searchColumns",method=RequestMethod.GET)
	public void bbsConfigVo1(@RequestParam(value="searchColumns") String searchColumns,
							@RequestParam(value="bbsCtgryList") String bbsCtgryList) {
		String encryptPwd = bcrypt.encode(searchColumns);
	}
	
	
	/** 3.@RequestParam 생략
	 * 조건 : parameter name should be the same
	 * 		@Requestparam 속성 사용불가
	 * @param id
	 * @param pwd
	 */
	@RequestMapping("searchDataVo.searchTy")
	public void searchDataVo1(String searchTy, String scrinNm) {
		String ePwd = bcrypt.encode(scrinNm);
	}
	
	
	
	
	
	
	/** 4.@ModelAttribute
	 * @param m
	 */
	@RequestMapping("searchDataVo.searchTy")
	public void searchDataVO1(@ModelAttribute MemberVO member, @ModelAttribute String user) {
		String ecPwd = bcrypt.encode(user);
		member.setAddress(ecPwd);
	}
	@RequestMapping("searchDataVo.searchTy")
	public void searchDataVO2(@ModelAttribute Object obj, @ModelAttribute String pwd){
		obj.toString();
		String encPw = bcrypt.encode(pwd);
	}
	
	/** 5.@ModelAttribute 생략
	 * @param M 
	 */
	@RequestMapping("searchDataVo.scrinNm")
	public void searchKeyTy(MemberVO mem, Object obj) {
		mem.setAddress("aa");
		obj.equals(mem); // boolean return
	}
	
	
	
	
	
//	UserMenu.domnCode
//	bbsConfigVo.searchColumns
//	bbsConfigVo.bbsCtgryList
//	private List<BbsCtgryCacheVO> bbsCtgryList
//	searchDataVo.searchTy
//	searchDataVo.scrinNm
//	paramMap.q_menu
//	paramMap.q_clCode
//	paramMap.q_searchKeyTy
//	reqstdocVo
//	resveManageVo
	
//	public class ParamMap<K, V> extends HashMap<K,V> {
//	}
	
	
    
							  
	

}