package mclass.store.tripant.apikeys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Getter
@Component
@PropertySource("classpath:/keyproperties/apikeys.properties")
public class KeysJaewon {
	
	private final String isLocal = "local";// local, tripant
	
	//도메인
	@Value("${domain."+isLocal+"}")
	private String domain;
	
	//카카오 로그인/로그아웃
	@Value("${kakao.login."+isLocal+".url}")
	private String kakaoLoginUrl;
	@Value("${kakao.login.clientid}")
	private String kakaoClientId;
	@Value("${kakao.login.clientsecret}")
	private String kakaoClientSecret;
	@Value("${kakao.login."+isLocal+".redirecturi}")
	private String kakaoLoginRedirectUri;
	@Value("${kakao.logout."+isLocal+".redirecturi}")
	private String kakaoLogoutRedirectUri;
	
	//네이버 로그인/로그아웃
	@Value("${naver.login."+isLocal+".url}")
	private String naverLoginUrl;
	@Value("${naver.login.clientid}")
	private String naverLoginClientId;
	@Value("${naver.login.clientsecret}")
	private String naverLoginClientSecret;
	@Value("${naver.login."+isLocal+".callbackurl}")
	private String naverCallbackUrl;
	
	
}
