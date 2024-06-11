package mclass.store.tripant.member.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mclass.store.tripant.member.domain.CustomOAuth2User;
import mclass.store.tripant.member.domain.MemberEntity;
import mclass.store.tripant.member.domain.MemberRole;
import mclass.store.tripant.member.model.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class OAuth2SecurityService extends DefaultOAuth2UserService {
	
	private final MemberRepository memberRepository;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		log.debug("[sjw] loadUser===========");
		OAuth2User oAuth2User = super.loadUser(userRequest);
		String email = "";
		Map<String, Object> map = new HashMap<>();
		if(userRequest.getClientRegistration().getRegistrationId().equals("kakao")) {
			map.put("memType", "K");
			Map<String, Object> kakaoAttributes = oAuth2User.getAttribute("kakao_account");
			email = (String) kakaoAttributes.get("email"); 
		}else if(userRequest.getClientRegistration().getRegistrationId().equals("naver")) {
			map.put("memType", "N");
			Map<String, Object> naverAttributes = oAuth2User.getAttribute("response");
			email = (String) naverAttributes.get("email"); 
		}else if(userRequest.getClientRegistration().getRegistrationId().equals("google")){
			map.put("memType", "G");
			email = (String) oAuth2User.getAttributes().get("email");
		}
		map.put("memEmail", email);
		
		log.debug("[sjw] email = "+email);
		Optional<MemberEntity> memberEntityOp = Optional.ofNullable(memberRepository.login(email));
		if(memberEntityOp.isEmpty()) {
			throw new UsernameNotFoundException(map.toString());
		}
		MemberEntity memberEntity = memberEntityOp.get();
		List<GrantedAuthority> authorities = new ArrayList<>();
		switch(memberEntity.getMemRole()) {
			case "ROLE_ADMIN": authorities.add(new SimpleGrantedAuthority(MemberRole.ADMIN.getRole()));
			case "ROLE_VIP": authorities.add(new SimpleGrantedAuthority(MemberRole.VIP.getRole()));
			case "ROLE_MEM": authorities.add(new SimpleGrantedAuthority(MemberRole.MEM.getRole()));
		}
		log.debug("[sjw] oAuth2User = "+oAuth2User.toString());
		return new CustomOAuth2User(email, memberEntity.getMemPassword(), authorities);
	}
}
