package com.kosta.board.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosta.board.dto.Member;

@Repository
public class MemberDaoImpl implements MemberDao {
	
	@Autowired
	private SqlSession sqlSession;
	//원래는 servlet-context.xml에다가 memberDao 추가해야하지만 클래스 위에 @Repository추가 하는 방법
	//Dao에서 많이 사용. 서블릿컨텍스트에 bean을 memberDao 추가하는 것과 같은 효과이다.
	//아래는 주석처리할 수 있다 
//	public MemberDaoImpl(SqlSession sqlSession) {
//		this.sqlSession = sqlSession;
//	}
	
	
	@Override
	public void insertMember(Member member) throws Exception {
		Member smember = selectMember(member.getId());
		if(smember!=null) throw new Exception("아이디 중복 오류");
		sqlSession.insert("mapper.member.insertMember", member);
		//sqlSession.commit();
	}

	@Override
	public Member selectMember(String id) throws Exception {
		return sqlSession.selectOne("mapper.member.selectMember",id);
	}

	@Override
	public void updateMember(Member member) throws Exception {
		sqlSession.update("mapper.member.updateMember", member);
		//sqlSession.commit();
	}
}