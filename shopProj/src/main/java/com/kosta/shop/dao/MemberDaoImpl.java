package com.kosta.shop.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosta.shop.dto.Member;

@Repository
public class MemberDaoImpl implements MemberDao {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public Member selectMember(String userid) throws Exception {
		return sqlSession.selectOne("mapper.member.selectMember", userid);
	}

	@Override
	public void insertMember(Member member) throws Exception {
		Member smember = selectMember(member.getUserid());
		if (smember != null) throw new Exception("아이디 중복 오류");
		sqlSession.insert("mapper.member.insertMember", member);		
	}

}
