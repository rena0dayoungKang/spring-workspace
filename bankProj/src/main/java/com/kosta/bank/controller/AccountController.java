package com.kosta.bank.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kosta.bank.dto.Account;
import com.kosta.bank.service.AccountService;

@Controller
public class AccountController {

	@Autowired
	private AccountService accountService;

	@RequestMapping(value = "accountInfo", method = RequestMethod.GET)
	public String accountInfo() {
		return "accountinfoform"; // �Ķ���� ���� �׳� view�� �������ָ� ��
	}

	@RequestMapping(value = "accountInfo", method = RequestMethod.POST)
	public String accountInfo(@RequestParam("id") String id, Model model) {
		try {
			Account acc = accountService.accountinfo(id); // �Ķ���ͷ� ������ id�� accInfo��ȸ
			model.addAttribute("acc", acc); // ��ȸ�� ������ model�� ��Ƽ� acc���·� ���
			return "accountinfo"; // acc���·� ���� ������ accountinfo ��� view�� ����
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("err", "������ȸ ����");
			return "err";
		}
	}

	@RequestMapping(value = "deposit", method = RequestMethod.GET)
	public String deposit() {
		return "deposit"; // view�� �ٲٴ� �޼ҵ�
	}

	@RequestMapping(value = "deposit", method = RequestMethod.POST)
	public ModelAndView deposit(@RequestParam("id") String id, @RequestParam("money") Integer money) {
		ModelAndView mav = new ModelAndView();
		try {
			Account acc = accountService.deposit(id, money);
			mav.addObject("acc", acc);
			mav.setViewName("accountinfo");
		} catch (Exception e) {
			mav.addObject("err", e.getMessage());
			mav.setViewName("err");
		}
		return mav;
	}

	@RequestMapping(value = "withdraw", method = RequestMethod.GET)
	public String withdraw() {
		return "withdraw";
	}

	// ���1
//	@RequestMapping(value = "withdraw", method = RequestMethod.POST)
//	public ModelAndView withdraw(@RequestParam("id") String id, @RequestParam("money") Integer money) {
//		ModelAndView mav = new ModelAndView();
//		try {
//			Account acc = accountService.withdraw(id, money);
//			mav.addObject("acc", acc);
//			mav.setViewName("accountinfo");
//		} catch (Exception e) {
//			mav.addObject("err", e.getMessage());
//			mav.setViewName("err");
//		}
//		return mav;
//	}

	// ���2 Map�̿� 
//	@RequestMapping(value = "withdraw", method = RequestMethod.POST)
//	public String withdraw(@RequestParam Map<String, String> param, Model model) {
//		try {
//			String id = param.get("id");
//			Integer money = Integer.parseInt(param.get("money"));
//			Account acc = accountService.withdraw(id, money);
//			model.addAttribute("acc", acc);
//			return "accountinfo";
//		} catch (Exception e) {
//			model.addAttribute("err", e.getMessage());
//			return "err";
//		}
//	}
	
	//���3 HttpServletRequest�̿�
	@RequestMapping(value = "withdraw", method = RequestMethod.POST)
	public String withdraw(HttpServletRequest request, Model model) {
		try {
			String id = request.getParameter("id");
			Integer money = Integer.parseInt(request.getParameter("money"));
			Account acc = accountService.withdraw(id, money);
			model.addAttribute("acc", acc);
			return "accountinfo";
		} catch (Exception e) {
			model.addAttribute("err", e.getMessage());
			return "err";
		}
	}
	
	@RequestMapping(value="makeAccount", method=RequestMethod.GET)
	public String makeAccount() {
		return "makeaccount";
	}
	
	//param���� �޾� ���� �ʰ� ��ü ���·� �޾ƿ��� ����� ����ߴ� 
	//��, Account dto��ü�� ���� �̸��� makeaccount �� form �� �ִ� value�� ���ƾ� �Ѵ� 
	@RequestMapping (value="makeAccount", method=RequestMethod.POST) 
	public ModelAndView makeAccount(Account acc) {
		ModelAndView mav = new ModelAndView();
		try {
			Account sacc = accountService.makeAccount(acc);
			mav.addObject("acc", sacc);
			mav.setViewName("accountinfo");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("err", e.getMessage());
			mav.setViewName("err");
		}
		return mav;
	}
	
	
	@RequestMapping("allAccountInfo") //�̷��� ���� �⺻�� GET��� , header.jsp�� �ִ� href�̸��� ���� 
	public ModelAndView allAccountinfo() {
		ModelAndView mav = new ModelAndView();
		try {
			List<Account> accs = accountService.allacountinfo();
			mav.addObject("accs", accs);
			mav.setViewName("allaccountinfo");
		} catch (Exception e) {
			mav.addObject("err", e.getMessage());
			mav.setViewName("err");
		}
		return mav;
	}
	
	
	@RequestMapping(value="transfer", method=RequestMethod.GET)
	public String transfer() {
		return "transfer";
	}
	
	@RequestMapping(value="transfer", method=RequestMethod.POST)
	public ModelAndView transfer(@RequestParam("sid") String sid, @RequestParam("rid") String rid, @RequestParam("money") Integer money) {
		ModelAndView mav = new ModelAndView();
		try {
			accountService.transfer(sid, rid, money);
			Account acc = accountService.accountinfo(sid);
			mav.addObject("acc", acc);
			mav.setViewName("accountinfo");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	//�񵿱��� ������ �ٶ�
	@RequestMapping(value="accountDoubleId", method=RequestMethod.POST) //makeaccount ajax url
	@ResponseBody //return�� �ִ� �ְ� view�� �ƴ� data�� �ǹ���
	public String accDoubleId(@RequestParam("id") String id) {
		try {
			boolean check = accountService.doubleId(id);
			return String.valueOf(check);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

}
