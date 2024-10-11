package com.kosta.fac.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kosta.fac.dto.Employee;
import com.kosta.fac.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;
	@Autowired
	private HttpSession session;
	
	@RequestMapping("/member/signUp")
	public String signUp() {
		return "signUp";
	}

	@RequestMapping(value = "/member/signUp", method = RequestMethod.POST)
	public String signUp(Employee employee, Model model) {
		try {
			memberService.signUp(employee);
			session.setAttribute("user", employee);
			return "index";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("err", e.getMessage());
			return "error";
		}
	}
	
	@RequestMapping(value="/member/login", method=RequestMethod.POST)
	public String login(@RequestParam("empNo") String empNo, @RequestParam("empPw") String empPw) {
		try {
			Employee emp = memberService.login(empNo, empPw);
			session.setAttribute("empNo", emp.getEmpNo());
			session.setAttribute("empNm", emp.getEmpNm());
			return "redirect:/list";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

}
