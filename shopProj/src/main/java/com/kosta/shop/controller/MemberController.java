package com.kosta.shop.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kosta.shop.dto.Member;
import com.kosta.shop.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;
	@Autowired
	private HttpSession session;

	@RequestMapping("/index")
	public String index() {
		return "index";
	}

	@RequestMapping(value = "signUp", method = RequestMethod.GET)
	public String signUp() {
		return "signUpForm";
	}

	@RequestMapping(value = "signUp", method = RequestMethod.POST)
	public String signUp(Member member, Model model) {
		try {
			memberService.join(member);
			return "index";
		} catch (Exception e) {
			model.addAttribute("err", e.getMessage());
			return "err";
		}
	}

	@RequestMapping(value = "idCheck", method = RequestMethod.GET)
	@ResponseBody
	public String memberDoubleId(@RequestParam("id") String userid) {
		try {
			boolean check = memberService.checkDoubleId(userid);
			if (!check) {
				return "사용가능";
			} else {
				return "사용 불가능";
			}

		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(@RequestParam("userid") String userid, @RequestParam("passwd") String passwd, Model model) {
		try {
			Member member = memberService.login(userid, passwd);
			session.setAttribute("user", member);
			return "shopMain";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("err", e.getMessage());
			return "err";
		}
	}

}
