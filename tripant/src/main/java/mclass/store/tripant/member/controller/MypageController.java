package mclass.store.tripant.member.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import mclass.store.tripant.member.model.service.MemberService;

@Controller
@RequiredArgsConstructor
public class MypageController {

	private final MemberService memberService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	// 마이페이지
	@GetMapping("/my/home")
	public String myHome(Model model, Principal principal) {
		String memEmail = principal.getName();
		Map<String, Object> map = memberService.myInfo(memEmail);
		model.addAttribute("memEmail", map.get("MEM_EMAIL"));
		model.addAttribute("memNick", map.get("MEM_NICK"));
		return "mypage/home";
	}

	// 닉네임 변경 페이지
	@GetMapping("/my/nick")
	public String myNick() {
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
	public String myPwd() {
		return "mypage/chPwd";
	}
	
	// 비밀번호 변경
	@PostMapping("/save/pwd")
	@ResponseBody
	public int savePwd(String memPassword, Principal principal) {
		String memEmail = principal.getName();
		Map<String, Object> map = new HashMap<>();
		map.put("memPassword", bCryptPasswordEncoder.encode(memPassword));
		map.put("memEmail", memEmail);
		int result = memberService.savePwd(map);
		return result;
	}

	// 회원 탈퇴 페이지
	@GetMapping("/my/quit")
	public String myQuit() {
		return "mypage/quit";
	}
	
	// 현재 비밀번호
	@PostMapping("/pwd/use")
	@ResponseBody
	public int pwdUse(String memPassword, Principal principal) {
		String memEmail = principal.getName();
		String currPwd = memberService.currPwd(memEmail);
		if(bCryptPasswordEncoder.matches(memPassword, currPwd)) {
			return 1;
		}else {
			return 0;
		}
	}
	
	// 회원 탈퇴
	@PostMapping("/my/quit")
	@ResponseBody
	public int myQuit(Principal principal) {
		String memEmail = principal.getName();
		int result = memberService.memQuit(memEmail);
		return result;
	}

}
