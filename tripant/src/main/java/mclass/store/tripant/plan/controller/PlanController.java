package mclass.store.tripant.plan.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import jakarta.servlet.http.HttpSession;
import mclass.store.tripant.place.domain.AreaEntity;
import mclass.store.tripant.place.domain.PlaceboxEntity;
import mclass.store.tripant.plan.model.service.PlanService;

@Controller
@RequestMapping(value = "/plan")
//@Slf4j  //private Logger log = LoggerFactory.getLogger(PlanController.class); 내용을 대신해줌
public class PlanController {
	// private Logger log = LoggerFactory.getLogger(PlanController.class);

	@Autowired
	private PlanService planService;

	@GetMapping("")
	public String make(@SessionAttribute(name = "areaCode") Integer areaCode,
			@SessionAttribute(name = "planTitle") String planTitle) {
		return "plan/make";
	}

	// 지역&제목 모달에서 선택한 지역 정보 가져오기
	@PostMapping("/area")
	@ResponseBody
	public List<AreaEntity> area(@RequestParam("areaCode") Integer areaCode) {
		List<AreaEntity> areaList = planService.selectAreaInfoList(areaCode);
		return areaList;
	}

	@PostMapping("/keep")
	public String keep(@RequestParam("areaCode") Integer areaCode, @RequestParam("planTitle") String planTitle,
			HttpSession session) throws IOException {
		session.setAttribute("areaCode", areaCode);
		session.setAttribute("planTitle", planTitle);
		// 짧은 이름으로 넘기기
		String areaShortName = planService.selectAreaShortName(areaCode);
		session.setAttribute("areaShortName", areaShortName);
		return "redirect:/plan";
	}

	@PostMapping("/spot")
	public String spot(Model model, @RequestParam("areaCode") Integer areaCode,
			@RequestParam("spotType") Integer spotType) throws IOException {
		List<PlaceboxEntity> spotTypeList = planService.selectTypeList(areaCode, spotType);
		model.addAttribute("spotTypeList", spotTypeList);
		return "plan/spot_tab_content";
	}

	@PostMapping("/spot/more")
	public String spotMore(Model model, @RequestParam("areaCode") Integer areaCode,
			@RequestParam("spotType") Integer spotType, @RequestParam("clickSpotNum") Integer clickSpotNum)
			throws IOException {
		// 10개씩 더 출력하기
		int maxNum = (clickSpotNum + 1) * 10;
		List<PlaceboxEntity> spotTypeList = planService.selectTypeListMore(areaCode, spotType, maxNum);
		model.addAttribute("spotTypeList", spotTypeList);
		return "plan/spot_tab_content";
	}

	@PostMapping("/stay")
	public String stay(Model model, @RequestParam("areaCode") Integer areaCode,
			@RequestParam("stayType") Integer stayType) throws IOException {
		List<PlaceboxEntity> stayTypeList = planService.selectTypeList(areaCode, stayType);
		model.addAttribute("stayTypeList", stayTypeList);
		return "plan/stay_tab_content";

	@PostMapping("/stay/more")
	public String stayMore(Model model, @RequestParam("areaCode") Integer areaCode,
			@RequestParam("stayType") Integer stayType, @RequestParam("clickStayNum") Integer clickStayNum)
			throws IOException {
		// 10개씩 더 출력하기
		int maxNum = (clickStayNum + 1) * 10;
		List<PlaceboxEntity> stayTypeList = planService.selectTypeListMore(areaCode, stayType, maxNum);
		model.addAttribute("stayTypeList", stayTypeList);
		return "plan/stay_tab_content";
	}

	@ExceptionHandler
	public String numberFormatExceptionHandler(NumberFormatException e) {
		e.printStackTrace();
		return "redirect:/";
	}

}
