package mclass.store.tripant.member.model.repository;

import org.apache.ibatis.annotations.Mapper;

import mclass.store.tripant.member.domain.MemberEntity;

@Mapper
public interface MemberRepository {
	public MemberEntity login(String memEmail);
	public int joinEmail(MemberEntity newMem);
	public int existNick(String memNick);
}
