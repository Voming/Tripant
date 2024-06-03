package mclass.store.tripant.member.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import mclass.store.tripant.member.model.service.MemberService;

@Controller
public class MypageController {

	@Autowired
	private MemberService memberService;

	// 마이페이지
	@GetMapping("/my/home")
	public String mypage() {
		return "mypage/home";
	}

	// 닉네임 변경 페이지
	@GetMapping("/my/nick")
	public String chNick() {
		return "mypage/chNick";
	}

	// 닉네임 변경
	@PostMapping("/save/nick")
	@ResponseBody
	public int saveNick(String memNick, Principal principal) {
		String memEmail = principal.getName();
		Map<String, Object> map = new HashMap<>();
		map.put("memNick", memNick);
		map.put("memEmail", memEmail);
		int result = memberService.saveNick(map);
		return result;
	}

	// 비밀번호 변경 페이지
	@GetMapping("/my/pwd")
	public String chPwd() {
		return "mypage/chPwd";
	}

	// 비밀번호 변경
	@PostMapping("/save/pwd")
	@ResponseBody
	public int savePwd(String memPassword, Principal principal) {
		String memEmail = principal.getName();
		Map<String, Object> map = new HashMap<>();
		map.put("memPassword", new BCryptPasswordEncoder().encode(memPassword));
		map.put("memEmail", memEmail);
		int result = memberService.savePwd(map);
		return result;
	}

	// 회원 탈퇴 페이지
	@GetMapping("/my/quit")
	public String quit() {
		return "mypage/quit";
	}
	
	// 회원 탈퇴 시 현재 비밀번호 확인
	@PostMapping("/quit/pwd")
	@ResponseBody
	public int quitPwd(String memPassword, Principal principal) {
		String memEmail = principal.getName();
		Map<String, Object> map = new HashMap<>();
		map.put("memEmail", memEmail);
		map.put("memPassword", new BCryptPasswordEncoder().encode(memPassword));
		int result = memberService.quitPwd(map);
		return result;
	}

}
