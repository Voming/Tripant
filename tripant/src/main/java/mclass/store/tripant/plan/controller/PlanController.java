package mclass.store.tripant.plan.controller;

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

import mclass.store.tripant.plan.domain.AreaNameEntity;
import mclass.store.tripant.plan.model.service.PlanService;

@Controller
public class PlanController {
	@Autowired
	private PlanService planService;
	
	@GetMapping("/sample")
	public String sample(Model model) {
		//model.addAttribute();
		return "sample_layout";
	}
	
	@GetMapping("/")
	public String home(Principal principal, Authentication authentication, Model model) {
		System.out.println("principal = "+principal);
		System.out.println("auth = "+authentication);
		
		model.addAttribute("planCount", planService.selectPlanCount());
		model.addAttribute("memCount", planService.selectMemCount());
		
		model.addAttribute("areaNameList", planService.selectAreaNameList());
		
		return "plan/main/home";
	}
	
	@PostMapping("/find")
	@ResponseBody
	public List<AreaNameEntity> find(@RequestParam("findArea") String findArea) {
		System.out.println("findArea :" + findArea);
		List<AreaNameEntity> areaList = planService.selectAreaFindList(findArea);
		System.out.println(areaList);
		return areaList;
	}
}
