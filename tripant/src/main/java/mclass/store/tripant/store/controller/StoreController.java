package mclass.store.tripant.store.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import mclass.store.tripant.apikeys.KeysJaewon;
import mclass.store.tripant.store.domain.ItemEntity;
import mclass.store.tripant.store.model.service.StoreService;

@Controller
@RequiredArgsConstructor
public class StoreController {
	
//	private final KeysJaewon keysJaewon;
	private final StoreService storeService; 
	
	@GetMapping("/store")
	public String store(Model model) {
		List<ItemEntity> themeList = storeService.themeList();
		List<ItemEntity> fontList = storeService.fontList();
		model.addAttribute("themeList", themeList);
		model.addAttribute("fontList", fontList);
		return "store/home";
	}
}
