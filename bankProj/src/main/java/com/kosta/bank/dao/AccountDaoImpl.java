package com.kosta.bank.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.kosta.bank.dto.Account;

public class AccountDaoImpl implements AccountDao {
	private SqlSessionTemplate sqlSession;
	
	
	public AccountDaoImpl(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public void insertAccount(Account acc) throws Exception {
		sqlSession.insert("mapper.account.insertAccount",acc);
		sqlSession.commit();

	}

	@Override
	public Account selectAccount(String id) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("mapper.account.selectAccount",id);
	}

	@Override
	public void updateBalance(Account acc) throws Exception {
		
		sqlSession.update("mapper.account.updateBalance",acc);
		sqlSession.commit();
	}

	@Override
	public List<Account> selectAllAccount() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("mapper.account.selectAllAccount");
	}

}
