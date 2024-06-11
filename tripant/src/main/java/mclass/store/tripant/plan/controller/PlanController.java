package mclass.store.tripant.plan.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import mclass.store.tripant.place.domain.AreaEntity;
import mclass.store.tripant.place.domain.AreaNameEntity;
import mclass.store.tripant.plan.model.service.PlaceService;
import mclass.store.tripant.plan.model.service.PlanService;
import mclass.store.tripant.plan.model.service.TimeService;

@Controller
@Slf4j  //private Logger log = LoggerFactory.getLogger(PlanController.class); 내용을 대신해줌
public class PlanController {
	//private Logger log = LoggerFactory.getLogger(PlanController.class);
	
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
	public ModelAndView home(Principal principal, Authentication authentication, ModelAndView mv) {
		log.trace("principal = "+principal);
		log.debug("auth = "+authentication);
		mv.addObject("principal", principal);
		mv.addObject("auth", authentication);
		//model 대신 ModelAndView를 사용함
		mv.addObject("planCount", planService.selectPlanCount());
		mv.addObject("memCount", planService.selectMemCount());
		
		mv.addObject("areaNameList", planService.selectAreaNameList());
		
		mv.setViewName("plan/home");
		
		//System.out.println("insertPlace : " + placeService.insertPlace());
		//System.out.println("selectPlaceMapList : " + timeService.selectPlaceMapList(1));
		//timeService.makeTimeList(); //TODO
		
		return mv;
	}
	
	//지역 리스트 검색
	@PostMapping("/find/area")
	@ResponseBody
	public List<AreaNameEntity> findArea(@RequestParam("findArea") String findArea) {
		System.out.println("findArea :" + findArea);
		List<AreaNameEntity> areaList = planService.selectAreaFindList(findArea);
		System.out.println(areaList);
		return areaList;
	}
	
	//지역&제목 모달에서 선택한 지역 정보 가져오기
	@PostMapping("/make/area")
	@ResponseBody
	public List<AreaEntity> makeArea(@RequestParam("areaName") String areaName) {
		System.out.println("areaName :" + areaName);
		List<AreaEntity> areaList = planService.selectAreaInfoList(areaName);
		System.out.println(areaList);
		return areaList;
	}
	
	@PostMapping("/make/keep")
	public String makeKeep(@RequestParam("areaName") String areaName, 
			@RequestParam("planTitle") String planTitle
			, RedirectAttributes rttb
			) throws IOException {
//		System.out.println("areaName :" + areaName);
//		System.out.println("planTitle :" + planTitle);
		
		rttb.addFlashAttribute("areaName", areaName);
		rttb.addFlashAttribute("planTitle", planTitle);
		//짧은 이름으로 넘기기 
		String areaShortName = planService.selectAreaShortName(areaName);
		rttb.addFlashAttribute("areaShortName", areaShortName);
		return "redirect:/make";
	}
	
	@GetMapping("/make")
	public String make(
			//@ModelAttribute("areaName") String areaName, @ModelAttribute("planTitle") String planTitle
			) {
//		System.out.println("areaName :" + areaName);
//		System.out.println("planTitle :" + planTitle);
//		
		return "plan/make";
	}
}
