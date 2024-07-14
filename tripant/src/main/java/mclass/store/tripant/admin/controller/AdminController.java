package mclass.store.tripant.admin.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.google.gson.Gson;

import mclass.store.tripant.admin.domain.AdminChartEntity;
import mclass.store.tripant.admin.model.service.AdminService;

@Controller
@RequestMapping(value="/admin")
public class AdminController {

	@Autowired
	private AdminService adminservice;
	
	@Autowired
	private Gson gson;
	
	@Value("${pay.secret}")
	private String paySecret;
	@Value("${pay.storeId}")
	private String storeId;
	
	//한 페이지 몇개씩 나올지 정하기(한페이지당글수) 
	private int num =8;
	
	//화면 하단에 나타날 페이지수
	private int pageNum = 5;
	
	//누른 현재 페이지 알아야함(어떻게 기준으로 삼을지..)
	//private int currentPage = 1;  // 기본1  // @RequestParam(required = false, defaultValue = "1") 
	
	@GetMapping("/member")
	public String member(Model model
			, @RequestParam(name = "page", required = false, defaultValue = "1")Integer currentPage
			, @RequestParam(required = false )String searchMem) throws MethodArgumentTypeMismatchException 
	{
		model.addAttribute("memMap",adminservice.selectMemList( num, pageNum, currentPage, searchMem));
		return "admin/admin_member";
	}
	
	//ajax  
	//검색 // paging
	@PostMapping("/member/search") 
	public String  memberSearch(Model model 
			, @RequestParam(name = "page", required = false, defaultValue = "1")Integer currentPage
			, @RequestParam(required = false )String searchMem
			,@RequestParam(required = false ) String sort
			,@RequestParam(required = false ) String sortCount
			) {
		if(sortCount.equals("1")) {
			model.addAttribute("memMap", adminservice.search( num, pageNum, currentPage, searchMem,sort));
		}else if (sortCount.equals("0")) {
			model.addAttribute("memMap",adminservice.selectMemList( num, pageNum, currentPage, searchMem));
		}
		
		return "admin/member_fragment";
	}

	 //ajax
	 //회원정보 수정(등급변경 , 활성화여부)
	@PostMapping("/member/info") 
	@ResponseBody
	public Integer MemberInfo(Integer selectRole, String memEmail,Integer memEnabled) {
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
		map.put("memEnabled", memEnabled);
	
		
		if(memEnabled==1 ) { adminservice.reportReset(memEmail); }
		 
		int result = adminservice.adminMemInfo(map);
		return result;
	}
	
	//게시글
	@GetMapping("/board")
	public String board(Model model,  @RequestParam(name = "page", required = false, defaultValue = "1")Integer currentPage
			, @RequestParam(required = false )String search, @RequestParam(required = false) String pick) throws MethodArgumentTypeMismatchException {
		model.addAttribute("diaryMap",adminservice.boardList( num, pageNum, currentPage, pick,search));
		return "admin/admin_board";
	}
	
	//ajax
	//게시글 조건검색(select)  +정렬
	@PostMapping("/keyword")
	public String keywordSearch(
			Model model
			,@RequestParam(name = "page" ,required = false, defaultValue = "1")Integer currentPage
			,@RequestParam(required = false ) String search
			,@RequestParam(required = false ) String pick
			,@RequestParam(required = false ) String sort
			,@RequestParam(required = false ) String sortCount
			) {
		if(sortCount.equals("1")) {
			model.addAttribute("diaryMap",adminservice.keywordSearch(num, pageNum, currentPage, pick, search, sort));
		}else if(sortCount.equals("0")) {
			model.addAttribute("diaryMap",adminservice.boardList( num, pageNum, currentPage, pick,search));
		}
		
		return "admin/board_fragment";
	}
	
	//신고게시글
	@GetMapping("/complain")
	public String complain(Model model
			, @RequestParam(name = "page", required = false, defaultValue = "1")Integer currentPage
	 , @RequestParam(required = false )String searchMem )throws MethodArgumentTypeMismatchException {
		model.addAttribute("complainMap",adminservice.complainList(num, pageNum, currentPage,searchMem));
		
		return "admin/admin_complain";
	}
	
	//신고게시글 검색+정렬
		@PostMapping("/complain/search")
		public String complainsearch(Model model
				, @RequestParam( required = false, defaultValue = "1")Integer currentPage
		 , @RequestParam(required = false )String searchMem
		 ,@RequestParam(required = false ) String sort
		 ,@RequestParam(required = false ) String sortCount){
			if(sortCount.equals("1")) {
				model.addAttribute("complainMap",adminservice.complainsearch(num, pageNum, currentPage,searchMem,sort));
			}else if(sortCount.equals("0")) {
				model.addAttribute("complainMap",adminservice.complainList(num, pageNum, currentPage,searchMem));
			}
			System.out.println("!!!!!!"+sortCount);
			return "admin/complain_fragment";
		}
		
