package com.kosta.shop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.shop.dao.CartDao;
import com.kosta.shop.dto.Cart;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartDao cartDao;

	@Override
	public void addCart(Cart cart) throws Exception {
		cartDao.insertCart(cart);
	}

	@Override
	public List<Cart> cartList(String userid) throws Exception {
		return cartDao.selectCartList(userid);
	}

	@Override
	public void cartDeleteAll(List<Integer> list) throws Exception {
		cartDao.deleteMultiCart(list);		
	}

	@Override
	public void cartDelete(Integer num) throws Exception {
		cartDao.deleteCart(num);		
	}

	@Override
	public void cartUpdate(Integer num, Integer gAmount) throws Exception {
		Map<String, Integer> param = new HashMap<>();
		param.put("num", num);
		param.put("gAmount", gAmount);
		cartDao.updateCart(param);
		
	}

	@Override
	public Cart cartRetrieve(Integer num) throws Exception {
		return cartDao.selectCart(num);
	}

	@Override
	public List<Cart> orderAllConfirm(List<Integer> list) throws Exception {
		return cartDao.selectCheckCart(list);
	}

}
