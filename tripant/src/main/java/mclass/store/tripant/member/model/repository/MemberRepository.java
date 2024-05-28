package mclass.store.tripant.member.model.repository;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import mclass.store.tripant.member.domain.LoginEntity;
import mclass.store.tripant.member.domain.MemberEntity;

@Mapper
public interface MemberRepository {
	public LoginEntity login(String memEmail);
	public MemberEntity insert(HashMap<String, Object> map);
}