	//신고수 초기화
	@PostMapping("/reset")
	@ResponseBody
	public int complainReset(Integer diaryId) {
		int result=adminservice.complainReset(diaryId);
		return result;
	}
	
	// 결제 취소 페이지
	@GetMapping("/cancel")
	public String cancel(Model model,  @RequestParam(name = "page", required = false, defaultValue = "1")Integer currentPage
			 , @RequestParam(required = false )String searchMem) {
		Map<String, Object> list = adminservice.payList(num, pageNum, currentPage,searchMem);
		if(list != null) {
			model.addAttribute("list", list);
		}
		return "admin/admin_cancel";
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
		System.out.println(responseMap);
		if(responseMap.get("pgCode").equals("2015")||responseMap.get("type").toString().contains("already")) {
			Map<String, Object> map = new HashMap<>();
			map.put("buyId", Integer.parseInt(buyId));
			map.put("memEmail", memEmail);
			adminservice.payCancel(map);
			return -1;
		}else {
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
	}
	
	//결제취소 회원 검색
	@PostMapping("/cancel/search")
	public String cancelSearch(Model model,  @RequestParam(name = "page", required = false, defaultValue = "1")Integer currentPage
			 , @RequestParam(required = false )String searchMem){
				 Map<String, Object> list = adminservice.cancelSearch(num, pageNum, currentPage,searchMem);
					if(list != null) {
						model.addAttribute("list", list);
					}
		return "admin/cancel_fragment";
	}
	
	// 상품관리 페이지
	@GetMapping("/goods")
	public String goods(Model model, @RequestParam(name = "page", required = false, defaultValue = "1")Integer currentPage
			, @RequestParam(required = false )String itemCode)
			throws MethodArgumentTypeMismatchException  {
		model.addAttribute("goodsMap", adminservice.itemList(num, pageNum, currentPage,itemCode));
		return "admin/admin_goods";
	}
	
	//상품검색
	//ajax + fragment
	@PostMapping("/goods/search")
	public String itemsearch(Model model, @RequestParam(name = "page", required = false, defaultValue = "1")Integer currentPage
			, @RequestParam(required = false )String itemCode){
		model.addAttribute("goodsMap", adminservice.itemListSearch(num, pageNum, currentPage,itemCode));
		return "admin/goodspage_fragment";
	}
	
	// 상품추가
	@PostMapping("/goods/insert")
	@ResponseBody
	public int goodsInsert(String itemCode, String itemName, Integer itemPrice, Integer itemDur, Integer itemSale, String itemColor, String itemSrc) {
		Map<String, Object> map = new HashMap<>();
		map.put("itemCode", itemCode);
		map.put("itemName", itemName);
		map.put("itemPrice", itemPrice);
		map.put("itemDur", itemDur != null ? itemDur : null);
		map.put("itemSale", itemSale != null ? itemSale : null);
		map.put("itemColor", itemColor != null ? itemColor : null);
		map.put("itemSrc", itemSrc != null ? itemSrc : null);
		
		int result = adminservice.itemInsert(map);
		
		return result;
	}
	
	// 상품정보 불러오기
	@PostMapping("/goods/info")
	@ResponseBody
	public Map<String, Object> goodsInfo(String itemCode) {
		return adminservice.itemInfo(itemCode);
	}
	
	// 상품수정
	@PostMapping("/goods/update")
	@ResponseBody
	public int goodsUpdate(String itemCode, String itemName, Integer itemPrice, Integer itemDur, Integer itemSale, String itemColor, String itemSrc) {
		Map<String, Object> map = new HashMap<>();
		map.put("itemCode", itemCode);
		map.put("itemName", itemName);
		map.put("itemPrice", itemPrice);
		map.put("itemDur", itemDur != null ? itemDur : null);
		map.put("itemSale", itemSale != null ? itemSale : null);
		map.put("itemColor", itemColor != null ? itemColor : "");
		map.put("itemSrc", itemSrc != null ? itemSrc : null);
		
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
	
	@GetMapping("/mchart")
	public String mchart(Model model) {
		List<AdminChartEntity> list = adminservice.chart();
		List<AdminChartEntity> list2 = adminservice.plan();
		
		int[] arr = new int[7];
		int[] arr2 = new int[7];
		
		for(int i = 0; i < list.size(); i++) {
			arr[list.get(i).getBefore()] = list.get(i).getNum();
		}
		
		for(int i = 0; i < list2.size(); i++) {
			arr2[list2.get(i).getBefore()] = list2.get(i).getNum();
		}
		
		for(int i = 0; i < 7; i++) {
			model.addAttribute("chart"+i, arr[i]);
			model.addAttribute("plan"+i, arr2[i]);
		}
		
		return "admin/admin_mchart";
	}
	
	@GetMapping("/bchart") 
	public String bchart(Model model) {
		try {
			model.addAttribute("area", adminservice.area());
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return "admin/admin_bchart";
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public String typeMissmatchExceptionHandler() {
		return "redirect:/admin/member";
	}
	
	
}
