package com.kosta.shop.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kosta.shop.dto.Cart;
import com.kosta.shop.dto.Goods;
import com.kosta.shop.dto.Member;
import com.kosta.shop.service.CartService;
import com.kosta.shop.service.GoodsService;

@Controller
public class GoodsController {
	
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private CartService cartService;
	@Autowired
	private HttpSession session;
	
	@RequestMapping("/goodsRetrieve")
	public ModelAndView goodsRetrieve(@RequestParam("gCode") String gCode,
			@RequestParam(name="cart", required=false, defaultValue="N") String cart) {
		ModelAndView mav = new ModelAndView();
		try {
			Goods goods = goodsService.goodsRetrieve(gCode);
			mav.addObject("item", goods);
			mav.addObject("cart", cart); 
			//<input type="hidden" name="gImage" value="${item.gImage}">
			mav.setViewName("goodsRetrieve");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("action", "��ǰ �� ��ȸ");
			mav.addObject("message", "��ǰ �� ��ȸ ����");
			mav.setViewName("memberResult");
		}
		return mav;		
	}
	
	@RequestMapping("/orderConfirm")
	//gImage=top1&gCode=T1&gName=�ٸ���+����+ž&gPrice=12100  --> Goods dto�� ����
	//&gSize=L&gColor=navy&gAmount=1 --> ���� ������ ��
	public ModelAndView orderConfirm(Goods goods, @RequestParam("gSize") String gSize, @RequestParam("gColor") String gColor,
			@RequestParam("gAmount") Integer gAmount) {
		ModelAndView mav = new ModelAndView("orderConfirm");
		//gDTO :��ǰ����
		mav.addObject("gDTO", goods);
		//gSize
		mav.addObject("gSize", gSize);
		//gColor
		mav.addObject("gColor", gColor);
		//gAmount
		mav.addObject("gAmount", gAmount);
		return mav;
	}
	
	@RequestMapping("/addCart")
	//addCart?
	//gImage=top10&gCode=T10&gName=Ȧ+����+����+������+����&gPrice=27800
	//&gSize=L&gColor=navy&gAmount=1
	public ModelAndView addCart(Cart cart) {
		ModelAndView mav = new ModelAndView();
		try {
			Member member = (Member)session.getAttribute("user");
			cart.setUserid(member.getUserid());
			cartService.addCart(cart);
			mav.setViewName("redirect:goodsRetrieve?gCode="+cart.getgCode()+"&cart=Y");
			//��ǰ�� ��ٱ��Ͽ� ����� �ٽ� �󼼺��� ȭ������ redirect
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("action", "��ٱ��� ���"); //addObject : key�� value�� ��� ���� �� �ִ� �޼���
			mav.addObject("message", "��ٱ��� ��� ����");
			mav.setViewName("memberResult");
		}
		return mav;
	}
	
	@RequestMapping("/cartList")
	@ModelAttribute("cartList")  
	//���� ���� ���. (Model : ������)
	//���� �������� �ִ� �ְ� cartList��� �𵨿� ����ش�
	//setViewName ��ſ� <c:if test="${empty cartList}"> ������ cartList �̸��� ���ƾ���
	public List<Cart> cartList() {
		List<Cart> cartList = null;
		try {
			Member member = (Member)session.getAttribute("user");
			cartList = cartService.cartList(member.getUserid());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cartList;
	}
	
	//��ٱ��Ͽ��� ���û��� ���
	//CartDelAll?cartAmount=1&cartAmount=1&cartAmount=1&check=15&cartAmount=1
	@RequestMapping("/CartDelAll")
	public String cartDelAll(@RequestParam("check") Integer[] num) {
		try {
			cartService.cartDeleteAll(Arrays.asList(num));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/cartList";
	}
	
	//��ٱ��Ͽ��� �ϳ��� ������ư
	@ResponseBody
	@RequestMapping("/cartDelete")
	public void cartDelete(@RequestParam("num") Integer num) {
		try {
			cartService.cartDelete(num);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//��ٱ��Ͽ��� ���� ���� 
	@ResponseBody
	@RequestMapping("/cartUpdate")
	public void cartUpdate(@RequestParam("num") Integer num, @RequestParam("gAmount") Integer gAmount) {
		try {
			cartService.cartUpdate(num, gAmount);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//cartOrderConfirm?num=15
	//��ٱ��Ͽ��� �ٷ� �ֹ���ư Ŭ��
	@RequestMapping("/cartOrderConfirm")
	public ModelAndView cartOrderConfirm(@RequestParam("num") Integer num) {
		ModelAndView mav = new ModelAndView();
		try {
			Cart cart = cartService.cartRetrieve(num);
			mav.addObject("cDTO", cart);  //<c:if test="${not empty cDTO}"> 
			mav.setViewName("orderConfirm");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("action", "�ֹ�Ȯ��");
			mav.addObject("message", "�ֹ�Ȯ�� ����");
			mav.setViewName("memberResult");
		}
		return mav;
	}
	
	//cartOrderAllConfirm?allCheck=on&check=15&cartAmount=1&check=16&cartAmount=6&check=17&cartAmount=1
	//��ٱ��Ͽ��� ���� �ֹ�
	@RequestMapping("/cartOrderAllConfirm")
	public ModelAndView cartOrderAllConfirm(@RequestParam("check") Integer[] num) {
		ModelAndView mav = new ModelAndView();
		try {
			List<Cart> cartList = cartService.orderAllConfirm(Arrays.asList(num));
			mav.addObject("cartList",cartList);
			mav.setViewName("orderAllConfirm");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("action", "�ֹ�Ȯ��");
			mav.addObject("message", "�ֹ�Ȯ�� ����");
			mav.setViewName("memberResult");
		}
		return mav;
	}
	
	
	
	
	
}
