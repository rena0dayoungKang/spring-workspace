package com.kosta.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.shop.dao.MemberDao;
import com.kosta.shop.dto.Member;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDao memberDao;

	@Override
	public Member login(String userid, String passwd) throws Exception {
		Member member = memberDao.selectMember(userid);
		
		if (member == null) {
			throw new Exception("아이디 오류");
		}
		if (!passwd.equals(member.getPasswd())) {
			throw new Exception("비밀번호 오류");
		}
		return member;
	}

	@Override
	public void join(Member member) throws Exception {
		memberDao.insertMember(member);		
	}

	@Override
	public boolean checkDoubleId(String userid) throws Exception {
		Member member = memberDao.selectMember(userid);
		if (member == null) return false;
		return true;
	}

}
