package mclass.store.tripant.trip.model.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import mclass.store.tripant.trip.domain.TripListEntity;
import mclass.store.tripant.trip.domain.TripShareEntity;

@Mapper //mybatis를 사용하기 위한 어노테이션
public interface TripListRepository {
	//나의 일정
	//목록 
	List<TripListEntity>  selectTripList(String memEmail) throws DataAccessException;
	
	//삭제 : 생성자
	Integer delete(Integer planId) throws DataAccessException;
	
	//삭제 : 공유자
	Integer deleteRole(Map<String, Object> map) throws DataAccessException;
	
	//유저검색
	List<TripShareEntity> find(Map<String, Object> map) throws DataAccessException;
	
	//일정 공유 중인 맴버
	List<TripShareEntity> share(Map<String, Object> map) throws DataAccessException;
	
	//유저 추가
	Integer add(Map<String, Object> map) throws DataAccessException;
	
	//유저 삭제
	Integer remove(Map<String, Object> map) throws DataAccessException;
}
