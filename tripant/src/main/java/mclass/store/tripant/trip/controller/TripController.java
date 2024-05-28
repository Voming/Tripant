package mclass.store.tripant.trip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TripController {
	
	@GetMapping("/map")
	public String mapMain(Model model) {
		//model.addAttribute();
		return "trip/trip";
	}
}
