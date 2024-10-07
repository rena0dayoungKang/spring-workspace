package com.kosta.board.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HeartDaoImpl implements HeartDao {
	@Autowired
	private SqlSession sqlSession;
	
//	public HeartDaoImpl(SqlSession sqlSession) {
//		//sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();
//		this.sqlSession = sqlSession;
//	}

	@Override
	public void insertHeart(String memberId, Integer boardNum) throws Exception {
		Map<String,Object> param = new HashMap<>();
		param.put("mem_id", memberId);
		param.put("board_num", boardNum);
		sqlSession.insert("mapper.heart.insertHeart",param);
		//sqlSession.commit();
	}

	@Override
	public Integer selectHeart(String memberId, Integer boardNum) throws Exception {
		Map<String,Object> param = new HashMap<>();
		param.put("mem_id", memberId);
		param.put("board_num", boardNum);
		return sqlSession.selectOne("mapper.heart.selectHeart",param);
	}

	@Override
	public void deleteHeart(String memberId, Integer boardNum) throws Exception {
		Map<String,Object> param = new HashMap<>();
		param.put("mem_id", memberId);
		param.put("board_num", boardNum);
		sqlSession.delete("mapper.heart.deleteHeart",param);
		//sqlSession.commit();
	}
}
