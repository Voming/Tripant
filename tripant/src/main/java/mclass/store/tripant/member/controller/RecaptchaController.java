package mclass.store.tripant.member.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import mclass.store.tripant.config.RecaptchaConfig;

@RestController
@RequiredArgsConstructor
public class RecaptchaController {

	@Value("${robot.secret}")
	private String robotSecret;
	
	@PostMapping("/robot/join")
	@ResponseBody
	public int robotJoin(HttpServletRequest request) {
		RecaptchaConfig.setSecretKey(robotSecret);
		String gRecaptchaResponse = request.getParameter("recaptcha");
		try {
			if(RecaptchaConfig.verify(gRecaptchaResponse)) {
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
