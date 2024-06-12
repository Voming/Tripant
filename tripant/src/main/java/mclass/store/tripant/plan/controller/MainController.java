package mclass.store.tripant.plan.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;
import mclass.store.tripant.place.domain.AreaNameEntity;
import mclass.store.tripant.plan.model.service.PlaceServiceCrawling;
import mclass.store.tripant.plan.model.service.PlanService;
import mclass.store.tripant.plan.model.service.TimeServiceCrawling;

@Controller
@Slf4j
public class MainController {
	@Autowired
	private PlanService planService;
	@Autowired
	private PlaceServiceCrawling placeService;
	@Autowired
	private TimeServiceCrawling timeService;

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
		
		mv.setViewName("main/home");
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
}
