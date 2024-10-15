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
		return "accountinfoform"; // 파라미터 없이 그냥 view만 전달해주면 됨
	}

	@RequestMapping(value = "accountInfo", method = RequestMethod.POST)
	public String accountInfo(@RequestParam("id") String id, Model model) {
		try {
			Account acc = accountService.accountinfo(id); // 파라미터로 가져온 id로 accInfo조회
			model.addAttribute("acc", acc); // 조회된 정보를 model에 담아서 acc형태로 담기
			return "accountinfo"; // acc형태로 담은 정보를 accountinfo 라는 view로 전달
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("err", "계좌조회 오류");
			return "err";
		}
	}

	@RequestMapping(value = "deposit", method = RequestMethod.GET)
	public String deposit() {
		return "deposit"; // view만 바꾸는 메소드
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

	// 방식1
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

	// 방식2 Map이용 
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
	
	//방식3 HttpServletRequest이용
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
	
	//param으로 받아 오지 않고 객체 형태로 받아오는 방법을 사용했다 
	//단, Account dto객체의 변수 이름과 makeaccount 의 form 에 있는 value가 같아야 한다 
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
	
	
	@RequestMapping("allAccountInfo") //이렇게 쓰면 기본이 GET방식 , header.jsp에 있는 href이름과 같음 
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
	
	//비동기방식 데이터 줄때
	@RequestMapping(value="accountDoubleId", method=RequestMethod.POST) //makeaccount ajax url
	@ResponseBody //return을 주는 애가 view가 아닌 data를 의미함
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
