package com.kosta.shop.dao;

import com.kosta.shop.dto.Member;

public interface MemberDao {
	Member selectMember(String userid) throws Exception;
	void insertMember(Member member) throws Exception;

}
