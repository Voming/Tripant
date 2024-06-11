package mclass.store.tripant.store.model.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import mclass.store.tripant.store.domain.ItemEntity;

@Mapper
public interface StoreRepository {
	// 스토어
	// 테마 목록
	public List<ItemEntity> themeList(String memEmail);

	// 폰트 목록
	public List<ItemEntity> fontList();
	
	// 장바구니에 담기
	public int insertItems(List<Map<String, Object>> list);

	// 장바구니
	public List<Map<String, Object>> cart(String memEmail);
}
