package mclass.store.tripant.test.common;

import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Getter
@Component
@PropertySource("classpath:/keyproperties/apikeys.properties")
public class KeysJaewon {
	
	//도메인
	@Value("${domain.local}")
	private String localDomain;
	@Value("${domain.tripant}")
	private String tripantDomain;
	
	//카카오 로그인/로그아웃
	@Value("${kakao.login.local.url}")
	private String kakaoLoginLocalUrl;
	@Value("${kakao.login.clientid}")
	private String kakaoClientId;
	@Value("${kakao.login.clientsecret}")
	private String kakaoClientSecret;
	@Value("${kakao.login.local.redirecturi}")
	private String kakaoLoginRedirectUri;
	@Value("${kakao.logout.local.redirecturi}")
	private String kakaoLogoutRedirectUri;
	
	//네이버 로그인/로그아웃
	@Value("${naver.login.local.url}")
	private String naverLoginLocalUrlPre;
	
	
}
