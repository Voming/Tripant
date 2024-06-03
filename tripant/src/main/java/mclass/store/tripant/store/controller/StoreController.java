package mclass.store.tripant.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import mclass.store.tripant.apikeys.KeysJaewon;

@Controller
public class StoreController {
	
	@Autowired
	private KeysJaewon keysJaewon;
	
	@GetMapping("/store")
	public String store() {
		return "store/home";
	}
}
