package mclass.store.tripant.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mclass.store.tripant.member.domain.MemberEntity;
import mclass.store.tripant.member.model.repository.MemberRepository;

@Service
public class MemberService {
	
	@Autowired
	private MemberRepository memberRepository; 
	
	public MemberEntity login(String memEmail) {
		return memberRepository.login(memEmail);
	}
	
	public Integer joinEmail(MemberEntity newMem) {
		return memberRepository.joinEmail(newMem);
	}
	
	public Integer existNick(String memNick) {
		return memberRepository.existNick(memNick);
	}
	
	public Integer existEmail(String memEmail) {
		return memberRepository.existEmail(memEmail);
	}
}
