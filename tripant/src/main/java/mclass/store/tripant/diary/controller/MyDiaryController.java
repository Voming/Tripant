package mclass.store.tripant.diary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nimbusds.oauth2.sdk.Response;

import mclass.store.tripant.diary.service.DiaryService;
@Controller
public class MyDiaryController {

	@Autowired
	private DiaryService diaryService;
	//나의 글보기
		@GetMapping("/my/diary")// 나의 여행기(비공개글) 목록 가져오기
		public String mydiary(Model model) {
			diaryService.selectDiaryList();
			model.addAttribute("diaries",diaryService.selectDiaryList());
			System.out.println("======mydiary controller===" + diaryService.selectDiaryList());
			return "mydiary/mydiary_board";
		}
		
//		일단 글쓰기 페이지 보이기만 
//		TODO from으로 db 서버 보내기
		@GetMapping("/my/write")
		public String diarywrite() {
			return "mydiary/mydiary_write";
		}
//		@PostMapping
		/*public ResponseEntity<?> createDiary(@RequestParam String diaryTitle,
		                                     @RequestParam String diaryContent,
		                                     @RequestParam String diaryDate,
		                                     @RequestParam String diaryOpen) {
		    Diary diary = new Diary();
		    diary.setTitle(title);
		    diary.setDetail(detail);
		    diary.setTheme(theme);
		    diary.setVisibility(visibility);
		    diaryRepository.save(diary);

		    return ResponseEntity.ok().body(diary);
		}*/
}
