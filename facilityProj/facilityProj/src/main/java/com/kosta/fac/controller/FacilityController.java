package com.kosta.fac.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kosta.fac.dto.Facility;
import com.kosta.fac.service.FacilityService;
import com.kosta.fac.service.TeamBiz;

@Controller
public class FacilityController {
	
	@Autowired
	private FacilityService facilityService;
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView();
		try {
			List<Facility> facilityList = facilityService.facList();
			mav.addObject("teamBudget", new TeamBiz().selectTeamBudget());
			mav.addObject("facilityList", facilityList);
			//System.out.println(facilityList);
			mav.setViewName("list");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("err", "시설목록 조회 오류");
			mav.setViewName("error");
		}
		return mav;
	}
	
	@RequestMapping("/update")
	public ModelAndView modify(@RequestParam("id") String id) {
		ModelAndView mav = new ModelAndView();
		try {
			Facility facility = facilityService.getFacility(id);
			mav.addObject("facility", facility);
			mav.setViewName("update");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("err", "시설수정 오류");
			mav.setViewName("error");
		}
		return mav;
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	//@ModelAttribute("facility")
	public String update(@ModelAttribute Facility facility, Model model) {
		try {
			facilityService.modifyFacility(facility);
			model.addAttribute("facility", facilityService.getFacility(facility.getId()));
			return "update";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("err", "시설 정보 수정 오류");
			return "error";
		}
	}
}
