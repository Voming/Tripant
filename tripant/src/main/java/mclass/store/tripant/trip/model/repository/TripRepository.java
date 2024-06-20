package mclass.store.tripant.trip.model.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import mclass.store.tripant.trip.domain.DayEntity;


@Mapper
public interface TripRepository {
	
	//여행 일정 불러오기
	public List<DayEntity> detailList(Integer planId);
	
	//여행 기본(포괄)정보 불러오기
	public Map<String,Object> planInfo(Integer planId); 
}
