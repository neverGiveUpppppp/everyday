package com.kh.a;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.a.member.model.vo.MemberVO;


@Controller
public class PracticeController {


	/*****************************파라미터 전송 받기 *****************************/
	// 1. HttpServletRequest 방식 : JSP/Servlet 방식
	// 2. @RequestParam방식 : 파라메터1:1매핑
	// 3. @RequestParam 생략방식
	// 4. @ModelAttribute 방식 : 객체매핑
	// 5. @ModelAttribute 생략방식
	
	
	/** 1. HttpServletRequest 방식
	 * @param request
	 */
	@RequestMapping(value="login.me", method=RequestMethod.POST)
	public void login(HttpServletRequest request) {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		System.out.println("id1" + id);
		System.out.println("pwd1" + pwd);
	}
	@RequestMapping(value="login.me", method=RequestMethod.POST)
	public void login2(HttpServletRequest request) {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
	}
	@RequestMapping(value="url", method=RequestMethod.POST)
	public void login3(HttpServletRequest httpSerReq) {
		String id = httpSerReq.getParameter("id");
		String pwd = httpSerReq.getParameter("pwd");
	}
	
	/** 2.@RequestParam 방식
	 * @param id
	 * @param pwd
	 * 1)value 속성 생략	
	 * 2)value + defaultValue
	 * 3)value + defaultValue + required 
	 */
	@RequestMapping(value="login.me", method=RequestMethod.POST)
	public void login1(@RequestParam("id") String id, @RequestParam("Pwd") String pwd) {
		
	}
	@RequestMapping(value="login.me",method=RequestMethod.POST)
	public void login2(@RequestParam(value="id", defaultValue="hello") String id,
					   @RequestParam(value="Pwd", defaultValue="world") String pwd) {
		
	}
	@RequestMapping(value="login.me",method=RequestMethod.POST)
	public void login3(@RequestParam(value="id", defaultValue="hello", required=false) String id,
					   @RequestParam(value="Pwd", defaultValue="world", required=false) String pwd,
					   @RequestParam(value="test", defaultValue="test", required=false) String test) {
	}
	@RequestMapping(value="login.me", method=RequestMethod.POST)
	public void requestParam1(@RequestParam("bbscode") String bbsCode,
							  @RequestParam("sj") String sj,
							  @RequestParam("lwqi") String lwqi) {
	}
	@RequestMapping(value="BD_selectBbs", method=RequestMethod.GET)
	public void requestParam2(@RequestParam("bbsCode") String bbsCode,
							  @RequestParam("bbdVo") String bbsVo,
							  @RequestParam("pageVo") String pageVo){
    }
    @RequestMapping(value="BD_selectBbsConfig", method=RequestMethod.GET)
    public void requestParam3(@RequestParam(value="bbsVo", defaultValue="BbsVO") String bbsVo,
    						  @RequestParam(value="bbsConfigVo", defaultValue="BbsConfigVO") String bbsConfigVo) {
    }	
	@RequestMapping(value="INC_select.do", method=RequestMethod.POST)
	public void requestBody(@RequestParam(value="searchDataVo",required=true) String searchDataVo,
							@RequestParam(value="searchTy",required=false, defaultValue="searchDataVo.searchTy") String searchTy) {
		
	}
	
	

	
	
	/** 3.@RequestParam 생략
	 * 조건 : parameter name should be the same
	 * 		@Requestparam 속성 사용불가
	 * @param id
	 * @param pwd
	 */
	@RequestMapping(value="BD_delete.do")
	public void loginEllipsis(String id, String pwd) {
	}
	@RequestMapping(value="PD_postmember.me")
	public void paramEllipsis1(String id, String pwd) {
	}
	@RequestMapping(value="a.do")
	public void loginEllipsis2(String id, String pwd, String adres1) {
	}
	@RequestMapping(value="Usermen.domnCode")
	public void loginEllipsis3(String adres1, String adres2) {
	}
	@RequestMapping(value="UserMenu.domnCode", method=RequestMethod.PUT)
	public void loginEllipsis4(String bbsConfig, String bbsCtgryList, List<String> BbsCtgryCacheVO) {
	}

	
	/** 4.@ModelAttribute
	 * @param m
	 */
	@RequestMapping(value="BD_delete.do")
	public void loginMA1(@ModelAttribute MemberVO m) {
	}
	@RequestMapping(value="BD_selectAllNoticeList.do")
	public void loginMA2(@ModelAttribute MemberVO mem) {
	}
	@RequestMapping(value="BD_selectBbsList.do", method=RequestMethod.PUT)
	public void loginMA3(@ModelAttribute String bbsVo, @ModelAttribute String bbsObj) {
	}
	
	
	/** 5.@ModelAttribute 생략
	 * @param M 
	 */
	@RequestMapping(value="login.me", method=RequestMethod.POST)
	public void loginMAEllipsis1(MemberVO m) {
		System.out.println(m);
	}
	
