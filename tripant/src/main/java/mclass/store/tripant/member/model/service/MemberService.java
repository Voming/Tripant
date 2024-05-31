package mclass.store.tripant.member.model.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mclass.store.tripant.member.domain.MemberEntity;
import mclass.store.tripant.member.model.repository.MemberRepository;

@Service
public class MemberService {
	
	@Autowired
	private MemberRepository memberRepository; 
	
	//로그인
	public MemberEntity login(String memEmail) {return memberRepository.login(memEmail);}
	//회원가입
	public Integer join(MemberEntity memberEntity) {return memberRepository.join(memberEntity);}
	//닉네임 중복 여부
	public Integer existNick(String memNick) {return memberRepository.existNick(memNick);}
	//이메일 가입 여부
	public Integer existEmail(String memEmail) {return memberRepository.existEmail(memEmail);}
	//비밀번호 재설정
	public int setPwd(HashMap<String, Object> hashMap) {return memberRepository.setPwd(hashMap);}
}
