package com.kosta.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosta.board.dto.Board;

@Repository
public class BoardDaoImpl implements BoardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
//	public BoardDaoImpl(SqlSession sqlSession) {
//		//sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();
//		this.sqlSession = sqlSession;
//	}

	@Override
	public void insertBoard(Board board) throws Exception {
		sqlSession.insert("mapper.board.insertBoard", board);
		//sqlSession.commit();
	}

	@Override
	public Board selectBoard(Integer num) throws Exception {
		return sqlSession.selectOne("mapper.board.selectBoard", num);
	}

	@Override
	public void updateBoard(Board board) throws Exception {
		sqlSession.update("mapper.board.updateBoard", board);
		//sqlSession.commit();
	}

	@Override
	public List<Board> selectBoardList(Integer row) throws Exception {
		return sqlSession.selectList("mapper.board.selectBoardList", row-1);
	}

	@Override
	public void updateViewCnt(Integer num) throws Exception {
		sqlSession.update("mapper.board.updateViewCnt", num);
		//sqlSession.commit();
	}

	@Override
	public Integer selectBoardCount() throws Exception {
		return sqlSession.selectOne("mapper.board.selectBoardCount");
	}
}