	@RequestMapping("BD_selectBbs.do")
	public void loginMAEllipsis2(MemberVO mVo) {
	}
	@RequestMapping("BD_insertBbsForm.do")
	public void loginMAEllipsis3(MemberVO member, String bbsConfig, String bbsConfigVo) {
	}
	@RequestMapping("BD_updateBbsForm.do")
	public void loginMAEllipsis4(String dataVo, String paramMap, String paramMapp) {
	}
	@RequestMapping(value="BD_updateBbsForm.do", method=RequestMethod.POST)
	public void loginMAEllipsis5(String dataVo, String paramMap2, String paramMapp2) {
	}
	
	
	
	

	/*****************************파라미터 전송 받기 *****************************/
	// 1. HttpServletRequest 방식 : JSP/Servlet 방식
	// 2. @RequestParam방식 : 파라메터 1:1매핑
	// 3. @RequestParam 생략방식
	// 4. @ModelAttribute 방식 : 객체 매핑
	// 5. @ModelAttribute 생략방식
	
	
	// 1. HttpServletRequest 방식 : JSP/Servlet 방식
	@RequestMapping(value="UserMenu.domnCode", method=RequestMethod.POST)
	public void htpservReq1(HttpServletRequest req, HttpServletResponse res) {
		String id = req.getParameter("id");
		String pwd = req.getParameter("pwd");
	}
	@RequestMapping("bbsConfigVo.searchColumns")
	public void htpservReq2(HttpServletRequest req) {
		String id = req.getParameter("id");
		String pwd = req.getParameter("pwd");
	}
	@RequestMapping("url")
	public void htpServReq3(HttpServletRequest req, HttpServlet hs) {
		String id = req.getParameter("id");
		String pwd = req.getParameter("pwd");
		hs.destroy();
	}
	@RequestMapping("url")
	public void htpServReq4(HttpServletRequest req, HttpSession session) {
		session.getAttribute("id");
	}
	@RequestMapping("url")
	public void htpServReq5(HttpServletRequest request, HttpServletResponse res) {
		
	}
	@RequestMapping(".do")
	public void hsr(HttpServletRequest request, HttpSession session) {
	}
	
	
	
	// 2. @RequestParam방식 : 파라미터 1:1매핑
	@RequestMapping(value="login.url")
	public void rp1(@RequestParam(value="id") String id){
		id.charAt(0);
	}
	@RequestMapping("url.bbsConfigVo.bbsCtgryList")
	public void rp2(@RequestParam(value="id") String id, @RequestParam(value="pw") String pw) {
		
	}
	@RequestMapping(value="url.login")
	public void rp3(@RequestParam(value="id") String id, @RequestParam(value="pwd", defaultValue="p") String pwd) {
	}
	@RequestMapping(value="url.logout")
	public void rp4(@RequestParam(value="id", defaultValue="user01", required=false) String id,
					@RequestParam(value="pw", defaultValue="user01", required=true) String pwd) {
	}
	@RequestMapping("login.do")
	public void rp5(@RequestParam(value="name", defaultValue="김철수", required=true) String name) {
	}
	@RequestMapping("login.do")
	public void rp6(@RequestParam(value="page") int page, @RequestParam(value="goods", defaultValue="apple") String goods) {}
	
	
	// 3. @RequestParam 생략방식
	@RequestMapping(value="url.logout")
	public void rpEllipsis1(String id, String pwd) {
		
	}
	@RequestMapping(value="url.logout")
	public void rpEllipsis2(String id, String pw) {
	}
	@RequestMapping(value="url.logout")
	public void rpEllipsis3(String name, int page) {
	}
	@RequestMapping(value="url.logout")
	public void rpEllipsis4(int pager, String title) {
	}
	@RequestMapping(value="urlWritein", method=RequestMethod.PUT)
	public void rpEllipsis5(String title, String author) {}
	@RequestMapping(value="buy.buy")
	public void rpEllipsis6(String goods, int ea, String desc) {}
	@RequestMapping(value="goods.goods", method=RequestMethod.DELETE)
	public void rpEllipsis7(String goods, int ea) {}
	
	// 4. @ModelAttribute 방식 : 객체 매핑
	@RequestMapping(value="url", method=RequestMethod.GET)
	public void ma1(@ModelAttribute MemberVO memVo){
	}
	@RequestMapping(value=".do", method=RequestMethod.POST)
	public void ma2(@ModelAttribute String a, MemberVO m	) {
	}
	@RequestMapping(value=".do", method=RequestMethod.POST)
	public void ma3(@ModelAttribute MemberVO m) {
	}
	@RequestMapping(value="apple.buy", method=RequestMethod.PATCH)
	public void appleBuy(@ModelAttribute MemberVO mVo) {}
	
	// 5. @ModelAttribute 생략방식
	@RequestMapping(value=".do", method=RequestMethod.POST)
	public void maEllipsis1(MemberVO m) {
	}
	@RequestMapping(value=".do", method=RequestMethod.POST)
	public void maEllipsis2(MemberVO m) {
	}
	@RequestMapping(value="watermelon.buy", method=RequestMethod.HEAD)
	public void maEllipsis3(MemberVO m) {
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