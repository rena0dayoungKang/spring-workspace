package com.kosta.shop.service;

import com.kosta.shop.dto.Member;

public interface MemberService {
	Member login(String userid, String passwd) throws Exception;
	void join(Member member) throws Exception;
	boolean checkDoubleId(String userid) throws Exception;

}
