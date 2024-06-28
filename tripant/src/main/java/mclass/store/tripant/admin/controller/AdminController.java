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
	public ModelAndView Member(ModelAndView mv, String searchMem,String memNick,
			String currentPage) {
		
		//정보를 받아올 때 어떤것을 참조해서 받아올지 --> 매개변수(java에서의 getParameter 역할을 대신해줌)
		
//		한 페이지 몇개씩 나올지 정하기(한페이지당글수) -> 3개
		int memNum = 1;
		
//		화면 하단에 나타날 페이지수는 5개(1, 2, 3, 4, 5)
		int memPageNum = 2;
		
//		누른 현재 페이지 알아야함(어떻게 기준으로 삼을지..)
		int currentPageNum = 1;  // 기본1
		
		if(currentPage != null && !currentPage.equals("") ) {
			try {
				currentPageNum = Integer.parseInt(currentPage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		//model.addAttribute("memList",adminservice.selectMemList());
		mv.addObject("memList",adminservice.selectMemList(searchMem, memNick, memNum, memPageNum, currentPageNum));
		mv.setViewName("admin/admin_member");
		
		return mv;
		//return "admin/admin_member";
	}
	
		//ajax  
		//검색 
		@PostMapping("/member/search") 
		@ResponseBody
		public List<AdminMemEntity> memberSearch(Model model, String searchMem) {
			List<AdminMemEntity> memList=adminservice.search(searchMem);
			return memList ;
		}

	 //ajax
	 //회원정보 수정(등급변경 , 활성화여부)
	@PostMapping("/member/info") 
	@ResponseBody
	public Integer MemberInfo(Integer selectRole, String memEmail,Integer selectActive) {
		
		System.out.println("###########"+selectRole);
		System.out.println("###########"+selectActive);
		System.out.println("###########"+memEmail);
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
		map.put("memEnabled", selectActive);
		int result = adminservice.adminMemInfo(map);
		
		return result;
	}
	
	//게시글
	@GetMapping("/board")
	public String board(Model model) {
		model.addAttribute("memBoard",adminservice.boardList());
		
		return "admin/admin_board";
	}
	
	//ajax
	//게시글 검색(select)  
	@PostMapping("/keyword")
	@ResponseBody
	public List<AdminBoardEntity> keywordSearch(Model model,@RequestParam String write, @RequestParam String pick) {
		Map<String, Object> map=new HashMap<>();
		map.put("write",write);
		map.put("pick",pick);
		System.out.println("###########"+write);
		System.out.println("###########"+pick);
		return adminservice.keywordsearch(map);
	}
	
	//ajax
	//좋아요 정렬   
	@PostMapping("/like")
	@ResponseBody
	public List<AdminBoardEntity> boardLike() {
		return adminservice.boardLikes();
	}
	
	//ajax
	//조회수 정렬   
	@PostMapping("/view")
	@ResponseBody
	public List<AdminBoardEntity> boardView() {
		return adminservice.boardView();
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
	public List<AdminBoardEntity> complainsearch(Model model, String memNick){
		List<AdminBoardEntity> boardList=adminservice.complainsearch(memNick);
		return boardList;
	}
	
	//신고수 정렬
	@PostMapping("/report")
	@ResponseBody
	public List<AdminBoardEntity> boardReport() {
		return adminservice.boardReport();
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
	
	//결제취소 회원 검색
	@PostMapping("/cancel/search")
	@ResponseBody
	public List<AdminStoreEntity> cancelSearch(Model model, String memNick){
		List<AdminStoreEntity> boardList=adminservice.cancelSearch(memNick);
		return boardList;
	}
	
	// 상품관리 페이지
	@GetMapping("/goods")
	public ModelAndView goods(ModelAndView mv) {
		mv.addObject("goodsList", adminservice.itemList());
		mv.setViewName("admin/admin_goods");
		return mv;
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
	
	//상품검색
	//ajax + fragment
	@PostMapping("/goods/search")
	public String itemsearch(Model model, String itemCode){
		List<AdminStoreEntity> itemsearchList=adminservice.itemsearch(itemCode);
		model.addAttribute("goodsList", itemsearchList);
		return "admin/goods_fragment";
	}
	
	@GetMapping("/mchart")
	public String mchart() {
		return "admin/admin_mchart";
	}
	
	@GetMapping("/bchart")
	public String bchart() {
		return "admin/admin_bchart";
	}
	
	@GetMapping()
	public String page(Model model) {
	
		return "";
	}
	
}
