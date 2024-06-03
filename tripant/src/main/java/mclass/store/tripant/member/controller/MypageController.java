package mclass.store.tripant.member.controller;

import java.security.Principal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import mclass.store.tripant.member.model.service.MemberService;

@Controller
public class MypageController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private Map<String, Object> map;

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
	
	//닉네임 변경
	@PostMapping("/save/nick")
	@ResponseBody
	public int saveNick(String memNick, Principal principal) {
		String memEmail = principal.getName();
		map.put("memNick", memNick);
		map.put("memEmail", memEmail);
		int result = memberService.saveNick(map);
		return result;
	}
	
	//비밀번호 변경 페이지
	@GetMapping("/my/pwd")
	public String chPwd() {
		return "mypage/chPwd";
	}
	
	//닉네임 변경
	@PostMapping("/save/pwd")
	@ResponseBody
	public int savePwd(String memPassword, Principal principal) {
		String memEmail = principal.getName();
		map.put("memPassword", memPassword);
		map.put("memEmail", memEmail);
		int result = memberService.savePwd(map);
		return result;
	}
	
	//회원 탈퇴 페이지
	@GetMapping("/my/quit")
	public String quit() {
		return "mypage/quit";
	}
	
}
