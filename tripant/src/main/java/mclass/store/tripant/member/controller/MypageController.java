package mclass.store.tripant.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MypageController {

	//마이페이지
	@GetMapping("/my/home")
	public String mypage() {
		return "mypage/home";
	}
}
