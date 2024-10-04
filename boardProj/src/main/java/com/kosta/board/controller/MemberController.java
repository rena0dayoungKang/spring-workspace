package com.kosta.board.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kosta.board.dto.Member;
import com.kosta.board.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private HttpSession session; //session에다가 member를 저장하기 위함 

	@RequestMapping(value="join", method = RequestMethod.GET)
	public String join() {
		return "join";
	}
	
	//join 방식1
//	@RequestMapping(value="join", method = RequestMethod.POST)
//	public ModelAndView join(Member member) {
//		ModelAndView mav = new ModelAndView();
//		try {
//			memberService.join(member);
//			mav.addObject(member);
//			mav.setViewName("makeaccount");
//		} catch (Exception e) {
//			mav.addObject("err", e.getMessage());
//			mav.setViewName("err");
//		}
//		return mav;
//	}
	
	//join 방식2
	@RequestMapping(value="join", method = RequestMethod.POST)
	public String join(Member member, Model model) {
		try {
			memberService.join(member);
			return "login";
		} catch (Exception e) {
			model.addAttribute("err", e.getMessage());
			return "err";
		}
		
	}

	@RequestMapping(value="memberDoubleId", method = RequestMethod.POST)
	@ResponseBody
	public String memberDoubleId(@RequestParam("id") String id) {
		try {
			boolean check = memberService.checkDoubleId(id);
			return String.valueOf(check); 
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	@RequestMapping(value="login", method=RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	//login 방식1
//	@RequestMapping(value="login", method=RequestMethod.POST)
//	public ModelAndView login(@RequestParam("id") String id, @RequestParam("password") String password) {
//		ModelAndView mav = new ModelAndView();
//		try {
//			Member member = memberService.login(id, password);
//			mav.addObject("member", member);
//			mav.setViewName("login");
//		} catch(Exception e) {
//			mav.addObject("err", e.getMessage());
//			mav.setViewName("err");
//		}
//		return mav;
//	}
	
	//login 방식2
	@RequestMapping(value="login", method=RequestMethod.POST)
	public String login(@RequestParam("id") String id, @RequestParam("password") String password, @RequestParam(name="type", required=false) String autoLogin, Model model) {
		try {
			Member member = memberService.login(id, password);
			member.setPassword("");
			//System.out.println(member);
			session.setAttribute("member", member); //member 정보를 session에 저장 
			return "redirect:boardList"; //컨트롤러의 boardList를 다시 요청해야 다시 조회해서 리스트를 조회할 수 있음.
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("err", e.getMessage());
			return "err";
		}
	}
	
	@RequestMapping("logout") 
	public String logout() {
		session.removeAttribute("member");
		return "login";
	}
}
