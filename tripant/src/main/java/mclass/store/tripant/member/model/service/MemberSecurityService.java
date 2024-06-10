package mclass.store.tripant.member.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mclass.store.tripant.member.domain.MemberEntity;
import mclass.store.tripant.member.domain.MemberRole;
import mclass.store.tripant.member.model.repository.MemberRepository;

@RequiredArgsConstructor
@Service
@Slf4j
public class MemberSecurityService implements UserDetailsService {
	
	private final MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String memEmail) throws UsernameNotFoundException {
		log.debug("[sjw] membersecurityservice==============");
		Optional<MemberEntity> loginEntityOp = Optional.ofNullable(memberRepository.login(memEmail));
		System.out.println("[sjw] loginEntityOp = "+loginEntityOp);
		if(loginEntityOp.isEmpty()) {
			throw new UsernameNotFoundException("가입해");
		}
		MemberEntity loginEntity = loginEntityOp.get();
		System.out.println("[sjw] loginEntity = "+loginEntity);
		List<GrantedAuthority> authorities = new ArrayList<>();
		switch(loginEntity.getMemRole()) {
			case "ROLE_OWNER": authorities.add(new SimpleGrantedAuthority(MemberRole.OWNER.getRole()));
			case "ROLE_ADMIN": authorities.add(new SimpleGrantedAuthority(MemberRole.ADMIN.getRole()));
			case "ROLE_VIP": authorities.add(new SimpleGrantedAuthority(MemberRole.VIP.getRole()));
			case "ROLE_MEM": authorities.add(new SimpleGrantedAuthority(MemberRole.MEM.getRole()));
		}
		return new User(loginEntity.getMemEmail(), loginEntity.getMemPassword(), authorities);
	}
}
