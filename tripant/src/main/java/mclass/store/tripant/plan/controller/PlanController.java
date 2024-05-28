package mclass.store.tripant.plan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PlanController {

	@GetMapping("/sample")
	public String sample(Model model) {
		//model.addAttribute();
		return "sample_layout";
	}
	
	@GetMapping("/main")
	public String home(Model model) {
		//model.addAttribute();
		return "plan/main/main";
	}
}
