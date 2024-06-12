package mclass.store.tripant.admin.controller;


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
import org.springframework.web.servlet.ModelAndView;

import mclass.store.tripant.admin.domain.AdminMemEntity;
import mclass.store.tripant.admin.service.AdminMemSerivce;


@Controller
@RequestMapping(value="/admin")
public class AdminController {

	@Autowired
	private AdminMemSerivce adminservice;
	
	@GetMapping("/member")
	public ModelAndView Member(ModelAndView mv) {
		
		//model.addAttribute("memList",adminservice.selectMemList());
		mv.addObject("memList",adminservice.selectMemList());
		mv.setViewName("admin/admin_member");
		
		return mv;
		//return "admin/admin_member";
	}
	
	@PostMapping("/member/role")
	@ResponseBody
	public Integer MemberRole(Integer selectRole, String memEmail) {
		
		String memRole = "";
		switch(selectRole) {
		case 1: memRole = "ROLE_SLEEP"; break;
		case 2: memRole = "ROLE_MEM"; break;
		case 3: memRole = "ROLE_VIP"; break;
		case 4: memRole = "ROLE_ADMIN"; break;
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("memRole", memRole);
		map.put("memEmail", memEmail);
		int result = adminservice.adminMemRole(map);
		
		return result;
	}
	
	@GetMapping("/board")
	public String board(Model model) {
		model.addAttribute("memBoard",adminservice.boardList());
		
		return "admin/admin_board";
	}
	

	@GetMapping("/complain")
	public String complain() {
		return "admin/admin_complain";
	}
	
	@GetMapping("/goods")
	public String goods() {
		return "admin/admin_goods";
	}
	
	@GetMapping("/cancel")
	public String cancel() {
		return "admin/admin_cancel";
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
