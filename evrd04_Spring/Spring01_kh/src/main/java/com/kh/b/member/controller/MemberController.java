package com.kh.b.member.controller;

import java.util.HashMap;

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
	@RequestMapping(value="login.me",method=RequestMethod.POST)
	public String login2(MemberVO memVo, Model model) {
		MemberVO loginMember = mService.login(memVo);
		boolean match = bcrypt.matches(memVo.getPwd(), loginMember.getPwd());
		
		if(match) {
			model.addAttribute("loginUser",loginMember);
			logger.info(loginMember.getId());
			return "redirect:home.do";
		}else {
			throw new MemberException("로그인에 실패하였습니다");
		}
	}
	@RequestMapping("login.me")
	public String login3(@ModelAttribute MemberVO member, Model model) {
		MemberVO loginMember = mService.login(member);
		boolean match = bcrypt.matches(member.getPwd(), loginMember.getPwd());
		if(match) {
			model.addAttribute("loginUser",loginMember);
			logger.info(member.getId());
			return "redirect:home.do";
		}else {
			throw new MemberException("failed");
		}
	}
	
	
	/** 로그아웃
	 * @param status
	 * @return
	 */
	@RequestMapping("logout.me")
	public String logout(SessionStatus status){
		status.setComplete();	// 해당 유저의 세션 종료하는 메소드
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
	
	
	
	
	/**	회원가입(@RequestParam버젼)
	 * @param id
	 * @param name
	 * @param pwd
	 * @param pwd2
	 * @param nickName
	 */
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
	
	/**	회원가입(최종버젼)
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
	@RequestMapping(value="minsert.me",method=RequestMethod.POST)
	public String insertMember2(@ModelAttribute MemberVO mem,
								@RequestParam("post") String post,
								@RequestParam("address") String address) {
		mem.setAddress(address+post);
		String encodePwd = bcrypt.encode(mem.getPwd());
		mem.setPwd(encodePwd);
		int result = mService.insertMember(mem);
		if(result > 0) {
			return "redirect:home.do";
		}else {
			throw new MemberException("회원가입 완료");
		}
	}
	
	
	
	/** 유저 정보 업데이트
	 * @param memberVo
	 * @param post
	 * @param address1
	 * @param address2
	 * @param model
	 * @return
	 */
	
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
	
	
	@RequestMapping("myinfo.me")
	public String myInfo2() {
		return "mypage";
	}
	@RequestMapping("mupdateView.me")
	public String updateView2() {
		return "memberUpdateForm";
	}
	@RequestMapping("mupdate.me")
	public String updateMember2(@ModelAttribute MemberVO memberVo,
								@RequestParam("post") String post,
								@RequestParam("address1") String address1,
								@RequestParam("address2") String address2,
								Model model) {
		memberVo.setAddress(post+address1+address2);
		int result = mService.updateMember(memberVo);
		MemberVO myInfoNew = mService.login(memberVo);
		if(result > 0) {
			model.addAttribute("loginUser",myInfoNew);
			return "redirect:myinfo.me";
		}else {
			throw new MemberException("회원 정보 수정 실패");
		}
	}
	@RequestMapping("mupdateView.me")
	public String updateMember3(@ModelAttribute MemberVO memVo,
								@RequestParam(value="post", defaultValue="주소없음") String post,
								@RequestParam(value="address") String address,
								Model model){
		memVo.setAddress(post+address);
		int result = mService.updateMember(memVo);
		MemberVO loginUser = mService.login(memVo);
		if(result >0) {
			model.addAttribute("loginUser",loginUser);
			return "redirect:myinfo.me";
		}else {
			throw new MemberException("수정 실패");
		}
		
	}
	@RequestMapping("mupdate.me")
	public String updateMember4(@ModelAttribute MemberVO memberVo,
								@RequestParam("post") String post,
								@RequestParam("address1") String address1,
								@RequestParam("address2") String address2,
								Model model) {
		memberVo.setAddress(post + "/" + address1 + "/" + address2 );
		
		int result = mService.updateMember(memberVo);
		MemberVO infoUpdate = mService.login(memberVo);
		
		if(result > 0	) {
			model.addAttribute("loginUser",infoUpdate);
			return "redirect:myinfo.me";
		}else {
			throw new MemberException("회원정보 수정 실패");
		}
		
	}
	@RequestMapping("mupdate.me")
	public String updateMember5(MemberVO memVo, @RequestParam("post") String post,
												@RequestParam("address1") String address1,
												@RequestParam("address2") String address2,
												Model model) {
		memVo.setAddress(post+address1+address2);
		int result = mService.updateMember(memVo);
		MemberVO loginUser = mService.login(memVo);
		if(result > 0) {
			model.addAttribute("loginUser",loginUser);
			return "redirect:myinfo.me";
		}else {
			throw new MemberException("업데이트 실패");
		}
	}
	@RequestMapping("mupdate.me")
	public String updateMember6(MemberVO memVo, @RequestParam("post") String post,
												@RequestParam("address1") String address1,
												@RequestParam("address2") String address2,
												Model model) {
		memVo.setAddress(post+address1+address2);
		int result = mService.updateMember(memVo);
		MemberVO loginUser = mService.login(memVo);
		if(result>0) {
			model.addAttribute("loginUser",loginUser);
			return "redirect:myinfo.me";
		}else {
			throw new MemberException("업데이트 실패");
		}
		
	}
	
	
	
	@RequestMapping("mupdateView.me")
	public String pwdUpdateView() {
		return "memberPwdUpdateForm";
	}
	
	
	@RequestMapping("mPwdUpdate.me")
	public String updatePwd(@RequestParam("pwd") String oldPwd, 
		 					@RequestParam("newPwd1") String newPwd, Model model) {
//		MemberVO memVo = (MemberVO)model.getAttribute("loginUser");
//		String encode = bcrypt.encode(oldPwd);
//		boolean match = bcrypt.matches(oldPwd, encode);
//		if(match) {
//			model.addAttribute();
//			return ;
//		}
		MemberVO memVo = (MemberVO)model.getAttribute("loginUser");
		
		int result = 0;
		String encode = null;
		if(bcrypt.matches(oldPwd, memVo.getPwd())) {
			HashMap<String,String> map = new HashMap<>(); // 객체선언함. 프레임워크 IoC 원칙에 거스르긴함
			map.put("id",memVo.getId());
			encode = bcrypt.encode(memVo.getPwd());
			map.put("newPwd",encode);
			result = mService.updatePwd(map);
		}
		
		if(result > 0) {
			model.addAttribute("loginUser",memVo);
			return "redirect:myinfo.me";
		}else {
			throw new MemberException("업데이트 실패");
		}
	}
	@RequestMapping("mPwdUpdate.me")
	public String updatePwd2(@RequestParam("pwd") String oldPwd, 
		 					@RequestParam("newPwd1") String newPwd, Model model) {
		
		MemberVO memVo = (MemberVO)model.getAttribute("loginUser");
		
		int result = 0;
		String encode = null;
		if(bcrypt.matches(memVo.getPwd(), newPwd)) {
			HashMap<String, String> map = new HashMap<>();
			map.put("id",memVo.getId());
			encode = bcrypt.encode(memVo.getPwd());
			map.put("newPwd",encode);
			result = mService.updatePwd(map);
		}
		if(result > 0) {
			memVo.setPwd(encode);
			model.addAttribute("loginUser",memVo);
			return "redirect:myinfo.me";
		}else {
			throw new MemberException("업데이트 실패");
		}
	}
	@RequestMapping("mPwdUpdate.me")
	public String updatePwd3(@RequestParam("pwd") String oldpwd, @RequestParam("newPwd1") String newPwd, Model model	) {
		
		
	}
	/** 연습 텍스트**/
	// 로그인 정보 가져오기
	// 전 비번과 새 비번 일치하는 지 비교
	// 바뀐 비번 저장하기
	// 바뀐 비번, DB에 업데이트 저장
	// 새 비번 정보를 뷰에 보내기
	
}
