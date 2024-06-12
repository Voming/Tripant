package mclass.store.tripant.trip.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import mclass.store.tripant.trip.domain.TripShareEntity;
import mclass.store.tripant.trip.model.service.TripListService;

@Controller
@RequestMapping(value = "/trip")
public class TripListController {
	
	@Autowired
	private TripListService tripListService;
	
	//여행목록 불러오기
	@GetMapping("/list")
	public String tripList(Model model , Principal principal /* ,@RequestParam String memEmail */) {
		model.addAttribute("planlist", tripListService.selectTripList(principal.getName()));
		return "trip/tripList";
	}
	
	//여행 삭제
	@PostMapping("/list/delete")//ajax
	@ResponseBody
	public int listDelete(Integer planId) {
		int result =  tripListService.delete(planId);
		return result;
	}
	
	//유저검색
	@PostMapping("/search/nick")//ajax
	@ResponseBody
	public List<TripShareEntity> seachNick(Integer planId, String findNick) {
		Map<String, Object> map = new HashMap<>();
		map.put("planId", planId);
		map.put("findNick", findNick);
		List<TripShareEntity> nickList = tripListService.find(map);
		return nickList;
	}
	
	
}
