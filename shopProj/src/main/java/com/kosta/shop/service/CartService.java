package com.kosta.shop.service;

import java.util.List;

import com.kosta.shop.dto.Cart;

public interface CartService {
	void addCart(Cart cart) throws Exception;
	List<Cart> cartList(String userid) throws Exception;
	void cartDeleteAll(List<Integer> list) throws Exception;
	void cartDelete(Integer num) throws Exception;
	void cartUpdate(Integer num, Integer gAmount) throws Exception; 
	Cart cartRetrieve(Integer num) throws Exception;
	List<Cart> orderAllConfirm(List<Integer> list) throws Exception;
}
