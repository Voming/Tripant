package mclass.store.tripant.trip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/plan/list")
public class TripListController {
	
	@GetMapping("")
	public String mainList(Model model) {
		
		return "trip/tripList";
	}
	@PostMapping("/delete")//ajax
	@ResponseBody
	public String planDelete(Model model) {
		
		return "trip/tripList";
	}
}
