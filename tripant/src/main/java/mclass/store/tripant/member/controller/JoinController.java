package mclass.store.tripant.member.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import mclass.store.tripant.apikeys.KeysJaewon;
import mclass.store.tripant.member.domain.MemberEntity;
import mclass.store.tripant.member.model.service.MemberService;

@RequiredArgsConstructor
@Controller
public class JoinController {
	
	private final KeysJaewon keysJaewon;
	
	private final MemberService memberService;

	//회원가입 페이지
	@GetMapping("/join")
	public String join() {
		return "login/join";
	}
	
	// 닉네임 중복 검사
	@PostMapping("/join/nick/check")
	@ResponseBody
	public Integer nickCheck(@RequestParam String memNick) {
		int result = memberService.existNick(memNick);
		return result;
	}
	
	//회원가입
	@PostMapping("/join")
	@ResponseBody
	public int joinMember(MemberEntity memberEntity, String recaptcha) {
		
		memberEntity.setMemPassword(new BCryptPasswordEncoder().encode(memberEntity.getMemPassword()));
		memberEntity.setMemEnabled(1);
		memberEntity.setMemRole("ROLE_MEM");
		memberEntity.setMemType("T");
		System.out.println("mem = "+memberEntity);
		
		RecaptchaConfig.setSecretKey(keysJaewon.getRobotSecret());
		try {
			if(RecaptchaConfig.verify(recaptcha)) {
				memberService.join(memberEntity);
				return 1;
			}else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
}
