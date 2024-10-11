package com.kosta.fac.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.fac.dao.FacilityDao;
import com.kosta.fac.dto.Facility;

@Service
public class FacilityServiceImpl implements FacilityService {
	
	@Autowired
	private FacilityDao facilityDao;

	@Override
	public List<Facility> facList() throws Exception {
		return facilityDao.selectFacList();
	}

	@Override
	public void modifyFacility(Facility facility) throws Exception {
		facilityDao.updateFacility(facility);
	}

	@Override
	public Facility getFacility(String id) throws Exception {
		return facilityDao.selectFacility(id);
	}

}
