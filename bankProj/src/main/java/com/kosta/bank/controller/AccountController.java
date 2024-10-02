package com.kosta.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kosta.bank.dto.Account;
import com.kosta.bank.service.AccountService;

@Controller
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@RequestMapping(value="accountInfo", method=RequestMethod.GET)
	public String accountInfo() {
		return "accountinfoform";
	}
	
	@RequestMapping(value="accountInfo", method=RequestMethod.POST)
	public String accountInfo(@RequestParam("id") String id, Model model) {
		try {
			Account acc = accountService.accountinfo(id);
			model.addAttribute("acc", acc);
			return "accountinfo";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("err", "������ȸ ����");
			return "err";
		}
		
	}
}
