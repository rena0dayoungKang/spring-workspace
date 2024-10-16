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
import com.kosta.shop.dto.Order;
import com.kosta.shop.dto.OrderInfo;
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
			@RequestParam(name = "cart", required = false, defaultValue = "N") String cart) {
		ModelAndView mav = new ModelAndView();
		try {
			Goods goods = goodsService.goodsRetrieve(gCode);
			mav.addObject("item", goods);
			mav.addObject("cart", cart);
			// <input type="hidden" name="gImage" value="${item.gImage}">
			mav.setViewName("goodsRetrieve");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("action", "상품 상세 조회");
			mav.addObject("message", "상품 상세 조회 실패");
			mav.setViewName("memberResult");
		}
		return mav;
	}

	@RequestMapping("/orderConfirm")
	// gImage=top1&gCode=T1&gName=앨리스+데님+탑&gPrice=12100 --> Goods dto에 있음
	// &gSize=L&gColor=navy&gAmount=1 --> 내가 선택한 값
	public ModelAndView orderConfirm(Goods goods, @RequestParam("gSize") String gSize,
			@RequestParam("gColor") String gColor, @RequestParam("gAmount") Integer gAmount) {
		ModelAndView mav = new ModelAndView("orderConfirm");
		// gDTO :상품정보
		mav.addObject("gDTO", goods);
		// gSize
		mav.addObject("gSize", gSize);
		// gColor
		mav.addObject("gColor", gColor);
		// gAmount
		mav.addObject("gAmount", gAmount);
		return mav;
	}

	@RequestMapping("/addCart")
	// addCart?
	// gImage=top10&gCode=T10&gName=홀+포켓+보이+프렌드+셔츠&gPrice=27800
	// &gSize=L&gColor=navy&gAmount=1
	public ModelAndView addCart(Cart cart) {
		ModelAndView mav = new ModelAndView();
		try {
			Member member = (Member) session.getAttribute("user");
			cart.setUserid(member.getUserid());
			cartService.addCart(cart);
			mav.setViewName("redirect:goodsRetrieve?gCode=" + cart.getgCode() + "&cart=Y");
			// 상품을 장바구니에 담고나서 다시 상세보기 화면으로 redirect
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("action", "장바구니 담기"); // addObject : key와 value를 담아 보낼 수 있는 메서드
			mav.addObject("message", "장바구니 담기 실패");
			mav.setViewName("memberResult");
		}
		return mav;
	}

	@RequestMapping("/cartList")
	@ModelAttribute("cartList")
	// 많이 쓰는 방법. (Model : 데이터)
	// 내가 응답으로 주는 애가 cartList라는 모델에 담아준다
	// setViewName 대신에 <c:if test="${empty cartList}"> 에서의 cartList 이름과 같아야함
	public List<Cart> cartList() {
		List<Cart> cartList = null;
		try {
			Member member = (Member) session.getAttribute("user");
			cartList = cartService.cartList(member.getUserid());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cartList;
	}

	// 장바구니에서 선택삭제 기능
	// CartDelAll?cartAmount=1&cartAmount=1&cartAmount=1&check=15&cartAmount=1
	@RequestMapping("/CartDelAll")
	public String cartDelAll(@RequestParam("check") Integer[] num) {
		try {
			cartService.cartDeleteAll(Arrays.asList(num));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/cartList";
	}

	// 장바구니에서 하나씩 삭제버튼
	@ResponseBody
	@RequestMapping("/cartDelete")
	public void cartDelete(@RequestParam("num") Integer num) {
		try {
			cartService.cartDelete(num);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 장바구니에서 수량 수정
	@ResponseBody
	@RequestMapping("/cartUpdate")
	public void cartUpdate(@RequestParam("num") Integer num, @RequestParam("gAmount") Integer gAmount) {
		try {
			cartService.cartUpdate(num, gAmount);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// cartOrderConfirm?num=15
	// 장바구니에서 바로 주문버튼 클릭
	@RequestMapping("/cartOrderConfirm")
	public ModelAndView cartOrderConfirm(@RequestParam("num") Integer num) {
		ModelAndView mav = new ModelAndView();
		try {
			Cart cart = cartService.cartRetrieve(num);
			mav.addObject("cDTO", cart); // <c:if test="${not empty cDTO}">
			mav.setViewName("orderConfirm");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("action", "주문확인");
			mav.addObject("message", "주문확인 실패");
			mav.setViewName("memberResult");
		}
		return mav;
	}

	// cartOrderAllConfirm?allCheck=on&check=15&cartAmount=1&check=16&cartAmount=6&check=17&cartAmount=1
	// 장바구니에서 선택 주문
	@RequestMapping("/cartOrderAllConfirm")
	public ModelAndView cartOrderAllConfirm(@RequestParam("check") Integer[] num) {
		ModelAndView mav = new ModelAndView();
		try {
			List<Cart> cartList = cartService.orderAllConfirm(Arrays.asList(num));
			mav.addObject("cartList", cartList);
			mav.setViewName("orderAllConfirm");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("action", "주문확인");
			mav.addObject("message", "주문확인 실패");
			mav.setViewName("memberResult");
		}
		return mav;
	}

	// orderDone
	@RequestMapping("/cartOrderDone")
	public ModelAndView cartOrderDone(@ModelAttribute OrderInfo orderInfo, @ModelAttribute Order order,
			@RequestParam(name = "orderNum", required = false) Integer orderNum) {
		ModelAndView mav = new ModelAndView();
		try {
			Member member = (Member) session.getAttribute("user");
			orderInfo.setUserid(member.getUserid());
			order.setUserid(member.getUserid());
			cartService.orderDone(orderInfo, order, orderNum);
			mav.setViewName("orderDone");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("action", "상품결제");
			mav.addObject("message", "상품결제 실패");
			mav.setViewName("memberResult");
		}
		return mav;
	}
	
	
	// orderAllDone
	@RequestMapping("/cartOrderAllDone")
	public ModelAndView cartOrderAllDone(@RequestParam("num") Integer[] nums, @ModelAttribute OrderInfo orderInfo) {
		ModelAndView mav = new ModelAndView();
		try {
			Member member = (Member)session.getAttribute("user");
			orderInfo.setUserid(member.getUserid());
			List<Order> orderList = cartService.orderAllDone(orderInfo, Arrays.asList(nums));
			mav.addObject("orderAllDone", orderList);
			mav.setViewName("orderAllDone");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("action", "상품결제");
			mav.addObject("message", "상품결제 실패");
			mav.setViewName("memberResult");
		}		
		return mav;
	}
}
