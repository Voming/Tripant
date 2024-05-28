package mclass.store.tripant.trip.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TripMapController {
	
	//sts꺼
//	@Value("${kakao.map.rest.api}")
	
//	@GetMapping("/map/getduration")
//	public String getduration(
//			@RequestParam double startLat,
//			@RequestParam double startLng,
//			@RequestParam double endLat,
//			@RequestParam double endLng,
//			Model model) {
//		
//		String urlStr="https://apis-navi.kakaomobility.com/v1/directions?"
//				+ "origin="+12+","+33
//				+ "destination=127.10823557165544,37.401928707331656&priority=TIME";
//		
//		return "trip/trip";
//	}
}
