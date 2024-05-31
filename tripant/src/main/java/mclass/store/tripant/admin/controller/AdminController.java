package mclass.store.tripant.admin.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import mclass.store.tripant.admin.service.AdminMemSerivce;


@Controller
@RequestMapping(value="/admin")
public class AdminController {

	@Autowired
	private AdminMemSerivce adminservie;
	
	@GetMapping("/member")
	public String admin(Model model) {
		
		
		model.addAttribute("memList",adminservie.selectMemList());
		
		return "admin/admin_member";
	}
	
	@GetMapping("/board")
	public String adminlist() {
		return "admin/admin_board";
	}
	
	@GetMapping("/complain")
	public String complain() {
		return "admin/admin_complain";
	}
	
	@GetMapping("/cancel")
	public String cancel() {
		return "admin/admin_cancel";
	}
	
	@GetMapping("/mchart")
	public String adminmchart() {
		return "admin/admin_mchart";
	}
	
	@GetMapping("/bchart")
	public String adminbchart() {
		return "admin/admin_bchart";
	}
}
