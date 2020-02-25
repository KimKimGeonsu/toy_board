package com.test.geonsu.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.geonsu.domain.MemberVO;
import com.test.geonsu.service.MemberService;

@RequestMapping("/member/*")
@Controller
public class MemberController {
	
	@Inject
	MemberService service;
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	//회원가입 get
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void getRegister() {
		logger.info("get 회원가입");
	}
	
	//회원가입 post
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String postRegister(MemberVO vo) {		
		logger.info("Post 회원가입");
		service.register(vo);
		return "redirect:/";
	}
	
	
	//로그인
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(MemberVO vo, HttpServletRequest req, RedirectAttributes rttr) {
		
		logger.info("로그인 POST");
		
		HttpSession session = req.getSession();
		MemberVO login =service.login(vo);		
		
		System.out.println(login);
		if(login ==null) {
			session.setAttribute("member", null);
			rttr.addFlashAttribute("msg", false);
		}else {			
			session.setAttribute("member", login);
		}		
		
		return "redirect:/";
	}
	
	//로그아웃
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		logger.info("로그아웃");
		session.invalidate();
		
		return "redirect:/";
	}
	
	//회원정보수정
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void getModify() {
		logger.info("회원정보수정");
		
		
	}
	
	//회원정보수정 post
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String postModify(HttpSession session, MemberVO vo) {
		logger.info("post 회원정보수정");
		service.modify(vo);
		
		session.invalidate();
		
		return "redirect:/";
	}
	
	//회원탈퇴
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public void getdelete() {
		logger.info("회원탈퇴");
	}
	
	//회원탈퇴 Post
	@RequestMapping(value = "/delete", method =  RequestMethod.POST)
	public String postDelete(HttpSession session, MemberVO vo, RedirectAttributes rttr) {
		logger.info("POST 회원탈퇴");
		
		MemberVO member = (MemberVO)session.getAttribute("member");
		
		String oldPass = member.getUserPass();
		String newPass = vo.getUserPass();
		
		if(!(oldPass.equals(newPass))) {
			rttr.addFlashAttribute("msg",false);
			return "redirect:delete";
			
		}
		session.invalidate();
		service.delete(vo);
		return "redirect:/";
		
	}
	
	//아이디확인
	@ResponseBody
	@RequestMapping(value = "/idCheck",method = RequestMethod.POST)
	public int postIdcheck(HttpServletRequest req) {
		logger.info("아이디확인");
		
		String userId = req.getParameter("userId");
		MemberVO idCheck = service.idCheck(userId);
		
		int result =0;
		
		if(idCheck !=null) {
			result =1;
		}
		
		return result;
		
	}
	
}


