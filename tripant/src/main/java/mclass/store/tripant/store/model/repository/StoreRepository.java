package mclass.store.tripant.store.model.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import mclass.store.tripant.store.domain.ItemEntity;

@Mapper
public interface StoreRepository {
	// 스토어
	// 테마 목록
	List<ItemEntity> themeList(String memEmail) throws DataAccessException;

	// 폰트 목록
	List<ItemEntity> fontList() throws DataAccessException;
	
	// 장바구니에 담기
	int fontDel(String memEmail) throws DataAccessException;
	int insertItems(Map<String, Object> map) throws DataAccessException;
	
	// 장바구니
	// 장바구니 목록
	List<Map<String, Object>> cart(String memEmail) throws DataAccessException;
	
	// 장바구니 삭제
	int cartDel(Map<String, Object> map) throws DataAccessException;
	
	// 구매내역
	// 구매내역 목록
	List<Map<String, Object>> buy(String memEmail) throws DataAccessException;
	
	// 주문정보
	Map<String, Object> buyInfo(String memEmail) throws DataAccessException;
	
	// 구매내역 추가
	int beforePay(Map<String, Object> map) throws DataAccessException;
	int pay(Map<String, Object> map) throws DataAccessException;
}
