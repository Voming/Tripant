package mclass.store.tripant.schedule.modle.service;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {
	//스케줄쪽에서 해야할 일
	//1. API로 place 정보 받아오기
	//2. 
	
	
	
	
	@Scheduled(cron = "*/10 * * * * *")
	public void updatePlace(){
		System.out.println("####################scheduled############" + new Date());
		
		
		
		
		
		
		//장소에 값 넣기
//		int result;
//		try {
//			result = ScheduleRepository.insertPlace();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
