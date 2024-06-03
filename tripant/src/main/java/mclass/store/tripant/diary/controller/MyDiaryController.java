package mclass.store.tripant.diary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import mclass.store.tripant.diary.service.DiaryService;
@Controller
public class MyDiaryController {

	@Autowired
	private DiaryService diaryService;
	//나의 글보기
		@GetMapping("/my/diary")
		public String mydiary(Model model) {
			diaryService.selectDiaryList();
			model.addAttribute("diaries",diaryService.selectDiaryList());
			return "mydiary/diary_my_board";
		}
		
//		일단 글쓰기 페이지 보이기만 
//		TODO from으로 db 서버 보내기
		@GetMapping("/my/write")
		public String diarywrite() {
			return "mydiary/diary_write";
		}

}
