package mclass.store.tripant.trip.model.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import mclass.store.tripant.trip.domain.DayEntity;

@Mapper
public interface TripRepository {
	
	//여행세부일정 목록
	public List<DayEntity> detailInfo(Integer planId);
	
	
}
