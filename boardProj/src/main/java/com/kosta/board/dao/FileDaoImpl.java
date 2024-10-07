package com.kosta.board.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosta.board.dto.BFile;

@Repository
public class FileDaoImpl implements FileDao {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void insertFile(BFile bFile) throws Exception {
		sqlSession.insert("mapper.file.insertFile", bFile);

	}

	@Override
	public void deleteFile(Integer num) throws Exception {
		sqlSession.delete("mapper.file.deleteFile", num);
	}

}
