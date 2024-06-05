package mclass.store.tripant.plan.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletResponse;
import mclass.store.tripant.place.domain.AreaEntity;
import mclass.store.tripant.place.domain.AreaNameEntity;
import mclass.store.tripant.plan.model.service.PlaceService;
import mclass.store.tripant.plan.model.service.PlanService;
import mclass.store.tripant.plan.model.service.TimeService;

@Controller
public class PlanController {
	@Autowired
	private PlanService planService;
	@Autowired
	private PlaceService placeService;
	@Autowired
	private TimeService timeService;
	
	/*
	 * @GetMapping("/sample") public String sample(Model model) {
	 * //model.addAttribute();
	 * 
	 * return "sample_layout"; }
	 */
	
	@GetMapping("/")
	public String home(Principal principal, Authentication authentication, Model model) {
		System.out.println("principal = "+principal);
		System.out.println("auth = "+authentication);
		
		model.addAttribute("planCount", planService.selectPlanCount());
		model.addAttribute("memCount", planService.selectMemCount());
		
		model.addAttribute("areaNameList", planService.selectAreaNameList());
		
		//System.out.println("insertPlace : " + placeService.insertPlace());
		//System.out.println("timeService : " + timeService.deleteAllPlaceMoveTime());
		//System.out.println("selectPlaceMapList : " + timeService.selectPlaceMapList(1));
		//timeService.selectPlaceMapList(1);
		//timeService.makeTimeList(); //TODO
		
		return "plan/main/home";
	}
	
	//지역 리스트 검색
	@PostMapping("/find/area")
	@ResponseBody
	public List<AreaNameEntity> find(@RequestParam("findArea") String findArea) {
		System.out.println("findArea :" + findArea);
		List<AreaNameEntity> areaList = planService.selectAreaFindList(findArea);
		System.out.println(areaList);
		return areaList;
	}
	
	//지역&제목 모달에서 선택한 지역 정보 가져오기
	@PostMapping("/make/area")
	@ResponseBody
	public List<AreaEntity> makeAreaInfo(@RequestParam("areaName") String areaName) {
		System.out.println("areaName :" + areaName);
		List<AreaEntity> areaList = planService.selectAreaInfoList(areaName);
		System.out.println(areaList);
		return areaList;
	}
	
	@PostMapping("/make/keep")
	@ResponseBody
	public String makeAreaKeep(HttpServletResponse response, 
			@RequestParam("areaName") String areaName, 
			@RequestParam("planTitle") String planTitle
			, Model model) throws IOException {
//		System.out.println("areaName :" + areaName);
//		System.out.println("planTitle :" + planTitle);
		model.addAttribute("areaName", areaName);
		model.addAttribute("planTitle", planTitle);
		
		return "/make";
	}
	
	@GetMapping("/make")
	public String make(Principal principal, Authentication authentication, Model model) {
		System.out.println(model.getAttribute("areaName"));
		
		return "plan/make/basic";
	}
}
