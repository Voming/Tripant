package mclass.store.tripant.admin.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import mclass.store.tripant.admin.domain.AdminBoardEntity;
import mclass.store.tripant.admin.domain.AdminMemEntity;
import mclass.store.tripant.admin.domain.AdminStoreEntity;
import mclass.store.tripant.admin.service.AdminSerivce;

@Controller
@RequestMapping(value="/admin")
public class AdminController {

	@Autowired
	private AdminSerivce adminservice;
	
	@Autowired
	private Gson gson;
	
	@Value("${pay.secret}")
	private String paySecret;
	@Value("${pay.storeId}")
	private String storeId;
	
	@GetMapping("/member")
	public ModelAndView Member(ModelAndView mv) {
		
		//model.addAttribute("memList",adminservice.selectMemList());
		mv.addObject("memList",adminservice.selectMemList());
		mv.setViewName("admin/admin_member");
		
		return mv;
		//return "admin/admin_member";
	}
	

	// ajax
	 //등급변경 
	@PostMapping("/member/role") 
	@ResponseBody
	public Integer MemberRole(Integer selectRole, String memEmail) {
		
		String memRole = "";
		switch(selectRole) {
		case 1: memRole = "ROLE_SLEEP"; 
				break;
		case 2: memRole = "ROLE_MEM"; 
				break;
		case 3: memRole = "ROLE_VIP"; 
				break;
		case 4: memRole = "ROLE_ADMIN"; 
				break;
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("memRole", memRole);
		map.put("memEmail", memEmail);
		int result = adminservice.adminMemRole(map);
		
		return result;
	}
	

	//ajax  TODO fragment
	//검색 
	@PostMapping("/member/search") 
	@ResponseBody
	public List<AdminMemEntity> memberSearch(Model model, String searchMem) {
		List<AdminMemEntity> memList=adminservice.search(searchMem);
		return memList ;
	}
	
	//게시글
	@GetMapping("/board")
	public String board(Model model) {
		model.addAttribute("memBoard",adminservice.boardList());
		
		return "admin/admin_board";
	}
	
	//ajax
	//게시글 검색(키워드 선택)
	@GetMapping("/keyword")
	@ResponseBody
	public List<AdminBoardEntity> keywordSearch(@RequestParam("type") String type ,
			@RequestParam("keyword") String keyword , Model model) {
		AdminBoardEntity boarddto=new AdminBoardEntity();
		boarddto.setType(type);
		boarddto.setKeyword(keyword);
		
		return adminservice.keywordsearch(type, keyword);
	}
	
	//ajax
	//좋아요 정렬
	@PostMapping("/like")
	@ResponseBody
	public void boardLike() {
		
	}
	
	
	//신고게시글
	@GetMapping("/complain")
	public String complain(Model model) {
		model.addAttribute("complainBoard",adminservice.complainList());
		
		return "admin/admin_complain";
	}
	
	//신고수 초기화
	@PostMapping("/reset")
	@ResponseBody
	public int complainReset(Integer diaryId) {
		int result=adminservice.complainReset(diaryId);
		return result;
	}
	
	//신고게시글 검색
	@PostMapping("/complain/search")
	@ResponseBody
	public List<AdminBoardEntity> boardSearch(Model model, String memNick){
		List<AdminBoardEntity> boardList=adminservice.boardSearch(memNick);
		return boardList;
	}
	
	// 결제 취소 페이지
	@GetMapping("/cancel")
	public ModelAndView cancel(ModelAndView mv) {
		mv.setViewName("admin/admin_cancel");
		List<Map<String, Object>> list = adminservice.payList();
		if(list != null) {
			mv.addObject("list", list);
		}
		return mv;
	}
	
	// 결제 취소
	@PostMapping("/cancel")
	@ResponseBody
	public int cancelP(String memEmail, String buyId) throws IOException, InterruptedException {
		Map<String, Object> requestBody = new HashMap<>();
		requestBody.put("reason", "고객 요청으로 결제 취소");
		
		// 결제 취소 API 호출
		HttpRequest request = HttpRequest.newBuilder()
			    .uri(URI.create("https://api.portone.io/payments/"+buyId+"/cancel"))
			    .header("Content-Type", "application/json")
			    .header("Authorization", "PortOne " + paySecret)
			    .method("POST", HttpRequest.BodyPublishers.ofString(gson.toJson(requestBody)))
			    .build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		Map<String, Object> responseMap = gson.fromJson(response.body(), Map.class);
		Map<String, Object> cancellation = gson.fromJson(gson.toJson(responseMap.get("cancellation")), Map.class);
		String status = (String) cancellation.get("status");
		int result;
		// 결제 취소 완료 상태면 결제 내역 테이블에서 삭제
		if(status != null) {
			if(status.equals("SUCCEEDED")) {
				Map<String, Object> map = new HashMap<>();
				map.put("buyId", Integer.parseInt(buyId));
				map.put("memEmail", memEmail);
				result = adminservice.payCancel(map);
				return result;
			}else {
				return 0;
			}
		}else {
			return 0;
		}
	}

	// 상품관리 페이지
	@GetMapping("/goods")
	public ModelAndView goods(ModelAndView mv) {
		mv.setViewName("admin/admin_goods");
		mv.addObject("itemList", adminservice.itemList());
		return mv;
	}
	
	// 상품추가
	@PostMapping("/goods/insert")
	@ResponseBody
	public int goodsInsert(String itemCode, String itemName, Integer itemPrice, Integer itemDur, Integer itemSale) {
		Map<String, Object> map = new HashMap<>();
		map.put("itemCode", itemCode);
		map.put("itemName", itemName);
		map.put("itemPrice", itemPrice);
		if(itemDur != null && itemSale != null) {
			map.put("itemDur", itemDur);
			map.put("itemSale", itemDur);
		}else {
			map.put("itemDur", null);
			map.put("itemSale", null);
		}
		
		int result = adminservice.itemInsert(map);
		
		return result;
	}
	
	// 상품정보 불러오기
	@PostMapping("/goods/info")
	@ResponseBody
	public Map<String, Object> goodsInfo(String itemCode) {
		Map<String, Object> map = adminservice.itemInfo(itemCode);
		
		String itemName = (String) map.get("ITEM_NAME");
		BigDecimal itemPrice = (BigDecimal) map.get("ITEM_PRICE");
		BigDecimal itemDur = (BigDecimal) map.get("ITEM_DUR");
		BigDecimal itemSale = (BigDecimal) map.get("ITEM_SALE");
		
		map.put("itemCode", itemCode);
		map.put("itemName", itemName);
		map.put("itemPrice", itemPrice);

		if(itemDur != null) {
			map.put("itemDur", itemDur);
		}
		if(itemSale != null) {
			map.put("itemSale", itemSale);
		}
		
		return map;
	}
	
	// 상품수정
	@PostMapping("/goods/update")
	@ResponseBody
	public int goodsUpdate(String itemCode, String itemName, Integer itemPrice, Integer itemDur, Integer itemSale) {
		Map<String, Object> map = new HashMap<>();
		map.put("itemCode", itemCode);
		map.put("itemName", itemName);
		map.put("itemPrice", itemPrice);
		if(itemDur != null) {
			map.put("itemDur", itemDur);
		}else {
			map.put("itemDur", null);
		}
		if(itemSale != null) {
			map.put("itemSale", itemDur);
		}else {
			map.put("itemSale", null);
		}
		
		int result = adminservice.itemUpdate(map);
		
		return result;
	}
	
	//상품삭제
	//ajax
	@PostMapping("/goods/delete")
	@ResponseBody
	public int goodsDelete(String itemCode) {
		int result=adminservice.itemDelete(itemCode);
		return result;
	}
	//상품검색
	//ajax
	@PostMapping("/goods/search")
	@ResponseBody
	public List<AdminStoreEntity> itemsearch(Model model, String itemCode){
		List<AdminStoreEntity> itemsearchList=adminservice.itemsearch(itemCode);
		return itemsearchList;
	}
	@GetMapping("/mchart")
	public String mchart() {
		return "admin/admin_mchart";
	}
	
	@GetMapping("/bchart")
	public String bchart() {
		return "admin/admin_bchart";
	}
	

}
