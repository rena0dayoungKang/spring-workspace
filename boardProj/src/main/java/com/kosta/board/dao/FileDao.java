package com.kosta.board.dao;

import com.kosta.board.dto.BFile;

public interface FileDao {
	void insertFile(BFile bFile) throws Exception;
	void deleteFile(Integer num) throws Exception;
}
