package mclass.store.tripant.member.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import mclass.store.tripant.member.domain.CustomOAuth2User;
import mclass.store.tripant.member.domain.KakaoEntity;
import mclass.store.tripant.member.domain.LoginEntity;
import mclass.store.tripant.member.domain.MemberEntity;
import mclass.store.tripant.member.domain.MemberRole;
import mclass.store.tripant.member.model.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class UserOAuth2UserService extends DefaultOAuth2UserService {

	private final MemberService memberService;
	private final MemberRepository memberRepository;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2User oAuth2User = super.loadUser(userRequest);
		KakaoEntity kakaoEntity = new KakaoEntity(oAuth2User.getAttribute("kakao_account"));
		String name = oAuth2User.getName();
		System.out.println("name = "+name);
		String providerTypeCode = userRequest.getClientRegistration().getRegistrationId().toUpperCase();
		System.out.println("providerTypeCode = "+providerTypeCode);
		String username = providerTypeCode+name;

		MemberEntity memberEntity;
		
		Optional<MemberEntity> optional = Optional.ofNullable(memberRepository.login(name));
		memberEntity = optional.get();
		
		String memRole = memberEntity.getMemRole();
		List<GrantedAuthority> authorities = new ArrayList<>();
		switch(memberEntity.getMemRole()) {
			case "ROLE_OWNER": authorities.add(new SimpleGrantedAuthority(MemberRole.OWNER.getRole()));
			case "ROLE_ADMIN": authorities.add(new SimpleGrantedAuthority(MemberRole.ADMIN.getRole()));
			case "ROLE_VIP": authorities.add(new SimpleGrantedAuthority(MemberRole.VIP.getRole()));
			case "ROLE_MEM": authorities.add(new SimpleGrantedAuthority(MemberRole.MEM.getRole()));
		}
		return new CustomOAuth2User(memberEntity.getUsername(), memberEntity.getPassword(), authorities);
	}
}
