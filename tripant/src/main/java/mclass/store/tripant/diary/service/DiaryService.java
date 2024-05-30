package mclass.store.tripant.diary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mclass.store.tripant.diary.domain.DiaryBoardEntity;
import mclass.store.tripant.diary.model.repository.DiaryRepository;

@Service
public class DiaryService {
	@Autowired
	private DiaryRepository diaryRepository;
	
	public List<DiaryBoardEntity> selectDiaryList(){
		return diaryRepository.selectDiaryList();
	}
	
	
}
