package com.kosta.fac.dao;

import java.util.List;

import com.kosta.fac.dto.Facility;

public interface FacilityDao {
	List<Facility> selectFacList() throws Exception;
	Facility selectFacility(String id) throws Exception;
	void updateFacility(Facility facility) throws Exception;
}
