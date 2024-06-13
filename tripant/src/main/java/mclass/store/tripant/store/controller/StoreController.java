package mclass.store.tripant.store.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;
import mclass.store.tripant.store.domain.ItemEntity;
import mclass.store.tripant.store.model.service.StoreService;


@RestController
@RequiredArgsConstructor
public class StoreController {
	
	@Value("${pay.secret}")
	private String payKey;
	
	private final StoreService storeService;
	
	// 스토어 페이지
	@GetMapping("/store")
	public ModelAndView store(ModelAndView mv, Principal principal) {
		mv.setViewName("store/home");
		String memEmail;
		if(principal != null) {
			memEmail = principal.getName();
		}else {
			memEmail = "";
		}
		List<ItemEntity> themeList = storeService.themeList(memEmail);
		List<ItemEntity> fontList = storeService.fontList();
		if(themeList.size() > 0) {
			mv.addObject("themeList", themeList);
		}
		mv.addObject("fontList", fontList);
		return mv;
	}
	
	// 장바구니에 담기
	@PostMapping("/store/insert")
	@ResponseBody
	public int storeInsert(@RequestParam List<String> items, Principal principal) {
		int size = items.size();
		List<Map<String, Object>> list = new ArrayList<>();
		String memEmail = principal.getName();
		for(String itemCode : items) {
			Map<String, Object> map = new HashMap<>();
			map.put("itemCode", itemCode);
			map.put("memEmail", memEmail);
			list.add(map);
		}
		int insertNum = storeService.insertItems(memEmail, list);
		int result;
		if(size == insertNum) {
			result = 1;
		}else {
			result = 0;
		}
		return result;
	}
	
	// 장바구니 페이지
	@GetMapping("/store/cart")
	public ModelAndView storeCart(ModelAndView mv, Principal principal) {
		mv.setViewName("store/cart");
		if(principal != null) {
		
			// 사용자 이메일
			String memEmail = principal.getName();
			mv.addObject("memEmail", memEmail);
			
			Map<String, Object> map = storeService.buyInfo(memEmail);
			
			// 사용자 닉네임
			String memNick = (String) map.get("MEM_NICK");
			mv.addObject("memNick", memNick);
			
			// 주문번호
			int buyId = Integer.parseInt(String.valueOf(map.get("BUY_ID")));
			mv.addObject("buyId", buyId);
			
			List<Map<String, Object>> list = storeService.cart(memEmail);
			if(list.size() > 0) {
				mv.addObject("cart", list);
			}
		}
		return mv;
	}
	
	// 장바구니 삭제
	@PostMapping("/store/cart/del")
	@ResponseBody
	public int storeCartDel(@RequestParam List<String> items, Principal principal) {
		int size = items.size();
		Map<String, Object> map = new HashMap<>();
		String memEmail = principal.getName();
		map.put("memEmail", memEmail);
		map.put("itemCode", items);
		int delNum = storeService.cartDel(map);
		int result;
		if(size == delNum) {
			result = 1;
		}else {
			result = 0;
		}
		return result;
	}
	
	// 구매내역 페이지
	@GetMapping("/store/buy")
	public ModelAndView storeBuy(ModelAndView mv, Principal principal) {
		mv.setViewName("store/buy");
		String memEmail;
		if(principal != null) {
			memEmail = principal.getName();
		}else {
			memEmail = "";
		}
		List<Map<String, Object>> list = storeService.buy(memEmail);
		if(list.size() > 0) {
			mv.addObject("list", list);
		}
		return mv;
	}
}
