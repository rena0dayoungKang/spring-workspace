package com.kosta.shop.service;

import com.kosta.shop.dto.Member;

public interface MemberService {
	Member login(String userid, String passwd) throws Exception;
	void signUp(Member member) throws Exception;
	boolean idCheck(String userid) throws Exception;

}
