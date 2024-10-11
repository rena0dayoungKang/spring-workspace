package com.kosta.fac.service;

import java.util.List;

import com.kosta.fac.dto.Facility;

public interface FacilityService {
	List<Facility> facList() throws Exception;
	Facility getFacility(String id) throws Exception;
	void modifyFacility(Facility facility) throws Exception;
}
