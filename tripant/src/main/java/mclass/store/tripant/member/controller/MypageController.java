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
	
	//닉네임 변경 페이지
	@GetMapping("/my/nick")
	public String chNick() {
		return "mypage/chNick";
	}
	
	//비밀번호 변경 페이지
	@GetMapping("/my/pwd")
	public String chPwd() {
		return "mypage/chPwd";
	}
	
	//회원 탈퇴 페이지
	@GetMapping("/my/quit")
	public String quit() {
		return "mypage/quit";
	}
	
}
