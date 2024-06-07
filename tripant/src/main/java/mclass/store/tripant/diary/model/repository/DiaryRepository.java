package mclass.store.tripant.diary.model.repository;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import mclass.store.tripant.diary.domain.DiaryBoardEntity;
import mclass.store.tripant.diary.domain.DiaryPostEntity;

@Mapper
public interface DiaryRepository {
	
	    // 일기 목록을 선택하는 메서드
	    public List<DiaryBoardEntity> selectDiaryList();
	    
	    // 여행기 글 등록
	    public void insertDiary(DiaryPostEntity diary);
	}
