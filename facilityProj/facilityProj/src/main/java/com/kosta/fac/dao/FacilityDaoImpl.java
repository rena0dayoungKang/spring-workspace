package com.kosta.fac.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosta.fac.dto.Facility;

@Repository
public class FacilityDaoImpl implements FacilityDao {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<Facility> selectFacList() throws Exception {
		return sqlSession.selectList("mapper.facility.selectFacList");
	}

	@Override
	public void updateFacility(Facility facility) throws Exception {
		sqlSession.update("mapper.facility.updateFacility", facility);
	}

	@Override
	public Facility selectFacility(String id) throws Exception {
		Facility facility = sqlSession.selectOne("mapper.facility.selectFacility", id);
		return facility;
	}
	
}
