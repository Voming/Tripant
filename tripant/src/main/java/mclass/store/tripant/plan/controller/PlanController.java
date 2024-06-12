package mclass.store.tripant.plan.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mclass.store.tripant.place.domain.AreaEntity;
import mclass.store.tripant.plan.model.service.PlanService;

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
		return "plan/make";
	}
	
	//지역&제목 모달에서 선택한 지역 정보 가져오기
	@PostMapping("/area")
	@ResponseBody
	public List<AreaEntity> area(@RequestParam("areaName") String areaName) {
		List<AreaEntity> areaList = planService.selectAreaInfoList(areaName);
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
