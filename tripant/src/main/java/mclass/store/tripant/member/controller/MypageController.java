package mclass.store.tripant.member.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import lombok.RequiredArgsConstructor;
import mclass.store.tripant.member.model.service.MemberService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/my")
public class MypageController {

	private final MemberService memberService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final Gson gson;
	
	// 마이페이지
	@GetMapping("/home")
	public String myHome(Model model, Principal principal) {
		String memEmail = principal.getName();
		Map<String, Object> map = memberService.myInfo(memEmail);
		model.addAttribute("memEmail", map.get("MEM_EMAIL"));
		model.addAttribute("memNick", map.get("MEM_NICK"));
		
		BigDecimal memTypeDec = (BigDecimal) map.get("MEM_TYPE");
		int memType = Integer.parseInt(String.valueOf(memTypeDec), 2);
		
		int isKakao = memType & Integer.parseInt("0100", 2);
		int isNaver = memType & Integer.parseInt("0010", 2);
		int isGoogle = memType & Integer.parseInt("0001", 2);
		
		model.addAttribute("isKakao", isKakao);
		model.addAttribute("isNaver", isNaver);
		model.addAttribute("isGoogle", isGoogle);
		
		return "mypage/home";
	}
	
	// 카카오 연동 해제
	@PostMapping("/unlink/kakao")
	@ResponseBody
	public int unlinkKakao(Principal principal) throws IOException, InterruptedException {
		int result;
		String memEmail = principal.getName();
		Map<String, Object> map = memberService.tokenValue(memEmail);
		
		HttpRequest request = HttpRequest.newBuilder()
			    .uri(URI.create("https://kauth.kakao.com/oauth/authorize"))
			    .header("Content-Type", "application/json")
			    .header("Authorization", "Bearer " + map.get("MEM_TOKEN_KAKAO"))
			    .method("POST", HttpRequest.BodyPublishers.ofString("{}"))
			    .build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		System.out.println(response.body());
		
		return result = 1;
	}

	// 닉네임 변경 페이지
	@GetMapping("/nick")
	public String myNick() {
		return "mypage/chNick";
	}

	// 닉네임 변경
	@PostMapping("/nick")
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
	@GetMapping("/pwd")
	public String myPwd() {
		return "mypage/chPwd";
	}
	
	// 비밀번호 변경
	@PostMapping("/pwd")
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
	@GetMapping("/quit")
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
	@PostMapping("/quit")
	@ResponseBody
	public int myQuit(Principal principal) {
		String memEmail = principal.getName();
		int result = memberService.memQuit(memEmail);
		return result;
	}

}
