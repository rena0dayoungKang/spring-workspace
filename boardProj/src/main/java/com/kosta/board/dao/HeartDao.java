package com.kosta.board.dao;

public interface HeartDao {
	void insertHeart(String memberId, Integer boardNum) throws Exception;
	Integer selectHeart(String memberId, Integer boardNum) throws Exception;
	void deleteHeart(String memberId, Integer boardNum) throws Exception;
}
