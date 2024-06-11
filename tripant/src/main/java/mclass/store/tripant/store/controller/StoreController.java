package mclass.store.tripant.store.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;
import mclass.store.tripant.store.domain.ItemEntity;
import mclass.store.tripant.store.model.service.StoreService;


@Controller
@RequiredArgsConstructor
public class StoreController {
	
	private final StoreService storeService; 
	
	@GetMapping("/store")
	public ModelAndView store(ModelAndView mv) {
		mv.setViewName("store/home");
		List<ItemEntity> themeList = storeService.themeList();
		List<ItemEntity> fontList = storeService.fontList();
		mv.addObject("themeList", themeList);
		mv.addObject("fontList", fontList);
		return mv;
	}
	
	@GetMapping("/store/cart")
	public ModelAndView storeCart(ModelAndView mv, Principal principal) {
		mv.setViewName("store/cart");
		if(principal != null) {
			String memEmail = principal.getName();
			List<Map<String, Object>> map = storeService.cart(memEmail);
			System.out.println(map.size());
			if(map.size() > 0) {
				mv.addObject("cart", map);
			}
		}
		return mv;
	}
	
	@GetMapping("/store/buy")
	public String storeBuy() {
		return "store/buy";
	}
}
