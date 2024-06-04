package mclass.store.tripant.diary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import mclass.store.tripant.diary.service.DiaryService;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class DiaryController {
	@Autowired
	private DiaryService diaryService;
	// 전체 글보기(공개 글)
	@GetMapping("/diary")
	public String diary(Model model){
		model.addAttribute("diaries",diaryService.selectDiaryList());
		System.out.println("======dddd" + diaryService.selectDiaryList());
		return "diary/diary_board";
	}
	
	
	
	
}
