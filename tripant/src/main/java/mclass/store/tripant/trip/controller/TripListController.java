package mclass.store.tripant.trip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/triplist")
public class TripListController {
	
	@GetMapping("")
	public String mainList(Model model) {
		
		return "trip/tripList";
	}
}
