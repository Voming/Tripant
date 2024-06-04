package mclass.store.tripant.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import mclass.store.tripant.apikeys.KeysJaewon;

@Controller
//@RequiredArgsConstructor
public class StoreController {
	
//	private final KeysJaewon keysJaewon;
	
	@GetMapping("/store")
	public String store() {
		return "store/home";
	}
}
