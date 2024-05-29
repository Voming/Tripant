package mclass.store.tripant.member.service;

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
import mclass.store.tripant.member.domain.MemberEntity;
import mclass.store.tripant.member.domain.MemberRole;
import mclass.store.tripant.member.model.repository.MemberRepository;

@RequiredArgsConstructor
@Service
public class MemberSecurityService implements UserDetailsService {
	
	private final MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String memEmail) throws UsernameNotFoundException {
		System.out.println("membersecurityservice==============");
		Optional<MemberEntity> loginEntityOp = Optional.ofNullable(memberRepository.login(memEmail));
		System.out.println(loginEntityOp);
		if(loginEntityOp.isEmpty()) {
			throw new UsernameNotFoundException("가입해");
		}
		MemberEntity loginEntity = loginEntityOp.get();
		System.out.println(loginEntity);
		List<GrantedAuthority> authorities = new ArrayList<>();
		switch(loginEntity.getMemRole()) {
			case "owner": authorities.add(new SimpleGrantedAuthority(MemberRole.OWNER.getValue()));
			case "admin": authorities.add(new SimpleGrantedAuthority(MemberRole.ADMIN.getValue()));
			case "vip": authorities.add(new SimpleGrantedAuthority(MemberRole.FONTUSER.getValue()));
			case "mem": authorities.add(new SimpleGrantedAuthority(MemberRole.USER.getValue()));
		}
		return new User(loginEntity.getMemEmail(), loginEntity.getMemPassword(), authorities);
	}
}
