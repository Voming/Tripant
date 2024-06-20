package mclass.store.tripant.trip.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import mclass.store.tripant.trip.domain.DayEntity;
import mclass.store.tripant.trip.model.repository.TripRepository;

@Service
@RequiredArgsConstructor
public class TripService {
	
	private final TripRepository repository;
	
	//여행 일정 목록 불러오기
	public List<DayEntity> detailList(Integer planId){
		return repository.detailList(planId);
	};
	
	//여행 기본(포괄)정보 불러오기
	public Map<String , Object> planInfo(Integer planId){
		return repository.planInfo(planId);
	};
}
