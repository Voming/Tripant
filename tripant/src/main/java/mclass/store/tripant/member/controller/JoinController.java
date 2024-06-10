package mclass.store.tripant.member.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mclass.store.tripant.apikeys.KeysJaewon;
import mclass.store.tripant.config.RecaptchaConfig;
import mclass.store.tripant.member.domain.MemberEntity;
import mclass.store.tripant.member.model.service.MemberService;

@RequiredArgsConstructor
@Controller
@Slf4j
public class JoinController {
	
	private final KeysJaewon keysJaewon;
	private final MemberService memberService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	//회원가입 페이지
	@GetMapping("/join")
	public String join(Model model) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		String today = sdf.format(calendar.getTime());
		model.addAttribute("today", today);
		return "member/join";
	}
	
	//SNS 회원가입 페이지
	@GetMapping("/join/sns")
	public String joinSns(Model model) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		String today = sdf.format(calendar.getTime());
		model.addAttribute("today", today);
		return "member/joinsns";
	}
	
	// 닉네임 중복 검사
	@PostMapping("/join/nick/check")
	@ResponseBody
	public Integer joinNickCheck(@RequestParam String memNick) {
		int result = memberService.existNick(memNick);
		return result;
	}
	
	// 회원가입
	@PostMapping("/join")
	@ResponseBody
	public int joinP(MemberEntity memberEntity, String recaptcha) {
		
		memberEntity.setMemPassword(bCryptPasswordEncoder.encode(memberEntity.getMemPassword()));
		memberEntity.setMemEnabled(1);
		memberEntity.setMemRole("ROLE_MEM");
		memberEntity.setMemType("T");
		log.debug("[sjw] mem = "+memberEntity);
		
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
	
	// SNS 회원가입
	@PostMapping("/join/sns")
	@ResponseBody
	public int joinSnsP(MemberEntity memberEntity, String recaptcha, HttpSession session) {
		
		String memEmail = (String) session.getAttribute("memEmail");
		String memType = (String) session.getAttribute("memType");
		if(memEmail != null && memType != null) {
			memberEntity.setMemEmail(memEmail);
		}else {
			memType = "T";
		}
		System.out.println("memEmail = "+memEmail);
		memberEntity.setMemPassword(bCryptPasswordEncoder.encode(memberEntity.getMemPassword()));
		memberEntity.setMemEnabled(1);
		memberEntity.setMemRole("ROLE_MEM");
		memberEntity.setMemType(memType);
		log.debug("[sjw] mem = "+memberEntity);
		
		RecaptchaConfig.setSecretKey(keysJaewon.getRobotSecret());
		try {
			if(RecaptchaConfig.verify(recaptcha)) {
				memberService.join(memberEntity);
				session.removeAttribute("memEmail");
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
