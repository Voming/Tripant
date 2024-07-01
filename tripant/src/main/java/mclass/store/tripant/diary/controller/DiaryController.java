package mclass.store.tripant.diary.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import mclass.store.tripant.diary.model.service.DiaryService;

import org.springframework.web.bind.annotation.RequestMapping;


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

	// 전체 글보기(공개 글) --
//	@PostMapping("")
//	public String diary2(Model model, String areaname, Integer clickNum, Principal pricipal) {
//		String memEmail = null;
//		if(pricipal != null) {
//			memEmail = pricipal.getName();
//		}
//		int maxNum = (clickNum + 1) * 3;
//		model.addAttribute("diaries", diaryService.selectDiaryList(areaname, maxNum, memEmail));
//		return "diary/diary_area_fragment";
//	}
	// 최신순 정렬 // 전체 글보기(공개 글)
	@PostMapping("/popular/latest")
	public String getLatestDiaries(Model model, String areaname, Integer clickNum, Principal pricipal) {
		String memEmail = null;
		if (pricipal != null) {
			memEmail = pricipal.getName();
		}
		int maxNum = (clickNum + 1) * 3;
		model.addAttribute("diaries", diaryService.getLatestDiaries(areaname, maxNum, memEmail));
		return "diary/diary_area_fragment";
	}

	// 좋아요 정렬
	@PostMapping("/popular/likes")
	public String selectLikesPopular(Model model, String areaname, Integer clickNum, Principal pricipal) {
		String memEmail = null;
		if (pricipal != null) {
			memEmail = pricipal.getName();
		}
		int maxNum = (clickNum + 1) * 3;
		model.addAttribute("diaries", diaryService.selectLikesPopular(areaname, maxNum, memEmail));
		return "diary/diary_area_fragment";
	}

	// 조회수 정렬
	@PostMapping("/popular/views")
	public String selectViewsPopular(Model model, String areaname, Integer clickNum, Principal pricipal) {
		String memEmail = null;
		if (pricipal != null) {
			memEmail = pricipal.getName();
		}
		int maxNum = (clickNum + 1) * 3;
		model.addAttribute("diaries", diaryService.selectViewsPopular(areaname, maxNum, memEmail));
		return "diary/diary_area_fragment";
	}

	// 글 상세보기
	@GetMapping("/read/{diaryId}")
	public String diartRead(@PathVariable int diaryId, Model model) {
		model.addAttribute("diary", diaryService.getDiaryById(diaryId));
		model.addAttribute("likes", diaryService.selectDiaryLike(diaryId));
		return "diary/diary_read";
	}

}
