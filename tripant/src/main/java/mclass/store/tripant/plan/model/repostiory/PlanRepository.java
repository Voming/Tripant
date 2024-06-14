package mclass.store.tripant.plan.model.repostiory;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import mclass.store.tripant.place.domain.AreaEntity;
import mclass.store.tripant.place.domain.AreaNameEntity;

@Mapper
public interface PlanRepository {
	public int selectPlanCount(); //생성된 일정 수
	public int selectMemCount();  //가입한 멤버 수
	public List<AreaNameEntity> selectAreaNameList(); //지역명 리스트
	public List<AreaNameEntity> selectAreaFindList(String findArea);  //지역명 검색
	public List<AreaEntity> selectAreaInfoList(int areaCode); // 지역 정보 
	public String selectAreaShortName(int areaCode); // 짧은 이름
}
