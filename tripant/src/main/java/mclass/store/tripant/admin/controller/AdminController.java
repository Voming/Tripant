package mclass.store.tripant.admin.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
	public Integer MemberRole() {
		
		
		//int result=adminservie.adminMemRole();

		return 0;
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
