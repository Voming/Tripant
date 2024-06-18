package mclass.store.tripant.admin.controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import mclass.store.tripant.admin.domain.AdminMemEntity;
import mclass.store.tripant.admin.service.AdminSerivce;

@Controller
@RequestMapping(value="/admin")
public class AdminController {

	@Autowired
	private AdminSerivce adminservice;
	
	@Autowired
	private Gson gson;
	
	@Value("${pay.secret}")
	private String paySecret;
	@Value("${pay.storeId}")
	private String storeId;
	
	@GetMapping("/member")
	public ModelAndView Member(ModelAndView mv) {
		
		//model.addAttribute("memList",adminservice.selectMemList());
		mv.addObject("memList",adminservice.selectMemList());
		mv.setViewName("admin/admin_member");
		
		return mv;
		//return "admin/admin_member";
	}
	

	// ajax
	 //등급변경 
	@PostMapping("/member/role") 
	@ResponseBody
	public Integer MemberRole(Integer selectRole, String memEmail) {
		
		String memRole = "";
		switch(selectRole) {
		case 1: memRole = "ROLE_SLEEP"; 
				break;
		case 2: memRole = "ROLE_MEM"; 
				break;
		case 3: memRole = "ROLE_VIP"; 
				break;
		case 4: memRole = "ROLE_ADMIN"; 
				break;
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("memRole", memRole);
		map.put("memEmail", memEmail);
		int result = adminservice.adminMemRole(map);
		
		return result;
	}
	

	//ajax  TODO fragment
	//검색 
	@PostMapping("/member/search") 
	@ResponseBody
	public List<AdminMemEntity> MemberSearch(Model model, String searchMem) {
		List<AdminMemEntity> memList=adminservice.search(searchMem);
		return memList ;
	}
	
	@GetMapping("/board")
	public String board(Model model) {
		model.addAttribute("memBoard",adminservice.boardList());
		
		return "admin/admin_board";
	}
	
	//ajax
	//좋아요 정렬
	@PostMapping("/like")
	@ResponseBody
	public void boardLike(Model model) {
		
	}
	
	
	//신고게시글
	@GetMapping("/complain")
	public String complain(Model model) {
		model.addAttribute("complainBoard",adminservice.complainList());
		return "admin/admin_complain";
	}
	
	@GetMapping("/goods")
	public String goods() {
		return "admin/admin_goods";
	}
	
	// 결제 취소 페이지
	@GetMapping("/cancel")
	public ModelAndView cancel(ModelAndView mv) {
		mv.setViewName("admin/admin_cancel");
		List<Map<String, Object>> list = adminservice.payList();
		if(list != null) {
			mv.addObject("list", list);
		}
		return mv;
	}
	
	// 결제 취소
	@PostMapping("/cancel")
	@ResponseBody
	public int cancelP(String memEmail, String buyId) throws IOException, InterruptedException {
		HttpRequest request = HttpRequest.newBuilder()
			    .uri(URI.create("https://api.portone.io/payments/paymentId/pre-register"))
			    .header("Content-Type", "application/json")
			    .header("Authorization", "PortOne " + paySecret)
			    .method("POST", HttpRequest.BodyPublishers.ofString("{}"))
			    .build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		System.out.println(response.body());
		return 0;
	}
	
	
	@GetMapping("/mchart")
	public String mchart() {
		return "admin/admin_mchart";
	}
	
	@GetMapping("/bchart")
	public String bchart() {
		return "admin/admin_bchart";
	}
	

}
