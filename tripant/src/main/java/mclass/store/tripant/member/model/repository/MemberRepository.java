package mclass.store.tripant.member.model.repository;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import mclass.store.tripant.member.domain.MemberEntity;

@Mapper
public interface MemberRepository {
	public MemberEntity insert(HashMap<String, Object> map);
}
