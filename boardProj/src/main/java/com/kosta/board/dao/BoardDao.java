package com.kosta.board.dao;

import java.util.List;

import com.kosta.board.dto.Board;


public interface BoardDao {
	void insertBoard(Board board) throws Exception;
	Board selectBoard(Integer num) throws Exception;
	void updateBoard(Board board) throws Exception;
	void updateViewCnt(Integer num) throws Exception;
	Integer selectBoardCount() throws Exception;
	List<Board> selectBoardList(Integer row) throws Exception;
}
