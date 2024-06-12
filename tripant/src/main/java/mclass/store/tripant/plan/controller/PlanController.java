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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import mclass.store.tripant.place.domain.AreaEntity;
import mclass.store.tripant.place.domain.AreaNameEntity;
import mclass.store.tripant.plan.model.service.PlaceServiceCrawling;
import mclass.store.tripant.plan.model.service.PlanService;
import mclass.store.tripant.plan.model.service.TimeServiceCrawling;

@Controller
@RequestMapping(value = "/plan")
//@Slf4j  //private Logger log = LoggerFactory.getLogger(PlanController.class); 내용을 대신해줌
public class PlanController {
	//private Logger log = LoggerFactory.getLogger(PlanController.class);
	
	@Autowired
	private PlanService planService;
	
	@GetMapping("")
	public String make(
			//@ModelAttribute("areaName") String areaName, @ModelAttribute("planTitle") String planTitle
			) {
//		System.out.println("areaName :" + areaName);
//		System.out.println("planTitle :" + planTitle);
//		
		return "/plan/make";
	}
	
	//지역&제목 모달에서 선택한 지역 정보 가져오기
	@PostMapping("/area")
	@ResponseBody
	public List<AreaEntity> area(@RequestParam("areaName") String areaName) {
		//System.out.println("areaName :" + areaName);
		List<AreaEntity> areaList = planService.selectAreaInfoList(areaName);
		//System.out.println(areaList);
		return areaList;
	}
	
	@PostMapping("/keep")
	public String keep(@RequestParam("areaName") String areaName, 
			@RequestParam("planTitle") String planTitle
			, RedirectAttributes rttb
			) throws IOException {
		rttb.addFlashAttribute("areaName", areaName);
		rttb.addFlashAttribute("planTitle", planTitle);
		//짧은 이름으로 넘기기 
		String areaShortName = planService.selectAreaShortName(areaName);
		rttb.addFlashAttribute("areaShortName", areaShortName);
		return "redirect:/plan";
	}
	
	
}
