package mclass.store.tripant.member.model.repository;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import mclass.store.tripant.member.domain.MemberEntity;

@Mapper
public interface MemberRepository {
	//로그인
	public MemberEntity login(String memEmail);
	//회원가입
	public Integer join(MemberEntity memberEntity);
	//닉네임 중복 여부
	public Integer existNick(String memNick);
	//이메일 가입 여부
	public Integer existEmail(String memEmail);
	//비밀번호 재설정
	public int setPwd(HashMap<String, Object> hashMap);
}
