package mclass.store.tripant.diary.model.repository;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import mclass.store.tripant.diary.domain.DiaryBoardEntity;

@Mapper
public interface DiaryRepository {
	
	    // 일기 목록을 선택하는 메서드
	    public List<DiaryBoardEntity> selectDiaryList();

	    // 일기를 삽입하는 메서드
	    default void insertDiary(DiaryBoardEntity diary) {
	    	// java.sql.Date로 변환
	        Date diaryDate = diary.getDiaryDate();
	        // 변환된 날짜를 엔티티에 설정
	        diary.setDiaryDate(diaryDate);

	        // MyBatis를 사용하여 삽입 작업 수행
	        // 적절한 MyBatis의 삽입 메서드를 호출해야 합니다
	    }
	}
