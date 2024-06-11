package mclass.store.tripant.store.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import mclass.store.tripant.store.domain.ItemEntity;
import mclass.store.tripant.store.model.repository.StoreRepository;

@Service
@RequiredArgsConstructor
public class StoreService {

	private final StoreRepository storeRepository;

	// 스토어
	// 테마 목록
	public List<ItemEntity> themeList() {
		return storeRepository.themeList();
	}

	// 폰트 목록
	public List<ItemEntity> fontList() {
		return storeRepository.fontList();
	}

	// 장바구니
	public List<Map<String, Object>> cart(String memEmail){
		return storeRepository.cart(memEmail);
	}
}
