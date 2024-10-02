package com.kosta.bank.service;

import com.kosta.bank.dao.MemberDao;
import com.kosta.bank.dao.MemberDaoImpl;
import com.kosta.bank.dto.Member;

public class MemberServiceImpl implements MemberService {
	
	private MemberDao memberDao;
	public MemberServiceImpl(MemberDao memberDAO) {
		this.memberDao = memberDao;
	}

   @Override
   public void join(Member member) throws Exception {
      
      MemberDao memberdao = new MemberDaoImpl();
      Member smember = new Member();
      smember=memberdao.selectMember(member.getId());
      
      if(smember == null) {
         memberdao.insertMember(member);
      }else {
         throw new Exception("계정 중복");
      }

   }

   @Override
   public Member login(String id, String password) throws Exception {
      MemberDao memberdao = new MemberDaoImpl(); 
      Member lmember = memberdao.selectMember(id); 
      
      if(lmember == null) throw new Exception("아이디 오류");
      if(!password.equals(lmember.getPassword())) throw new Exception("패스워드 오류"); 
      return lmember;
      
   }
   
   @Override
   public boolean checkDoubleId(String id) throws Exception {
      MemberDao memberdao = new MemberDaoImpl();
      Member member = memberdao.selectMember(id);
      if(member==null) return false;
      
      return true;
   }

}
