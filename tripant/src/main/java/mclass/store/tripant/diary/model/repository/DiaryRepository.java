package mclass.store.tripant.diary.model.repository;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DiaryRepository {
	public String selectDiaryList();
}
