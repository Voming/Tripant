package mclass.store.tripant.diary.model.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import mclass.store.tripant.diary.domain.DiaryBoardEntity;

@Mapper
public interface DiaryRepository {
	public List<DiaryBoardEntity> selectDiaryList();
	
}
