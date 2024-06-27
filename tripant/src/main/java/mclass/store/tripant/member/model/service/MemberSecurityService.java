package mclass.store.tripant.member.model.service;

import java.security.Principal;
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
		Optional<MemberEntity> memberEntityOp = Optional.ofNullable(memberRepository.login(memEmail));
		if(memberEntityOp.isEmpty()) {
			throw new UsernameNotFoundException("가입해");
		}
		MemberEntity memberEntity = memberEntityOp.get();
		List<GrantedAuthority> authorities = new ArrayList<>();
		switch(memberEntity.getMemRole()) {
			case "ROLE_ADMIN": authorities.add(new SimpleGrantedAuthority(MemberRole.ADMIN.getRole())); break;
			case "ROLE_VIP": authorities.add(new SimpleGrantedAuthority(MemberRole.VIP.getRole())); break;
			case "ROLE_MEM": authorities.add(new SimpleGrantedAuthority(MemberRole.MEM.getRole())); break;
		}
		
		boolean isEnabled = true;
		if(memberEntity.getMemEnabled() == 0) isEnabled = false;
		
		return new User(memberEntity.getMemEmail(), memberEntity.getMemPassword(), isEnabled, true, true, true, authorities);
	}
}
