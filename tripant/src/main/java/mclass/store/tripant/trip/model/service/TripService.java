package mclass.store.tripant.trip.model.service;

import java.util.List;

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
}
