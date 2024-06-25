package mclass.store.tripant.diary.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import mclass.store.tripant.diary.domain.DiaryBoardEntity;
import mclass.store.tripant.diary.service.DiaryService;
import mclass.store.tripant.place.domain.PlaceboxEntity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping(value = "/diary")
public class DiaryController {
	@Autowired
	private DiaryService diaryService;

	// 전체 글보기(공개 글)
	@GetMapping("")
	// modelAndView로 db두번 갔다오지 않게 하기
	public ModelAndView diary(ModelAndView mv, String areaname) {
//		mv.addObject("diaries", diaryService.selectDiaryList(areaname));
		mv.setViewName("diary/diary_board");
		return mv;
	}

	// 전체 글보기(공개 글)
	@PostMapping("")
	public String diary2(Model model, String areaname, Integer clickNum) {
		int maxNum = (clickNum + 1) * 8;
		model.addAttribute("diaries", diaryService.selectDiaryList(areaname, maxNum));
		return "diary/diary_area_fragment";
	}

	// 글 상세보기
	@GetMapping("/read/{diaryId}")
	public String diartRead(@PathVariable int diaryId, Model model) {
		DiaryBoardEntity diary = diaryService.getDiaryById(diaryId);
		model.addAttribute("diary", diary);
		return "diary/diary_read";
	}
	

/*	// 좋아요 기능
	@PostMapping("/like/{Id}")
	@ResponseBody
	public Map<String, Object> likeDiary(@PathVariable Long Id) {
		boolean success = diaryService.incrementLikes(Id);
		Map<String, Object> response = new HashMap<>();
		response.put("success", success);
		return response;
	}*/
	//최신순 정렬
	@GetMapping("/latest")
    public List<DiaryBoardEntity> getLatestDiaries() {
        return diaryService.getLatestDiaries();
    }
// 좋아요순 정렬
    @GetMapping("/popular")
    public List<DiaryBoardEntity> getPopularDiaries() {
        return diaryService.getPopularDiaries();
    }
	
	}

