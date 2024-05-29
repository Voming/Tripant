package mclass.store.tripant.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/admin")
public class AdminController {

	@GetMapping("/member")
	public String admin() {
		return "admin/admin-member";
	}
	
	@GetMapping("/board")
	public String adminlist() {
		return "admin/admin-board";
	}
	
	@GetMapping("/mchart")
	public String adminmchart() {
		return "admin/admin-mchart";
	}
	
	@GetMapping("/bchart")
	public String adminbchart() {
		return "admin/admin-bchart";
	}
}
