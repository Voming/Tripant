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
// 여행기, 나만의 여행기 글 리스트 가져오기	
	public List<DiaryBoardEntity> selectDiaryList(){
		return diaryRepository.selectDiaryList();
	}
	
	
}
