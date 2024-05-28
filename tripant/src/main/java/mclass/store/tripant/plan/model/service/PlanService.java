package mclass.store.tripant.plan.model.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mclass.store.tripant.plan.model.repostiory.PlanRepository;

@Service
public class PlanService {
	@Autowired
	private PlanRepository boardRepository;
	public int selectPlanCount(){
		return boardRepository.selectPlanCount();
	}
}
