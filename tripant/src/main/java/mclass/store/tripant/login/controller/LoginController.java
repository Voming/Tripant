package mclass.store.tripant.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class LoginController {
	
	private final KeysJaewon keysJaewon;
	
	private final MemberService memberService;
	
	@Autowired
	private MemberEntity memberEntity;
	
	//로그인 페이지
	@GetMapping("/login")
	public String login(@RequestParam(value = "error", required = false)String error, 
						@RequestParam(value = "exception", required = false)String exception, 
						Model model) {
		String msg="";
		if(exception != null) {
			switch(exception) {
			case "BadCredentialsException":
				msg = "이메일 또는 비밀번호가 맞지 않습니다. 다시 확인해주세요.";
				break;
			case "InternalAuthenticationServiceException":
				msg = "시스템 문제로 인해 요청을 처리할 수 없습니다. 관리자에게 문의해주세요.";
				break;
			case "UsernameNotFoundException":
				msg = "계정이 존재하지 않습니다. 회원가입 진행 후 로그인 해주세요.";
				break;
			case "AuthenticationException":
				msg = "탈퇴 처리된 회원입니다. 관리자에게 문의해주세요.";
				break;
			default:
				msg = "알 수 없는 이유로 로그인에 실패하였습니다. 관리자에게 문의해주세요.";
				break;
			}
		}
		model.addAttribute("robotKey", keysJaewon.getRobotKey());
		model.addAttribute("error", error);
		model.addAttribute("exception", msg);
		return "login/login";
	}
	
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
				memberService.joinEmail(memberEntity);
				return 1;
			}else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	//카카오 로그인
	@GetMapping("/login/kakao")
	public String kakaoLogin() {
		return "redirect:"+keysJaewon.getKakaoLoginUrl();
	}
	
	//네이버 로그인
	@GetMapping("/login/naver")
	public String naverLogin() {
		return "redirect:"+keysJaewon.getNaverLoginUrl();
	}
	
}

